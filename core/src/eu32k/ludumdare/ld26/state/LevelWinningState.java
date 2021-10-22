package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.events.messages.GenericEvent;

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
      GlobalState gs = StateMachine.instance().getState(GlobalState.class);
      gs.getEvents().enqueue(gs.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_NEXTLEVEL));
   }
   
   @Override
   public void destroy() {
      
   }

}
