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

   public final static IntensityData INTENSITY_BITBREAK = new IntensityData(new float[] { 0.023333f, 0.026667f, 0.030000f, 0.036667f, 0.043333f, 0.050000f, 0.060000f, 0.070000f, 0.080000f, 0.096667f,
         0.103333f, 0.113333f, 0.120000f, 0.130000f, 0.136667f, 0.143333f, 0.153333f, 0.163333f, 0.180000f, 0.190000f, 0.193333f, 0.196667f, 0.200000f, 0.210000f, 0.216667f, 0.220000f, 0.230000f,
         0.233333f, 0.246667f, 0.250000f, 0.250000f, 0.250000f, 0.253333f, 0.260000f, 0.263333f, 0.280000f, 0.283333f, 0.290000f, 0.296667f, 0.306667f, 0.316667f, 0.330000f, 0.346667f, 0.363333f,
         0.370000f, 0.376667f, 0.386667f, 0.396667f, 0.400000f, 0.410000f, 0.416667f, 0.426667f, 0.436667f, 0.440000f, 0.446667f, 0.453333f, 0.456667f, 0.456667f, 0.466667f, 0.470000f, 0.483333f,
         0.496667f, 0.550000f, 0.553333f, 0.556667f, 0.560000f, 0.563333f, 0.566667f, 0.566667f, 0.573333f, 0.573333f, 0.573333f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f,
         0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.573333f, 0.573333f, 0.573333f, 0.573333f, 0.573333f,
         0.570000f, 0.570000f, 0.570000f, 0.566667f, 0.566667f, 0.563333f, 0.563333f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f,
         0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f,
         0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.560000f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.566667f, 0.566667f,
         0.566667f, 0.570000f, 0.570000f, 0.570000f, 0.573333f, 0.573333f, 0.573333f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.576667f, 0.580000f, 0.580000f, 0.580000f, 0.580000f,
         0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f,
         0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f,
         0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.580000f, 0.576667f, 0.576667f, 0.576667f, 0.573333f, 0.573333f, 0.573333f, 0.573333f, 0.573333f, 0.573333f, 0.570000f, 0.570000f, 0.570000f,
         0.570000f, 0.570000f, 0.570000f, 0.566667f, 0.566667f, 0.566667f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f,
         0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.563333f, 0.566667f, 0.566667f, 0.566667f, 0.566667f, 0.566667f, 0.566667f, 0.566667f, 0.566667f,
         0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.570000f, 0.573333f, 0.573333f, 0.573333f, 0.576667f, 0.580000f, 0.580000f, 0.580000f, 0.583333f,
         0.583333f, 0.586667f, 0.586667f, 0.586667f, 0.590000f, 0.590000f, 0.593333f, 0.593333f, 0.593333f, 0.593333f, 0.593333f, 0.596667f, 0.596667f, 0.596667f, 0.596667f, 0.596667f, 0.596667f,
         0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.610000f,
         0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f,
         0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.610000f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f,
         0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.606667f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f,
         0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.603333f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f, 0.600000f,
         0.600000f, 0.596667f, 0.596667f, 0.593333f, 0.593333f, 0.593333f, 0.593333f, 0.593333f, 0.593333f, 0.596667f, 0.596667f, 0.596667f, 0.600000f, 0.600000f, 0.600000f, 0.603333f, 0.603333f,
         0.606667f, 0.610000f, 0.610000f, 0.610000f, 0.613333f, 0.613333f, 0.613333f, 0.613333f, 0.613333f, 0.613333f, 0.613333f, 0.616667f, 0.616667f, 0.616667f, 0.616667f, 0.620000f, 0.620000f,
         0.623333f, 0.623333f, 0.626667f, 0.626667f, 0.626667f, 0.626667f, 0.626667f, 0.626667f, 0.626667f, 0.626667f, 0.630000f, 0.630000f, 0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.633333f,
         0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.633333f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f,
         0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f,
         0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f,
         0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.636667f, 0.640000f, 0.640000f, 0.640000f,
         0.643333f, 0.643333f, 0.643333f, 0.643333f, 0.643333f, 0.643333f, 0.646667f, 0.646667f, 0.646667f, 0.646667f, 0.650000f, 0.650000f, 0.650000f, 0.650000f, 0.650000f, 0.650000f, 0.650000f,
         0.653333f, 0.653333f, 0.653333f, 0.656667f, 0.656667f, 0.656667f, 0.656667f, 0.660000f, 0.663333f, 0.663333f, 0.663333f, 0.666667f, 0.666667f, 0.670000f, 0.670000f, 0.673333f, 0.673333f,
         0.676667f, 0.680000f, 0.680000f, 0.680000f, 0.683333f, 0.683333f, 0.686667f, 0.686667f, 0.686667f, 0.686667f, 0.693333f, 0.693333f, 0.693333f, 0.696667f, 0.696667f, 0.696667f, 0.700000f,
         0.703333f, 0.703333f, 0.706667f, 0.706667f, 0.710000f, 0.710000f, 0.710000f, 0.713333f, 0.713333f, 0.713333f, 0.716667f, 0.720000f, 0.720000f, 0.720000f, 0.723333f, 0.726667f, 0.726667f,
         0.726667f, 0.730000f, 0.733333f, 0.733333f, 0.736667f, 0.736667f, 0.736667f, 0.740000f, 0.740000f, 0.743333f, 0.743333f, 0.743333f, 0.743333f, 0.743333f, 0.746667f, 0.746667f, 0.746667f,
         0.746667f, 0.750000f, 0.750000f, 0.750000f, 0.750000f, 0.750000f, 0.750000f, 0.753333f, 0.753333f, 0.756667f, 0.756667f, 0.756667f, 0.756667f, 0.760000f, 0.763333f, 0.763333f, 0.763333f,
         0.766667f, 0.766667f, 0.770000f, 0.770000f, 0.773333f, 0.773333f, 0.776667f, 0.776667f, 0.776667f, 0.776667f, 0.780000f, 0.783333f, 0.786667f, 0.786667f, 0.786667f, 0.786667f, 0.786667f,
         0.786667f, 0.790000f, 0.790000f, 0.790000f, 0.790000f, 0.793333f, 0.796667f, 0.796667f, 0.796667f, 0.800000f, 0.800000f, 0.803333f, 0.806667f, 0.806667f, 0.806667f, 0.810000f, 0.810000f,
         0.813333f, 0.813333f, 0.816667f, 0.816667f, 0.820000f, 0.820000f, 0.820000f, 0.820000f, 0.823333f, 0.823333f, 0.826667f, 0.826667f, 0.830000f, 0.830000f, 0.833333f, 0.836667f, 0.836667f,
         0.836667f, 0.840000f, 0.843333f, 0.846667f, 0.846667f, 0.846667f, 0.850000f, 0.853333f, 0.853333f, 0.853333f, 0.856667f, 0.860000f, 0.860000f, 0.863333f, 0.863333f, 0.863333f, 0.866667f,
         0.866667f, 0.866667f, 0.870000f, 0.870000f, 0.873333f, 0.873333f, 0.873333f, 0.876667f, 0.876667f, 0.880000f, 0.880000f, 0.880000f, 0.883333f, 0.886667f, 0.890000f, 0.890000f, 0.890000f,
         0.893333f, 0.893333f, 0.893333f, 0.896667f, 0.900000f, 0.900000f, 0.900000f, 0.900000f, 0.900000f, 0.903333f, 0.906667f, 0.906667f, 0.906667f, 0.910000f, 0.913333f, 0.916667f, 0.916667f,
         0.916667f, 0.916667f, 0.920000f, 0.923333f, 0.923333f, 0.923333f, 0.923333f, 0.923333f, 0.926667f, 0.926667f, 0.926667f, 0.926667f, 0.926667f, 0.930000f, 0.930000f, 0.930000f, 0.930000f,
         0.930000f, 0.930000f, 0.930000f, 0.930000f, 0.930000f, 0.930000f, 0.930000f, 0.930000f, 0.933333f, 0.933333f, 0.936667f, 0.936667f, 0.940000f, 0.940000f, 0.943333f, 0.943333f, 0.946667f,
         0.946667f, 0.946667f, 0.950000f, 0.950000f, 0.950000f, 0.950000f, 0.953333f, 0.953333f, 0.953333f, 0.953333f, 0.956667f, 0.956667f, 0.960000f, 0.963333f, 0.963333f, 0.966667f, 0.966667f,
         0.966667f, 0.970000f, 0.970000f, 0.973333f, 0.976667f, 0.976667f, 0.980000f, 0.983333f, 0.983333f, 0.986667f, 0.990000f, 0.993333f, 0.996667f, 1.000000f, 1.000000f, 1.000000f, 1.000000f,
         1.000000f, 1.000000f, 1.000000f, 1.000000f, 0.996667f, 0.996667f, 0.993333f, 0.990000f, 0.986667f, 0.986667f, 0.983333f, 0.980000f, 0.970000f, 0.966667f, 0.963333f, 0.953333f, 0.950000f,
         0.946667f, 0.943333f, 0.940000f, 0.930000f, 0.923333f, 0.920000f, 0.913333f, 0.910000f, 0.906667f, 0.903333f, 0.900000f, 0.896667f, 0.893333f, 0.893333f, 0.893333f, 0.893333f, 0.893333f,
         0.893333f, 0.893333f, 0.893333f, 0.893333f, 0.893333f, 0.893333f, 0.893333f, 0.896667f, 0.900000f, 0.900000f, 0.900000f, 0.903333f, 0.910000f, 0.913333f, 0.913333f, 0.916667f, 0.916667f,
         0.920000f, 0.923333f, 0.926667f, 0.930000f, 0.933333f, 0.936667f, 0.936667f, 0.940000f, 0.946667f, 0.950000f, 0.950000f, 0.953333f, 0.960000f, 0.963333f, 0.966667f, 0.966667f, 0.970000f,
         0.973333f, 0.976667f, 0.980000f, 0.980000f, 0.986667f, 0.986667f, 0.990000f, 0.993333f, 0.993333f, 0.993333f, 0.993333f, 0.993333f, 0.993333f, 0.990000f, 0.986667f, 0.980000f, 0.976667f,
         0.970000f, 0.966667f, 0.956667f, 0.953333f, 0.936667f, 0.930000f, 0.916667f, 0.910000f, 0.903333f, 0.893333f, 0.886667f, 0.880000f, 0.866667f, 0.856667f, 0.853333f, 0.843333f, 0.840000f,
         0.836667f, 0.833333f, 0.826667f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.823333f, 0.826667f, 0.826667f, 0.830000f, 0.833333f,
         0.833333f, 0.843333f, 0.846667f, 0.860000f, 0.876667f, 0.880000f, 0.886667f, 0.896667f, 0.900000f, 0.903333f, 0.906667f, 0.913333f, 0.920000f, 0.923333f, 0.926667f, 0.933333f, 0.936667f,
         0.943333f, 0.946667f, 0.950000f, 0.953333f, 0.953333f, 0.956667f, 0.960000f, 0.963333f, 0.966667f, 0.970000f, 0.970000f, 0.973333f, 0.976667f, 0.976667f, 0.976667f, 0.980000f, 0.980000f,
         0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.980000f, 0.976667f, 0.973333f,
         0.973333f, 0.970000f, 0.966667f, 0.966667f, 0.960000f, 0.960000f, 0.956667f, 0.950000f, 0.950000f, 0.946667f, 0.946667f, 0.943333f, 0.940000f, 0.936667f, 0.933333f, 0.930000f, 0.926667f,
         0.923333f, 0.920000f, 0.916667f, 0.910000f, 0.903333f, 0.896667f, 0.886667f, 0.880000f, 0.873333f, 0.870000f, 0.856667f, 0.853333f, 0.840000f, 0.823333f, 0.820000f, 0.810000f, 0.796667f,
         0.790000f, 0.783333f, 0.776667f, 0.766667f, 0.740000f, 0.723333f, 0.713333f, 0.690000f, 0.663333f, 0.636667f, 0.630000f, 0.606667f, 0.580000f, 0.550000f, 0.523333f, 0.513333f, 0.443333f,
         0.420000f, 0.410000f, 0.380000f, 0.373333f, 0.333333f, 0.330000f, 0.320000f, 0.303333f, 0.296667f, 0.286667f, 0.276667f, 0.273333f, 0.203333f, 0.173333f, 0.170000f, 0.166667f, 0.153333f,
         0.143333f, 0.140000f, 0.126667f, 0.120000f }, 0.078f);
   // , 0.078f);

   public final static IntensityData INTENSITY_FULL = new IntensityData(new float[] { 1f }, 1);
   public final static IntensityData INTENSITY_EMPTY = new IntensityData(new float[] { 0f }, 1);

   private Color currentColor;
   private IntensityData beatIntensity;
   private IntensityData songIntensity;
   private Color mainColor;

   private float minSongIntensity;

   public ColorPulseManager() {
      setMinSongIntensity(0f);
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

      beatValue *= songValue;

      float minimumOffset = 1f - minSongIntensity;
      if (minSongIntensity > 0) {
         songValue = songValue * minimumOffset + minSongIntensity;
      }
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

   public float getMinSongIntensity() {
      return minSongIntensity;
   }

   public void setMinSongIntensity(float minSongIntensity) {
      this.minSongIntensity = minSongIntensity;
      if (this.minSongIntensity < 0)
         this.minSongIntensity = 0;
      if (this.minSongIntensity > 1)
         this.minSongIntensity = 1;
   }
}
