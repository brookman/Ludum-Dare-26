package eu32k.ludumdare.ld26.pool;

import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.events.messages.GenericEvent;
import eu32k.ludumdare.ld26.events.messages.GenericEvent.GenericEventType;

public class GenericEventPool extends ObjectPool<GenericEvent> {

   @Override
   protected GenericEvent instanceItem() {
      return new GenericEvent();
   }

   public GenericEvent fadeComplete(TileFade fade) {
      GenericEvent ev = getFreeItem();
      ev.init();
      ev.type = GenericEventType.FADE_COMPLETE;
      ev.fade = fade;
      return ev;
   }

   public GenericEvent gameEvent() {
      GenericEvent ev = getFreeItem();
      return ev;
   }

   public GenericEvent tileEvent() {
      GenericEvent ev = getFreeItem();
      return ev;
   }

   public GenericEvent tileFade() {
      GenericEvent ev = getFreeItem();
      return ev;
   }
}
