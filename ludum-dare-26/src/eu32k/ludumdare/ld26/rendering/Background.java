package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import eu32k.libgdx.common.Time;

public class Background {

   private static Background instance;

   public static Background getInstance() {
      if (instance == null) {
         instance = new Background();
      }
      return instance;
   }

   AdvancedShader backgroundShader;

   private Background() {
      backgroundShader = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/background.fsh").readString());
   }

   public void draw(Color color, boolean bg) {
      draw(color, bg, Time.getTime());
   }

   public void draw(Color color, boolean bg, float time) {
      backgroundShader.begin();
      backgroundShader.setUniformf("color", color.r, color.g, color.b);
      backgroundShader.setUniformf("bg", bg ? 0.0f : 1.0f);
      backgroundShader.setUniformf("timeFixed", time);
      backgroundShader.renderToQuad(null, true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      backgroundShader.end();
   }

}
