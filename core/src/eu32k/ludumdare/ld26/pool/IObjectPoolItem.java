package eu32k.ludumdare.ld26.pool;

public interface IObjectPoolItem {
   public boolean isInUse();
   public void setInUse(boolean inUse);
}
