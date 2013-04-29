package eu32k.ludumdare.ld26.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import eu32k.ludumdare.ld26.MultiLayerSprite;

public abstract class GameObject {
   private boolean freeMovement;
   
   public Vector2 position;
   private float width, height;
   private MultiLayerSprite sprite;

   public GameObject(Vector2 position, float width, float height) {
      this.position = position;
      this.width = width;
      this.height = height;
      sprite = loadSprite();
   }

   public abstract MultiLayerSprite loadSprite();

   public void draw(SpriteBatch batch) {
      sprite.setPosition(position.x, position.y);
      sprite.draw(batch);
   }

   public MultiLayerSprite getSprite() {
      return sprite;
   }

   public void setPosition(float x, float y) {
      position.x = x - width / 2f;
      position.y = y - height / 2f;
   }

   public float getX() {
      return position.x + width / 2f;
   }

   public float getY() {
      return position.y + height / 2f;
   }

   public boolean isFreeMovement() {
      return freeMovement;
   }

   public void setFreeMovement(boolean freeMovement) {
      this.freeMovement = freeMovement;
   }
   
   public abstract float radius();
   
   public boolean intersects(GameObject obj)
   {
      if(obj == null)
         return false;
      
      return isCircleCollision(getX(), getY(), radius(), obj.getX(), obj.getY(), obj.radius());
   }
   private boolean isCircleCollision(float x1, float y1, float r1, float x2, float y2, float r2)
   {
       final double a = r1 + r2;
       final double dx = x1 - x2;
       final double dy = x1 - x2;
       return a * a > (dx * dx + dy * dy);
   }
}
