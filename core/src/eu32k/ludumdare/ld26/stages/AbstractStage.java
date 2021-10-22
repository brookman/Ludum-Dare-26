package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import eu32k.ludumdare.ld26.effects.EffectsManager;

public abstract class AbstractStage extends Stage {
   protected EffectsManager effects;
   protected static Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

   public AbstractStage() {
//      super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()));
      super(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()));
   }

   public EffectsManager getEffects() {
      return effects;
   }
}