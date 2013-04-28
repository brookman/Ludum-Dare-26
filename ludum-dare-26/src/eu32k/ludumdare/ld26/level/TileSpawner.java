package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.level.TileEvent.TileEventType;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileSpawner implements IEventHandler {

   private GlobalState globalState;

   public TileSpawner() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      globalState.getEvents().addHandler(this);
   }

   public void init() {
      globalState.getEvents().enqueue(new TileEvent(5, null, TileEventType.TRIGGER_SPAWN));
   }

   @Override
   public void handle(IEvent ev) {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      if (ev instanceof TileEvent) {
         TileEvent event = (TileEvent) ev;
         switch (event.getType()) {
         case TRIGGER_SPAWN:
            levelState.spawned = levelState.getLevel().spawnTile();
            levelState.getTileAnimator().animateSpawn(levelState.spawned);
//            globalState.getEvents().enqueue(new TileEvent(1, levelState.spawned, TileEventType.SPAWNED));
            break;
         case SPAWNED:
            levelState.getTileAnimator().animateShift(event.getTile());
            break;
         case TRIGGER_POP:
            levelState.getLevel().popTile(event.getTile());
            globalState.getEvents().enqueue(new TileEvent(0, event.getTile(), TileEventType.POPPED));
            break;
         case POPPED:
            globalState.getEvents().enqueue(new TileEvent((5 / levelState.getLevel().getDufficulty()), null, TileEventType.TRIGGER_SPAWN));
            break;
         }
      } else if (ev instanceof MoveComplete) {
         MoveComplete event = (MoveComplete) ev;
         Tile moved = event.move.getTile();
         if (moved.equals(levelState.toPop)) {
            globalState.getEvents().enqueue(new TileEvent(0, moved, TileEventType.TRIGGER_POP));
         } else if(moved.equals(levelState.spawned)) {
            globalState.getEvents().enqueue(new TileEvent((2 / levelState.getLevel().getDufficulty()) - 1, moved, TileEventType.SPAWNED));
            levelState.spawned = null;
         }
      }

   }

   public void update(float delta) {
   }

}
