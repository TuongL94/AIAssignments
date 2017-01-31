
public class State {
	private int [][] stateMatrix = new int[8][8];
	private int sumOfBlack;
	private int sumOfWhite;
	
	/**
	 * Constructor
	 */
	public State() {
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
	public void updateState() {
		
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
	
	public int getNbrOfBlack() {
		return sumOfBlack;
	}
	
	public int getNbrOfWhite() {
		return sumOfWhite;
	}
	
}
