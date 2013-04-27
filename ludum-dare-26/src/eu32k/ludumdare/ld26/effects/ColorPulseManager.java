package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.graphics.Color;


public class ColorPulseManager {
   public final static IntensityData TEST_01 = new IntensityData(new float[] { 0.2f, 0.5f, 1f, 0.7f }, 0.180f);
   public final static IntensityData TEST_02 = new IntensityData(new float[] { 0.7f, 0.75f, 8f, 0.85f, 0.95f, 1f, 0.95f, 0.85f, 0.8f, 0.75f }, 0.0200f);

   public final static IntensityData INTENSITY_BEAT = new IntensityData(new float[] { 1.000000f, 1.000000f, 0.760000f, 0.450000f, 0.330000f, 0.240000f, 0.200000f, 0.170000f, 0.150000f, 0.130000f,
         0.110000f, 0.100000f, 0.090000f, 0.080000f, 0.080000f, 0.070000f, 0.060000f, 0.050000f, 0.050000f, 0.040000f, 0.030000f, 0.030000f, 0.020000f, 0.020000f, 0.010000f, 0.010000f, 0.000000f,
         0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f,
         0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.760000f, 0.760000f, 0.750000f, 0.410000f, 0.320000f, 0.230000f, 0.160000f, 0.110000f, 0.050000f, 0.000000f,
         0.000000f, 0.000000f, 1.000000f, 1.000000f, 0.760000f, 0.450000f, 0.330000f, 0.240000f, 0.200000f, 0.170000f, 0.150000f, 0.130000f, 0.110000f, 0.100000f, 0.090000f, 0.080000f, 0.080000f,
         0.070000f, 0.060000f, 0.050000f, 0.050000f, 0.040000f, 0.030000f, 0.030000f, 0.020000f, 0.020000f, 0.010000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f,
         0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.760000f, 0.760000f, 0.750000f, 0.410000f, 0.320000f, 0.230000f, 0.160000f, 0.110000f, 0.050000f, 0.000000f, 0.000000f,
         0.000000f, 1.000000f, 1.000000f, 0.760000f, 0.450000f, 0.330000f, 0.240000f, 0.200000f, 0.170000f, 0.150000f, 0.130000f, 0.110000f, 0.100000f, 0.090000f, 0.080000f, 0.080000f, 0.070000f,
         0.060000f, 0.050000f, 0.050000f, 0.040000f, 0.030000f, 0.030000f, 0.020000f, 0.020000f, 0.010000f, 0.760000f, 0.760000f, 0.750000f, 0.410000f, 0.320000f, 0.230000f, 0.160000f, 0.110000f,
         0.050000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f,
         1.000000f, 1.000000f, 0.760000f, 0.450000f, 0.330000f, 0.240000f, 0.200000f, 0.170000f, 0.150000f, 0.130000f, 0.110000f, 0.100000f, 0.090000f, 0.080000f, 0.080000f, 0.070000f, 0.060000f,
         0.050000f, 0.050000f, 0.040000f, 0.030000f, 0.030000f, 0.020000f, 0.020000f, 0.010000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f,
         0.000000f, 0.000000f, 0.000000f }, 0.03f);

   public final static IntensityData TEST_03 = new IntensityData(new float[] { 0.220000f, 0.230000f, 0.230000f, 0.240000f, 0.250000f, 0.250000f, 0.270000f, 0.280000f, 0.290000f, 0.300000f, 0.320000f,
         0.320000f, 0.340000f, 0.350000f, 0.360000f, 0.380000f, 0.380000f, 0.410000f, 0.420000f, 0.430000f, 0.450000f, 0.460000f, 0.470000f, 0.480000f, 0.490000f, 0.500000f, 0.510000f, 0.520000f,
         0.530000f, 0.530000f, 0.540000f, 0.550000f, 0.550000f, 0.560000f, 0.560000f, 0.570000f, 0.570000f, 0.570000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f,
         0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.570000f, 0.570000f, 0.570000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.550000f, 0.540000f, 0.540000f, 0.530000f, 0.520000f, 0.510000f,
         0.500000f, 0.500000f, 0.490000f, 0.480000f, 0.480000f, 0.470000f, 0.450000f, 0.450000f, 0.440000f, 0.440000f, 0.420000f, 0.410000f, 0.400000f, 0.390000f, 0.370000f, 0.360000f, 0.350000f,
         0.330000f, 0.320000f, 0.310000f, 0.300000f, 0.290000f, 0.290000f, 0.280000f, 0.270000f, 0.270000f, 0.260000f, 0.250000f, 0.250000f, 0.240000f, 0.240000f, 0.240000f, 0.230000f, 0.230000f,
         0.230000f, 0.220000f, 0.220000f, 0.220000f, 0.220000f, 0.220000f, 0.230000f, 0.230000f, 0.230000f, 0.240000f, 0.240000f, 0.250000f, 0.250000f, 0.260000f, 0.270000f, 0.270000f, 0.270000f,
         0.280000f, 0.290000f, 0.300000f, 0.310000f, 0.320000f, 0.320000f, 0.330000f, 0.340000f, 0.340000f, 0.350000f, 0.360000f, 0.370000f, 0.380000f, 0.380000f, 0.390000f, 0.400000f, 0.410000f,
         0.420000f, 0.430000f, 0.440000f, 0.450000f, 0.460000f, 0.470000f, 0.480000f, 0.490000f, 0.500000f, 0.500000f, 0.510000f, 0.520000f, 0.520000f, 0.530000f, 0.540000f, 0.550000f, 0.550000f,
         0.550000f, 0.560000f, 0.560000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.560000f, 0.560000f,
         0.560000f, 0.560000f, 0.550000f, 0.550000f, 0.550000f, 0.550000f, 0.550000f, 0.540000f, 0.540000f, 0.530000f, 0.530000f, 0.530000f, 0.520000f, 0.520000f, 0.510000f, 0.500000f, 0.490000f,
         0.490000f, 0.480000f, 0.480000f, 0.470000f, 0.460000f, 0.460000f, 0.450000f, 0.440000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f, 0.430000f,
         0.430000f, 0.430000f }, 0.03f);

   public final static IntensityData INTENSITY_FULL = new IntensityData(new float[] { 1f }, 1);
   public final static IntensityData INTENSITY_EMPTY = new IntensityData(new float[] { 0f }, 1);

   private Color currentColor;
   private IntensityData beatIntensity;
   private IntensityData songIntensity;
   private Color mainColor;

   public ColorPulseManager() {
      currentColor = new Color();
   }

   public void init(IntensityData beatIntensity, IntensityData songIntensity, Color mainColor) {
      stop();
      if (beatIntensity == null || songIntensity == null || mainColor == null)
         return;
      this.mainColor = mainColor;
      this.beatIntensity = beatIntensity;
      this.songIntensity = songIntensity;

      updateColor();

   }

   public void stop() {
      currentColor.set(1f, 1f, 1f, 1f);
      beatIntensity = null;
      songIntensity = null;
   }

   public void update(float delta) {
      if (beatIntensity == null || songIntensity == null)
         return;

      if (beatIntensity.update(delta) || songIntensity.update(delta)) {
         updateColor();
      }
   }

   private void updateColor() {
      float songValue = songIntensity.getValue();
      float beatValue = beatIntensity.getValue();

      currentColor.r = mainColor.r * songValue;
      currentColor.g = mainColor.g * songValue;
      currentColor.b = mainColor.b * songValue;

      currentColor.r = fillToOne(currentColor.r, beatValue);
      currentColor.g = fillToOne(currentColor.g, beatValue);
      currentColor.b = fillToOne(currentColor.b, beatValue);

   }

   private float fillToOne(float currentValue, float intensity) {
      return currentValue + (1f - currentValue) * intensity;
   }

   public Color getCurrentColor() {

      return currentColor;
   }
}
