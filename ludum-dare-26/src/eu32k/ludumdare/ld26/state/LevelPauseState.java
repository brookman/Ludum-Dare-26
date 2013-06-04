package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.stages.PauseStage;


public class LevelPauseState extends GameState {


   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(MenuState.class);
	}
   
   @Override
   public void enter() {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      levelState .setRunning(false);
      levelState.setPaused(true);
      PauseStage pauseStage = (PauseStage) stage;
   }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
