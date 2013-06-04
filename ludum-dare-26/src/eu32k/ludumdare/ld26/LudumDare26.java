package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.libgdx.SimpleGame;
import eu32k.libgdx.rendering.DynamicFrameBuffer;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.effects.SoundButton;
import eu32k.ludumdare.ld26.level.TileBoundingBoxes;
import eu32k.ludumdare.ld26.level.TileSprites;
import eu32k.ludumdare.ld26.recorder.Recorder;
import eu32k.ludumdare.ld26.stages.AbstractStage;
import eu32k.ludumdare.ld26.stages.FinishStage;
import eu32k.ludumdare.ld26.stages.GameStage;
import eu32k.ludumdare.ld26.stages.LostStage;
import eu32k.ludumdare.ld26.stages.MenuStage;
import eu32k.ludumdare.ld26.stages.PauseStage;
import eu32k.ludumdare.ld26.stages.SeedStage;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelLosingState;
import eu32k.ludumdare.ld26.state.LevelLostState;
import eu32k.ludumdare.ld26.state.LevelPauseState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.LevelWinningState;
import eu32k.ludumdare.ld26.state.LevelWonState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.SeedState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class LudumDare26 extends SimpleGame {

   private EffectsManager effects;

   private MenuStage menuStage;
   private GameStage gameStage;
   private LostStage lostStage;
   private FinishStage finishStage;
   private SeedStage seedStage;

   private AbstractStage pauseStage;

   private Recorder recorder;

   public LudumDare26() {
      this(null);
   }

   public LudumDare26(Recorder recorder) {
      super(false);
      this.recorder = recorder;

      StateMachine.instance().createState(new GlobalState());
      StateMachine.instance().createState(new MenuState());
      StateMachine.instance().createState(new LevelState());
      StateMachine.instance().createState(new LevelWinningState());
      StateMachine.instance().createState(new LevelWonState());
      StateMachine.instance().createState(new LevelLostState());
      StateMachine.instance().createState(new LevelLosingState());
      StateMachine.instance().createState(new LevelPauseState());
      StateMachine.instance().createState(new LevelInitState());
      StateMachine.instance().createState(new SeedState());
   }

   @Override
   public void init() {
      Preloader.preLoad();

      TileSprites.init();
      TileBoundingBoxes.init();
      SoundButton.init();

      effects = new EffectsManager();
      // effects.initOtgy(1000);
      effects.initBitbreak(1000);

      menuStage = new MenuStage(effects);
      gameStage = new GameStage(effects);
      lostStage = new LostStage(effects);
      finishStage = new FinishStage(effects);
      pauseStage = new PauseStage(effects);
      seedStage = new SeedStage(effects);
      StateMachine.instance().getState(MenuState.class).setStage(menuStage);
      StateMachine.instance().getState(SeedState.class).setStage(seedStage);
      StateMachine.instance().getState(LevelState.class).setStage(gameStage);
      StateMachine.instance().getState(LevelWinningState.class).setStage(gameStage);
      StateMachine.instance().getState(LevelLosingState.class).setStage(gameStage);
      StateMachine.instance().getState(LevelPauseState.class).setStage(gameStage);
      StateMachine.instance().getState(LevelInitState.class).setStage(gameStage);
      StateMachine.instance().getState(LevelLostState.class).setStage(lostStage);
      StateMachine.instance().getState(LevelWonState.class).setStage(finishStage);
      StateMachine.instance().getState(LevelPauseState.class).setStage(pauseStage);
      StateMachine.instance().enterState(MenuState.class);
      GlobalState gs = StateMachine.instance().getState(GlobalState.class);
      gs.setGravitySensorEnabled(Gdx.app.getType() == ApplicationType.Android);
   }

   @Override
   public void draw(float delta) {
      StateMachine.instance().getState(GlobalState.class).getEvents().tick(delta);
      effects.update(delta);

      Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      AbstractStage current = StateMachine.instance().getCurrentState().getStage();
      if (current != null) {
         Gdx.input.setInputProcessor(current);
         current.draw();
      }
      SoundButton.draw();

      if (recorder != null) {
         recorder.record(30);
      }
   }

   @Override
   public void dispose() {
      super.dispose();
   }

   @Override
   public void resize(int width, int height) {
      DynamicFrameBuffer.resetAllBuffers(width, height);

      menuStage.setViewport(width, height, false);
      lostStage.setViewport(width, height, false);
      finishStage.setViewport(width, height, false);
      pauseStage.setViewport(width, height, false);

      // aspectRatio = (float) width / (float) height;
      // resetCamera();
   }
}
