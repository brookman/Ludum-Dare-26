package eu32k.ludumdare.ld26.gameplay;

import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelLosingState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.LevelWinningState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class WinLoseConditionHandler implements IEventHandler {
   
   private GlobalState globalState;
   private LevelState levelState;
   
   public WinLoseConditionHandler() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      levelState = StateMachine.instance().getState(LevelState.class);
      levelState.getEvents().addHandler(this);
   }

   @Override
   public void handle(IEvent ev) {
      if(ev instanceof GameplayEvent) {
         GameplayEvent event = (GameplayEvent) ev;
         switch(event.getType()) {
         case WIN:
            StateMachine.instance().getState(LevelState.class).log("WON");
            StateMachine.instance().enterState(LevelWinningState.class);
            break;
         case LOSE:
            String death = "LOST";
            switch(event.getParam())
            {
            case GameplayEvent.PARAM_LOSE_FALLOFFBOARD:
               death = "Player has fallen off the board";
               break;
            case GameplayEvent.PARAM_LOSE_SQUASHED:
               death = "Player has been squashed to death";
               break;
            case GameplayEvent.PARAM_LOSE_TIMEOUT:
               death = "Time is up";
               break;
            }
            
            StateMachine.instance().getState(LevelState.class).log(death);
            
            StateMachine.instance().enterState(LevelLosingState.class);
//            StateMachine.instance().createState(new LevelState());
//            StateMachine.instance().getState(LevelState.class).setStage(new GameStage());
            break;
         }
      }
   }
   
}
