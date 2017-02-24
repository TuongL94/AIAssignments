package model;

public class Robot {
	
	private int[] pos;
	private int heading;
	
	public Robot(){
		int row = (int) Math.floor(Math.random() * 4);
		int col = (int) Math.floor(Math.random() * 4);
		heading = (int) Math.floor(Math.random() * 4);
		pos[0] = row;
		pos[1] = col;
	}
	
	public int[] getPosition(){
		return pos;
	}
	
	public void walk(){
		
	}

}
