package eu32k.ludumdare.ld26.events;

import eu32k.ludumdare.ld26.pool.IObjectPoolItem;

public interface IEvent extends IObjectPoolItem {
   public void update(float delta);
   public float timeLeft();
}
