package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import eu32k.libgdx.common.Assets;
import eu32k.libgdx.common.KeyPressEvent;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class PauseStage extends AbstractStage {
   private Image title;
   private TextButton continueButton;
   private TextButton menuButton;
   private TextButton gravityButton;
   private TextButton exitButton;

   private KeyPressEvent keyPause;
   private KeyPressEvent keyPause2;
   private KeyPressEvent keyMainMenu;
   private KeyPressEvent keyMainMenu2;
   
   private LevelState levelState;

   public PauseStage(EffectsManager effects) {
      this.effects = effects;
      
      keyPause = new KeyPressEvent(Input.Keys.ESCAPE) {         
         @Override
         public void onRelease() {
            doContinue();
         }
         @Override
         public void onPress() {
         }
      };
      keyPause2 = new KeyPressEvent(Input.Keys.P) {         
         @Override
         public void onRelease() {
            doContinue();
         }
         @Override
         public void onPress() {
         }
      };
      
      keyMainMenu = new KeyPressEvent(Input.Keys.X) {
         @Override
         public void onRelease() {
            doExit();
         }       
         @Override
         public void onPress() {
         }
      };

      keyMainMenu2 = new KeyPressEvent(Input.Keys.Q) {
         @Override
         public void onRelease() {
            doExit();
         }       
         @Override
         public void onPress() {
         }
      };

      Table table = new Table();
      table.setFillParent(true);
      table.center();
      levelState = StateMachine.instance().getState(LevelState.class);

      title = new Image(new TextureRegion(Assets.MANAGER.get("textures/title.png", Texture.class), 256, 64));
      continueButton = new TextButton("Continue", skin);
      continueButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            doContinue();
            return false;
         }
      });

      menuButton = new TextButton("Back to Menu", skin);
      menuButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            doExit();
            return false;
         }
      });
      gravityButton = new TextButton("Gravity Sensor: Enabled", skin);
      gravityButton.addListener(new InputListener() {
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
            Gdx.app.exit();
            return false;
         }
      });

      int padding = 4;

      table.add(title).fill().pad(padding);
      table.row();
      table.add(continueButton).fill().pad(padding);
      table.row();
      table.add(menuButton).fill().pad(padding);

    if(Gdx.app.getType() == ApplicationType.Android){
      table.row();
      table.add(gravityButton).fill().pad(padding).padTop(padding * 8);
     }


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
      gravityButton.setText("Gravity Sensor: " + (state.isGravitySensorEnabled() ? "Enabled" : "Disabled"));
   }

   public void doContinue() {
      levelState.setRunning(true);
      levelState.setPaused(false);
      StateMachine.instance().enterState(LevelState.class);
   }

   public void doExit() {
      levelState.setRunning(false);
      levelState.setPaused(true);
      StateMachine.instance().enterState(MenuState.class);
   }

   @Override
   public void draw() {
      keyMainMenu.update();
      keyMainMenu2.update();
      keyPause.update();
      keyPause2.update();
      Color color = effects.getCurrentColor();
      continueButton.setColor(color);
      menuButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);

      Background.draw(color, false);
      super.draw();
   }

}
