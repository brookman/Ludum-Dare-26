package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GlobalState extends GameState {
   
   private Random masterRandom;
   
   private Map<String, Random> randoms;
   
   private static final long TEST_SEED = 543524654365L;

   @Override
   public void init() {
      masterRandom = new Random(TEST_SEED);
      randoms = new HashMap<String, Random>();
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
   
   public Random createNewRandom(String name) {
      Random rand = new Random(masterRandom.nextLong());
      this.randoms.put(name, rand);
      return rand;
   }

}
