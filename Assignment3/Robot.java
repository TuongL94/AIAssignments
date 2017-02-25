package model;

import java.util.ArrayList;
import java.util.Random;

public class Robot {
	
	private int[] pos;
	private int heading;
	private int rows;
	private int cols;
	Random randomizer;
	
	/*
	 * Constructor
	 */
	public Robot(int rows, int cols){
		randomizer = new Random();
		this.rows = rows;
		this.cols = cols;
		int row = randomizer.nextInt(rows);
		int col = randomizer.nextInt(cols);
		heading = randomizer.nextInt(4);
		pos = new int[2];
		pos[0] = row;
		pos[1] = col;
	}
	
	/*
	 * Returns the current position of the robot
	 */
	public int[] getPosition(){
		return pos;
	}
	
	/*
	 * The robot moves in a heading determined by the move strategy of the robot
	 */
	public void walk(){
		ArrayList<Integer> feasibleHeadings = Utilities.getFeasibleHeadings(pos, rows, cols);
		if(Utilities.wall(pos[0], pos[1], heading, rows, cols)){
				heading = feasibleHeadings.get(randomizer.nextInt(feasibleHeadings.size()));
		} else {
			if(randomizer.nextDouble() > 0.7){
				int newHeading = heading;
				while(newHeading == heading){
					newHeading = feasibleHeadings.get(randomizer.nextInt(feasibleHeadings.size()));
				}
				heading = newHeading;
			}
		}
		step();
	}

	private void step() {
		if(heading == 0){
			pos[0] = pos[0] - 1;
		} else if(heading == 1){
			pos[1] = pos[1] + 1;
		} else if(heading == 2){
			pos[0] = pos[0] + 1;
		} else if(heading == 3){
			pos[1] = pos[1] - 1;
		}
	}
}