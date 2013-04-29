package eu32k.ludumdare.ld26.gameplay;

import eu32k.ludumdare.ld26.events.EventBase;

public class GameplayEvent extends EventBase {
   
   public final static int PARAM_NONE = 0;
   
   public final static int PARAM_LOSE_FALLOFFBOARD = 1;
   public final static int PARAM_LOSE_TIMEOUT = 2;
   public final static int PARAM_LOSE_SQUASHED = 3;
   
   public enum GameplayEventType {
      WIN,LOSE;
   }
      
   private GameplayEventType type;
   private int param;
   
   public GameplayEvent(GameplayEventType type) {
      this(type, PARAM_NONE);
   }

   public GameplayEvent(GameplayEventType type, int param) {
      this.type = type;
      this.setParam(param);
   }
   

   public GameplayEventType getType() {
      return type;
   }

   public void setType(GameplayEventType type) {
      this.type = type;
   }

   public int getParam() {
      return param;
   }

   public void setParam(int param) {
      this.param = param;
   }
   
}
