package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.LudumDare26;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class MenuStage extends Stage {

   private Skin skin;

   public MenuStage() {
      skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      Image title = new Image(Textures.get("textures/title.png"));

      TextButton challengeButton = new TextButton("Challenge mode", skin);
      challengeButton.setColor(Color.CYAN);
      challengeButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            StateMachine.instance().enterState(LevelState.class);
            return false;
         }
      });

      TextButton seedButton = new TextButton("Seed mode", skin);
      seedButton.setColor(Color.CYAN);
      seedButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            return false;
         }
      });

      TextButton exitButton = new TextButton("Exit", skin);
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
      Background.getInstance().draw();
      super.draw();
   }

}
