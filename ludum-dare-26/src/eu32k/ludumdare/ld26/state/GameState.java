package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {
	
	protected List<Class<? extends GameState>> transitions;
	
	public GameState() {
		transitions = new ArrayList<Class<? extends GameState>>();
	}
	
	public List<Class<? extends GameState>> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Class<? extends GameState>> transitions) {
		this.transitions = transitions;
	}
	
	public abstract void init();

	public abstract void enter();
	
	public abstract void destroy();

}
