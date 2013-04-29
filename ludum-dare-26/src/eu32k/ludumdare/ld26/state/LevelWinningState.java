package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;

public class LevelWinningState extends GameState {

   @Override
   public void init() {
      transitions.add(LevelWonState.class);
      transitions.add(LevelState.class);
      transitions.add(MenuState.class);      
      transitions.add(LevelInitState.class);
   }

   
   @Override
   public void enter(){
      StateMachine.instance().getState(GlobalState.class).getEvents().enqueue(new GameplayEvent(GameplayEventType.NEXTLEVEL));
   }
   
   @Override
   public void destroy() {
      
   }

}
