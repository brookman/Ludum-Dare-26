package eu32k.ludumdare.ld26.stages;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.ludumdare.ld26.Player;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileMove;
import eu32k.ludumdare.ld26.level.TileSpawner;
import eu32k.ludumdare.ld26.rendering.MainRenderer;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelFinishedState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.PauseState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class GameStage extends Stage {

   private static final float ZOOM = 5.55555555555f;
   private Camera camera;

   private MainRenderer renderer;
   private Player player;
   private Level level;
   private EffectsManager effects;

   private TileSpawner tileSpawner;

   private LevelState levelState;

   private PlayerState playerState;

   public GameStage() {
      float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
      camera = new OrthographicCamera(2.0f * aspectRatio * ZOOM, 2.0f * ZOOM);

      StateMachine.instance().createState(new GlobalState());
      StateMachine.instance().createState(new MenuState());
      StateMachine.instance().createState(new LevelState());
      StateMachine.instance().createState(new LevelFinishedState());
      StateMachine.instance().createState(new PauseState());
      StateMachine.instance().enterState(LevelState.class);

      effects = new EffectsManager();
      tileSpawner = new TileSpawner();
      levelState = StateMachine.instance().getState(LevelState.class);

      renderer = new MainRenderer();

      StateMachine.instance().createState(new PlayerState());
      playerState = StateMachine.instance().getState(PlayerState.class);
      player = playerState.getPlayer();
      playerState.initLevel(0, 0);

      level = new Level(5, 5);
      level.generateRandomTiles();

      levelState.setLevel(level);
      tileSpawner.init();
      effects.initOtgy(0);

      renderer.getConsole().addLine("Hallo Velo");
      renderer.getConsole().addLine("Hallo Tibau abuas");
   }

   @Override
   public void draw() {
      float delta = Gdx.graphics.getDeltaTime();

      // player inputs --------------------------------
      boolean up = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean down = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
      boolean left = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);

      boolean escapePressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);

      // updates --------------------------------------
      setPlayerTile();

      List<TileMove> moves = levelState.getMovingTiles();
      Iterator<TileMove> moveIterator = moves.iterator();
      while (moveIterator.hasNext()) {
         TileMove move = moveIterator.next();
         if (move.complete()) {
            moveIterator.remove();
         } else {
            move.update(delta);
            if (move.complete()) {
               moveIterator.remove();
            }
         }
      }

      Vector2 velocity = new Vector2(0.0f, 0.0f);
      if (up) {
         velocity.add(new Vector2(0.0f, 1.0f));
      }
      if (down) {
         velocity.add(new Vector2(0.0f, -1.0f));
      }
      if (left) {
         velocity.add(new Vector2(-1.0f, 0.0f));
      }
      if (right) {
         velocity.add(new Vector2(1.0f, 0.0f));
      }
      if (Gdx.input.isTouched()) {
         Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
         camera.unproject(touch);
         velocity = new Vector2(touch.x - Player.WIDTH / 2, touch.y - Player.HEIGHT / 2).sub(player.position);
      }
      velocity.nor();
      velocity.mul(delta);
      player.move(velocity, level.getTiles());

      if (escapePressed) {
         effects.stopSong(null);
      }
      StateMachine.instance().getState(GlobalState.class).getEvents().tick(delta);
      tileSpawner.update(delta);
      // tileAnimator.update(delta);
      effects.update(delta);

      camera.position.x = level.getWidth() / 2.0f;
      camera.position.y = level.getHeight() / 2.0f;
      camera.update();

      // rendering ------------------------------------
      renderer.render(delta, camera, level.getTiles(), player, effects.getCurrentColor());
   }

   private void setPlayerTile() {
      float px = player.position.x;
      float py = player.position.y;
      if (levelState.playerTile != null && levelState.playerTile.contains(px, py)) {
         return;
      }
      Tile t = findPlayerTile();
      levelState.playerTile = t;
      if (t != null) {
         renderer.getConsole().addLine("Player entered tile on position: " + Float.toString(t.getX()) + "/" + Float.toString(t.getY()));
      }
   }

   private Tile findPlayerTile() {
      List<Tile> tiles = levelState.getLevel().getTiles();
      for (Tile tile : tiles) {
         if (tile.contains(player.position.x, player.position.y)) {
            return tile;
         }
      }
      return null;
   }
}
