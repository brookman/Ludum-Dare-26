package eu32k.ludumdare.ld26.state;


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
   }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
