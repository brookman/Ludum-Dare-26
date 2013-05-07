package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eu32k.ludumdare.ld26.effects.EffectsManager;

public abstract class AbstractStage extends Stage {

   protected EffectsManager effects;
   protected static Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

   public AbstractStage() {
      super();
   }

   public AbstractStage(float width, float height, boolean keepAspectRatio) {
      super(width, height, keepAspectRatio);
   }

   public AbstractStage(float width, float height, boolean keepAspectRatio, SpriteBatch batch) {
      super(width, height, keepAspectRatio, batch);
   }

   public EffectsManager getEffects() {
      return effects;
   }
}