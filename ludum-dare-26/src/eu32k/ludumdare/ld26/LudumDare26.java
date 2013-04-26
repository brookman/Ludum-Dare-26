package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu32k.libgdx.SimpleGame;
import eu32k.libgdx.rendering.Textures;

public class LudumDare26 extends SimpleGame {

   private SpriteBatch batch;
   private Texture texture;
   private Sprite sprite;

   public LudumDare26() {
      super(false);
   }

   @Override
   public void init() {
      batch = new SpriteBatch();

      texture = Textures.get("textures/libgdx.png");

      sprite = new Sprite(texture);
      sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
      sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
      sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
   }

   @Override
   public void draw(float delta) {
      Gdx.gl.glClearColor(1, 0, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      batch.setProjectionMatrix(camera.combined);
      batch.begin();
      sprite.draw(batch);
      batch.end();
   }

   @Override
   public void dispose() {
      super.dispose();
      batch.dispose();
      texture.dispose();
   }
}
