package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu32k.ludumdare.ld26.Player;
import eu32k.ludumdare.ld26.Tile2;
import eu32k.ludumdare.ld26.level.Level;

public class MainRenderer {

   private SpriteBatch batch;

   public MainRenderer() {
      batch = new SpriteBatch();
   }

   public void render(float delta, Camera camera, Level level, Tile2[][] tiles, Player player, Color color) {
      Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      for (Tile2[] tile1 : tiles) {
         for (Tile2 tile : tile1) {
            tile.getSprite().setColor(color);
            tile.draw(batch);
         }
      }
      player.draw(batch);

      batch.end();
   }

   public void dispose() {
      batch.dispose();
   }

}
