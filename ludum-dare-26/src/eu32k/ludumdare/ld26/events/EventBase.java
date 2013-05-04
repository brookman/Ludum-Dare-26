package eu32k.ludumdare.ld26.events;

import eu32k.ludumdare.ld26.pool.IObjectPoolItem;

public class EventBase implements IEvent , IObjectPoolItem{
   private float time;
   private boolean inUse;

   protected void setTime(float time) {
      this.time = time;
   }

   @Override
   public void update(float delta) {
      time -= delta;
   }

   @Override
   public float timeLeft() {
      return time;
   }

   @Override
   public boolean isInUse() {
      return inUse;
   }

   @Override
   public void setInUse(boolean inUse) {
      this.inUse = inUse;
   }
}
