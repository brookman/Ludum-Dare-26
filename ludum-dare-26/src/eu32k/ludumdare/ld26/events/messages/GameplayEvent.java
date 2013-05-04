package eu32k.ludumdare.ld26.events.messages;

import eu32k.ludumdare.ld26.events.EventBase;
import eu32k.ludumdare.ld26.events.messages.GameplayEvent.GameplayEventType;

public class GameplayEvent extends EventBase {
   
   public final static int PARAM_NONE = 0;
   
   public final static int PARAM_LOSE_FALLOFFBOARD = 1;
   public final static int PARAM_LOSE_TIMEOUT = 2;
   public final static int PARAM_LOSE_SQUASHED = 3;

   public static final int PARAM_LOSE_TOLOST = 4;
   
   public enum GameplayEventType {
      START_GAME,PAUSE,RESUME,WIN,LOSE,EXIT, NEXTLEVEL;
   }
      
   private GameplayEventType type;
   private int param;
   
   public GameplayEvent(GameplayEventType type) {
      this(type, 0, PARAM_NONE);
   }

   public GameplayEvent(GameplayEventType type, float timer, int param) {
      setTime(timer);
      this.type = type;
      this.setParam(param);
   }
   

   public GameplayEvent(GameplayEventType startGame, float f) {
      this(startGame, f, 0);
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