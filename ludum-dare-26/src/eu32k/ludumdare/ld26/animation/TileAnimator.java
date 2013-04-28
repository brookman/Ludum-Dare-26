package eu32k.ludumdare.ld26.animation;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileMove;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileAnimator {
   
   private float speed = 40f;
   
   private GlobalState globalState;
   
   public TileAnimator() {
      globalState = StateMachine.instance().getState(GlobalState.class);
   }
   
   public void animateShift(Tile spawned) {
      Tile toShift = spawned;
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      Direction dir = toShift.getNeighbors().keySet().iterator().next();
      Tile last = toShift;
      System.out.println("=========");
      do {
         float targetX, targetY;
         switch(dir) {
         case N:
            targetX = toShift.getX();
            targetY = toShift.getY() + toShift.getSprite().getHeight();
            break;
         case E:
            targetX = toShift.getX() + toShift.getSprite().getWidth();
            targetY = toShift.getY();
            break;
         case S:
            targetX = toShift.getX();
            targetY = toShift.getY() - toShift.getSprite().getHeight();
            break;
         case W:
         default:
            targetX = toShift.getX() - toShift.getSprite().getWidth();
            targetY = toShift.getY();
            break;
         }
         TileMove move = new TileMove();
         move.initMove(toShift, targetX, targetY, speed);
         levelState.getMovingTiles().add(move);
         last = toShift;
         System.out.println(dir);
      } while((toShift = toShift.getNeighbors().get(dir))!=null);
      levelState.getLevel().updateNeighbors(spawned, dir);
      System.out.println("=========");
      levelState.toPop = last;
   }
   
//   public void update(float delta) {
//      LevelState levelState = StateMachine.instance().getState(LevelState.class);
//      Tile tile = levelState.getLevel().getNextTile();
//      Tile toPop = null;
//      boolean animationComplete = false;
//      if(tile != null) {
//         Direction dir = tile.getNeighbors().keySet().iterator().next();
//         System.out.println(dir);
//         float movement;
//         movement = delta * speed;
//         System.out.println(movement);
//         do {
//            if(animatedPixels >= tile.getSprite().getWidth()) {
//               movement = animatedPixels - tile.getSprite().getWidth();
//               animationComplete = true;
//            }
//            switch(dir) {
//            case N:
//               tile.setX(tile.getX() + movement);
//               break;
//            case E:
//               tile.setY(tile.getY() - movement);
//               break;
//            case S:
//               tile.setX(tile.getX() - movement);
//               break;
//            case W:
//            default:
//               tile.setY(tile.getY() + movement);
//               break;
//            }
//            toPop = tile;
//         } while((tile = tile.getNeighbors().get(dir)) != null);
//         animatedPixels += movement;
//         
//         System.out.println(animatedPixels);
//         if(animationComplete) {
//            animatedPixels = 0;
//            levelState.getLevel().setNextTile(null);
//            System.out.println("animation complete");
//            globalState.getEvents().enqueue(new TileEvent(0, toPop, TileEventType.TRIGGER_POP));
//         }
//         System.out.println("=======================================");
//      }
//   }

}
