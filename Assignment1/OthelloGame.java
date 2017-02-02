import java.util.*;
public class OthelloGame {

	public static void main(String [] args) {
		Player human = new Player(1);
		Player robot = new Player(-1);
		State currentState = new State();
		Game othello = new Game(human, robot);
		int depth = 1;
		
		while(!othello.isFinished()) {
			Node rootNode = new Node(currentState);
			Player p = othello.getCurrentPlayer();
			rootNode.createChildren(depth,p.getId());
			Point action = p.chooseAction(rootNode, depth);
			if(p.getId() == robot.getId()){
				System.out.println(action.getRow()+", "+action.getCol());
			}
			othello.updateGame(action);
		}
	}
}
