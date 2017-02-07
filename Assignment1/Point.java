
public class Point extends Object{
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
	@Override
	public boolean equals(Object o){
		if(this.row == ((Point) o).getRow() && this.col == ((Point) o).getCol()){
			return true;
		} else {
			return false;
		}
	}
}
