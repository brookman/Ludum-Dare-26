package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.math.Interpolation;

import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.pool.IObjectPoolItem;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileFade implements IRunningEffect, IObjectPoolItem {
   private boolean complete = false;
   private boolean running;
   private Tile tile;
   private float fadeTime;
   private float runningTime;
   private float fadeFrom;
   private float fadeTo;
   private boolean inUse;
   private boolean notifyCompletion;

   public TileFade() {
      clear();
   }

   public void clear() {
      this.complete = true;
      this.runningTime = 0f;
      this.tile = null;
      this.fadeTime = 0f;
      this.fadeFrom = 0f;
      this.fadeTo = 0f;
   }

   public void init(Tile tile, float fadeTime, float fadeFrom, float fadeTo) {
      init(tile, fadeTime, fadeFrom, fadeTo, false);
   }
   public void init(Tile tile, float fadeTime, float fadeFrom, float fadeTo, boolean notifyCompletion) {
      this.complete = false;
      this.runningTime = 0f;
      this.tile = tile;
      this.fadeTime = fadeTime;
      this.fadeFrom = fadeFrom;
      this.fadeTo = fadeTo;
      this.notifyCompletion = notifyCompletion;
   }

   @Override
   public boolean complete() {
      return complete;
   }

   @Override
   public void update(float delta) {
      runningTime += delta;
      if (runningTime > fadeTime) {
         tile.setAlpha(fadeTo);

         if (notifyCompletion) {
            GlobalState gs = StateMachine.instance().getState(GlobalState.class);
            LevelState ls = StateMachine.instance().getState(LevelState.class);
            ls.getEvents().enqueue(gs.pool().events().fadeComplete(this));
            // clear();
         }
         else{
            setInUse(false);
         }
         complete = true;
         return;
      }
      tile.setAlpha(Interpolation.linear.apply(fadeFrom, fadeTo, runningTime / fadeTime));
   }

   public Tile getTile() {
      return this.tile;
   }

   public float fadeTo() {
      return fadeTo;
   }

   @Override
   public boolean isInUse() {
      return this.inUse;
   }

   @Override
   public void setInUse(boolean inUse) {
      this.inUse = inUse;
   }

}
