package eu32k.ludumdare.ld26.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventQueue {
   private List<IEvent> events;
   private List<IEventHandler> handlers;

   public EventQueue() {
      events = new ArrayList<IEvent>();
      handlers = new ArrayList<IEventHandler>();
   }

   public void enqueue(IEvent event) {
      events.add(event);
   }

   public void addHandler(IEventHandler handler) {
      handlers.add(handler);
   }

   public void tick(float delta) {
      Iterator<IEvent> iterator = events.iterator();
      while (iterator.hasNext()) {
         eventTick(delta, iterator);
      }
   }

   private void eventTick(float delta, Iterator<IEvent> iterator) {
      IEvent event = iterator.next();
      event.update(delta);
      if (event.timeLeft() <= 0) {
         iterator.remove();
         notifyEvent(event);
      }
   }

   private void notifyEvent(IEvent event) {
      for (IEventHandler handler : handlers) {
         handler.handle(event);
      }
   }
}
