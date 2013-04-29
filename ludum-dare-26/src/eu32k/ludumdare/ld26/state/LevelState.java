package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import eu32k.ludumdare.ld26.animation.TileAnimator;
import eu32k.ludumdare.ld26.effects.IRunningEffect;
import eu32k.ludumdare.ld26.events.EventQueue;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
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

   public float deathConditionTimer;

   public EventQueue events;

   private boolean paused;

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

   public Level getLevel() {
      return level;
   }

   public void setLevel(Level level) {
      this.level = level;
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
}
