package eu32k.ludumdare.ld26.effects;

import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileFade implements IRunningEffect {
   private boolean complete;
   private Tile tile;
   private float speed;
   private LevelState levelState;
   private float currentAlpha;
   
   /* (non-Javadoc)
    * @see eu32k.ludumdare.ld26.effects.IUpdateUntilComplete#complete()
    */
   @Override
   public boolean complete() {
      return complete;
   }

   public TileFade()
   {
      this.levelState = StateMachine.instance().getState(LevelState.class);
      this.currentAlpha = 1;
   }
   
   public void initFade(Tile tile, float speed)
   {
      this.tile = tile;
      complete = false;
      this.speed = speed;
   }
   
   /* (non-Javadoc)
    * @see eu32k.ludumdare.ld26.effects.IUpdateUntilComplete#update(float)
    */
   @Override
   public void update(float delta) {
      currentAlpha -= (delta);
      if(currentAlpha < 0) {
         currentAlpha = 0;
         complete = true;
         tile.setDead(true);
         levelState.getEvents().enqueue(new FadeComplete(this));
      }
      tile.setAlpha(currentAlpha);
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }
   
}
