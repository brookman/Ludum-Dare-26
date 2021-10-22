package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.events.messages.GenericEvent;
import eu32k.ludumdare.ld26.level.Tile;

public class LevelLosingState extends GameState {

   private float timer;
   private float fadeOutTime;
   private List<IRunningEffect> effects;

   public LevelLosingState() {
      setFadeOutTime(1f);
      effects = new ArrayList<IRunningEffect>();
   }

   @Override
   public void init() {
      transitions.add(LevelLostState.class);
      transitions.add(MenuState.class);
   }

   @Override
   public void enter() {
      GlobalState gs = StateMachine.instance().getState(GlobalState.class);
      gs.getEvents().enqueue(gs.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_LOSE, 1, GenericEvent.GAMEEVENT_LOSE_TOLOST));
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      List<Tile> tiles = ls.getLevel().getTiles();
      effects.clear();
      for (Tile t : tiles) {
         if (t.isInUse()) {
            TileFade fade = gs.pool().fades().getFreeItem();
            fade.init(t, fadeOutTime, 1f, 0f);
            effects.add(fade);
         }
      }
      this.timer = 0f;
   }

   public void update(float delta) {
      timer += delta;
      for (IRunningEffect effect : effects) {
         effect.update(delta);
      }
   }

   @Override
   public void destroy() {
   }

   @Override
   public void leave() {
      effects.clear();
      GlobalState gs = StateMachine.instance().getState(GlobalState.class);
      gs.pool().fades().setInUseForAll(false);
      gs.pool().tiles().setInUseForAll(false);
   }

   public boolean handleKeys(float delta) {
      timer += delta;
      return timer > 2f;

   }

   public float getFadeOutTime() {
      return fadeOutTime;
   }

   public void setFadeOutTime(float fadeOutTime) {
      this.fadeOutTime = fadeOutTime;
   }

}
