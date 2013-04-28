package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.level.TileEvent.TileEventType;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileSpawner implements IEventHandler {

   private GlobalState globalState;
   
   public Tile toPop;

   public TileSpawner() {
      globalState = StateMachine.instance().getState(GlobalState.class);
      globalState.getEvents().addHandler(this);
   }

   public void init() {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      globalState.getEvents().enqueue(new TileEvent(2, levelState.getLevel().spawnTile(), TileEventType.TRIGGER_SPAWN));
   }

   @Override
   public void handle(IEvent ev) {
      if (ev instanceof TileEvent) {
         TileEvent event = (TileEvent) ev;
         LevelState levelState = StateMachine.instance().getState(LevelState.class);
         switch (event.getType()) {
         case TRIGGER_SPAWN:
            levelState.getLevel().getTiles().add(event.getTile());
            globalState.getEvents().enqueue(new TileEvent(0, levelState.getLevel().spawnTile(), TileEventType.SPAWNED));
            break;
         case SPAWNED:
            levelState.getLevel().setNextTile(event.getTile());
            levelState.getTileAnimator().animateShift(event.getTile());
            break;
         case TRIGGER_POP:
            levelState.getLevel().popTile(event.getTile());
            globalState.getEvents().enqueue(new TileEvent(0, event.getTile(), TileEventType.POPPED));
            break;
         case POPPED:
            globalState.getEvents().enqueue(new TileEvent(1, levelState.getLevel().spawnTile(), TileEventType.TRIGGER_SPAWN));
            break;
         }
      } if(ev instanceof MoveComplete) {
         MoveComplete event = (MoveComplete) ev;
         Tile moved = event.move.getTile();
         if(moved.equals(toPop)) {
            globalState.getEvents().enqueue(new TileEvent(1, moved, TileEventType.TRIGGER_POP));
         }
      }

   }

   public void update(float delta) {
   }

}
