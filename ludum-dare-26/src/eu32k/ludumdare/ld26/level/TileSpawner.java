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
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      globalState.getEvents().enqueue(new TileEvent(5, null, TileEventType.TRIGGER_SPAWN));
   }

   @Override
   public void handle(IEvent ev) {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      if (ev instanceof TileEvent) {
         TileEvent event = (TileEvent) ev;
         switch (event.getType()) {
         case TRIGGER_SPAWN:
            globalState.getEvents().enqueue(new TileEvent(0, levelState.getLevel().spawnTile(), TileEventType.SPAWNED));
            break;
         case SPAWNED:
            System.out.println("spawned");
            levelState.getTileAnimator().animateShift(event.getTile());
            break;
         case TRIGGER_POP:
            levelState.getLevel().popTile(event.getTile());
            globalState.getEvents().enqueue(new TileEvent(0, event.getTile(), TileEventType.POPPED));
            break;
         case POPPED:
            globalState.getEvents().enqueue(new TileEvent(1, null, TileEventType.TRIGGER_SPAWN));
            break;
         }
      } else if (ev instanceof MoveComplete) {
         MoveComplete event = (MoveComplete) ev;
         Tile moved = event.move.getTile();
         if (moved.equals(levelState.toPop)) {
            globalState.getEvents().enqueue(new TileEvent(1, moved, TileEventType.TRIGGER_POP));
         }
      }

   }

   public void update(float delta) {
   }

}
