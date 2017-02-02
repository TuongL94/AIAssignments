import java.util.*;

public class State {
	private int [][] stateMatrix;
	private int sumOfBlack;
	private int sumOfWhite;
	private int lastPlacedRow;
	private int lastPlacedCol;
	
	/**
	 * Constructor
	 */
	public State() {
		stateMatrix = new int[8][8];
		stateMatrix[4][4] = -1;
		stateMatrix[5][5] = -1;
		stateMatrix[4][5] = 1;
		stateMatrix[5][4] = 1;
		sumOfBlack = 2;
		sumOfWhite = -2;
	}
	
	/**
	 * Checks if the given position is empty
	 * @param row, row index
	 * @param col, column index
	 * @return true if the position is empty, otherwise false
	 */
	public boolean isEmpty(int row, int col) {
		return stateMatrix[row][col] == 0;
	}
	
	/**
	 * Updates the current state
	 */
	public void updateState(Point a, int playerId) {
		if(playerId == 1) {
			stateMatrix[a.getRow()][a.getCol()] = 1;
		} else {
			stateMatrix[a.getRow()][a.getCol()] = -1;
		}
	}
	
	/**
	 * Checks if the board is full
	 * @return true if the board is full, otherwise false
	 */
	public boolean isFull() {
		boolean checkFull = true;
		for(int i = 0; i<= 7; i++) {
			for(int j = 0; j<= 7; j++) {
				if(stateMatrix[i][j] == 0) {
					checkFull = false;
				}
			}
		}
		return checkFull;
	}
	
	/**
	 * Checks what actions are possible for player identity
	 * @return a list of possible actions
	 */
	public ArrayList<Point> possibleActions(int identity) {
		ArrayList<Point> actionList = new ArrayList<Point>();
		int opponent = -identity;
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++){
				if(stateMatrix[i][j] == opponent){
					Point target = new Point(i,j);
					ArrayList<Point> neighbours = getEmptyNeighbours(target);
					for(Point p : neighbours){
						if(feasible(target,p,identity)){
							Point a = new Point(p.getCol(),p.getRow());
							actionList.add(a);
						}
					}
				}
			}
		}
		return actionList;
	}
	
	private boolean feasible(Point t, Point p, int identity) {
		boolean b = false;
		int dx = p.getRow()-t.getRow();
		int dy = p.getCol()-t.getCol();
		if(dx == 0){
			int i = t.getCol()-dy;
			while((i>=0) && (i<=7)) {
				if(stateMatrix[t.getRow()][i] == identity){
					b = true;
				}
				i = i - dy;
			}
		} else if(dy == 0) {
			int i = t.getRow()-dx;
			while((i>=0) && (i<=7)) {
				if(stateMatrix[i][t.getCol()] == identity){
					b = true;
				}
				i = i - dx;
			}
		} else {
			int i = t.getRow()-dx;
			int j = t.getCol()-dy;
			while((i>=0) && (i<=7) && (j>=0) && (j<=7)) {
				if(stateMatrix[i][j] == identity){
					b = true;
				}
				i = i - dx;
				j = j - dy;
			}
			
		}
		return b;
	}
	
	private ArrayList<Point> getEmptyNeighbours(Point t) {
		ArrayList<Point> neighbours = new ArrayList<Point>();
		int x = t.getRow();
		int y = t.getCol();
		for(int i = x-1; i <= x+1; i++){
			for(int j = y-1; j <= y+1; j++){
				if((i >= 0) && (i <= 7) && (j >= 0) && (j <= 7) && ((i != x) || (j != y)) && (stateMatrix[i][j] == 0)){
					Point p = new Point(i,j);
					neighbours.add(p);
				}
			}
		}
		return neighbours;
	}


	public int getNbrOfBlack() {
		return sumOfBlack;
	}
	
	public int getNbrOfWhite() {
		return sumOfWhite;
	}
	
	public int getLastPlacedRow() {
		return lastPlacedRow;
	}
	
	public int getLastPlacedCol() {
		return lastPlacedCol;
	}
}
