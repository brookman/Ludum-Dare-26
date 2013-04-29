package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import eu32k.ludumdare.ld26.animation.TileAnimator;
import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.events.EventQueue;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.objects.GameObject;
import eu32k.ludumdare.ld26.objects.Goal;
import eu32k.ludumdare.ld26.objects.Player;
import eu32k.ludumdare.ld26.rendering.TextConsole;

public class LevelState extends GameState {

   private TextConsole console;

   private boolean running;
   private int deathType;

   private Level level;
   private List<IRunningEffect> runningEffects;
   private TileAnimator tileAnimator;

   public Tile toPop;
   public Tile spawned;
   public Tile playerTile;

   private Goal goal;

   public float deathConditionTimer;

   public EventQueue events;

   private boolean paused;

   private int width;

   private int height;

   public LevelState() {
      this.runningEffects = new ArrayList<IRunningEffect>();
      tileAnimator = new TileAnimator();
      deathConditionTimer = 0;
      this.events = new EventQueue();
   }

   @Override
   public void init() {
      transitions.add(LevelWinningState.class);
      transitions.add(LevelPauseState.class);
      transitions.add(LevelLosingState.class);
      transitions.add(MenuState.class);
   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   @Override
   public void enter() {
      running = true;
   }

   @Override
   public void leave() {
      running = false;
   }

   public boolean ready() {
      return level != null;
   }

   public Level getLevel() {
      return level;
   }

   public void setLevel(Level level) {
      this.level = level;
      if (level != null) {
         this.width = level.getWidth();
         this.height = level.getHeight();
      }
   }

   public List<IRunningEffect> getRunningEffects() {
      return runningEffects;
   }

   public TileAnimator getTileAnimator() {
      return tileAnimator;
   }

   public void setTileAnimator(TileAnimator tileAnimator) {
      this.tileAnimator = tileAnimator;
   }

   public void setTextConsole(TextConsole console) {
      this.console = console;
   }

   public void log(String text) {
      if (console != null) {
         console.addLine(text);
      }
   }

   public void log(String text, Color color) {
      if (console != null) {
         console.addLine(text, color);
      }
   }

   public int getDeathType() {
      return deathType;
   }

   public void setDeathType(int deathType) {
      this.deathType = deathType;
   }

   public boolean isRunning() {
      return running;
   }

   public void setRunning(boolean running) {
      this.running = running;
   }

   public EventQueue getEvents() {
      return events;
   }

   public void setPaused(boolean paused) {
      this.paused = paused;
   }

   public boolean isPaused() {
      return this.paused;
   }

   public Goal getGoal() {
      return goal;
   }

   public void setGoal(Goal goal) {
      this.goal = goal;
   }

   public void initLevel(int width, int height) {
      if (width < 3) {
         width = 3;
      }
      if (height < 3) {
         height = 3;
      }
      this.width = width;
      this.height = height;
      this.level = new Level(width, height);
      Random random = new Random();

      Vector2 p = Vector2.tmp;
      Vector2 g = Vector2.tmp2;
      Vector2 tmp = Vector2.tmp3;
      p.set(random.nextInt(width), random.nextInt(height));
      do {
         g.set(random.nextInt(width), random.nextInt(height));
         tmp.set(g);
      } while (tmp.sub(p).len() < 1.5f);

      Player player = StateMachine.instance().getState(PlayerState.class).getPlayer();
      if (goal == null) {
         goal = new Goal(0, 0);
      }

      positionGameObject(player, (int) p.x, (int) p.y);
      positionGameObject(goal, (int) g.x, (int) g.y);

      level.generateRandomTiles();
      if (goal == null)
         goal = new Goal(0, 0);

   }

   public void positionGameObject(GameObject obj, int x, int y) {
      obj.setPosition(x + 0.5f, y + 0.5f);
   }
}
