package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class PauseStage extends AbstractStage {

   private Image title;
   private TextButton continueButton;
   private TextButton menuButton;
   private TextButton exitButton;

   private LevelState levelState;

   public PauseStage(EffectsManager effects) {
      this.effects = effects;

      Table table = new Table();
      table.setFillParent(true);
      table.center();
      levelState = StateMachine.instance().getState(LevelState.class);

      title = new Image(new TextureRegion(Textures.get("textures/title.png"), 256, 64));

      continueButton = new TextButton("Continue", skin);
      continueButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            levelState.setRunning(true);
            levelState.setPaused(false);
            StateMachine.instance().enterState(LevelState.class);
            return false;
         }
      });

      menuButton = new TextButton("Back to Menu", skin);
      menuButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            levelState.setRunning(false);
            levelState.setPaused(true);
            StateMachine.instance().enterState(MenuState.class);
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

      table.row();
      table.add(exitButton).fill().pad(padding);

      table.row();

      addActor(table);
   }

   @Override
   public void draw() {
      Color color = effects.getCurrentColor();
      continueButton.setColor(color);
      menuButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);

      Background.draw(color, false);
      super.draw();
   }

}
