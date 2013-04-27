package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class TileSpawner implements IEventHandler {

   private GlobalState globalState;

   public TileSpawner() {
      globalState = StateMachine.instance().getState(GlobalState.class);
   }

   @Override
   public void handle(IEvent ev) {
      if (ev instanceof SpawnTileEvent) {
         SpawnTileEvent event = (SpawnTileEvent) ev;
         StateMachine.instance().getState(LevelState.class).getLevel().getTiles().add(event.getTile());
      }

   }

}
