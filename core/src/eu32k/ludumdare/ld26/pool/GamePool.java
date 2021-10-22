package eu32k.ludumdare.ld26.pool;

public class GamePool {
   private TilePool tiles;
   private TileFadePool fades;
   private GenericEventPool events;
   
   public GamePool(){
      tiles = new TilePool();
      fades = new TileFadePool();
      events = new GenericEventPool();
   }
   
   public TilePool tiles() {
      return tiles;
   }
   
   public TileFadePool fades(){
      return fades;
   }
   
   public GenericEventPool events(){
      return events;
   }
}
