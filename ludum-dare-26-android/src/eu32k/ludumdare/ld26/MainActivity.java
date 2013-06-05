package eu32k.ludumdare.ld26;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import eu32k.libgdx.common.IOWriter;
import eu32k.libgdx.common.ProfileService;

public class MainActivity extends AndroidApplication implements IOWriter{
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

      AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
      cfg.useGL20 = true;

      Config.SHOW_BACKGROUND = false;
      Config.SHOW_PARTICLES = true;
      Config.SHOW_PARTICLES_GLOW = true;

      initialize(new LudumDare26(new ProfileService(this, "profile")), cfg);
      
   }
   @Override
   public void writeFile(String filename, String content) throws Exception{
		FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
		fos.write(content.getBytes());
		fos.close();
   }
   @Override
   public String readFile(String filename) throws Exception{
	   FileInputStream fis = openFileInput(filename);
	   String content = convertStreamToString(fis);
	   fis.close();
	   return content;
   }
   public static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}