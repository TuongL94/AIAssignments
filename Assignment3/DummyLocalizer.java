package model;

import control.EstimatorInterface;

public class DummyLocalizer implements EstimatorInterface {
		
	private int rows, cols, head;
	private double [][] transitionMatrix = new double [64][64];

	public DummyLocalizer( int rows, int cols, int head) {
		this.rows = rows;
		this.cols = cols;
		this.head = head;
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
		
		int[] ret = new int[2];
		ret[0] = rows/2;
		ret[1] = cols/2;
		return ret;

	}

	public int[] getCurrentReading() {
		int[] ret = null;
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
				int[] row_coord = getCoord(i);
				int[] col_coord = getCoord(j);
				int row_heading = i % 4;
				int col_heading = j % 4;
				if(Math.abs(row_coord[0]-col_coord[0]) > 1 || Math.abs(row_coord[1]-col_coord[1]) > 1 ||
						((row_coord[0] != col_coord[0]) &&  (row_coord[1] != col_coord[1]))){
					transitionMatrix[i][j] = 0;
				} else {
					if((row_coord[0]-col_coord[0] > 0 && col_heading == 0) || (row_coord[0]-col_coord[0] < 0 && col_heading == 2)
							|| (row_coord[1]-col_coord[1] < 0 && col_heading == 1) || (row_coord[1]-col_coord[1] > 0 && col_heading == 3)){
						if(wall(row_coord[0],row_coord[1],row_heading) && isCorner(row_coord)) {
							transitionMatrix[i][j] = 0.5;
						} else if (wall(row_coord[0],row_coord[1],row_heading)){
							transitionMatrix[i][j] = 1.0/3.0;
						} else {
							if(row_heading == col_heading){
								transitionMatrix[i][j] = 0.7;
							} else {
								if(isCorner(row_coord)){
									transitionMatrix[i][j] = 0.3;
								} else if(isWall(row_coord)) {
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
	

	private boolean isCorner(int[] coord) {
		return((coord[0]==0 && coord[1]==0) || (coord[0]==0 && coord[1]==3) || (coord[0]==3 && coord[1]==0) || (coord[0]==3 && coord[1]==3));
	}

	private int[] getCoord(int state) {
		int[] coord = new int[2];
		int square = state/4;
		coord[0] = square/4;
		coord[1] = square%4;
		return coord;
	}

	private boolean wall(int x, int y, int h) {
		boolean foundWall = false;
		switch(h) {
		case 0:
			if(x-1 < 0) {
				foundWall = true;
			}
			break;
		case 1:
			if(y+1 > transitionMatrix.length ) {
				foundWall = true;
			}
			break;
		case 2:
			if(x+1 > transitionMatrix.length) {
				foundWall = true;
			}
			break;
		case 3:
			if(y-1 < 0) {
				foundWall = true;
			}
			break;
		}
		return foundWall;

	}
	
	private boolean isWall(int[] coord) {
		return (coord[0] == 0 || coord[0] == transitionMatrix.length || coord[1] == 0 || coord[1] == transitionMatrix.length);
	}
		
}