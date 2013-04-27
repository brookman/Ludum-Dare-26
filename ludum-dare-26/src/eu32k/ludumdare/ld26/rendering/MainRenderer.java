package eu32k.ludumdare.ld26.rendering;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import eu32k.ludumdare.ld26.Player;
import eu32k.ludumdare.ld26.Tile;
import eu32k.ludumdare.ld26.level.Level;

public class MainRenderer {

   private SpriteBatch batch;
   private ShapeRenderer debugRenderer;

   public MainRenderer() {
      batch = new SpriteBatch();
      debugRenderer = new ShapeRenderer();
   }

   public void render(float delta, Camera camera, Level level, List<Tile> tiles, Player player, Color color) {
      Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      debugRenderer.setProjectionMatrix(camera.combined);
      debugRenderer.begin(ShapeType.FilledRectangle);
      debugRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
      for (Tile tile : tiles) {
         for (Rectangle rect : tile.getBounds()) {
            debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
         }
      }
      debugRenderer.end();

      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      for (Tile tile : tiles) {
         tile.getSprite().setColor(color);
         tile.draw(batch);
      }
      player.draw(batch);

      batch.end();

   }

   public void dispose() {
      batch.dispose();
   }

}
