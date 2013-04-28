package eu32k.ludumdare.ld26;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

   protected Vector2 position;
   private float width, height;
   private MultiLayerSprite sprite;

   public GameObject(Vector2 position, float width, float height) {
      this.position = position;
      this.width = width;
      this.height = height;
      sprite = loadSprite();
   }

   public abstract Texture[] getTextures();

   public abstract MultiLayerSprite loadSprite();

   public void draw(SpriteBatch batch) {
      sprite.setPosition(position.x, position.y);
      sprite.draw(batch);
   }

   public Rectangle getBounds(Vector2 position) {
      return new Rectangle(position.x, position.y, width, height);
   }

   public MultiLayerSprite getSprite() {
      return sprite;
   }

   public Vector2 getPosition() {
      return position;
   }
}
