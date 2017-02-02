import java.util.*;

public class Node {
	private State nodeState;
	private ArrayList<Node> children;
	private int cost;
	
	public Node(State s, ArrayList<Node> children) {
		nodeState = s;
		cost = s.getNbrOfBlack() + s.getNbrOfWhite();
		this.children = children;
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
}
