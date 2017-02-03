
public class Point {
	private int row;
	private int col;
	
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean equals(Point p){
		if(row == p.getRow() && col == p.getCol()){
			return true;
		} else {
			return false;
		}
	}
}
