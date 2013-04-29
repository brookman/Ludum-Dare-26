package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.libgdx.SimpleGame;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.level.TileSprites;
import eu32k.ludumdare.ld26.stages.GameStage;
import eu32k.ludumdare.ld26.stages.LostStage;
import eu32k.ludumdare.ld26.stages.MenuStage;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelFinishedState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.LostState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.PauseState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class LudumDare26 extends SimpleGame {

   private EffectsManager effects;

   private MenuStage menuStage;
   private GameStage gameStage;
   private LostStage lostStage;
   private Stage currentStage;

   public LudumDare26() {
      super(false);
      StateMachine.instance().createState(new GlobalState());
      StateMachine.instance().createState(new MenuState());
      StateMachine.instance().createState(new LevelState());
      StateMachine.instance().createState(new LevelFinishedState());
      StateMachine.instance().createState(new LostState());
      StateMachine.instance().createState(new PauseState());
   }

   @Override
   public void init() {
      effects = new EffectsManager();
      effects.initOtgy(0);

      menuStage = new MenuStage(effects);
      gameStage = new GameStage(effects);
      lostStage = new LostStage(effects);
      StateMachine.instance().getState(MenuState.class).setStage(menuStage);
      StateMachine.instance().getState(LevelState.class).setStage(gameStage);
      StateMachine.instance().getState(LostState.class).setStage(lostStage);
      StateMachine.instance().enterState(MenuState.class);

      TileSprites.init();
   }

   @Override
   public void draw(float delta) {
      StateMachine.instance().getState(GlobalState.class).getEvents().tick(delta);
      effects.update(delta);
      // if (currentStage == null) {
      // selectStage(menuStage);
      // }
      Stage current = StateMachine.instance().getCurrentState().getStage();
      if (current != null) {
         Gdx.input.setInputProcessor(current);
         current.draw();
      }
   }

   @Override
   public void dispose() {
      super.dispose();
   }
}
