package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.level.Level;

public class LevelState extends GameState {
   
   private Level level;

	@Override
	public void init() {
		transitions.add(LevelFinishedState.class);
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
