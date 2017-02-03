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
}
