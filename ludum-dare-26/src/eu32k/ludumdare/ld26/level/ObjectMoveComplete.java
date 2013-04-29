package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.effects.GameObjectMove;
import eu32k.ludumdare.ld26.events.EventBase;

public class ObjectMoveComplete extends EventBase {
   public GameObjectMove obj;
   
   public ObjectMoveComplete(GameObjectMove obj)
   {
      this.obj = obj;
   }
}
