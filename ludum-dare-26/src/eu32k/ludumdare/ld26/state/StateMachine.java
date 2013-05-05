package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {

   private static StateMachine INSTANCE;

   private Map<Class<? extends GameState>, GameState> states;

   private GameState current;

   private StateMachine() {
      states = new HashMap<Class<? extends GameState>, GameState>();
   }

   public static StateMachine instance() {
      if (INSTANCE == null) {
         INSTANCE = new StateMachine();
      }
      return INSTANCE;
   }

   public void createState(GameState state) {
      Class<? extends GameState> stateClass = state.getClass();
      GameState origState = states.get(state.getClass());
      if (origState != null) {
         origState.destroy();
      }
      try {
         state.init();
         this.states.put(stateClass, state);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @SuppressWarnings("unchecked")
   public <T extends GameState> T enterState(Class<T> stateClass) {
      //System.out.println("entering state "+stateClass.getName());
      if (current == null) {
         changeCurrentState(stateClass);
      } else {
         if (current.getTransitions().contains(stateClass)) {
            changeCurrentState(stateClass);
         } else {
            // TODO: Logging
            // System.out.println("State has no transition to "+stateClass.getName());
         }
      }
      return (T) current;
   }

   private <T extends GameState> void changeCurrentState(Class<T> stateClass) {
      if (current != null) {
         current.leave();
      }
      current = states.get(stateClass);
      if (current != null) {
         current.enter();
      }
   }

   @SuppressWarnings("unchecked")
   public <T extends GameState> T getState(Class<T> stateClass) {
      return (T) states.get(stateClass);
   }

   public GameState getCurrentState() {
      return current;
   }

}
