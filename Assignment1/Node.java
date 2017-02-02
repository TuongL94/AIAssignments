import java.util.*;

public class Node {
	private State nodeState;
	private ArrayList<Node> children;
	private int cost;
	
	public Node(State s) {
		nodeState = s;
		cost = s.getNbrOfBlack() + s.getNbrOfWhite();
		children = new ArrayList<Node>();
	}
	
	public State getNodeState() {
		return nodeState;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void addChildren(Node child) {
		getChildren().add(child);
	}
	
	 
	public void createChildren(int depth, int id) {
		ArrayList<Point> possibleMoves = nodeState.possibleActions(id);
		if(depth == 0) {
			return;
		} else {
			for(int i = 0; i < possibleMoves.size(); i++) {
				int[][] currentStateMatrix = getNodeState().getStateMatrix();
				State tempState = new State(currentStateMatrix);
				tempState.updateState(possibleMoves.get(i),id);
				Node child = new Node(tempState);
				addChildren(child);
				getChildren().get(i).createChildren(depth -1,-id);
		}
	 }
   }
}
