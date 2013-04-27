package eu32k.ludumdare.ld26;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu32k.libgdx.SimpleGame;
import eu32k.ludumdare.ld26.Tile.Rotation;
import eu32k.ludumdare.ld26.Tile.Type;
import eu32k.ludumdare.ld26.effects.ColorPulseManager;
import eu32k.ludumdare.ld26.state.*;

public class LudumDare26 extends SimpleGame {

   private SpriteBatch batch;
   private List<Tile> tiles = new ArrayList<Tile>();
   private ColorPulseManager colors;
   private Player player;
    private List<Tile2> tiles2 = new ArrayList<Tile2>();

   public LudumDare26() {
      super(false);
      colors = new ColorPulseManager();
   }

   @Override
   public void init() {
      StateMachine.instance().createState(new MenuState());
      StateMachine.instance().createState(new LevelState());
      StateMachine.instance().createState(new LevelFinishedState());
      StateMachine.instance().createState(new PauseState());

      StateMachine.instance().enterState(LevelState.class);

      batch = new SpriteBatch();

      makeTile(0, 0, Type.L, Rotation.R);
      makeTile(0, 1, Type.L, Rotation.L);
      makeTile(0, 2, Type.L, Rotation.U);
      makeTile(0, 3, Type.L, Rotation.D);

      makeTile(1, 0, Type.I, Rotation.R);
      makeTile(1, 1, Type.I, Rotation.L);
      makeTile(1, 2, Type.I, Rotation.U);
      makeTile(1, 3, Type.I, Rotation.D);

      makeTile(2, 0, Type.X, Rotation.R);
      makeTile(2, 1, Type.X, Rotation.L);
      makeTile(2, 2, Type.X, Rotation.U);
      makeTile(2, 3, Type.X, Rotation.D);

      makeTile(3, 0, Type.T, Rotation.R);
      makeTile(3, 1, Type.T, Rotation.L);
      makeTile(3, 2, Type.T, Rotation.U);
      makeTile(3, 3, Type.T, Rotation.D);

      colors.init(ColorPulseManager.INTENSITY_BEAT, ColorPulseManager.TEST_03, new Color(41 / 255f, 106 / 255f, 149 / 255f, 1f));

      player = new Player(50, 50);
       tiles2.add(new Tile2(-27, -27, Tile2.Type.I, Tile2.Rotation.D));
       tiles2.add(new Tile2(-54, -27, Tile2.Type.T, Tile2.Rotation.D));
       tiles2.add(new Tile2(-27, -54, Tile2.Type.X, Tile2.Rotation.D));
       tiles2.add(new Tile2(-54, -54, Tile2.Type.L, Tile2.Rotation.D));

   }

   private void makeTile(int x, int y, Type t, Rotation r) {
      Tile tile = new Tile(t, r);
      Sprite sprite = tile.getSprite();
      sprite.setPosition(x * sprite.getWidth(), y * sprite.getHeight());
      tiles.add(tile);
   }

   private float zoom = 100.0f;

   @Override
   public void draw(float delta) {

      // player inputs --------------------------------
      boolean up = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean down = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
      boolean left = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);

      if (up) {
         player.moveUp();
      } else if (down) {
         player.moveDown();
      } else if (left) {
         player.moveLeft();
      } else if (right) {
         player.moveRight();
      }

      // updates --------------------------------------
      player.update(tiles2);
      setZoom(zoom);
      colors.update(delta);
      // rendering ------------------------------------
      Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      for (Tile tile : tiles) {
         tile.getSprite().setColor(colors.getCurrentColor());
         tile.getSprite().draw(batch);
      }

       for (Tile2 t : tiles2) {
           t.draw(batch);
       }
      player.draw(batch);

      batch.end();
   }

   @Override
   public void dispose() {
      super.dispose();
      batch.dispose();
   }
}
