package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;

import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;
import eu32k.ludumdare.ld26.level.Tile;

//TODO: Still needs to be implemented
public class LevelInitState extends GameState {
   private float timeSinceEnter;
   private float fadeInLength;
   private List<IRunningEffect> effects;

   public LevelInitState() {
      fadeInLength = 2f;
      effects = new ArrayList<IRunningEffect>();
   }

   @Override
   public void init() {
      transitions.add(LevelState.class);
   }

   @Override
   public void enter() {
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      state.getEvents().enqueue(new GameplayEvent(GameplayEventType.START_GAME, fadeInLength));
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      List<Tile> tiles = ls.getLevel().getTiles();
      ls.setPaused(false);
      effects.clear();
      timeSinceEnter = 0f;
      for (Tile t : tiles) {
         TileFade fade = new TileFade();
         fade.init(t, fadeInLength, 0f, 1f);
         effects.add(fade);
      }
   }

   public void update(float d) {
      timeSinceEnter += d;
      for (IRunningEffect effect : effects) {
         effect.update(d);
      }
   }

   public float getTimeSinceEnter() {
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
      setColor(mainColor, Interpolation.linear, value);
      setColor(playerColor, Interpolation.exp5, value / 100f);
      setColor(inverseColor, Interpolation.exp5, value / 10f + 0.9f);

   }

   private void setColor(Color color, Interpolation interpolation, float value) {
      color.r = interpolation.apply(0f, color.r, value);
      color.g = interpolation.apply(0f, color.g, value);
      color.b = interpolation.apply(0f, color.b, value);
   }

}
