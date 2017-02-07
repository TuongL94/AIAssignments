import java.util.*;

public class Player {
	private int identity;
	
	public Player(int identity) {
		this.identity = identity;
	}
	
	public ArrayList<Point> possibleActions(State s) {
		return s.possibleActions(identity);
	}
	
	public int getId() {
		return identity;
	}
	
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
	
	public Point miniMax(Node n, int depth, Boolean maximizingPlayer, int index) {
		if(depth == 0 || n.getChildren().size() == 0) {
			Point p = new Point(n.getCost(),index);
			return p;
		}
		
		if(maximizingPlayer) {
			int bestValue = -10^6;
			int bestIndex = 0;
			for(int i = 0; i < n.getChildren().size(); i++) {
				Point v = miniMax(n.getChildren().get(i), depth - 1, false, i);
				int temp = Math.max(bestValue,v.getRow());
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
				Point v = miniMax(n.getChildren().get(i), depth - 1, true, i);
				int temp = Math.min(bestValue,v.getRow());
				if(temp < bestValue) {
					bestValue = temp;
					bestIndex = i;
				}
			}
			Point bestMove = new Point(bestValue,bestIndex);
			return bestMove;
		}
	}
	
	private Point humanResponse(State currentState){
		ArrayList<Point> possibleMoves = possibleActions(currentState);
		int row = 0;
		String col = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Possible moves: ");
        for(int i = 0; i < possibleMoves.size(); i++) {
        	System.out.println("(" + possibleMoves.get(i).getRow() + "," + Utilities.translateNbrToLetter(possibleMoves.get(i).getCol()) + ")");
        }
        System.out.println("Enter a move among the possible moves, starting with the row number and then the column letter: ");
        	row = sc.nextInt();
        	col = sc.next();
        Point p = new Point(row,Utilities.translateLetterToNbr(col));
        return p;
	}
}
