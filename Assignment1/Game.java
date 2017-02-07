import java.util.ArrayList;

public class Game {
	private State currentState;
	private Player human;
	private Player bot;
	private Player currentPlayer;

	/**
	 * Constructor
	 * @param human - Player object representing the human player
	 * @param bot - Player object representing the bot player
	 */
	public Game(Player human, Player bot) {
		currentState = new State();
		this.human = human;
		this.bot = bot;
		currentPlayer = this.human;
	}
	
	
	/**
	 * Sets the state of the game to a given new state and changes the current player
	 * @param newState - State object representing the new state
	 */
	public void updateGame(State newState) {
		currentState = newState;
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
	
	/**
	 * 
	 * @return the current player of the game
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	/**
	 * 
	 * @return a State object representing the current state of the game
	 */
	public State getGameState(){
		return currentState;
	}
}
