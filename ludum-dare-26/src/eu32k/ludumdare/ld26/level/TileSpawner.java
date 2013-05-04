package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.effects.FadeComplete;
import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent;
import eu32k.ludumdare.ld26.gameplay.GameplayEvent.GameplayEventType;
import eu32k.ludumdare.ld26.level.TileEvent.TileEventType;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileSpawner implements IEventHandler {

   private GlobalState globalState;
   private LevelState levelState;

   public TileSpawner() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      levelState = StateMachine.instance().getState(LevelState.class);
      levelState.getEvents().addHandler(this);
   }

   public void spawnTile(float spawnTime) {
      levelState.getEvents().enqueue(new TileEvent(spawnTime, null, TileEventType.TRIGGER_SPAWN));
   }

   @Override
   public void handle(IEvent ev) {
      if (ev instanceof TileEvent) {
         TileEvent event = (TileEvent) ev;
         switch (event.getType()) {
         case TRIGGER_SPAWN:
            levelState.spawned = levelState.getLevel().spawnTile();
            levelState.getTileAnimator().animateSpawn(levelState.spawned);
            break;
         case SPAWNED:
            levelState.getTileAnimator().animateShift(event.getTile());
            break;
         case TRIGGER_POP:
            levelState.getLevel().popTile(event.getTile());
            if (event.getTile().equals(levelState.playerTile)) {
               levelState.playerTile = null;
            }
            levelState.getEvents().enqueue(new TileEvent(0, event.getTile(), TileEventType.POPPED));
            break;
         case POPPED:
            spawnTile((5f / levelState.getLevel().getDufficulty()));
            break;
         }
      } else if (ev instanceof MoveComplete) {
         MoveComplete event = (MoveComplete) ev;
         Tile moved = event.move.getTile();
         if (moved.equals(levelState.toPop)) {
            levelState.getTileAnimator().animatePop(moved);
         } else if (moved.equals(levelState.spawned)) {
            levelState.getEvents().enqueue(new TileEvent((2 / levelState.getLevel().getDufficulty()) - 1, moved, TileEventType.SPAWNED));
            levelState.spawned = null;
         }
      } else if (ev instanceof FadeComplete) {
         FadeComplete event = (FadeComplete) ev;
         if (event.fade.fadeTo() == 0f) {
            levelState.getEvents().enqueue(new TileEvent(0, event.fade.getTile(), TileEventType.TRIGGER_POP));
         }
      }

   }

   public void update(float delta) {
   }

}
