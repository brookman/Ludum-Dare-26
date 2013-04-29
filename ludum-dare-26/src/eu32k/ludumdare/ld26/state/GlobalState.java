package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.ludumdare.ld26.events.EventQueue;

public class GlobalState extends GameState {

   private Random masterRandom;

   private Map<String, Random> randoms;

   private EventQueue events;

   private static final long TEST_SEED = 76535225L;
   
   @Override
   public void init() {
      masterRandom = new Random(TEST_SEED);
      randoms = new HashMap<String, Random>();
      events = new EventQueue();
   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   public Random getMasterRandom() {
      return masterRandom;
   }

   public Random createNewRandom(String name) {
      Random rand = randoms.get(name);
      if (rand == null) {
         rand = new Random(masterRandom.nextLong());
         this.randoms.put(name, rand);
      }
      return rand;
   }
   
   public Random getRandom(String name) {
      return randoms.get(name);
   }
   
   public EventQueue getEvents() {
      return events;
   }

}
