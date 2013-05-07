package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;

import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.events.messages.GenericEvent;
import eu32k.ludumdare.ld26.level.Level;
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
      state.getEvents().enqueue(state.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_START_GAME, fadeInLength));
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      Level level = ls.getLevel();
      List<Tile> tiles = level.getTiles();
      ls.setPaused(false);
      effects.clear();
      timeSinceEnter = 0f;
      state.pool().fades().preloadTo(level.getWidth() * level.getHeight());
      for (Tile t : tiles) {
         if (t.isInUse()) {
            TileFade fade = state.pool().fades().getFreeItem();
            fade.init(t, fadeInLength, 0f, 1f);
            effects.add(fade);
         }
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

   @Override
   public void leave() {
      GlobalState gs = StateMachine.instance().getState(GlobalState.class);
      gs.pool().fades().setInUseForAll(false);
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
      setColor(playerColor, Interpolation.linear, value );
      setColor(inverseColor, Interpolation.linear, value );

   }

   private void setColor(Color color, Interpolation interpolation, float value) {
      color.r = interpolation.apply(0f, color.r, value);
      color.g = interpolation.apply(0f, color.g, value);
      color.b = interpolation.apply(0f, color.b, value);
   }

}
