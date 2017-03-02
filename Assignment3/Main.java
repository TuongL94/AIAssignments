package control;

import java.util.*;

import javax.swing.SwingUtilities;

import model.DummyLocalizer;
import view.RobotLocalizationViewer;

public class Main {
	/*
	 * build your own if you like, this is just an example of how to start the viewer
	 * ...
	 */
	
	public static void main( String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the desired number of rows for the grid, then press ENTER");
		int rows = scan.nextInt();
		System.out.println("Enter the desired number of columns for the grid, then press ENTER");
		int cols = scan.nextInt();
		/*
		 * generate you own localiser / estimator wrapper here to plug it into the 
		 * graphics class.
		 */
		EstimatorInterface l = new DummyLocalizer(rows, cols, 4);

		RobotLocalizationViewer viewer = new RobotLocalizationViewer( l);

		/*
		 * this thread controls the continuous update. If it is not started, 
		 * you can only click through your localisation stepwise
		 */
		new LocalizationDriver( 500, viewer).start();
		}	
	}
