package eu32k.ludumdare.ld26.pool;

import java.util.ArrayList;

public abstract class ObjectPool <T extends IObjectPoolItem>{
   ArrayList<T> items;
   
   public ObjectPool(){
      items = new ArrayList<T>();
   }
   
   protected abstract T instanceItem();
   
   public void preload(int amount){
      while(amount-- > 0){
         items.add(instanceItem());
      }
   }
   
   public T getFreeItem(){
      for(T item : items){
         if(!item.isInUse()){
            item.setInUse(true);
            return item;
         }
      }
      T newItem = instanceItem();
      newItem.setInUse(true);
      items.add(newItem);
      return newItem;
   }
   
   public void add(T fade) {
      items.add(fade);
   }


   public void clear(){
      items.clear();
   }
}