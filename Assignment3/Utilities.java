package model;

import java.util.ArrayList;

public class Utilities {

	/*
	 * Checks if this point/array corresponds to a boundary point of the 4x4 grid.
	 * Returns true if this point/array is a boundary point, otherwise false.
	 */
	public static boolean isWall(int[] coord) {
		return (coord[0] == 0 || coord[0] == 3 || coord[1] == 0 || coord[1] == 3);
	}
	
	/*
	 * Checks if this point/array corresponds to a corner point of the 4x4 grid.
	 * Returns true if this point/array is a corner point, otherwise false.
	 */
	public static boolean isCorner(int[] coord) {
		return((coord[0]==0 && coord[1]==0) || (coord[0]==0 && coord[1]==3) || (coord[0]==3 && coord[1]==0) || (coord[0]==3 && coord[1]==3));
	}
	
	/*
	 * Checks for the given position (x,y) if its heading h corresponds to a heading
	 * encountering a  wall or not. Returns true if the heading encounters a wall, 
	 * otherwise false.
	 */
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
	
	/*
	 * Returns a list of the 8-connected neighbours of this position that lie in within the 4x4 grid.
	 */
	public static ArrayList<int[]> getNeighbours(int[] position) {
		ArrayList<int[]> neighbours = new ArrayList<int[]>();
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int[] potentialNeighbour = {position[0] + i, position[1] + j};
				if(!outsideGrid(potentialNeighbour) && (potentialNeighbour[0] != position[0] ||  potentialNeighbour[1] != position[1])) {
					neighbours.add(potentialNeighbour);
				}
			}
		}
		return neighbours;
	}
	
	/*
	 *  Returns a list of the second neighbours of this position that lie in within the 4x4 grid.
	 *  The second neighbours are defined as the intersection of the two following sets:
	 *  S1 = {(x,y): x=position[0]-2 or x=position[0]+2} 
	 *  S2 = {(x,y): y=position[1]-2 or y=position[1]+2} 
	 */
	public static ArrayList<int[]> getSecondNeighbours(int[] position) {
		ArrayList<int[]> secondNeighbours = new ArrayList<int[]>();
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2 ; j++) {
				int[] potentialSecondNeighbour = {position[0] + i, position[1] + j};
				if((potentialSecondNeighbour[0] == position[0] - 2 || potentialSecondNeighbour[0] == position[0] + 2 ||
						potentialSecondNeighbour[1] == position[1] - 2 || potentialSecondNeighbour[1] == position[1] + 2)
						&& !outsideGrid(potentialSecondNeighbour) && (potentialSecondNeighbour[0] != position[0] || 
						potentialSecondNeighbour[1] != position[1]) ) {
					secondNeighbours.add(potentialSecondNeighbour);
				}
			}
		}
		return secondNeighbours;
	}
	
	/*
	 * Checks if this position is outside the 4x4 grid. 
	 * Returns true if this position is outside the grid, otherwise false.
	 */
	public static boolean outsideGrid(int[] position) {
		return position[0] < 0 || position[0] >3 || position[1] < 0 || position[1] > 3; 
	}
	
	/*
	 * Returns a list of the headings of this position that corresponds to headings not encountering
	 * a wall.
	 */
	public static ArrayList<Integer> getFeasibleHeadings(int[] pos){
		ArrayList<Integer> feasibleHeadings = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++) {
			if(!wall(pos[0],pos[1],i)){
				feasibleHeadings.add(i);
			}
		}
		return feasibleHeadings;
	}
	
	/*
	 * Returns the corresponding coordinates, in an array, of this state.
	 */
	public static int[] stateToCoord(int state) {
		int[] coord = new int[2];
		int square = state/4;
		coord[0] = square/4;
		coord[1] = square%4;
		return coord;
	}
	
	/*
	 * Returns the corresponding state of this position with the specified heading.
	 */
	public static int toState(int[] pos, int heading){
		return 16*pos[0]+4*pos[1]+heading;
	}
}
