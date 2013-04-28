package eu32k.ludumdare.ld26.state;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
