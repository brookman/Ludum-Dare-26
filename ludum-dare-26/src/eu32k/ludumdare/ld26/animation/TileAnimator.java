package eu32k.ludumdare.ld26.animation;

import eu32k.ludumdare.ld26.Direction;
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
         System.out.println(dir);
         float movement;
         movement = delta * speed;
         System.out.println(movement);
         do {
            if(animatedPixels >= tile.getSprite().getWidth()) {
               movement = animatedPixels - tile.getSprite().getWidth();
               animationComplete = true;
            }
            switch(dir) {
            case N:
               tile.setX(tile.getX() + movement);
               break;
            case E:
               tile.setY(tile.getY() - movement);
               break;
            case S:
               tile.setX(tile.getX() - movement);
               break;
            case W:
            default:
               tile.setY(tile.getY() + movement);
               break;
            }
            toPop = tile;
         } while((tile = tile.getNeighbors().get(dir)) != null);
         animatedPixels += movement;
         
         System.out.println(animatedPixels);
         if(animationComplete) {
            animatedPixels = 0;
            levelState.getLevel().setNextTile(null);
            System.out.println("animation complete");
            globalState.getEvents().enqueue(new TileEvent(0, toPop, TileEventType.TRIGGER_POP));
         }
         System.out.println("=======================================");
      }
   }

}
