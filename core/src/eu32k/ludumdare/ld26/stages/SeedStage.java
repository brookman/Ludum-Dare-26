package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;

import eu32k.libgdx.common.Assets;
import eu32k.libgdx.common.KeyPressEvent;
import eu32k.libgdx.common.Profile;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class SeedStage extends AbstractStage {

   private Image title;
   private TextButton challengeButton;
   private TextButton exitButton;
   private TextField seed;
   private Slider minWidthSlider;
   private Slider maxWidthSlider;
   private Slider minHeightSlider;
   private Slider maxHeightSlider;
   private Slider levelCountSlider;
   private Slider startingSpeedSlider;
   private Label minWidthLabel;
   private Label maxWidthLabel;
   private Label minHeightLabel;
   private Label maxHeightLabel;
   private Label seedLabel;
   private Label levelCountLabel;
   private Label startingSpeedLabel;
   private Label spanLabel;
   private Label emptyLabel;
   
   private KeyPressEvent keyBack;
   private KeyPressEvent keyStart;
   private KeyPressEvent keyAndroidBack;
   private Profile profile;
   
   public SeedStage(EffectsManager effects) {
      this.effects = effects;

      keyBack = new KeyPressEvent(Input.Keys.ESCAPE) {
         
         @Override
         public void onRelease() {
            backToMainMenu();
         }
         
         @Override
         public void onPress() {
            // TODO Auto-generated method stub
            
         }
      };
      
      keyStart = new KeyPressEvent(Input.Keys.ENTER) {
         
         @Override
         public void onRelease() {
            MenuStage.seedMode();
         }
         
         @Override
         public void onPress() {
            
            
         }
      };
      
      keyAndroidBack = new KeyPressEvent(Input.Keys.BACK) {
         
         @Override
         public void onRelease() {
            backToMainMenu();
         }
         
         @Override
         public void onPress() {
            // TODO Auto-generated method stub
            
         }
      };
     
      
      Table table = new Table();
      table.setFillParent(true);
      table.center();

      title = new Image(new TextureRegion(Assets.MANAGER.get("textures/title.png", Texture.class), 256, 64));

      challengeButton = new TextButton("Start Seed Mode", skin);
      challengeButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            try {
               MenuStage.seedMode();
            } catch (Exception e) {
               // ignore
            }
            return false;
         }
      });

      exitButton = new TextButton("Return to Main Menu", skin);
      exitButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            backToMainMenu();

            return false;
         }
      });

      seed = new TextField("31337", skin) {
         @Override
         public float getPrefWidth() {
            return 100;
            
         }

      };

      GlobalState state = StateMachine.instance().getState(GlobalState.class);
      profile = state.getProfileService().retrieveProfile();
      
      minWidthSlider = new Slider(3, 20, 1, false, skin);
      maxWidthSlider = new Slider(3, 20, 1, false, skin);
      minHeightSlider = new Slider(3, 15, 1, false, skin);
      maxHeightSlider = new Slider(3, 15, 1, false, skin);
      levelCountSlider = new Slider(5, 100, 5, false, skin);
      startingSpeedSlider = new Slider(0f, 1f, 0.1f, false, skin);

      minWidthSlider.setValue(profile.minWidth);
      maxWidthSlider.setValue(profile.maxWidth);
      minHeightSlider.setValue(profile.minHeight);
      maxHeightSlider.setValue(profile.maxHeight);
      startingSpeedSlider.setValue(profile.speed);
      levelCountSlider.setValue(profile.levels);
      seed.setText(Long.toString(profile.seed));
      
      addSliderListener(minWidthSlider);
      addSliderListener(maxWidthSlider);
      addSliderListener(minHeightSlider);
      addSliderListener(maxHeightSlider);
      addSliderListener(startingSpeedSlider);
      addSliderListener(levelCountSlider);

      
      minWidthLabel = new Label("Min Width", skin);
      maxWidthLabel = new Label("Max Width", skin);
      minHeightLabel = new Label("Min Height", skin);
      maxHeightLabel = new Label("Max Height", skin);
      levelCountLabel = new Label("", skin);
      startingSpeedLabel = new Label("", skin);
      seedLabel = new Label("Seed", skin);
      spanLabel = new Label("                                          ", skin);
      emptyLabel = new Label("", skin);
      seed.addListener(new EventListener() {
         
         @Override
         public boolean handle(Event event) {
            seedSettingChanged();
            return false;
         }
      });
      seed.setTextFieldFilter(new TextFieldFilter() {
         @Override
         public boolean acceptChar(TextField textField, char key) {
            int code = key;
            return code >= 48 && code <= 57;
         }
      });

      int padding = 4;

      table.add(title).center().pad(padding).colspan(4);
      table.row();
      table.add(challengeButton).fill().pad(padding).colspan(4);
      table.row();
      
      table.add(seedLabel).pad(padding);
      table.add(seed).fill().pad(padding).colspan(3);
      table.row();
      table.add(minWidthLabel).pad(padding);
      table.add(minWidthSlider).fill().pad(padding);
      table.add(maxWidthLabel).pad(padding);
      table.add(maxWidthSlider).fill().pad(padding);
      table.row();
      table.add(minHeightLabel).pad(padding);
      table.add(minHeightSlider).fill().pad(padding);
      table.add(maxHeightLabel).pad(padding);
      table.add(maxHeightSlider).fill().pad(padding);
      table.row();
      table.add(levelCountLabel).pad(padding);
      table.add(levelCountSlider).fill().pad(padding);
      table.add(startingSpeedLabel).pad(padding);
      table.add(startingSpeedSlider).fill().pad(padding);
      table.row();
      table.add(spanLabel).fill();
      table.add(emptyLabel);
      table.add(spanLabel).fill();
      table.add(emptyLabel);
      table.row();
      table.add(exitButton).fill().pad(padding).padTop(padding * 4).colspan(4);

      table.row();
      //table.setWidth(2f);

      addActor(table);
      seedSettingChanged();
   }

   private void addSliderListener(Slider s) {
      s.addListener(new EventListener() {         
         @Override
         public boolean handle(Event event) {
            seedSettingChanged();
            return false;
         }
      });
   }

   private void seedSettingChanged() {
      minWidthLabel.setText("Min Width: " + ((int)minWidthSlider.getValue()));
      maxWidthLabel.setText("Max Width: " + ((int)maxWidthSlider.getValue()));
      minHeightLabel.setText("Min Height: " + ((int)minHeightSlider.getValue()));
      maxHeightLabel.setText("Max Height: " + ((int)maxHeightSlider.getValue()));
      levelCountLabel.setText("Levels: " + ((int) levelCountSlider.getValue()));
      startingSpeedLabel.setText("Game Speed: " + getGameSpeedName());

      profile.minWidth = (int) minWidthSlider.getValue();
      profile.maxWidth = (int) maxWidthSlider.getValue();
      profile.minHeight = (int) minHeightSlider.getValue();
      profile.maxHeight = (int) maxHeightSlider.getValue();
      profile.speed = startingSpeedSlider.getValue();
      profile.levels = (int) levelCountSlider.getValue();
      try
      {
         profile.seed = Long.parseLong(seed.getText());
      }
      catch(Exception e){
         profile.seed = 0;
      }
   }

   private String getGameSpeedName() {
      String speedName = "";
      float speed = startingSpeedSlider.getValue();
      if(speed <= 0.2f)
      {
         speedName = "Slow";
      }
      else if(speed <= 0.5f)
      {
         speedName = "Medium";
      }
      else if(speed <= 0.8f)
      {
         speedName = "Fast";
      }
      else
      {
         speedName = "Insane";
      }
      return speedName;
   }

   @Override
   public void draw() {

      keyStart.update();
      keyBack.update();
      keyAndroidBack.update();
      Color color = effects.getCurrentColor();
      challengeButton.setColor(color);
      exitButton.setColor(color);
      title.setColor(color);
      seed.setColor(color);
      seed.getStyle().fontColor.a = color.a;
      minWidthSlider.setColor(color);
      maxWidthSlider.setColor(color);
      minHeightSlider.setColor(color);
      maxHeightSlider.setColor(color);
      levelCountSlider.setColor(color);
      startingSpeedSlider.setColor(color);
      Background.draw(color, false);
      super.draw();
   }

   private void backToMainMenu() {
      StateMachine.instance().enterState(MenuState.class);
   }

}
