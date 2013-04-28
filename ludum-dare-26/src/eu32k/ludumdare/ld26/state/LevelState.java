package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import eu32k.ludumdare.ld26.animation.TileAnimator;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileMove;

public class LevelState extends GameState {

   private Level level;
   private List<TileMove> movingTiles;
   private TileAnimator tileAnimator;
   public Tile toPop;
   public Tile spawned;
   public Tile playerTile;

   public LevelState() {
      this.movingTiles = new ArrayList<TileMove>();
      tileAnimator = new TileAnimator();
   }

   @Override
   public void init() {
      transitions.add(LevelFinishedState.class);
   }

   @Override
   public void enter() {
      // TODO Auto-generated method stub

   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   public Level getLevel() {
      return level;
   }

   public void setLevel(Level level) {
      this.level = level;
   }

   public List<TileMove> getMovingTiles() {
      return movingTiles;
   }

   public TileAnimator getTileAnimator() {
      return tileAnimator;
   }

   public void setTileAnimator(TileAnimator tileAnimator) {
      this.tileAnimator = tileAnimator;
   }
}
