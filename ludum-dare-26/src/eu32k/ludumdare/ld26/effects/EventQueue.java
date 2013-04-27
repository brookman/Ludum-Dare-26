package eu32k.ludumdare.ld26.effects;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
   public List<IEvent> events;
   
   public EventQueue()
   {
      events = new ArrayList<IEvent>();
   }
}
