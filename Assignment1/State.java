import java.util.*;

public class State {
	private int[][] stateMatrix;
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
		//stateMatrix[4][2] = -1;
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
	
	public int[][] getMatrix(){
		return stateMatrix;
	}
	
	/**
	 * Updates the current state
	 */
	public void updateState(Point a, int playerId) {
			int coord_x = a.getRow();
			int coord_y = a.getCol();
			stateMatrix[coord_x][coord_y] = playerId;
			int opponent = -playerId;
			for(int dir_x = -1; dir_x <= 1; dir_x++){
				for(int dir_y = -1; dir_y <= 1; dir_y++){
					int z = 1;
					boolean b = true;
					ArrayList<Point> wins = new ArrayList<Point>();
					while((0 <= coord_x+z*dir_x) && (coord_x+z*dir_x <=7) && (0 <= coord_y+z*dir_y) && (coord_y+z*dir_y <=7) && b){
						if(stateMatrix[coord_x+z*dir_x][coord_y+z*dir_y] == opponent){
							Point p = new Point(coord_x+z*dir_x, coord_y+z*dir_y);
							wins.add(p);
							z++;
						} else if((stateMatrix[coord_x+z*dir_x][coord_y+z*dir_y] == playerId) || (stateMatrix[coord_x+z*dir_x][coord_y+z*dir_y] == 0)){
							b = false;
						}
					}
					for(Point p : wins){
						stateMatrix[p.getRow()][p.getCol()] = playerId;
					}
					
				}
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
