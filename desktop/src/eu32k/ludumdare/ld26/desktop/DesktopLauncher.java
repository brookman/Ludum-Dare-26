package eu32k.ludumdare.ld26.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu32k.ludumdare.ld26.Config;
import eu32k.ludumdare.ld26.LudumDare26;

public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = "IMBARINTH";
    cfg.useGL30 = true;
    cfg.samples = 4;
    cfg.vSyncEnabled = true;
    // cfg.resizable = false;
    cfg.addIcon("textures/icon.png", Files.FileType.Local);

    cfg.width = Config.X_RESOLUTION;
    cfg.height = Config.Y_RESOLUTION;

    new LwjglApplication(new LudumDare26(), cfg);
  }
}
