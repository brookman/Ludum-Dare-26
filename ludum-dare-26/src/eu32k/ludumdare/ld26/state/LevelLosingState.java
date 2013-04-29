package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;

public class LevelLosingState extends GameState {

   private float timer;

   @Override
   public void init() {
      transitions.add(LevelLostState.class);
      transitions.add(MenuState.class);
   }

   @Override
   public void enter() {
      StateMachine.instance().getState(GlobalState.class).getEvents().enqueue(new GameplayEvent(GameplayEventType.LOSE, 10, GameplayEvent.PARAM_LOSE_TOLOST));
      this.timer = 0f;
   }

   @Override
   public void destroy() {

   }
   
   public boolean handleKeys(float delta)
   {
      timer += delta;
      return timer > 2f;

   }

}
