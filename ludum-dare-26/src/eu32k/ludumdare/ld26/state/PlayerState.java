package eu32k.ludumdare.ld26.state;

import java.util.HashMap;
import java.util.Map;

import eu32k.ludumdare.ld26.objects.Player;

public class PlayerState extends GameState {
   public final static String STATISTIC_RETRIES = "Retries";
   public final static String STATISTIC_DEATHS = "Deaths";
   public final static String STATISTIC_TOTALTIME = "Total Time";
   public final static String STATISTIC_SUCCESSFULTIME = "Successful Time";

   private Player player;

   public Map<String, Integer> genericStatistics;

   public PlayerState() {
      genericStatistics = new HashMap<String, Integer>();
   }

   @Override
   public void init() {
      player = new Player(0, 0);
   }

   public void countStatistic(String name) {
      if (genericStatistics.containsKey(name)) {
         genericStatistics.put(name, genericStatistics.get(name) + 1);
      } else {
         genericStatistics.put(name, 1);
      }
   }

   public void resetStatistic(String name) {
      setStatistic(name, 0);
   }

   public void setStatistic(String name, int value) {
      genericStatistics.put(name, value);
   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   public Player getPlayer() {
      return player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }

}
