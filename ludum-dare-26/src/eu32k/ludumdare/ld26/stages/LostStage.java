package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class LostStage extends Stage {

   private Skin skin;

   private EffectsManager effects;

   private Image title;
   private TextButton challengeButton;
   private TextButton seedButton;
   private TextButton exitButton;

   public LostStage(EffectsManager effects) {
      this.effects = effects;

      skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(Textures.get("textures/title.png"));

      challengeButton = new TextButton("Retry", skin);
      challengeButton.setColor(Color.CYAN);
      challengeButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            StateMachine.instance().getState(LevelState.class).initLevel(5, 5);
            StateMachine.instance().enterState(LevelState.class);
            return false;
         }
      });

      seedButton = new TextButton("Back to Menu", skin);
      seedButton.setColor(Color.CYAN);
      seedButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            StateMachine.instance().enterState(MenuState.class);
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
