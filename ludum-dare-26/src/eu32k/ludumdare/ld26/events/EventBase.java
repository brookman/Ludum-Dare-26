package eu32k.ludumdare.ld26.events;

public class EventBase implements IEvent{
   private float time;

   @Override
   public void update(float delta) {
      time -= delta;
   }

   @Override
   public float timeLeft() {
      return time;
   }
}
