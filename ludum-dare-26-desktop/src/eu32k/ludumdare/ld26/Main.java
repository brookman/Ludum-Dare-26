package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
   public static void main(String[] args) {

      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "IMBARINTH";
      cfg.useGL20 = true;
      cfg.samples = 4;
      cfg.vSyncEnabled = true;
      // cfg.resizable = false;
      cfg.addIcon("textures/icon.png", FileType.Local);

      // int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
      // int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
      // if (args.length == 1 && args[0].equals("-fullscreen")) {
      // cfg.fullscreen = true;
      // width = Toolkit.getDefaultToolkit().getScreenSize().width;
      // height = Toolkit.getDefaultToolkit().getScreenSize().height;
      // }
      // cfg.width = width;
      // cfg.height = height;

      cfg.width = Config.X_RESOLUTION;
      cfg.height = Config.Y_RESOLUTION;

      LwjglApplication app = new LwjglApplication(new LudumDare26(), cfg);
   }
}
