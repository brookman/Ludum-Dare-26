package eu32k.ludumdare.ld26.gameplay;

import eu32k.libgdx.common.Profile;
import eu32k.libgdx.common.ProfileService;
import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.events.messages.GenericEvent;
import eu32k.ludumdare.ld26.level.LevelConfigSequence;
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
      if (ev instanceof GenericEvent) {
         handleGenericEvent((GenericEvent) ev);
      }
   }

   private void handleGenericEvent(GenericEvent ev) {
      switch (ev.type) {
      case FADE_COMPLETE:
         handleFadeComplete(ev);
         break;
      case GAMEEVENT:
         handleGameplayEvent(ev);
         break;
      }
   }

   private void handleFadeComplete(GenericEvent ev) {
      ev.fade.setInUse(false);
      Tile t = ev.fade.getTile();
      if (ev.fade.fadeTo() == 0f) {

         t.setInUse(false);
         // levelState.getLevel().getTiles().remove(t);
         if (levelState.playerTile == t) {
            levelState.playerTile = null;
         }
         if (levelState.goalTile == t) {
            levelState.goalTile = null;
         }
      }

   }

   private void handleGameplayEvent(GenericEvent event) {
      GameState state = StateMachine.instance().getCurrentState();
      switch (event.intA) {
      case GenericEvent.GAMEEVENT_TYPE_PAUSE:
         levelState.setPaused(true);
         // levelState.log("Paused");
         StateMachine.instance().enterState(LevelPauseState.class);
         break;
      case GenericEvent.GAMEEVENT_TYPE_RESUME:
         levelState.setPaused(false);
         // levelState.log("Resumed");
         StateMachine.instance().enterState(LevelState.class);
         break;
      case GenericEvent.GAMEEVENT_TYPE_NEXTLEVEL:
         if (state instanceof LevelWinningState || state instanceof LevelWonState) {
            ProfileService service = globalState.getProfileService();
            Profile profile = service.retrieveProfile();
            LevelConfigSequence levels = levelState.getLevels();
            if (levels.nextLevel()) {
               levelState.addSuccessStatistic();
               globalState.pool().tiles().clear();
               profile.level = levels.getLevelIndex();
               profile.inGame = true;
               service.persist();
               levelState.initLevel();
               StateMachine.instance().enterState(LevelInitState.class);
            } else {
               levelState.addSuccessStatistic();
               profile.level = 0;
               profile.inGame = false;
               service.persist();
               StateMachine.instance().enterState(LevelWonState.class);
               
            }
         }
         break;
      case GenericEvent.GAMEEVENT_TYPE_WIN:
         // levelState.log("WON");
         levelState.getEvents().clear();
         StateMachine.instance().enterState(LevelWinningState.class);
         break;
      case GenericEvent.GAMEEVENT_TYPE_LOSE:
         switch (event.intB) {
         case GenericEvent.GAMEEVENT_LOSE_FALLOFFBOARD:
            lose("Player has fallen off the board");
            break;
         case GenericEvent.GAMEEVENT_LOSE_SQUASHED:
            lose("Player has been squashed to death");
            break;
         case GenericEvent.GAMEEVENT_LOSE_TIMEOUT:
            lose("Time is up");
            break;
         case GenericEvent.GAMEEVENT_LOSE_TOLOST:
            if (state instanceof LevelLosingState) {
               StateMachine.instance().enterState(LevelLostState.class);
            }
            return;
         }
         break;
      case GenericEvent.GAMEEVENT_TYPE_START_GAME:
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
