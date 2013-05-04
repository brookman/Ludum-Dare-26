package eu32k.ludumdare.ld26.events.messages;

import eu32k.ludumdare.ld26.effects.TileMove;
import eu32k.ludumdare.ld26.events.EventBase;

public class MoveComplete extends EventBase {
   public TileMove move;
   
   public MoveComplete(TileMove move)
   {
      this.move = move;
   }
}
