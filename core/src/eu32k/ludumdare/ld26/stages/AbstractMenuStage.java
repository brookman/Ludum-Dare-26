package eu32k.ludumdare.ld26.stages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import eu32k.ludumdare.ld26.effects.EffectsManager;
import eu32k.ludumdare.ld26.rendering.Background;

public abstract class AbstractMenuStage extends AbstractStage {
   private Table table;
   private List<Actor> actor = new ArrayList<Actor>();

   public AbstractMenuStage(EffectsManager effects) {
      this.effects = effects;

      table = new Table();
      table.setFillParent(true);
      table.center();

      addActor(table);
   }

   protected abstract void updateColors(Color color);

   @Override
   public void draw() {
      updateColors(effects.getCurrentColor());
      Background.draw(effects.getCurrentColor(), false);
      super.draw();
   }
}
