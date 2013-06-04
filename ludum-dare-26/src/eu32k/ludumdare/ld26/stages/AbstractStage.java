package eu32k.ludumdare.ld26.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eu32k.ludumdare.ld26.effects.EffectsManager;

public abstract class AbstractStage extends Stage {
   private float runningTime = 0f;
   private float runningTimeSinceEnter = 0f;

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

   public void resetEnteredTimer() {
      runningTimeSinceEnter = 0f;
   }

   public float getRunningTime() {
      return runningTime;
   }
   
   public float getRunningTimeSinceEnter(){
      return runningTimeSinceEnter;
   }

   public void drawStage() {
      float deltaTime = Gdx.graphics.getDeltaTime();
      runningTime += deltaTime;
      runningTimeSinceEnter += deltaTime;
      draw();
   }
}