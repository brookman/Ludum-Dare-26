package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.events.EventBase;

public class SpawnTileEvent extends EventBase {
   
   private Tile tile;

   public SpawnTileEvent(float time, Tile tile) {
      this.tile = tile;
      setTime(time);
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }

}