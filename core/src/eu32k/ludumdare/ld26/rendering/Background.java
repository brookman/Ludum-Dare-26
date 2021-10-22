package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import eu32k.libgdx.common.Time;
import eu32k.libgdx.rendering.AdvancedShader;
import eu32k.ludumdare.ld26.Config;

public class Background {

   private static AdvancedShader backgroundShader;

   public static void draw(Color color, boolean bg) {
      draw(color, bg, Time.getTime());
   }

   public static void draw(Color color, boolean bg, float time) {
      if (!Config.SHOW_BACKGROUND) {
         return;
      }
      if (backgroundShader == null) {
         backgroundShader = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/background.fsh").readString());
      }

      backgroundShader.begin();
      backgroundShader.setUniformf("color", color.r, color.g, color.b);
      backgroundShader.setUniformf("bg", bg ? 0.0f : 1.0f);
      backgroundShader.setUniformf("timeFixed", time);
      backgroundShader.renderToScreeQuad(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
      backgroundShader.end();
   }
}
