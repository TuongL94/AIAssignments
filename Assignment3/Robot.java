package model;

import java.util.ArrayList;
import java.util.Random;

public class Robot {
	
	private int[] pos;
	private int heading;
	Random randomizer;
	
	/*
	 * Constructor
	 */
	public Robot(){
		randomizer = new Random();
		int row = randomizer.nextInt(4);
		int col = randomizer.nextInt(4);
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
		ArrayList<Integer> feasibleHeadings = Utilities.getFeasibleHeadings(pos);
		if(Utilities.wall(pos[0], pos[1], heading)){
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