import java.util.*;

public class Player {
	private int identity;
	
	/**
	 * Constructor
	 * @param identity - player identity
	 */
	public Player(int identity) {
		this.identity = identity;
	}
	
	/**
	 * Returns an ArrayList of Point objects representing all possible actions
	 * @param s - State object representing the current State
	 * @return an ArrayList of possible moves
	 */
	public ArrayList<Point> possibleActions(State s) {
		return s.possibleActions(identity);
	}
	
	/**
	 * 
	 * @return the identity of this player
	 */
	public int getId() {
		return identity;
	}
	
	/**
	 * Returns a legal point for this player given a current state.
	 * @param n - the node representing the current state 
	 * @param depth - the depth used for the minimax algorithm
	 * @return a legal point 
	 */
	public Point chooseAction(Node n, int depth) {
		if(identity == 1) {
			return humanResponse(n.getNodeState());
		} else {
			Point p = miniMax(n, depth, -10^6, 10^6, false, 0);
			State optimalState =  n.getChildren().get(p.getCol()).getNodeState();
			Point optimalMove = new Point(optimalState.getLastPlacedRow(), optimalState.getLastPlacedCol());
			return optimalMove;
		}
	}

	/**
	 * Minimax algorithm with alpha-beta pruning used to determine an optimal legal move for the bot
	 * given a specific depth
	 * @param n - a node representing the current state
	 * @param depth - depth for the algorithm
	 * @param alpha - alpha parameter in alpha-beta pruning
	 * @param beta - beta parameter in alpha-beta pruning
	 * @param maximizingPlayer - boolean variable to indicate the current player at a level of the tree rooted at n
	 * @param index - index indicating the position of the root node (set to 0 by default)
	 * @return a Point object containing the difference between black and white pieces as first coordinate
	 * and the position of the child node of n corresponding to the optimal move
	 */
	public Point miniMax(Node n, int depth, double alpha, double beta, Boolean maximizingPlayer, int index) {
		if(depth == 0 || n.getChildren().size() == 0) {
			Point p = new Point(n.getCost(),index);
			return p;
		}
		
		if(maximizingPlayer) {
			int bestValue = -10^6;
			int bestIndex = 0;
			for(int i = 0; i < n.getChildren().size(); i++) {
				Point v = miniMax(n.getChildren().get(i), depth - 1, alpha, beta, false, i);
				int temp = Math.max(bestValue,v.getRow());
				alpha = Math.max(alpha, temp);
				if (beta <= alpha) {
					break;
				}
				if(temp > bestValue) {
					bestValue = temp;
					bestIndex = i;
				}
			}
			Point bestMove = new Point(bestValue,bestIndex);
			return bestMove;
		}
		else {
			int bestValue = 10^6;
			int bestIndex = 0;
			for(int i = 0; i < n.getChildren().size(); i++) {
				Point v = miniMax(n.getChildren().get(i), depth - 1, alpha, beta, true, i);
				int temp = Math.min(bestValue,v.getRow());
				beta = Math.min(beta, temp);
				if (beta <= alpha) {
					break;
				}
				if(temp < bestValue) {
					bestValue = temp;
					bestIndex = i;
				}
			}
			Point bestMove = new Point(bestValue,bestIndex);
			return bestMove;
		}
	}
	
	
	/**
	 * Prints all legal moves for the current state and lets the player (human) choose a legal point.
	 * The chosen point is then returned
	 * @param currentState - State object representing the current state
	 * @return a legal point (Point object) chosen by the player
	 */
	private Point humanResponse(State currentState){
		ArrayList<Point> possibleMoves = possibleActions(currentState);
		int row = 0;
		String col = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Possible moves: ");
        for(int i = 0; i < possibleMoves.size(); i++) {
        	System.out.println("(" + String.valueOf(possibleMoves.get(i).getRow() + 1) + "," + Utilities.nbrToLetter(possibleMoves.get(i).getCol()) + ")");
        }
        System.out.println("Enter a move among the possible moves, starting with the row number and then the column letter: ");
        	row = sc.nextInt();
        	col = sc.next();
        Point p = new Point(row - 1,Utilities.letterToNbr(col));
        return p;
	}
}
