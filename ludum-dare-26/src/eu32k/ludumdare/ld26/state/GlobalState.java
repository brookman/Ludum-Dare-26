package eu32k.ludumdare.ld26.state;

import java.util.Random;

public class GlobalState extends GameState {
   
   private Random masterRandom;
   
   private static final long TEST_SEED = 543524654365L;

   @Override
   public void init() {
      masterRandom = new Random(TEST_SEED);
   }

   @Override
   public void enter() {
      // TODO Auto-generated method stub

   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }
   
   public Random getMasterRandom() {
      return masterRandom;
   }

}
