package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.Tile;
import eu32k.ludumdare.ld26.events.EventBase;
import eu32k.ludumdare.ld26.level.Level.Edge;

public class SpawnTileEvent extends EventBase {
   
   private Edge edge;
   
   private Tile tile;

   public SpawnTileEvent(float time, Edge edge, Tile tile) {
      this.tile = tile;
      this.edge = edge;
      setTime(time);
   }

   public Edge getEdge() {
      return edge;
   }

   public void setEdge(Edge edge) {
      this.edge = edge;
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }

}
