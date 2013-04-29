package eu32k.ludumdare.ld26.state;


public class MenuState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(LevelInitState.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
