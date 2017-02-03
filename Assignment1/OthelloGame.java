import java.util.*;
public class OthelloGame {

	public static void main(String [] args) {
		Player human = new Player(1);
		Player robot = new Player(-1);
		Game othello = new Game(human, robot);
		int depth = 2;
		int[][] start = othello.getGameState().getStateMatrix();
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <= 7; j++){
				if(start[i][j] == -1) {
					System.out.print("w"+"  ");
				} else if(start[i][j] == 1){
					System.out.print("b"+"  ");
				} else {
					System.out.print(start[i][j]+"  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		while(!othello.isFinished()) {
			Node rootNode = new Node(othello.getGameState());
			Player p = othello.getCurrentPlayer();
			createChildren(depth,p.getId(), rootNode);
			Point action = p.chooseAction(rootNode, depth);
			if(p.getId() == robot.getId()){
				System.out.println(action.getRow()+", "+action.getCol());
			}
			othello.updateGame(action);
			int[][] currentMatrix = othello.getGameState().getStateMatrix();
			for(int i = 0; i <=7; i++){
				for(int j = 0; j <= 7; j++){
					if(currentMatrix[i][j] == -1) {
						System.out.print("w"+"  ");
					} else if(currentMatrix[i][j] == 1){
						System.out.print("b"+"  ");
					} else {
						System.out.print(currentMatrix[i][j]+"  ");
					}
				}
				System.out.print("\n");
			}
			depth--;
		}
		
	}
	
	
	private static void createChildren(int depth, int id, Node parent) {
		ArrayList<Point> possibleMoves = parent.getNodeState().possibleActions(id);
		if(depth == 0) {
			return;
		} else {
			for(Point move : possibleMoves) {
				State childState = new State(parent.getNodeState().getUpdatedStateMatrix(move, id));
				Node childNode = new Node(childState);
				parent.addChildren(childNode);
		}
			for(Node n : parent.getChildren()) {
				createChildren(depth - 1, -id, n);
			}
	 }
   }
}
