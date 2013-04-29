package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.level.LevelConfigSequence;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class MenuStage extends Stage {

   private Skin skin;

   private EffectsManager effects;

   private Image title;
   private TextButton challengeButton;
   private TextButton seedButton;
   private TextButton exitButton;

   public MenuStage(EffectsManager effects) {
      this.effects = effects;

      skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(new TextureRegion(Textures.get("textures/title.png"), 256, 64));

      challengeButton = new TextButton("Challenge mode", skin);
      challengeButton.setColor(Color.CYAN);
      challengeButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //TODO: Move this shit into levelstate
            LevelState levelState = StateMachine.instance().getState(LevelState.class);
            LevelConfigSequence levels = new LevelConfigSequence();
            LevelConfigSequence.addLevelsToSequence(levels, 9388, 4, 4, 12, 8, 10);
            levelState.setLevels(levels);
            levelState.initLevel();
            StateMachine.instance().enterState(LevelState.class);
            return false;
         }
      });

      seedButton = new TextButton("Seed mode", skin);
      seedButton.setColor(Color.CYAN);
      seedButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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
      table.add(challengeButton).fill().pad(padding);
      table.row();
      table.add(seedButton).fill().pad(padding);
      table.row();
      table.add(exitButton).fill().pad(padding);
      table.row();

      addActor(table);
   }

   @Override
   public void draw() {
      Color color = effects.getCurrentColor();
      challengeButton.setColor(color);
      seedButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);

      Background.getInstance().draw(new Vector3(color.r, color.g, color.b), false);
      super.draw();
   }

}
