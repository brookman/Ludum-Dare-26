package eu32k.ludumdare.ld26.animation;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.level.Level;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileEvent;
import eu32k.ludumdare.ld26.level.TileEvent.TileEventType;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileAnimator {
   
   private float speed = 100f;
   
   private float animatedPixels = 0;
   
   private GlobalState globalState;
   
   public TileAnimator() {
      globalState = StateMachine.instance().getState(GlobalState.class);
   }
   
   public void update(float delta) {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      Tile tile = levelState.getLevel().getNextTile();
      Tile toPop = null;
      boolean animationComplete = false;
      if(tile != null) {
         Direction dir = tile.getNeighbors().keySet().iterator().next();
         float movement;
         do {
            movement = delta * speed;
            System.out.println(movement);
            animatedPixels += movement;
            if(animatedPixels >= Level.TILE_WIDTH) {
               movement = animatedPixels - Level.TILE_WIDTH;
               animationComplete = true;
            }
            switch(dir) {
            case N:
               tile.setX(tile.getX() + movement);
               break;
            case E:
               movement *= -1;
               tile.setY(tile.getY() + movement);
               break;
            case S:
               movement *= -1;
               tile.setX(tile.getX() + movement);
               break;
            case W:
            default:
               tile.setY(tile.getY() + movement);
               break;
            }
            if(animationComplete) {
               animatedPixels = 0;
               System.out.println("animation complete");
               levelState.getLevel().setNextTile(null);
            } else {
               System.out.println("animating");
            }
            toPop = tile;
         } while((tile = tile.getNeighbors().get(dir)) != null);
         
      }
      if(animationComplete) {
         globalState.getEvents().enqueue(new TileEvent(0, toPop, TileEventType.TRIGGER_POP));
      }
      System.out.println("=======================================");
   }

}
