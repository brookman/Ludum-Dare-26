package eu32k.ludumdare.ld26.state;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelWonState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(MenuState.class);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
