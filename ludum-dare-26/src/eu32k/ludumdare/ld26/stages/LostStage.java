package eu32k.ludumdare.ld26.stages;

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
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class LostStage extends AbstractStage {

   private Image title;
   private TextButton retryButton;
   private TextButton backToMenuButton;
   private TextButton exitButton;

   private KeyPressEvent keyBackToMenu;
   private KeyPressEvent keyRetry;
   private KeyPressEvent keyAndroidBack;
   private KeyPressEvent keyAndroidMenu;

   public LostStage(EffectsManager effects) {
      this.effects = effects;

      keyBackToMenu = new KeyPressEvent(Input.Keys.ESCAPE) {
         @Override
         public void onRelease() {
            backToMenu();
         }
         @Override
         public void onPress() {
         }
      };
      keyRetry = new KeyPressEvent(Input.Keys.R) {
         @Override
         public void onRelease() {
            retry();
         }
         @Override
         public void onPress() {
         }
      };
      keyAndroidMenu= new KeyPressEvent(Input.Keys.MENU) {
         @Override
         public void onRelease() {
            backToMenu();
         }
         @Override
         public void onPress() {
         }
      };
      keyAndroidBack = new KeyPressEvent(Input.Keys.BACK) {
         @Override
         public void onRelease() {
            retry();
         }
         @Override
         public void onPress() {
         }
      };
      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(new TextureRegion(Assets.MANAGER.get("textures/title.png", Texture.class), 256, 64));

      retryButton = new TextButton("Retry", skin);
      retryButton.setColor(Color.CYAN);
      retryButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            retry();
            return false;
         }
      });

      backToMenuButton = new TextButton("Back to Menu", skin);
      backToMenuButton.setColor(Color.CYAN);
      backToMenuButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            backToMenu();
            return false;
         }

      });

      exitButton = new TextButton("Exit", skin);
      exitButton.setColor(Color.CYAN);
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
      table.add(retryButton).fill().pad(padding);
      table.row();
      table.add(backToMenuButton).fill().pad(padding).padTop(padding * 8);
      table.row();

      addActor(table);
   }

   public void backToMenu() {
      StateMachine.instance().enterState(MenuState.class);
   }

   @Override
   public void draw() {
      keyRetry.update();
      keyBackToMenu.update();
      keyAndroidBack.update();
      keyAndroidMenu.update();
      Color color = effects.getCurrentColor();
      retryButton.setColor(color);
      backToMenuButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);

      Background.draw(color, false);
      super.draw();
   }

   private void retry() {
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      levelState.events.clear();
      levelState.initLevel();
      levelState.addRetryStatistics();
      StateMachine.instance().enterState(LevelInitState.class);
   }

}
