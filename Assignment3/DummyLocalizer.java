package model;

import control.EstimatorInterface;

public class DummyLocalizer implements EstimatorInterface {
		
	private int rows, cols, head;
	private int [][] transitionMatrix = new int [64][64];

	public DummyLocalizer( int rows, int cols, int head) {
		this.rows = rows;
		this.cols = cols;
		this.head = head;
		
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
		return 0.0;
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
				
			}
		}
	}
	
	private boolean wall(int x, int y, int h) {
		boolean foundWall = false;
		switch(h) {
		case 0:
			if(x+1 > transitionMatrix.length) {
				foundWall = true;
			}
			break;
		case 1:
			if(y-1 < 0) {
				foundWall = true;
			}
			break;
		case 2:
			if(x-1 < 0) {
				foundWall = true;
			}
			break;
		case 3:
			if(y+1 > transitionMatrix.length) {
				foundWall = true;
			}
			break;
		}
		return foundWall;

	}
	
	
}
