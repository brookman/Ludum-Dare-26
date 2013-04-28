package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.TileMove;

public class LevelState extends GameState {

   private Level level;
   private List<TileMove> movingTiles;

   public LevelState() {
      this.movingTiles = new ArrayList<TileMove>();
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

   public List<TileMove> getMovingTiles()
   {
      return movingTiles;
   }
}
