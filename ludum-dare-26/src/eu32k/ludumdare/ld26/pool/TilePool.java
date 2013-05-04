package eu32k.ludumdare.ld26.pool;

import eu32k.ludumdare.ld26.level.Tile;

public class TilePool extends ObjectPool<Tile> {

   @Override
   protected Tile instanceItem() {
      return new Tile();
   }

}
