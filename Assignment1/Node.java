import java.util.*;

public class Node {
	private State nodeState;
	private ArrayList<Node> children;
	private int cost; // Difference of black and white pieces 
	
	/**
	 * Constructor
	 * @param s - a State object
	 */
	public Node(State s) {
		nodeState = s;
		cost = s.getNbrOfBlack() + s.getNbrOfWhite();
		children = new ArrayList<Node>();
	}
	
	/**
	 * 
	 * @return The state object associated with this node
	 */
	public State getNodeState() {
		return nodeState;
	}
	
	/**
	 * 
	 * @return An ArrayList containing the children of this node
	 */
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	/**
	 * 
	 * @return The cost at this node which is the difference between the amount of black and white pieces
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Adds a children to this node
	 * @param child - the child node to add
	 */
	public void addChildren(Node child) {
		getChildren().add(child);
	}
}
