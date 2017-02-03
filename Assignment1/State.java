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
		stateMatrix[3][3] = -1;
		stateMatrix[4][4] = -1;
		stateMatrix[3][4] = 1;
		stateMatrix[4][3] = 1;
		lastPlacedRow = 4;
		lastPlacedCol = 4;
		sumOfBlack = 2;
		sumOfWhite = -2;
	}
	
	public State(int[][] stateMatrix, int lastPlacedRow, int lastPlacedCol) {
		this.stateMatrix = stateMatrix;
		this.lastPlacedRow = lastPlacedRow;
		this.lastPlacedCol = lastPlacedCol;
		updateSum();
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
		int row = a.getRow();
		int col = a.getCol();
		stateMatrix[row][col] = playerId;
		int opponent = -playerId;
		for(int dir_row = -1; dir_row <= 1; dir_row++){
			for(int dir_col = -1; dir_col <= 1; dir_col++){
				int step = 1;
				boolean foundOwn = false;
				ArrayList<Point> myWins = new ArrayList<Point>();
				while((0 <= row+step*dir_row) && (row+step*dir_row <=7) && (0 <= col+step*dir_col) && (col+step*dir_col <=7) && !foundOwn && !(dir_row == 0 && dir_col == 0)){
					if(stateMatrix[row+step*dir_row][col+step*dir_col] == opponent){
						Point p = new Point(row+step*dir_row, col+step*dir_col);
						myWins.add(p);
						step++;
					} else if(stateMatrix[row+step*dir_row][col+step*dir_col] == playerId){
						foundOwn = true;
					} else if(stateMatrix[row+step*dir_row][col+step*dir_col] == 0){
						myWins.clear();
						break;
					}
				}
				for(Point p : myWins){
					stateMatrix[p.getRow()][p.getCol()] = playerId;
				}
			}
		}
		updateSum();
		lastPlacedRow = a.getRow();
		lastPlacedCol = a.getCol();
}
	
	public int[][] getUpdatedStateMatrix(Point a, int playerId) {
		int[][] updatedStateMatrix = copyStateMatrix();
		int row = a.getRow();
		int col = a.getCol();
		updatedStateMatrix[row][col] = playerId;
		int opponent = -playerId;
		for(int dir_row = -1; dir_row <= 1; dir_row++){
			for(int dir_col = -1; dir_col <= 1; dir_col++){
				int step = 1;
				boolean foundOwn = false;
				ArrayList<Point> myWins = new ArrayList<Point>();
				while((0 <= row+step*dir_row) && (row+step*dir_row <=7) && (0 <= col+step*dir_col) && (col+step*dir_col <=7) && !foundOwn && !(dir_row == 0 && dir_col == 0)){
					if(updatedStateMatrix[row+step*dir_row][col+step*dir_col] == opponent){
						Point p = new Point(row+step*dir_row, col+step*dir_col);
						myWins.add(p);
						step++;
					} else if(updatedStateMatrix[row+step*dir_row][col+step*dir_col] == playerId){
						foundOwn = true;
					} else if(updatedStateMatrix[row+step*dir_row][col+step*dir_col] == 0){
						myWins.clear();
						break;
					}
				}
				for(Point p : myWins){
					updatedStateMatrix[p.getRow()][p.getCol()] = playerId;
				}
			}
		}
		return updatedStateMatrix;
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
							Point a = new Point(p.getRow(),p.getCol());
							if(!actionList.contains(p)){
								actionList.add(a);
							}
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
	
	public int[][] getStateMatrix() {
		return stateMatrix;
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
	
	private void updateSum(){
		int black = 0;
		int white = 0;
		for(int i = 0; i <= 7; i++){
			for(int j = 0; j <= 7; j++){
				if(stateMatrix[i][j] == 1){
					black++;
				} else if(stateMatrix[i][j] == -1){
					white++;
				}
			}
		}
		sumOfBlack = black;
		sumOfWhite = -white;
	}
	
	private int[][] copyStateMatrix() {
		int[][] copyMatrix = new int[8][8];
		for(int i = 0; i < stateMatrix.length; i++) {
			for(int j = 0; j < stateMatrix[0].length; j++)
			copyMatrix[i][j] = stateMatrix[i][j];
		}
		return copyMatrix;
	}
	
}
