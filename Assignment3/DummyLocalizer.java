package model;

import java.util.*;
import control.EstimatorInterface;

public class DummyLocalizer implements EstimatorInterface {
		
	private int rows, cols, head;
	private double [][] transitionMatrix = new double [64][64];
	private double [][] observationMatrix = new double[64][64];
	private Robot bot;

	public DummyLocalizer( int rows, int cols, int head) {
		this.rows = rows;
		this.cols = cols;
		this.head = head;
		bot = new Robot();
		createTransitionMatrix();
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
		int currentState = 4*(y+4*x)+h;
		int newState = 4*(nY+4*nX)+nH;
		return transitionMatrix[currentState][newState];
	}

	public double getOrXY( int rX, int rY, int x, int y) {
		return 0.1;
	}


	public int[] getCurrentTruePosition() {
		return bot.getPosition();
	}

	public int[] getCurrentReading() {
		int[] ret;
		int[] truePos = bot.getPosition();
		ArrayList<int[]> neighbours = Utilities.getNeighbours(bot.getPosition());
		ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(bot.getPosition());
		double r = Math.random();
		if(r < 0.1) {
			ret = truePos;
		} else if(r >= 0.1 && r < 0.1 + neighbours.size()*0.05) {
			Random rand = new Random();
			ret = neighbours.get(rand.nextInt(neighbours.size()));
		} else if(r >= 0.1 + neighbours.size()*0.05 && r < 0.1 + neighbours.size()*0.05 + secondNeighbours.size()*0.025) {
			Random rand = new Random();
			ret = neighbours.get(rand.nextInt(secondNeighbours.size()));
		} else {
			ret = null;
		}
		return ret;
	}


	public double getCurrentProb( int x, int y) {
		double ret = 0.0;
		return ret;
	}
	
	public void update() {
		System.out.println("Nothing is happening, no model to go for...");
	}
	
	private void createTransitionMatrix() {
		for(int i = 0; i < transitionMatrix.length; i++) {
			for(int j = 0; j < transitionMatrix.length; j++) {
				int[] row_coord = Utilities.stateToCoord(i);
				int[] col_coord = Utilities.stateToCoord(j);
				int row_heading = i % 4;
				int col_heading = j % 4;
				if(Math.abs(row_coord[0]-col_coord[0]) > 1 || Math.abs(row_coord[1]-col_coord[1]) > 1 ||
						((row_coord[0] != col_coord[0]) &&  (row_coord[1] != col_coord[1]))){
					transitionMatrix[i][j] = 0;
				} else {
					if((row_coord[0]-col_coord[0] > 0 && col_heading == 0) || (row_coord[0]-col_coord[0] < 0 && col_heading == 2)
							|| (row_coord[1]-col_coord[1] < 0 && col_heading == 1) || (row_coord[1]-col_coord[1] > 0 && col_heading == 3)){
						if(Utilities.wall(row_coord[0],row_coord[1],row_heading) && Utilities.isCorner(row_coord)) {
							transitionMatrix[i][j] = 0.5;
						} else if (Utilities.wall(row_coord[0],row_coord[1],row_heading)){
							transitionMatrix[i][j] = 1.0/3.0;
						} else {
							if(row_heading == col_heading){
								transitionMatrix[i][j] = 0.7;
							} else {
								if(Utilities.isCorner(row_coord)){
									transitionMatrix[i][j] = 0.3;
								} else if(Utilities.isWall(row_coord)) {
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
	}
	
	public double[][] getObservationMatrix(int[] observedLocation){
		double[][] observationMatrix = new double[64][64];
		ArrayList<int[]> neighbours = Utilities.getNeighbours(observedLocation);
		for(int[]neighbour : neighbours){
			for(int i = 0; i < 4; i++){
				observationMatrix[Utilities.toState(neighbour,i)][Utilities.toState(neighbour,i)] = 0.05;
			}
		}
		ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(observedLocation);
		for(int[]secondNeighbour : secondNeighbours){
			for(int i = 0; i < 4; i++){
				observationMatrix[Utilities.toState(secondNeighbour,i)][Utilities.toState(secondNeighbour,i)] = 0.025;
			}
		}
		for(int i = 0; i < 4; i++){
			observationMatrix[Utilities.toState(observedLocation,i)][Utilities.toState(observedLocation,i)] = 0.1;
		}
		return observationMatrix;
	}
	
//	private void createSensorMatrix() {
//		int[] currentReading = getCurrentReading();
//		for(int i = 0; i < transitionMatrix.length; i++) {
//			int[] stateCoord = Utilities.stateToCoord(i);
//			ArrayList<int[]> neighbours = Utilities.getNeighbours(stateCoord);
//			ArrayList<int[]> secondNeighbours = Utilities.getSecondNeighbours(stateCoord);
//			if(currentReading != null) {
//				if(stateCoord[0] == currentReading[0] && stateCoord[1] == currentReading[1]) {
//					observationMatrix[i][i] = 0.1;
//				} else {
//					for(int[] n : neighbours) {
//						if(currentReading[0] == n[0] && currentReading[1] == n[1]) {
//							observationMatrix[i][i] = 0.05;
//							break;
//						}
//					}
//					if(observationMatrix[i][i] == 0) {
//						for(int[] n : secondNeighbours) {
//							if(currentReading[0] == n[0] && currentReading[1] == n[1]) {
//								observationMatrix[i][i] = 0.05;
//								break;
//							}
//						}
//					} else {
//						continue;
//					}
//				}
//			} else {
//				
//			}
//		}
//	}	
}