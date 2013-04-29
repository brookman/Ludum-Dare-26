package eu32k.ludumdare.ld26.animation;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.effects.TileMove;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileEvent;
import eu32k.ludumdare.ld26.level.TileEvent.TileEventType;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileAnimator {

   private float speed = 0.8f;
   
   private GlobalState globalState;

   public TileAnimator() {
      globalState = StateMachine.instance().getState(GlobalState.class);
   }

   public void animateShift(Tile spawned) {
      Tile toShift = spawned;
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      Direction dir = toShift.getNeighbors().keySet().iterator().next();
      Tile last = toShift;
      do {
         float targetX, targetY;
         switch (dir) {
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
         move.initMove(toShift, targetX, targetY, speed * levelState.getLevel().getDufficulty());
         levelState.getRunningEffects().add(move);
         last = toShift;
      } while ((toShift = toShift.getNeighbors().get(dir)) != null);
      levelState.getLevel().updateNeighbors(spawned, dir);
      levelState.toPop = last;
   }

   public void animateSpawn(Tile spawned) {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      float targetX = spawned.getX();
      float targetY = spawned.getY();
      Direction dir = spawned.getNeighbors().keySet().iterator().next();
      switch (dir) {
      case N:
         spawned.setY(spawned.getY()-1);
         break;
      case E:
         spawned.setX(spawned.getX()-1);
         break;
      case S:
         spawned.setY(spawned.getY()+1);
         break;
      case W:
         spawned.setX(spawned.getX()+1);
      default:
         break;
      }
      TileMove move = new TileMove();
      move.initMove(spawned, targetX, targetY, speed / 2 * levelState.getLevel().getDufficulty());
      StateMachine.instance().getState(LevelState.class).getRunningEffects().add(move);
   }

   public void animatePop(Tile popped) {
      TileFade fade = new TileFade();
      fade.initFade(popped, 1f);
      StateMachine.instance().getState(LevelState.class).getRunningEffects().add(fade);
   }

}
