package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.stages.MenuStage;



public class MenuState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(LevelInitState.class);
		transitions.add(SeedState.class);
	}
   @Override
   public void enter(){
      MenuStage stage = (MenuStage)getStage();
      stage.updateGravityButtonText();      
   }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
