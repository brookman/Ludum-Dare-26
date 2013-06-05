package eu32k.ludumdare.ld26.state;

import eu32k.libgdx.common.Profile;
import eu32k.libgdx.common.ProfileService;
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
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      ProfileService service = state.getProfileService();
      Profile profile = service.retrieveProfile();
      profile.inGame = false;
      profile.level = 0;
      service.persist();
   }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
