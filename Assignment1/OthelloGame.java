import java.util.*;

public class OthelloGame {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		Player human = new Player(1);
		Player robot = new Player(-1);
		Game othello = new Game(human, robot);
		int depth = 10;
		int[][] start = othello.getGameState().getStateMatrix();
		long timeLimit = timeLimit(sc).longValue();
		plotCurrentState(start);
		System.out.print("\n");
		Node rootNode = new Node(othello.getGameState());
		createChildren(depth,othello.getCurrentPlayer().getId(), rootNode);
		while(!othello.isFinished()) {
			Player p = othello.getCurrentPlayer();
			Point action = p.chooseAction(rootNode, depth);
			for(Node n : rootNode.getChildren()){
				if(n.getNodeState().getLastPlacedRow() == action.getRow() && n.getNodeState().getLastPlacedCol() == action.getCol()){
					rootNode = n;
				}
			}
			createChildren(depth, p.getId(), rootNode);
			if(p.getId() == robot.getId()){
				System.out.println(action.getRow()+", "+action.getCol());
				System.out.print("\n");
			}
			plotCurrentState(rootNode.getNodeState().getStateMatrix());
			othello.updateGame();			
		}
	}
	
	
	private static void createChildren(int depth, int id, Node parent) {
		ArrayList<Point> possibleMoves = parent.getNodeState().possibleActions(id);
		if(depth == 0) {
			return;
		} else {
			if(parent.getChildren().size() == 0){
				for(Point move : possibleMoves) {
					State childState = new State(parent.getNodeState().getUpdatedStateMatrix(move, id), move.getRow(), move.getCol());
					Node childNode = new Node(childState);
					parent.addChildren(childNode);
				}
			
				for(Node n : parent.getChildren()) {
					createChildren(depth - 1, -id, n);
				}
			}
		}
	}
	
	private static Integer timeLimit(Scanner sc){
		Integer timeLimit = 10^6;
        System.out.println("Please enter the number of seconds the computer is allowed to think: ");
        timeLimit = sc.nextInt();
        return timeLimit;
	}
	
	private static void plotCurrentState(int[][] currentMatrix){
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
	}
}
