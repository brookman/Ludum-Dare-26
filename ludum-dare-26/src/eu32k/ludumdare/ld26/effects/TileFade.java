package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.graphics.Color;

import eu32k.ludumdare.ld26.events.EventQueue;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileFade {
   private boolean complete;
   private Tile tile;
   private float speed;
   private EventQueue events;
   private LevelState levelState;
   private float currentAlpha;
   
   public boolean complete() {
      return complete;
   }

   public TileFade()
   {
      this.events = StateMachine.instance().getState(GlobalState.class).getEvents();
      this.levelState = StateMachine.instance().getState(LevelState.class);
      this.currentAlpha = 1;
   }
   
   public void initFade(Tile tile, float speed)
   {
      this.tile = tile;
      complete = false;
      this.speed = speed;
   }
   
   public void update(float delta) {
      currentAlpha -= (delta);
      System.out.println(currentAlpha);
      if(currentAlpha < 0) {
         currentAlpha = 0;
         complete = true;
         events.enqueue(new FadeComplete(this));
      }
      Color color = tile.getSprite().getColor();
      color.a = currentAlpha;
      tile.getSprite().setColor(color);
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }
   
}
