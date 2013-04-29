package eu32k.ludumdare.ld26.state;

import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;

//TODO: Still needs to be implemented
public class LevelInitState extends GameState {
   private float timeSinceEnter;
   private float fadeInLength;
   
   @Override
   public void init() {
      transitions.add(LevelState.class);
   }

   @Override
   public void enter() {
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      state.getEvents().enqueue(new GameplayEvent(GameplayEventType.START_GAME, 0.5f));
      timeSinceEnter = 0f;
   }

   public void update(float d) {
      timeSinceEnter += d;
   }
   
   public float getTimeSinceEnter(){
      return timeSinceEnter;
   }

   @Override
   public void destroy() {

   }

   public float getFadeInLength() {
      return fadeInLength;
   }

   public void setFadeInLength(float fadeInLength) {
      this.fadeInLength = fadeInLength;
   }

}
