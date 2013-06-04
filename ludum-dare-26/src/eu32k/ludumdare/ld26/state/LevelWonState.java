package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.stages.FinishStage;



public class LevelWonState extends GameState {

   @Override
	public void init() {
		transitions.add(LevelState.class);
		transitions.add(MenuState.class);
		
	}
   
   public void enter() {
      FinishStage fs = (FinishStage) getStage();
      fs.setStatistics();
      fs.resetRunningTime();
   }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
