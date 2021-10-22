package eu32k.ludumdare.ld26.state;


public class SeedState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(LevelInitState.class);
		transitions.add(MenuState.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
