package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {
	
	private static StateMachine INSTANCE;
	
	private List<GameState> states;
	
	private StateMachine() {
		states = new ArrayList<GameState>();
	}
	
	public StateMachine instance() {
		if(INSTANCE == null) {
			INSTANCE = new StateMachine();
		}
		return INSTANCE;
	}
	
	public GameState createState(Class<? extends GameState> stateClass) {
		try {
			GameState state = stateClass.getConstructor().newInstance();
			state.init();
			this.states.add(state);
			return state;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
