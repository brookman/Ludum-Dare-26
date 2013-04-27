package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {

	private static StateMachine INSTANCE;

	private Map<Class<? extends GameState>, GameState> states;

	private GameState current;

	private StateMachine() {
		states = new HashMap<Class<? extends GameState>, GameState>();
	}

	public StateMachine instance() {
		if (INSTANCE == null) {
			INSTANCE = new StateMachine();
		}
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public <T extends GameState> T createState(Class<T> stateClass) {
		GameState state = states.get(stateClass);
		if(state != null) {
			state.destroy();
		}
		try {
			state = stateClass.getConstructor().newInstance();
			state.init();
			this.states.put(stateClass, state);
			return (T) state;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends GameState> T enterState(Class<T> stateClass) {
		if (current.getTransitions().contains(stateClass)) {
			current = states.get(stateClass);
			if (current != null) {
				current.enter();
			}
		} else {
			System.out.println("State has no transition to "+stateClass.getName());
		}
		return (T) current;
	}

}
