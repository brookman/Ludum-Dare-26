package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import eu32k.libgdx.SimpleGame;
import eu32k.ludumdare.ld26.stages.GameStage;
import eu32k.ludumdare.ld26.stages.MenuStage;

public class LudumDare26 extends SimpleGame {

   private MenuStage menuStage;
   private GameStage gameStage;
   private Stage currentStage;

   public LudumDare26() {
      super(false);
   }

   public void startGame() {
      selectStage(gameStage);
   }

   private void selectStage(Stage stage) {
      Gdx.input.setInputProcessor(stage);
      currentStage = stage;
   }

   @Override
   public void init() {
      menuStage = new MenuStage(this);
      gameStage = new GameStage();
   }

   @Override
   public void draw(float delta) {

      if (currentStage == null) {
         selectStage(menuStage);
      }
      currentStage.draw();
   }

   @Override
   public void dispose() {
      super.dispose();
   }
}
