
public class Game {
	private State currentState;
	private Player human;
	private Player bot;
	private Player currentPlayer;

	/**
	 * Constructor
	 */
	public Game() {
		currentState = new State();
		human = new Player(1);
		bot = new Player(2);
		currentPlayer = human;
		
	}
	
	public void updateGame() {
		
	}
	
	/**
	 * Checks if the game is finished
	 * @return true if the game is finished, otherwise false
	 */
	public boolean isFinished() {
		if(currentPlayer.possibleActions().size() == 0 || currentState.isFull()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
