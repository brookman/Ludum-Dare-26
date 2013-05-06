package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.level.LevelConfigSequence;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.LevelInitState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class MenuStage extends AbstractStage {

   private Image title;
   private TextButton challengeButton;
   private TextButton seedButton;
   private TextButton exitButton;
   private List widthList;
   private List heightList;
   private TextField seed;

   public MenuStage(EffectsManager effects) {
      this.effects = effects;

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(new TextureRegion(Textures.get("textures/title.png"), 256, 64));

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
               seedMode();
            } catch (Exception e) {
               // ignore
            }
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

      widthList = new List(new Object[] { 3, 4, 5, 6, 7, 8, 9, 10, 15 }, skin);
      heightList = new List(new Object[] { 3, 4, 5, 6, 7, 8, 9, 10, 15 }, skin);
      widthList.setSelectedIndex(7);
      heightList.setSelectedIndex(5);

      seed = new TextField("31337", skin) {
         @Override
         public float getPrefWidth() {
            return 100;
         }

      };

      seed.setTextFieldFilter(new TextFieldFilter() {
         @Override
         public boolean acceptChar(TextField textField, char key) {
            int code = key;
            return code >= 48 && code <= 57;
         }
      });

      int padding = 4;

      table.add(title).fill().pad(padding).colspan(4);
      table.row();
      table.add(challengeButton).fill().pad(padding).colspan(4);
      table.row();
      table.row();

      table.add(widthList).fill().pad(padding);
      table.add(heightList).fill().pad(padding);
      table.add(seed).fillX().pad(padding);
      table.add(seedButton).fillX().pad(padding);

      table.row();
      table.row();
      table.add(exitButton).fill().pad(padding).colspan(4);

      table.row();

      addActor(table);
   }

   @Override
   public void draw() {
      if (Gdx.input.isKeyPressed(Input.Keys.C)) {
         challengeMode();
      } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
         // seedMode();
      }
      Color color = effects.getCurrentColor();
      challengeButton.setColor(color);
      seedButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);
      widthList.setColor(color);
      heightList.setColor(color);
      seed.setColor(color);
      seed.getStyle().fontColor.a = color.a;

      Background.draw(color, false);
      super.draw();
   }

   private void startGame(long seed, int minWidth, int minHeight, int maxWidth, int maxHeight, int levelCount) {
      // TODO Auto-generated method stub
      LevelState levelState = StateMachine.instance().getState(LevelState.class);
      LevelConfigSequence levels = new LevelConfigSequence();
      LevelConfigSequence.addLevelsToSequence(levels, seed, minWidth, minHeight, maxWidth, maxHeight, levelCount);
      levelState.setLevels(levels);
      levelState.initGame();
      levelState.initLevel();
      StateMachine.instance().enterState(LevelInitState.class);
   }

   private void challengeMode() {
      startGame(43454, 4, 3, 12, 8, 25);
   }

   private void seedMode() {
      long seedValue = Long.parseLong(seed.getText());
      int width = Integer.parseInt(widthList.getSelection());
      int height = Integer.parseInt(heightList.getSelection());
      startGame(seedValue, width, height, width, height, 100);
   }

}
