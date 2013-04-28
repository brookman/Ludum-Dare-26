package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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

   public void draw() {
      backgroundShader.begin();
      backgroundShader.renderToQuad(null, true, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      backgroundShader.end();
   }

}
