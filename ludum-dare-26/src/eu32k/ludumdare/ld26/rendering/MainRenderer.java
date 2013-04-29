package eu32k.ludumdare.ld26.rendering;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import eu32k.ludumdare.ld26.Config;
import eu32k.ludumdare.ld26.MultiLayerSprite;
import eu32k.ludumdare.ld26.Player;
import eu32k.ludumdare.ld26.level.Tile;

public class MainRenderer {

   private SpriteBatch batch;
   private SpriteBatch hudBatch;
   private ShapeRenderer debugRenderer;
   private RunText text;
   private BitmapFont fps;

   private FrameBuffer mainBuffer;
   private FrameBuffer secondaryBuffer;

   private FrameBuffer blurBuffer1;
   private AdvancedShader verticalBlur;

   private FrameBuffer blurBuffer2;
   private AdvancedShader horizontalBlur;
   private TextConsole console;
   private AdvancedShader mixerShader;
   private BitmapFont consolasFont;

   private AdvancedShader background;

   public MainRenderer() {
      batch = new SpriteBatch();
      hudBatch = new SpriteBatch();
      debugRenderer = new ShapeRenderer();
      text = new RunText("Welcome to the super minimalistic labyrinth game! yay! :D", 5.0f);
      fps = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"), Gdx.files.internal("fonts/calibri.png"), false);
      consolasFont = new BitmapFont(Gdx.files.internal("fonts/consolas.fnt"), Gdx.files.internal("fonts/consolas.png"), false);
      console = new TextConsole(consolasFont, 160.0f, Gdx.graphics.getHeight() - 10.0f, 15f, 5, Color.WHITE);
      int xScaleDown = Config.X_RESOLUTION / 4;
      int yScaleDown = Config.Y_RESOLUTION / 4;

      mainBuffer = SomeRenderer.makeFrameBuffer(Config.X_RESOLUTION, Config.Y_RESOLUTION);
      secondaryBuffer = SomeRenderer.makeFrameBuffer(xScaleDown, yScaleDown);
      mixerShader = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/mixer.fsh").readString());

      blurBuffer1 = SomeRenderer.makeFrameBuffer(xScaleDown, yScaleDown);
      verticalBlur = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/blur_v.fsh").readString());

      blurBuffer2 = SomeRenderer.makeFrameBuffer(xScaleDown, yScaleDown);
      horizontalBlur = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/blur_h.fsh").readString());

      background = new AdvancedShader(Gdx.files.internal("shaders/simple.vsh").readString(), Gdx.files.internal("shaders/background.fsh").readString());
      System.out.println(background.getLog());
   }

   public void render(float delta, Camera camera, List<Tile> tiles, Player player, Color color) {
      mainBuffer.begin();

      render(true, camera, tiles, player, color);
      // renderDebug(camera, tiles);

      hudBatch.begin();
      text.draw(hudBatch, 30.0f, 50.0f);
      fps.draw(hudBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 30.0f, Gdx.graphics.getHeight() - 30.0f);
      fps.draw(hudBatch, DebugText.text == null ? "null" : DebugText.text, 30.0f, Gdx.graphics.getHeight() - 60.0f);
      console.draw(hudBatch);
      hudBatch.end();

      mainBuffer.end();

      secondaryBuffer.begin();
      render(false, camera, tiles, player, color);
      secondaryBuffer.end();

      Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
      secondaryBuffer.getColorBufferTexture().bind();
      verticalBlur.begin();
      verticalBlur.setUniformi("uTexture", 0);
      verticalBlur.renderToQuad(blurBuffer1);

      Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
      blurBuffer1.getColorBufferTexture().bind();
      horizontalBlur.begin();
      horizontalBlur.setUniformi("uTexture", 0);
      horizontalBlur.renderToQuad(blurBuffer2);

      // --

      Gdx.gl.glActiveTexture(GL20.GL_TEXTURE1);
      mainBuffer.getColorBufferTexture().bind();

      Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
      blurBuffer2.getColorBufferTexture().bind();

      mixerShader.begin();
      mixerShader.setUniformi("uTexture1", 1);
      mixerShader.setUniformi("uTexture2", 0);

      mixerShader.setUniformf("uFactor1", 1.0f);
      mixerShader.setUniformf("uFactor2", 2.2f);

      mixerShader.renderToQuad(null, true, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      mixerShader.end();
   }

   private void renderDebug(Camera camera, List<Tile> tiles) {
      debugRenderer.setProjectionMatrix(camera.combined);
      debugRenderer.begin(ShapeType.FilledRectangle);
      debugRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
      for (Tile tile : tiles) {
         for (Rectangle rect : tile.getBounds()) {
            debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
         }
      }
      debugRenderer.end();

      debugRenderer.begin(ShapeType.FilledRectangle);
      debugRenderer.setColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
      Vector3 p = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
      camera.unproject(p);
      debugRenderer.filledRect(p.x, p.y, 10.0f, 10.0f);
      debugRenderer.end();
   }

   private void render(boolean bg, Camera camera, List<Tile> tiles, Player player, Color color) {

      Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      if (bg) {
         Background.getInstance().draw(new Vector3(color.r, color.g, color.b));
      }

      batch.setProjectionMatrix(camera.combined);
      batch.begin();
      float oldAlpha = 0f;
      for (Tile tile : tiles) {
         MultiLayerSprite sprite = tile.getSprite();

         if (bg) {
            sprite.activateLayer(0);
            Color c = Color.WHITE;
            oldAlpha = c.a;
            sprite.setColor(c);
            c.a = oldAlpha;
            sprite.draw(batch);
         }

         sprite.activateLayer(1);
         oldAlpha = color.a;
         color.a = tile.getAlpha();
         sprite.setColor(color);
         sprite.draw(batch);
         color.a = oldAlpha;
         
      }

      if (bg) {
         player.getSprite().activateLayer(0);
         player.getSprite().setColor(Color.WHITE);
         player.draw(batch);
      }

      player.getSprite().activateLayer(1);
      player.getSprite().setColor(color);
      player.draw(batch);

      batch.end();

      // debugRenderer.setProjectionMatrix(camera.combined);
      // debugRenderer.begin(ShapeType.FilledRectangle);
      // debugRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
      // for (Tile tile : tiles) {
      // for (Rectangle rect : tile.getBounds()) {
      // debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
      // }
      // }
      // debugRenderer.end();

      // debugRenderer.begin(ShapeType.FilledRectangle);
      // debugRenderer.setColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
      // Vector3 p = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
      // camera.unproject(p);
      // debugRenderer.filledRect(p.x, p.y, 10.0f, 10.0f);
      // debugRenderer.end();
   }

   public TextConsole getConsole() {
      return console;
   }

   public void dispose() {
      batch.dispose();
   }

}
