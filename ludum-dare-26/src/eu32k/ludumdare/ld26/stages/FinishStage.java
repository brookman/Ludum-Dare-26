package eu32k.ludumdare.ld26.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class FinishStage extends Stage {

   private Skin skin;

   private EffectsManager effects;

   private List<Label> list = new ArrayList<Label>();

   public FinishStage(EffectsManager effects) {
      this.effects = effects;

      skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

      Table table = new Table();
      table.setFillParent(true);
      table.center();

      Map<String, Integer> genericStatistics = StateMachine.instance().getState(PlayerState.class).genericStatistics;

      for (String key : genericStatistics.keySet()) {
         Label nameField = new Label(key + ": ", skin);
         list.add(nameField);
         Label valueField = new Label("" + genericStatistics.get(key), skin);
         list.add(valueField);
         table.add(nameField).fill().pad(4);
         table.add(valueField).fill().pad(4);
         table.row();
      }

      addActor(table);
   }

   @Override
   public void draw() {
      Color color = effects.getCurrentColor();
      Background.getInstance().draw(new Vector3(color.r, color.g, color.b), false);
      for (Label label : list) {
         label.setColor(color);
      }
      super.draw();
   }
}