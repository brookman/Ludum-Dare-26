package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.math.Interpolation;

import eu32k.ludumdare.ld26.level.Tile;

public class TileFadeIn implements IRunningEffect {
   private boolean complete = false;
   private boolean running;
   private Tile tile;
   private float fadeInTime;
   private float runningTime;
   
   public TileFadeIn(Tile tile, float fadeInTime)
   {
      this.runningTime = 0f;
      this.tile = tile;
      this.fadeInTime = fadeInTime;
   }
   
   @Override
   public boolean complete() {
      return complete;
   }

   @Override
   public void update(float delta) {
      runningTime += delta;
      if(runningTime > fadeInTime)
      {
         tile.setAlpha(1f);
         complete = true;
         return;
      }
      tile.setAlpha(Interpolation.linear.apply(0f, 1f, runningTime / fadeInTime));
   }

}
