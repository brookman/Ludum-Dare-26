package eu32k.ludumdare.ld26.gameplay;

import eu32k.ludumdare.ld26.effects.FadeComplete;
import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.state.GameState;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelLosingState;
import eu32k.ludumdare.ld26.state.LevelLostState;
import eu32k.ludumdare.ld26.state.LevelPauseState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.LevelWinningState;
import eu32k.ludumdare.ld26.state.LevelWonState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class GameEventHandler implements IEventHandler {

   private GlobalState globalState;
   private LevelState levelState;

   public GameEventHandler() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      levelState = StateMachine.instance().getState(LevelState.class);
      globalState.getEvents().addHandler(this);
      levelState.getEvents().addHandler(this);
   }

   @Override
   public void handle(IEvent ev) {
      if (ev instanceof GameplayEvent) {
         handleGameplayEvent((GameplayEvent) ev);
      } else if (ev instanceof FadeComplete) {
         handleFadeComplete((FadeComplete) ev);
      }
   }

   private void handleFadeComplete(FadeComplete ev) {
      Tile t = ev.fade.getTile();
      t.setDead(true);
      levelState.getLevel().getTiles().remove(t);
      if (levelState.playerTile == t) {
         levelState.playerTile = null;
      }
      if (levelState.goalTile == t) {
         levelState.goalTile = null;
      }
   }

   private void handleGameplayEvent(GameplayEvent event) {
      GameState state = StateMachine.instance().getCurrentState();
      switch (event.getType()) {
      case PAUSE:
         levelState.setPaused(true);
         // levelState.log("Paused");
         StateMachine.instance().enterState(LevelPauseState.class);
         break;
      case RESUME:
         levelState.setPaused(false);
         // levelState.log("Resumed");
         StateMachine.instance().enterState(LevelState.class);
         break;
      case NEXTLEVEL:
         if (state instanceof LevelWinningState || state instanceof LevelWonState) {
            if (levelState.getLevels().nextLevel()) {
               levelState.addSuccessStatistic();
               levelState.initLevel();
               PlayerState ps = StateMachine.instance().getState(PlayerState.class);
               StateMachine.instance().enterState(LevelInitState.class);
            } else {
               StateMachine.instance().enterState(LevelWonState.class);
            }
         }
         break;
      case WIN:
         // levelState.log("WON");
         levelState.getEvents().clear();
         StateMachine.instance().enterState(LevelWinningState.class);
         break;
      case LOSE:
         switch (event.getParam()) {
         case GameplayEvent.PARAM_LOSE_FALLOFFBOARD:
            lose("Player has fallen off the board");
            break;
         case GameplayEvent.PARAM_LOSE_SQUASHED:
            lose("Player has been squashed to death");
            break;
         case GameplayEvent.PARAM_LOSE_TIMEOUT:
            lose("Time is up");
            break;
         case GameplayEvent.PARAM_LOSE_TOLOST:
            if (state instanceof LevelLosingState) {
               StateMachine.instance().enterState(LevelLostState.class);
            }
            return;
         }
         break;
      case START_GAME:
         StateMachine.instance().enterState(LevelState.class);
         break;
      }
   }

   private void lose(String death) {
      levelState.getEvents().clear();
      // levelState.log(death);
      levelState.addDeathStatistics();
      StateMachine.instance().enterState(LevelLosingState.class);
   }

}
