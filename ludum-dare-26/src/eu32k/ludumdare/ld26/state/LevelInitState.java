package eu32k.ludumdare.ld26.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;

import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;

//TODO: Still needs to be implemented
public class LevelInitState extends GameState {
   private float timeSinceEnter;
   private float fadeInLength;
   
   public LevelInitState(){
      fadeInLength = 2f;
   }
   
   @Override
   public void init() {
      transitions.add(LevelState.class);
   }

   @Override
   public void enter() {
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      state.getEvents().enqueue(new GameplayEvent(GameplayEventType.START_GAME, fadeInLength));
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

   public void setColors(Color mainColor, Color playerColor, Color inverseColor) {
      float value = timeSinceEnter / fadeInLength;
      mainColor.r = 0f;
      mainColor.g = 0f;
      mainColor.b = 0f;
      mainColor.a = 0f;
      setColor(playerColor, value * 4);
      setColor(inverseColor, value * 2);
      
   }

   private void setColor(Color color, float value) {
      color.r = Interpolation.linear.apply(0f, color.r, value);
      color.g = Interpolation.linear.apply(0f, color.g, value);
      color.b = Interpolation.linear.apply(0f, color.b, value);
   }

}
