package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.Tile;

public class Level {
   
   enum Edge {
      TOP, RIGHT, BOTTOM, LEFT;
   }
   
   private Tile[][] tiles;
   
   private int width;
   
   private int height;
   
   public Level(int width, int height) {
      tiles = new Tile[height][width];
   }
   
   public void insertTile(Tile tile, Edge edge, int position) {
      if(edge.equals(Edge.TOP)) {
         
      } else if(edge.equals(Edge.RIGHT)) {
         
      } else if(edge.equals(Edge.BOTTOM)) {
         
      } else if(edge.equals(Edge.LEFT)) {
         
      }
   }
   
}
