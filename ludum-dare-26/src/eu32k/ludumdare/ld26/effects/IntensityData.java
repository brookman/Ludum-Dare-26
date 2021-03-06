package eu32k.ludumdare.ld26.effects;

public class IntensityData {
   private float[] intensity;
   private int index;
   private int length;
   private float stepSize;
   private float offset;

   public IntensityData(float stepSize, float[] data) {
      this.index = 0;
      this.offset = 0;
      this.stepSize = stepSize;
      this.intensity = data;
      this.length = this.intensity.length;
   }

   public boolean update(float delta) {
      offset += delta;
      boolean hasChanged = false;
      while (offset >= stepSize) {
         index++;
         offset -= stepSize;
         hasChanged = true;
      }
      while (index >= length) {
         index -= length;
      }
      return hasChanged;
   }

   public float getValue() {
      return intensity[index];
   }

   public boolean setTime(float ms) {
      int oldIndex = index;
      index = (int)(ms   / stepSize);
      while (index >= length) {
         index -= length;
      }
      return index != oldIndex;
   }
}
