package eu32k.ludumdare.ld26.rendering;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import eu32k.libgdx.rendering.MultiPassRenderer;
import eu32k.libgdx.rendering.Renderer;
import eu32k.libgdx.rendering.TextureRenderer;
import eu32k.ludumdare.ld26.Player;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;

public class MainRenderer {

   private SpriteBatch batch;
   private SpriteBatch hudBatch;
   private ShapeRenderer debugRenderer;
   private RunText text;
   private BitmapFont fps;

   private MultiPassRenderer multiPass;

   public MainRenderer() {
      batch = new SpriteBatch();
      hudBatch = new SpriteBatch();
      debugRenderer = new ShapeRenderer();
      text = new RunText("Welcome to the super minimalistic labyrinth game!           yay! :D", 5.0f);
      fps = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"), Gdx.files.internal("fonts/calibri.png"), false);

      ShaderProgram verticalBlur = new ShaderProgram(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/blur_v.fsh").readString());
      ShaderProgram horizontalBlur = new ShaderProgram(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/blur_h.fsh").readString());

      List<Renderer> renderStack = new ArrayList<Renderer>();
      renderStack.add(new TextureRenderer(400, 300, verticalBlur));
      renderStack.add(new TextureRenderer(400, 300, horizontalBlur));
      multiPass = new MultiPassRenderer(renderStack);
   }

   public void render(float delta, Camera camera, Level level, List<Tile> tiles, Player player, Color color) {
      // multiPass.begin();

      Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      debugRenderer.setProjectionMatrix(camera.combined);
      debugRenderer.begin(ShapeType.FilledRectangle);
      debugRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
      for (Tile tile : tiles) {
         for (Rectangle rect : tile.getBounds()) {
            debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
         }
      }
      debugRenderer.end();

      // debugRenderer.begin(ShapeType.FilledRectangle);
      // debugRenderer.setColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
      // Vector3 p = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
      // camera.unproject(p);
      // debugRenderer.filledRect(p.x, p.y, 10.0f, 10.0f);
      // debugRenderer.end();

      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      for (Tile tile : tiles) {
         tile.getSprite().setColor(color);
         tile.draw(batch);
      }
      player.getSprite().setColor(color);
      player.draw(batch);

      batch.end();

      hudBatch.begin();
      text.draw(hudBatch, 30.0f, 50.0f);
      fps.draw(hudBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 30.0f, Gdx.graphics.getHeight() - 30.0f);
      fps.draw(hudBatch, DebugText.text == null ? "null" : DebugText.text, 30.0f, Gdx.graphics.getHeight() - 60.0f);
      hudBatch.end();

      // multiPass.endAndRender();
   }

   public void dispose() {
      batch.dispose();
   }

}
