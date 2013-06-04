package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import eu32k.libgdx.common.Assets;
import eu32k.libgdx.common.KeyPressEvent;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.level.LevelConfig;
import eu32k.ludumdare.ld26.level.LevelConfigSequence;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.SeedState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class MenuStage extends AbstractStage {

   private Image title;
   private TextButton challengeButton;
   private TextButton seedButton;
   private TextButton gravitySensorButton;
   private TextButton exitButton;

   private KeyPressEvent keyStartGame;
   private KeyPressEvent keySeed;
   private KeyPressEvent keyExit;
   
   private KeyPressEvent keyAndroidBack;
   
   
   public MenuStage(EffectsManager effects) {
      this.effects = effects;

      keyStartGame = new KeyPressEvent(Input.Keys.ENTER) {
         @Override
         public void onRelease() {
            challengeMode();
         }
         @Override
         public void onPress() {}
      };
      
      keySeed = new KeyPressEvent(Input.Keys.S) {
         @Override
         public void onRelease() {
            enterSeedMode();
         }
         @Override
         public void onPress() {}
      };
      keyExit = new KeyPressEvent(Input.Keys.ESCAPE) {
         @Override
         public void onRelease() {
            exitGame();
         }
         @Override
         public void onPress() {}
      };
      keyAndroidBack = new KeyPressEvent(Input.Keys.BACK) {
         @Override
         public void onRelease() {
            exitGame();
         }
         @Override
         public void onPress() {}
      };
      
      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(new TextureRegion(Assets.MANAGER.get("textures/title.png", Texture.class), 256, 64));

      challengeButton = new TextButton("Challenge mode", skin);
      challengeButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            // TODO: Move this shit into levelstate
            challengeMode();
            return false;
         }
      });

      seedButton = new TextButton("Seed mode", skin);
      seedButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            try {
               enterSeedMode();
            } catch (Exception e) {
               // ignore
            }
            return false;
         }
      });

      gravitySensorButton = new TextButton("Gravity Sensor: Enabled", skin);
      gravitySensorButton.addListener(new InputListener() {         
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            toggleGravitySensor();
            return false;
         }
      });
      
      exitButton = new TextButton("Exit", skin);
      exitButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            exitGame();
            return false;
         }

      });

      int padding = 4;

      table.add(title).center().pad(padding);
      table.row();
      table.add(challengeButton).fill().pad(padding);
      table.row();

      table.add(seedButton).fillX().pad(padding);
      if(Gdx.app.getType() == ApplicationType.Android){
         table.row();
         table.add(gravitySensorButton).fill().pad(padding).padTop(padding * 8);
      }

      
      table.row();
      table.add(exitButton).fill().pad(padding).padTop(padding * 8);

      table.row();

      addActor(table);
   }
   protected void toggleGravitySensor() {
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      state.setGravitySensorEnabled(!state.isGravitySensorEnabled());
      updateGravityButtonText();
   }
   public void updateGravityButtonText() {
      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      gravitySensorButton.setText("Gravity Sensor: " + (state.isGravitySensorEnabled() ? "Enabled" : "Disabled"));
   }
   private void exitGame() {
      Gdx.app.exit();
   }

   @Override
   public void draw() {
/*
      if (getRunningTimeSinceEnter() >= 0.5f) {
         if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            challengeMode();
         } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            enterSeedMode();
            // StateseedMode();
         }
      }
      */
      keyStartGame.update();
      keySeed.update();
      keyExit.update();
      keyAndroidBack.update();
      Color color = effects.getCurrentColor();
      challengeButton.setColor(color);
      seedButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);
      gravitySensorButton.setColor(color);

      Background.draw(color, false);
      super.draw();
   }

   private void enterSeedMode() {
      StateMachine.instance().enterState(SeedState.class);
   }

   private void startGame(long seed, LevelConfig from, LevelConfig to, int levelCount) {
      // TODO Auto-generated method stub
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      LevelConfigSequence levels = new LevelConfigSequence();
      LevelConfigSequence.addLevelsToSequence(levels, seed, from, to, levelCount);
      levelState.setLevels(levels);
      levelState.initGame();
      levelState.initLevel();
      StateMachine.instance().enterState(LevelInitState.class);
   }

   private void challengeMode() {
      LevelConfig from = new LevelConfig();
      from.width = 4;
      from.height = 3;
      from.spawnDistance = 0;
      from.firstTileSpawnDelay = 5f;
      from.tileSpawnInterval = 2f;
      from.tileFadeTime = 1f;
      LevelConfig to = new LevelConfig();
      to.width = 20;
      to.height = 15;
      to.spawnDistance = 2;
      to.firstTileSpawnDelay = 0f;
      to.tileSpawnInterval = 0f;
      to.tileFadeTime = 0.1f;
      startGame(63463333, from, to, 25);
   }

}
