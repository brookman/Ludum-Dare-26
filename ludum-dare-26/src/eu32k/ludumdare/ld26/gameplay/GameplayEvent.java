package eu32k.ludumdare.ld26.gameplay;

import eu32k.ludumdare.ld26.events.EventBase;

public class GameplayEvent extends EventBase {
   
   public enum GameplayEventType {
      WIN,LOSE;
   }
   
   private GameplayEventType type;
   
   public GameplayEvent(GameplayEventType type) {
      this.type = type;
   }

   public GameplayEventType getType() {
      return type;
   }

   public void setType(GameplayEventType type) {
      this.type = type;
   }
   
}
