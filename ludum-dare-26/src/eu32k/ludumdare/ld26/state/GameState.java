package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.ludumdare.ld26.stages.AbstractStage;

public abstract class GameState {
	
	protected List<Class<? extends GameState>> transitions;
	
	protected AbstractStage stage;
	
	public GameState(AbstractStage stage) {
		transitions = new ArrayList<Class<? extends GameState>>();
		this.stage = stage;
	}
	
	public GameState() {
	   this(null);
	}
	
	public List<Class<? extends GameState>> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Class<? extends GameState>> transitions) {
		this.transitions = transitions;
	}
	
	public abstract void init();

	public void enter() {
	}
	
	public abstract void destroy();

   public Stage getStage() {
      return stage;
   }

   public void setStage(AbstractStage stage) {
      this.stage = stage;
   }

   public void leave() {      
   }
	

}
