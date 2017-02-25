package model;

import java.util.ArrayList;

public class Utilities {

	/*
	 * Checks if this point/array corresponds to a boundary point of the (rows)x(cols) grid.
	 * Returns true if this point/array is a boundary point, otherwise false.
	 */
	public static boolean isWall(int[] coord, int rows, int cols) {
		return (coord[0] == 0 || coord[0] == rows - 1 || coord[1] == 0 || coord[1] == cols - 1);
	}
	
	/*
	 * Checks if this point/array corresponds to a corner point of the (rows)x(cols) grid.
	 * Returns true if this point/array is a corner point, otherwise false.
	 */
	public static boolean isCorner(int[] coord, int rows, int cols) {
		return((coord[0]==0 && coord[1]==0) || (coord[0]==0 && coord[1]==cols - 1) || (coord[0]==rows-1 && coord[1]==0) || (coord[0]==rows-1 && coord[1]==cols-1));
	}
	
	/*
	 * Checks for the given position (x,y) if its heading h corresponds to a heading
	 * encountering a  wall or not in the (rows)x(cols) grid. Returns true if the heading encounters a wall, 
	 * otherwise false.
	 */
	public static boolean wall(int x, int y, int h, int rows, int cols) {
		boolean foundWall = false;
		switch(h) {
		case 0:
			if(x-1 < 0) {
				foundWall = true;
			}
			break;
		case 1:
			if(y+1 > cols - 1 ) {
				foundWall = true;
			}
			break;
		case 2:
			if(x+1 > rows - 1) {
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
	 * Returns a list of the 8-connected neighbours of this position that lie in within the (rows)x(cols) grid.
	 */
	public static ArrayList<int[]> getNeighbours(int[] position, int rows, int cols) {
		ArrayList<int[]> neighbours = new ArrayList<int[]>();
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int[] potentialNeighbour = {position[0] + i, position[1] + j};
				if(!outsideGrid(potentialNeighbour, rows, cols) && (potentialNeighbour[0] != position[0] ||  potentialNeighbour[1] != position[1])) {
					neighbours.add(potentialNeighbour);
				}
			}
		}
		return neighbours;
	}
	
	/*
	 *  Returns a list of the second neighbours of this position that lie in within the(rows)x(cols) grid.
	 *  The second neighbours are defined as the intersection of the two following sets:
	 *  S1 = {(x,y): x=position[0]-2 or x=position[0]+2} 
	 *  S2 = {(x,y): y=position[1]-2 or y=position[1]+2} 
	 */
	public static ArrayList<int[]> getSecondNeighbours(int[] position, int rows, int cols) {
		ArrayList<int[]> secondNeighbours = new ArrayList<int[]>();
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2 ; j++) {
				int[] potentialSecondNeighbour = {position[0] + i, position[1] + j};
				if((potentialSecondNeighbour[0] == position[0] - 2 || potentialSecondNeighbour[0] == position[0] + 2 ||
						potentialSecondNeighbour[1] == position[1] - 2 || potentialSecondNeighbour[1] == position[1] + 2)
						&& !outsideGrid(potentialSecondNeighbour, rows, cols)) {
					secondNeighbours.add(potentialSecondNeighbour);
				}
			}
		}
		return secondNeighbours;
	}
	
	/*
	 * Checks if this position is outside the (rows)x(cols) grid. 
	 * Returns true if this position is outside the grid, otherwise false.
	 */
	public static boolean outsideGrid(int[] position, int rows, int cols) {
		return position[0] < 0 || position[0] > rows - 1 || position[1] < 0 || position[1] > cols - 1; 
	}
	
	/*
	 * Returns a list of the headings of this position that corresponds to headings not encountering
	 * a wall.
	 */
	public static ArrayList<Integer> getFeasibleHeadings(int[] pos, int rows, int cols){
		ArrayList<Integer> feasibleHeadings = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++) {
			if(!wall(pos[0],pos[1],i, rows, cols)){
				feasibleHeadings.add(i);
			}
		}
		return feasibleHeadings;
	}
	
	/*
	 * Returns the corresponding coordinates, in an array, of this state.
	 */
	public static int[] stateToCoord(int state, int cols) {
		int[] coord = new int[2];
		int square = state/4;
		coord[0] = square/cols;
		coord[1] = square%cols;
		return coord;
	}
	
	/*
	 * Returns the corresponding state of this position with the specified heading.
	 */
	public static int toState(int[] pos, int heading, int cols){
		return 4*(pos[1] + cols*pos[0])+heading;
	}
}
