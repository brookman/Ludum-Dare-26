package eu32k.ludumdare.ld26;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

   public Vector2 position;
   public List<Rectangle> bounds;
   public float width, height;
   private Sprite sprite;

   public GameObject(Vector2 position, float width, float height) {
      this.position = position;
      bounds = new ArrayList<Rectangle>();
      this.width = width;
      this.height = height;
      sprite = loadSprite();
   }

   public abstract Sprite loadSprite();

   public abstract float getSpeed();

   public void draw(SpriteBatch batch) {
      sprite.draw(batch);
   }

   public void update() {
      sprite.setPosition(position.x, position.y);
      for (Rectangle r : bounds) {
         r.x = position.x - width / 2;
         r.y = position.y - height / 2;
      }
   }

   public void move(Vector2 velocity) {
      position.add(velocity);
   }
}
