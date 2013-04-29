package eu32k.ludumdare.ld26.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import eu32k.ludumdare.ld26.animation.TileAnimator;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileMove;
import eu32k.ludumdare.ld26.rendering.TextConsole;

public class LevelState extends GameState {

   private TextConsole console;

   private boolean running;
   private int deathType;

   private Level level;
   private List<TileMove> movingTiles;
   private TileAnimator tileAnimator;
   private List<TileFade> fadingTiles;

   public Tile toPop;
   public Tile spawned;
   public Tile playerTile;

   public float deathConditionTimer;

   public LevelState() {
      this.movingTiles = new ArrayList<TileMove>();
      fadingTiles = new ArrayList<TileFade>();
      tileAnimator = new TileAnimator();
      deathConditionTimer = 0;
   }

   @Override
   public void init() {
      transitions.add(LevelWinningState.class);
      transitions.add(PauseState.class);
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

   public List<TileMove> getMovingTiles() {
      return movingTiles;
   }

   public TileAnimator getTileAnimator() {
      return tileAnimator;
   }

   public void setTileAnimator(TileAnimator tileAnimator) {
      this.tileAnimator = tileAnimator;
   }

   public List<TileFade> getFadingTiles() {
      return fadingTiles;
   }

   public void setFadingTiles(List<TileFade> fadingTiles) {
      this.fadingTiles = fadingTiles;
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

}
