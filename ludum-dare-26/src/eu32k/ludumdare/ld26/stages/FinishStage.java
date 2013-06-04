package eu32k.ludumdare.ld26.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import eu32k.libgdx.common.KeyPressEvent;
import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.MenuState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class FinishStage extends AbstractStage {
   private float runningTime;
   
   
   private static final String TAP_MESSAGE = "Tap screen or press key to continue";
   private List<Label> list = new ArrayList<Label>();
   private Label tapLabel;
   private Label emptyLabel;

   private KeyPressEvent keyTap;
   
   public FinishStage(EffectsManager effects) {
      this.effects = effects;
      tapLabel = new Label("", skin);
      emptyLabel = new Label("                                                  ", skin);
      
      setStatistics();
      keyTap = new KeyPressEvent(Input.Keys.ANY_KEY) {
         
         @Override
         public void onRelease() {
            backToMainMenu();            
         }
         @Override
         public void onPress() {            
         }
      };
  }

   public void resetRunningTime(){
      runningTime = 0f;
   }
   
   public void setStatistics() {
      clear();
      Map<String, Integer> genericStatistics = StateMachine.instance().getState(PlayerState.class).genericStatistics;

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      for (String key : genericStatistics.keySet()) {
         Label nameField = new Label(key + ": ", skin);
         list.add(nameField);
         Label valueField = new Label("" + genericStatistics.get(key), skin);
         list.add(valueField);
         table.add(nameField).fill().pad(4);
         table.add(valueField).fill().pad(4);
         table.row();
      }
      list.add(tapLabel);
      table.row();
      table.add(emptyLabel).fill().colspan(2);
      table.row();
      table.add(tapLabel).fill().colspan(2);
      tapLabel.setText("");
      addActor(table);
   }
   
   @Override
   public void draw() {
      keyTap.update();
      runningTime += Gdx.graphics.getDeltaTime();
      if(runningTime > 2f){
         if(Gdx.input.isTouched()){
            backToMainMenu();

         }
         tapLabel.setText(TAP_MESSAGE);
      }
      
      Color color = effects.getCurrentColor();
      Background.draw(color, false);
      for (Label label : list) {
         label.setColor(color);
      }
      super.draw();
   }

   private void backToMainMenu() {
      StateMachine.instance().enterState(MenuState.class);
   }
}