package eu32k.ludumdare.ld26.stages;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.common.TempVector2;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.events.messages.GenericEvent;
import eu32k.ludumdare.ld26.gameplay.GameEventHandler;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.objects.Goal;
import eu32k.ludumdare.ld26.objects.Player;
import eu32k.ludumdare.ld26.rendering.MainRenderer;
import eu32k.ludumdare.ld26.state.GameState;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelLosingState;
import eu32k.ludumdare.ld26.state.LevelPauseState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class GameStage extends AbstractStage {

   private static final float ZOOM = 5.55555555555f;
   // private Camera camera;

   private MainRenderer renderer;
   private Player player;
   private Level level;

   private LevelState levelState;

   private PlayerState playerState;

   private GlobalState globalState;

   private boolean quickRetry;
   private float retryTimer;
   

   public GameStage(EffectsManager effects) {
      this.effects = effects;

      new GameEventHandler();

      levelState = StateMachine.instance().getState(LevelState.class);
      globalState = StateMachine.instance().getState(GlobalState.class);

      renderer = new MainRenderer();
      levelState.setTextConsole(renderer.getConsole());
      StateMachine.instance().createState(new PlayerState());
      playerState = StateMachine.instance().getState(PlayerState.class);
      player = playerState.getPlayer();

   }

   @Override
   public void draw() {

      boolean running = levelState.isRunning();
      float delta = Gdx.graphics.getDeltaTime();

      if (!levelState.ready()) {
         return;
      }
      level = levelState.getLevel();
      // updates --------------------------------------
      setPlayerAndGoalTile();

      printLevelProgress();


      if (running) {

         clearPauseScreen();

         repositionGoal();

         levelState.update(delta);

         updateRunningEffects(delta);

         checkGameConditions(delta);

         updatePlayerInput(delta);
      } 
      Color mainColor = effects.getCurrentColor();
      Color inverseColor = new Color();
      inverseColor.r = 1f - mainColor.r;
      inverseColor.g = 1f - mainColor.g;
      inverseColor.b = 1f - mainColor.b;
      inverseColor.a = 1f;
      Color playerColor = new Color(inverseColor);
      GameState currentState = StateMachine.instance().getCurrentState();
      if (currentState instanceof LevelLosingState) {
         LevelLosingState lState = (LevelLosingState) currentState;
         if (lState.handleKeys(delta) && (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched())) {
            globalState.getEvents().enqueue(globalState.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_LOSE, 0, GenericEvent.GAMEEVENT_LOSE_TOLOST));

         }
         lState.update(delta);
      } else if (currentState instanceof LevelInitState) {
         LevelInitState lis = (LevelInitState) currentState;
         lis.update(delta);
         lis.setColors(mainColor, playerColor, inverseColor);
      }
      // tileAnimator.update(delta);

      renderer.setPaused(levelState.isPaused());
      // rendering ------------------------------------
      setViewport(level.getWidth() + 2.0f, level.getHeight() + 2.0f, true);
      getCamera().position.x = level.getWidth() / 2.0f;
      getCamera().position.y = level.getHeight() / 2.0f;
      super.draw();
      renderer.render(delta, getCamera(), level.getTiles(), player, levelState.getGoal(), mainColor, playerColor, inverseColor);
   }

   private void drawPauseScreen() {
      renderer.getTextRenderer().addText("paused", "Paused", Gdx.graphics.getWidth() / 2 - 48, Gdx.graphics.getHeight() / 2 + 10);
   }

   private void clearPauseScreen() {
      renderer.getTextRenderer().removeText("paused");
   }

   private void printLevelProgress() {
      renderer.getTextRenderer().addText("level", "Level " + (levelState.getCurrentLevelIndex() + 1) + " / " + levelState.getLevels().size(), 30, Gdx.graphics.getHeight() - 30);
   }

   private void repositionGoal() {
      Goal goal = levelState.getGoal();
      if (goal != null && !goal.isFreeMovement() && levelState.goalTile == null) {
         levelState.repositionGoal();
      }
   }

   private void checkGameConditions(float delta) {
      List<IRunningEffect> runningEffects = levelState.getRunningEffects();
      if (runningEffects.size() > 0) {
         int count = 0;
         boolean movingTileInvolved = false;

         for (Tile t : levelState.getLevel().getTiles()) {
            if (t.isInUse()) {
               Vector2 shiftedPosition = player.getShiftedPosition();
               if (!Player.canMoveIntoTile(shiftedPosition, t)) {
                  count++;
                  if (t.isMoving()) {
                     movingTileInvolved = true;
                  }
               }
               if (movingTileInvolved && count > 1) {
                  levelState.getEvents().enqueue(globalState.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_LOSE, 0, GenericEvent.GAMEEVENT_LOSE_SQUASHED));
               }
            }
         }
      }

      if (levelState.playerTile == null) {
         levelState.deathConditionTimer += delta;
         if (levelState.deathConditionTimer > 0.05f) {
            levelState.getEvents().enqueue(globalState.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_LOSE, 0, GenericEvent.GAMEEVENT_LOSE_FALLOFFBOARD));
         }
      } else {
         levelState.deathConditionTimer = 0;
      }

      if (levelState.playerTile != null && levelState.playerTile.equals(levelState.goalTile)) {
         Goal g = levelState.getGoal();
         if (g.intersects(player)) {
            levelState.getEvents().enqueue(globalState.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_WIN));
         }
      }
   }

   private void updateRunningEffects(float delta) {
      List<IRunningEffect> runningEffects = levelState.getRunningEffects();
      Iterator<IRunningEffect> effectsIterator = runningEffects.iterator();
      while (effectsIterator.hasNext()) {
         IRunningEffect effect = effectsIterator.next();
         if (effect.complete()) {
            effectsIterator.remove();
         } else {
            effect.update(delta);
            if (effect.complete()) {
               effectsIterator.remove();
            }
         }
      }
   }

   private void updatePlayerInput(float delta) {
      // player inputs --------------------------------
      boolean up = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean down = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
      boolean left = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
      boolean retry = Gdx.input.isKeyPressed(Input.Keys.R) && !quickRetry;

      handleRetry(delta, retry);

      if (pausedPressed()) {
         levelState.getEvents().enqueue(globalState.pool().events().gameplayEvent(GenericEvent.GAMEEVENT_TYPE_PAUSE));
         resetEnteredTimer();
         return;
      }


      Vector2 velocity = TempVector2.tmp.set(0.0f, 0.0f);
      if (up) {
         velocity.add(0.0f, 1.0f);
      }
      if (down) {
         velocity.add(0.0f, -1.0f);
      }
      if (left) {
         velocity.add(-1.0f, 0.0f);
      }
      if (right) {
         velocity.add(1.0f, 0.0f);
      }
      float factor = 1.0f;

      if (Gdx.input.getPitch() != 0.0f || Gdx.input.getRoll() != 0.0f) {
         float x = MathUtils.clamp(-Gdx.input.getPitch(), -20.0f, 20.0f);

         float y = MathUtils.clamp(Gdx.input.getRoll(), -20.0f, 20.0f);
         velocity = TempVector2.tmp.set(x, y);
         factor = MathUtils.clamp(Math.min(velocity.len(), 20.0f) / 20.0f, 0.0f, 1.0f);
      }

      // DebugText.text = "p:" + Gdx.input.getPitch() + " r:" + Gdx.input.getRoll() + " f:" + factor;

      if (Gdx.input.isTouched()) {
         Vector2 touch = TempVector2.tmp.set(Gdx.input.getX(), Gdx.input.getY());
         screenToStageCoordinates(touch);
         velocity = TempVector2.tmp.set(touch.x - Player.WIDTH / 2, touch.y - Player.HEIGHT / 2).sub(player.position);
         factor = 1.0f;
      }

      velocity.nor();
      velocity.scl(delta * factor);
      player.move(velocity.x, velocity.y, level.getTiles());

   }

   private void handleRetry(float delta, boolean retry) {
      retryTimer += delta;
      if (retry) {
         retry();
         quickRetry = true;
         return;
      } else {
         if (retryTimer > 0.05f) {
            retryTimer = 0;
         }
      }
      quickRetry = false;
   }

   private boolean pausedPressed() {
      if(getRunningTimeSinceEnter() < 0.5f){
         return false;
      }
      return Gdx.input.isKeyPressed(Input.Keys.P) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
   }

   private void setPlayerAndGoalTile() {
      float px = player.position.x;
      float py = player.position.y;
      Goal g = levelState.getGoal();
      boolean setPlayerTile = !(levelState.playerTile != null && levelState.playerTile.contains(px, py));
      boolean setGoalTile = !(g != null && levelState.goalTile != null && levelState.goalTile.contains(g.getX(), g.getY()));

      if (setPlayerTile) {
         Tile t = findPlayerTile();
         levelState.playerTile = t;
         if (t != null) {
            renderer.getConsole().addLine("Player entered tile on position: " + Float.toString(t.getX()) + "/" + Float.toString(t.getY()));
         }
      }
      if (setGoalTile) {
         Tile t = findGoalTile();
         levelState.goalTile = t;
         if (t != null) {
            renderer.getConsole().addLine("Goal entered tile on position: " + Float.toString(t.getX()) + "/" + Float.toString(t.getY()));
         }

      }
   }

   private Tile findPlayerTile() {
      List<Tile> tiles = levelState.getLevel().getTiles();
      for (Tile tile : tiles) {
         if (tile.isInUse() && tile.contains(player.getX(), player.getY())) {
            return tile;
         }
      }
      return null;
   }

   private Tile findGoalTile() {
      Goal goal = levelState.getGoal();
      List<Tile> tiles = levelState.getLevel().getTiles();
      for (Tile tile : tiles) {
         if (tile.isInUse() && tile.contains(goal.position.x, goal.position.y)) {
            return tile;
         }
      }
      return null;
   }

   private void retry() {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      levelState.events.clear();
      globalState.pool().fades().setInUseForAll(false);
      globalState.pool().tiles().setInUseForAll(false);
      levelState.initLevel();
      levelState.addRetryStatistics();
      StateMachine.instance().enterState(LevelInitState.class);
   }
}
