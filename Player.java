import java.util.*;

public class Player {
	private int identity;
	
	public Player(int identity) {
		this.identity = identity;
	}
	
	public ArrayList<Action> possibleActions(State s) {
		return null;
	}
	
	public int getId() {
		return identity;
	}
	
	public Action chooseAction(ArrayList<Action> listOfActions, State s) {
		if(identity == 1) {
			return null;
		} else {
			return minimax(listOfActions,s);
		}
	}

	public Action minimax(ArrayList<Action> listOfActions, State s) {
		return null;
	}
}
