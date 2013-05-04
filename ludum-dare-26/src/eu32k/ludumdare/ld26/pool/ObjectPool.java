package eu32k.ludumdare.ld26.pool;

import java.util.ArrayList;

public abstract class ObjectPool<T extends IObjectPoolItem> {
   ArrayList<T> items;

   public ObjectPool() {
      items = new ArrayList<T>();
   }

   protected abstract T instanceItem();

   public void preload(int amount) {
      while (amount-- > 0) {
         items.add(instanceItem());
      }
   }

   public void preloadTo(int size) {
      preload(size - size());
      System.out.println("PoolSize: " + items.size());
   }

   public int size() {
      return items.size();
   }

   public void setInUseForAll(boolean inUse) {
      for (T item : items) {
         item.setInUse(inUse);
      }
   }

   public T getFreeItem() {
      for (T item : items) {
         if (!item.isInUse()) {
            item.setInUse(true);
            return item;
         }
      }
      T newItem = instanceItem();
      newItem.setInUse(true);
      items.add(newItem);
      System.out.println("PoolSize: " + items.size());
      return newItem;
   }

   public void add(T fade) {
      items.add(fade);
      System.out.println("PoolSize: " + items.size());
   }

   public void clear() {
      items.clear();
   }
}