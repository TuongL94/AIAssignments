package model;

import java.util.*;

import Jama.Matrix;
import control.EstimatorInterface;

public class DummyLocalizer implements EstimatorInterface {
		
	private int rows, cols, head;
	private Matrix transitionMatrix;
	private Matrix posterior;
	private Robot bot;
	private int[] sensorReading;

	/*
	 * Constructor
	 */
	public DummyLocalizer( int rows, int cols, int head) {
		this.rows = rows;
		this.cols = cols;
		this.head = head;
		bot = new Robot(rows, cols);
		transitionMatrix = createTransitionMatrix();
		posterior = new Matrix(rows*cols*4,1,1.0/(rows*cols*4));
		sensorReading = new int[2];
	}	
	
	public int getNumRows() {
		return rows;
	}
	
	public int getNumCols() {
		return cols;
	}
	
	public int getNumHead() {
		return head;
	}
	
	public double getTProb( int x, int y, int h, int nX, int nY, int nH) {
		int[] currentPos = {x,y};
		int[] secondPos = {nX,nY};
		int currentState = Utilities.toState(currentPos, h, cols);
		int newState = Utilities.toState(secondPos, nH, cols);
		return transitionMatrix.get(currentState, newState);
	}

	public double getOrXY( int rX, int rY, int x, int y) {
		if(rX == -1 || rY == -1){
			int[] pos = {x,y};
			ArrayList<int[]> neighbours = Utilities.getNeighbours(pos, rows, cols);
			ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(pos, rows, cols);
			return 1.0-0.1-neighbours.size()*0.05-secondNeighbours.size()*0.025;
		} else {
			int[] observedLocation = {rX,rY};
			Matrix oMatrix = getObservationMatrix(observedLocation);
			int[] pos = {x,y};
			int state = Utilities.toState(pos,0, cols);
			return oMatrix.get(state, state);
		}
	}

	public int[] getCurrentTruePosition() {
		return bot.getPosition();
	}
	
	public int[] getCurrentReading() {
		return sensorReading;
	}

	public double getCurrentProb( int x, int y) {
		double prob = 0;
		int[] pos = {x,y};
		for(int i = 0; i < 4; i++){
			int state = Utilities.toState(pos,i, cols);
			prob = prob + posterior.get(state,0);
		}
		return prob;
	}
	
	public void update() {
		bot.walk();
		newReading();
		Matrix obs = getObservationMatrix(sensorReading);
		Matrix postUnscaled = obs.times(transitionMatrix.transpose()).times(posterior);
		Matrix ones = new Matrix(rows*cols*4,1,1);
		double alpha = 1.0/(postUnscaled.transpose().times(ones)).get(0, 0);
		posterior = postUnscaled.times(alpha);
	}
	
	/*
	 * Updates the sensor reading based on the robot's location and the probabilities given
	 * in the assignment
	 */
	private void newReading(){
		int[] ret;
		int[] truePos = bot.getPosition();
		ArrayList<int[]> neighbours = Utilities.getNeighbours(bot.getPosition(), rows, cols);
		ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(bot.getPosition(), rows, cols);
		double r = Math.random();
		if(r < 0.1) {
			ret = truePos;
		} else if(r >= 0.1 && r < 0.1 + neighbours.size()*0.05) {
			Random rand = new Random();
			ret = neighbours.get(rand.nextInt(neighbours.size()));
		} else if(r >= 0.1 + neighbours.size()*0.05 && r < 0.1 + neighbours.size()*0.05 + secondNeighbours.size()*0.025) {
			Random rand = new Random();
			ret = secondNeighbours.get(rand.nextInt(secondNeighbours.size()));
		} else {
			ret = null;
		}
		sensorReading = ret;
	}
	
	/*
	 * Creates and returns the transition matrix
	 */
	private Matrix createTransitionMatrix() {
		double[][] transitionMatrix = new double[rows*cols*4][rows*cols*4];
		for(int i = 0; i < transitionMatrix.length; i++) {
			for(int j = 0; j < transitionMatrix.length; j++) {
				int[] row_coord = Utilities.stateToCoord(i,cols);
				int[] col_coord = Utilities.stateToCoord(j,cols);
				int row_heading = i % 4;
				int col_heading = j % 4;

				if(Math.abs(row_coord[0]-col_coord[0]) > 1 || Math.abs(row_coord[1]-col_coord[1]) > 1 ||
						((row_coord[0] != col_coord[0]) &&  (row_coord[1] != col_coord[1]))){
					transitionMatrix[i][j] = 0;
				} else {
					if((row_coord[0]-col_coord[0] > 0 && col_heading == 0) || (row_coord[0]-col_coord[0] < 0 && col_heading == 2)
							|| (row_coord[1]-col_coord[1] < 0 && col_heading == 1) || (row_coord[1]-col_coord[1] > 0 && col_heading == 3)){
						if(Utilities.wall(row_coord[0],row_coord[1],row_heading,rows,cols) && Utilities.isCorner(row_coord, rows, cols)) {
							transitionMatrix[i][j] = 0.5;
						} else if (Utilities.wall(row_coord[0],row_coord[1],row_heading,rows,cols)){
							transitionMatrix[i][j] = 1.0/3.0;
						} else {
							if(row_heading == col_heading){
								transitionMatrix[i][j] = 0.7;
							} else {
								if(Utilities.isCorner(row_coord, rows, cols)){
									transitionMatrix[i][j] = 0.3;
								} else if(Utilities.isWall(row_coord, rows, cols)) {
									transitionMatrix[i][j] = 0.3*0.5;
								} else{
									transitionMatrix[i][j] = 0.3/3.0;
								}
								
							}
						}
					} else {
						transitionMatrix[i][j] = 0;
					}
				}
			}
		}
		return new Matrix(transitionMatrix);
	}
	
	/*
	 * Creates and returns the observation/sensor matrix based on the current sensor reading
	 */
	private Matrix getObservationMatrix(int[] observedLocation){
		double[][] observationMatrix = new double[rows*cols*4][rows*cols*4];
		if(observedLocation == null) {
			for(int i = 0; i < transitionMatrix.getRowDimension(); i++) {
				int[] currentPos = Utilities.stateToCoord(i, cols);
				ArrayList<int[]> neighbours = Utilities.getNeighbours(currentPos, rows, cols);
				ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(currentPos, rows, cols);
				observationMatrix[i][i] = 1.0-0.1-neighbours.size()*0.05-secondNeighbours.size()*0.025;
			}
		} else {
			ArrayList<int[]> neighbours = Utilities.getNeighbours(observedLocation, rows, cols);
			for(int[]neighbour : neighbours){
				for(int i = 0; i < 4; i++){
					observationMatrix[Utilities.toState(neighbour,i, cols)][Utilities.toState(neighbour,i, cols)] = 0.05;
				}
			}
			ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(observedLocation, rows, cols);
			for(int[]secondNeighbour : secondNeighbours){
				for(int i = 0; i < 4; i++){
					observationMatrix[Utilities.toState(secondNeighbour,i, cols)][Utilities.toState(secondNeighbour,i, cols)] = 0.025;
				}
			}
			for(int i = 0; i < 4; i++){
				observationMatrix[Utilities.toState(observedLocation,i, cols)][Utilities.toState(observedLocation,i, cols)] = 0.1;
			}
		}
		return new Matrix(observationMatrix);
	}		
}