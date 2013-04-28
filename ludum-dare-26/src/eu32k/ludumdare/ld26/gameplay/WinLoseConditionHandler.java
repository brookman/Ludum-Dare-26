package eu32k.ludumdare.ld26.gameplay;

import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.stages.GameStage;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelFinishedState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.LostState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class WinLoseConditionHandler implements IEventHandler {
   
   private GlobalState globalState;
   
   public WinLoseConditionHandler() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      globalState.getEvents().addHandler(this);
   }

   @Override
   public void handle(IEvent ev) {
      if(ev instanceof GameplayEvent) {
         GameplayEvent event = (GameplayEvent) ev;
         switch(event.getType()) {
         case WIN:
            StateMachine.instance().enterState(LevelFinishedState.class);
            break;
         case LOSE:
            StateMachine.instance().enterState(LostState.class);
//            StateMachine.instance().createState(new LevelState());
//            StateMachine.instance().getState(LevelState.class).setStage(new GameStage());
            break;
         }
      }
   }
   
}
