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
			return humanResponse();
		} else {
			Point p = miniMax(n, depth, false, 0);
			State optimalState =  n.getChildren().get(p.getCol()).getNodeState();
			Point optimalMove = new Point(optimalState.getLastPlacedRow(), optimalState.getLastPlacedCol());
			return optimalMove;
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
			for(int i = 0; i <= n.getChildren().size(); i++) {
				Point v = miniMax(n.getChildren().get(i), depth - 1, false, i);
				int temp = Math.max(bestValue,v.getRow());
				if(temp > bestValue) {
					bestValue = temp;
					bestIndex = v.getCol();
				}
			}
			Point bestMove = new Point(bestValue,bestIndex);
			return bestMove;
		}
		else {
			int bestValue = 10^6;
			int bestIndex = 0;
			for(int i = 0; i <= n.getChildren().size(); i++) {
				Point v = miniMax(n.getChildren().get(i), depth - 1, true, i);
				int temp = Math.min(bestValue,v.getRow());
				if(temp < bestValue) {
					bestValue = temp;
					bestIndex = v.getCol();
				}
			}
			Point bestMove = new Point(bestValue,bestIndex);
			return bestMove;
		}
	}
	
	private Point humanResponse(){
		int row = 0;
		int col = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Your move: ");
        while(sc.hasNextInt()){
        	row = sc.nextInt();
        	col = sc.nextInt();
        }
        Point p = new Point(row,col);
        return p;
	}
}
