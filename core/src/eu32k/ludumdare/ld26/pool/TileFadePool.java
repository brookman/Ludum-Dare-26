package eu32k.ludumdare.ld26.pool;

import eu32k.ludumdare.ld26.effects.TileFade;

public class TileFadePool extends ObjectPool<TileFade>{

   @Override
   protected TileFade instanceItem() {
      return new TileFade();
   }
   @Override
   public String toString(){
      return "Fade Pool";
   }

}
