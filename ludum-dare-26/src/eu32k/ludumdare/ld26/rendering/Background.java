package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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

   public void draw(Vector3 color, boolean bg) {
      draw(color, bg, Time.getTime());
   }

   public void draw(Vector3 color, boolean bg, float time) {
      backgroundShader.begin();
      backgroundShader.setUniformf("color", color);
      backgroundShader.setUniformf("bg", bg ? 0.0f : 1.0f);
      backgroundShader.setUniformf("timeFixed", time);
      backgroundShader.renderToQuad(null, true, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      backgroundShader.end();
   }

}
