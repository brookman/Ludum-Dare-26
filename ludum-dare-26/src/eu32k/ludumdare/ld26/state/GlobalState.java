package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;

import eu32k.ludumdare.ld26.events.EventQueue;
import eu32k.ludumdare.ld26.pool.GamePool;

public class GlobalState extends GameState {
   private boolean isGravitySensorEnabled;
   
   private Random masterRandom;

   private Map<String, Random> randoms;

   private EventQueue events;

   private static final long TEST_SEED = 76535225L;
   
   private GamePool pool;
      
   @Override
   public void init() {
      pool = new GamePool();
      masterRandom = new Random(TEST_SEED);
      randoms = new HashMap<String, Random>();
      events = new EventQueue();
   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }
   
   public GamePool pool(){
      return pool;
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

   public boolean isGravitySensorEnabled() {
      return isGravitySensorEnabled;
   }

   public void setGravitySensorEnabled(boolean isGravitySensorEnabled) {
      this.isGravitySensorEnabled = isGravitySensorEnabled;
   }

}
