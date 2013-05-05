package eu32k.ludumdare.ld26.pool;

import eu32k.ludumdare.ld26.effects.GameObjectMove;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.effects.TileMove;
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

   public GenericEvent gameplayEvent(int eventType) {
      return gameplayEvent(eventType, 0f, 0);
   }
   public GenericEvent gameplayEvent(int eventType, float time) {
      return gameplayEvent(eventType, time, 0);
   }
   public GenericEvent gameplayEvent(int gameEventType, float time, int paramB) {
      GenericEvent ev = getFreeItem();
      ev.init();
      ev.type = GenericEventType.GAMEEVENT;
      ev.setTime(time);
      ev.intA = gameEventType;
      ev.intB = paramB;
      return ev;
   }

   public GenericEvent musicEvent(boolean soundPlaying) {
      GenericEvent ev = getFreeItem();
      ev.init();
      ev.type = GenericEventType.MUSICEVENT;
      ev.booleanA = soundPlaying;
      return ev;
   }

   public GenericEvent moveComplete(TileMove tileMove) {
      GenericEvent ev = getFreeItem();
      ev.init();
      ev.type = GenericEventType.MOVE_COMPLETE;
      ev.tileMove = tileMove;
      return ev;
   }

   public GenericEvent objectMoveComplete(GameObjectMove move) {
      GenericEvent ev = getFreeItem();
      ev.init();
      ev.type = GenericEventType.OBJECTMOVE_COMPLETE;
      ev.objectMove = move;
      return ev;
   }
@Override
   public String toString(){
      return "Event Pool";
   }

}
