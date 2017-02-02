import java.util.ArrayList;

public class Game {
	private State currentState;
	private Player human;
	private Player bot;
	private Player currentPlayer;

	/**
	 * Constructor
	 */
	public Game(Player human, Player bot) {
		currentState = new State();
		this.human = human;
		this.bot = bot;
		currentPlayer = this.human;
		
	}
	
	public void updateGame(Point a) {
		currentState.updateState(a,currentPlayer.getId());
		if(currentPlayer.getId() == 1) {
			currentPlayer = bot;
		} else {
			currentPlayer = human;
		}
	}
	
	/**
	 * Checks if the game is finished
	 * @return true if the game is finished, otherwise false
	 */
	public boolean isFinished() {
		if(currentPlayer.possibleActions(currentState).size() == 0 || currentState.isFull()) {
			return true;
		} else {
			return false;
		}
	}
}
