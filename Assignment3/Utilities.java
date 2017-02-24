package model;

public class Utilities {

	public static boolean isWall(int[] coord) {
		return (coord[0] == 0 || coord[0] == 3 || coord[1] == 0 || coord[1] == 3);
	}
	
	public static boolean isCorner(int[] coord) {
		return((coord[0]==0 && coord[1]==0) || (coord[0]==0 && coord[1]==3) || (coord[0]==3 && coord[1]==0) || (coord[0]==3 && coord[1]==3));
	}
	
	public static boolean wall(int x, int y, int h) {
		boolean foundWall = false;
		switch(h) {
		case 0:
			if(x-1 < 0) {
				foundWall = true;
			}
			break;
		case 1:
			if(y+1 > 3 ) {
				foundWall = true;
			}
			break;
		case 2:
			if(x+1 > 3) {
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
	
	public static ArrayList<Integer> getFeasibleHeadings(int[] pos){
		ArrayList<Integer> feasibleHeadings = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++) {
			if(!wall(pos[0],pos[1],i)){
				feasibleHeadings.add(i);
			}
		}
		return feasibleHeadings;
	}

}
