package eu32k.ludumdare.ld26.effects.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu32k.ludumdare.ld26.objects.GameObject;

public class GameObjectParticleEffect extends ParticleEffect {

   private GameObject gameObject;

   public GameObjectParticleEffect(GameObject gameObject, String path) {
      this.gameObject = gameObject;
      load(Gdx.files.internal(path), Gdx.files.internal("textures"));
      start();
   }

   public void draw(SpriteBatch batch, Color color, float delta) {
      setPosition(gameObject.getX(), gameObject.getY());
      getEmitters().get(0).getTint().setColors(new float[] { color.r, color.g, color.b, 1.0f });
      super.draw(batch, delta);

   }
}
