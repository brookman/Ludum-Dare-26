package eu32k.ludumdare.ld26.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventQueue {
   private boolean isTicking;
   private List<IEvent> events;
   private List<IEventHandler> handlers;
   private List<IEvent> tickEvents;
   private boolean clearAfterIteration;

   public EventQueue() {
      isTicking = false;
      events = new ArrayList<IEvent>();
      tickEvents = new ArrayList<IEvent>();
      handlers = new ArrayList<IEventHandler>();
   }

   public void enqueue(IEvent event) {
      if (isTicking) {
         tickEvents.add(event);
      } else {
         events.add(event);
      }
   }

   public void addHandler(IEventHandler handler) {
      handlers.add(handler);
   }

   public void tick(float delta) {
      isTicking = true;
      Iterator<IEvent> iterator = events.iterator();
      while (iterator.hasNext()) {
         eventTick(delta, iterator);
      }
      if (tickEvents.size() > 0) {
         events.addAll(tickEvents);
         tickEvents.clear();
      }
      if (clearAfterIteration) {
         events.clear();
         clearAfterIteration = false;
      }
      isTicking = false;
   }

   private void eventTick(float delta, Iterator<IEvent> iterator) {
      IEvent event = iterator.next();
      event.update(delta);
      if (event.timeLeft() <= 0) {
         notifyEvent(event);
         iterator.remove();
      }
   }

   private void notifyEvent(IEvent event) {
      for (IEventHandler handler : handlers) {
         handler.handle(event);
      }
   }

   public void clear() {
      if (isTicking) {
         this.clearAfterIteration = true;
      } else {
         events.clear();
      }
   }
}
