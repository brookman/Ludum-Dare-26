package eu32k.ludumdare.ld26;

import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
   public static void main(String[] args) {

      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "Ludum Dare 26";
      cfg.useGL20 = true;
      cfg.useCPUSynch = true;
      cfg.samples = 4;
      cfg.vSyncEnabled = true;
      // cfg.resizable = false;
      // cfg.addIcon("textures/icon_small.png", FileType.Local);

      int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
      int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
      if (args.length == 1 && args[0].equals("-fullscreen")) {
         cfg.fullscreen = true;
         width = Toolkit.getDefaultToolkit().getScreenSize().width;
         height = Toolkit.getDefaultToolkit().getScreenSize().height;
      }
      cfg.width = width;
      cfg.height = height;

      LwjglApplication app = new LwjglApplication(new LudumDare26(), cfg);
   }
}
