package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;
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
      StateMachine.instance().getState(GlobalState.class).getEvents().enqueue(new GameplayEvent(GameplayEventType.LOSE, 1, GameplayEvent.PARAM_LOSE_TOLOST));
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      List<Tile> tiles = ls.getLevel().getTiles();
      effects.clear();
      for (Tile t : tiles) {
         TileFade fade = new TileFade();
         fade.init(t, fadeOutTime, 1f, 0f);
         effects.add(fade);
      }
      this.timer = 0f;
   }

   public void update(float delta) {
      timer += delta;
      for(IRunningEffect effect : effects){
         effect.update(delta);
      }
   }

   @Override
   public void destroy() {
   }

   @Override
   public void leave(){
      effects.clear();
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
