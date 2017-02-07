
public class Point {
	private int row;
	private int col;
	
	/**
	 * Constructor
	 * @param row - First coordinate of the point
	 * @param col - Second coordinate of the point
	 */
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * 
	 * @return the first coordinate of this point
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @return the second coordinate of this point
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Compares two points for equality, where equality between point is defined as two points having the same coordinates
	 * @param p - the point to be compared with this point
	 * @return true if the points are equal, otherwise false
	 */
	@Override
	public boolean equals(Object o){
		if(this.row == ((Point) o).getRow() && this.col == ((Point) o).getCol()){
			return true;
		} else {
			return false;
		}
	}
}
