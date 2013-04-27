package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.Tile;
import eu32k.ludumdare.ld26.events.EventBase;
import eu32k.ludumdare.ld26.level.Level.Edge;

public class TileEvent extends EventBase {
   
   public enum TileEventType {
      SPAWNED, TRIGGER_SPAWN, TRIGGER_POP, POPPED;
   }
   
   private Tile tile;
   
   private TileEventType type;

   public TileEvent(float time, Tile tile, TileEventType type) {
      this.tile = tile;
      this.type = type;
      setTime(time);
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }

   public TileEventType getType() {
      return type;
   }

   public void setType(TileEventType type) {
      this.type = type;
   }

}
