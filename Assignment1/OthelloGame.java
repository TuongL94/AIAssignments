import java.util.*;

public class OthelloGame {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		Player human = new Player(1);
		Player robot = new Player(-1);
		Game othello = new Game(human, robot);
		int depth = 3;
		int[][] start = othello.getGameState().getStateMatrix();
		double timeLimit = timeLimit(sc);
		if(timeLimit < 0.5){
			depth = 3;
		} else if(timeLimit < 1){
			depth = 5;
		} else {
			depth = 6;
		}
		System.out.println("\n");
		plotCurrentState(start);
		System.out.print("\n");
		Node rootNode = new Node(othello.getGameState());
		createChildren(depth,othello.getCurrentPlayer().getId(), rootNode);
		while(!othello.isFinished()) {
			Player p = othello.getCurrentPlayer();
			System.out.print("\n");
			Point action = p.chooseAction(rootNode, depth);
			for(Node n : rootNode.getChildren()){
				if(n.getNodeState().getLastPlacedRow() == action.getRow() && n.getNodeState().getLastPlacedCol() == action.getCol()){
					rootNode = n;
				}
			}
			createChildren(depth, -p.getId(), rootNode);
			if(p.getId() == robot.getId()){
				System.out.println("The bot made the move: " + "(" + String.valueOf(action.getRow( ) + 1) + "," + Utilities.nbrToLetter(action.getCol()) + ")");
				System.out.print("\n");
			}
			othello.updateGame(rootNode.getNodeState());	
			plotCurrentState(othello.getGameState().getStateMatrix());
		}
		
		// The final score is printed and the winner is decided
		int yourScore = othello.getGameState().getNbrOfBlack();
		int botScore = othello.getGameState().getNbrOfWhite();
		System.out.println("Your score: " + yourScore);
		System.out.println("Bot's score " + -botScore);
		if(yourScore + botScore > 0) {
			System.out.println("You are victorious!");
		} else if(yourScore + botScore < 0) {
			System.out.println("You lost");
		} else {
			System.out.println("It is a draw");
		}
	}
	
	
	/**
	 * Creates a tree with a specified depth rooted at the given node.
	 * @param depth - the desired depth of the tree
	 * @param id - identity number of the current player (1 for human, -1 for bot)
	 * @param parent - the root node
	 */
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
	
	private static double timeLimit(Scanner sc){
		double timeLimit = 10^6;
        System.out.println("Please enter the number of seconds the computer is allowed to think: ");
        timeLimit = sc.nextDouble();
        return timeLimit;
	}
	
	/**
	 * Prints the current state of the game, where 'w' correspond to white pieces and
	 * 'b' to black pieces.
	 * @param currentMatrix - matrix representing the current state of the game
	 */
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