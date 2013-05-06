package eu32k.ludumdare.ld26;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
      cfg.useGL20 = true;

      Config.SHOW_BACKGROUND = false;
      Config.SHOW_PARTICLES = true;
      Config.SHOW_PARTICLES_GLOW = true;

      initialize(new LudumDare26(), cfg);
   }
}