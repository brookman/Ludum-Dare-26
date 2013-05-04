package eu32k.ludumdare.ld26.pool;

public class GamePool {
   private TilePool tiles;
   private TileFadePool fades;
   
   public GamePool(){
      tiles = new TilePool();
      fades = new TileFadePool();
   }
   
   public TilePool tiles() {
      return tiles;
   }
   
   public TileFadePool fades(){
      return fades;
   }
}
