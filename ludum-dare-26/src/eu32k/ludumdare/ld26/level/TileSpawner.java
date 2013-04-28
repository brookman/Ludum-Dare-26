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
            globalState.getEvents().enqueue(new TileEvent(1, levelState.getLevel().spawnTile(), TileEventType.SPAWNED));
            globalState.getEvents().enqueue(new TileEvent(1, levelState.getLevel().spawnTile(), TileEventType.TRIGGER_SPAWN));
            break;
         case SPAWNED:
            levelState.getLevel().setNextTile(event.getTile());
            break;
         case TRIGGER_POP:
            break;
         case POPPED:
            break;
         }
      }

   }

   public void update(float delta) {
   }

}
