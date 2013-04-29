package eu32k.ludumdare.ld26.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.MultiLayerSprite;
import eu32k.ludumdare.ld26.effects.particles.GameObjectParticleEffect;

public class Goal extends GameObject {

   public static final float WIDTH = 0.4f, HEIGHT = 0.4f;

   private GameObjectParticleEffect particleEffect;

   public Goal(float x, float y) {
      super(new Vector2(x - WIDTH / 2.0f, y - WIDTH / 2.0f), WIDTH, HEIGHT);

      particleEffect = new GameObjectParticleEffect(this, "particles/goal.txt");
   }

   @Override
   public MultiLayerSprite loadSprite() {
      Texture layer1tex = Textures.get("textures/square.png");
      Texture layer2tex = Textures.get("textures/square2.png");
      TextureRegion layer1reg = new TextureRegion(layer1tex);
      TextureRegion layer2reg = new TextureRegion(layer2tex);

      MultiLayerSprite sprite = new MultiLayerSprite(layer1reg, layer2reg);
      sprite.setSize(WIDTH, HEIGHT);
      return sprite;
   }

   public GameObjectParticleEffect getEffect() {
      return particleEffect;
   }

   @Override
   public float radius() {
      return (WIDTH + HEIGHT) / 4;
   }
}
