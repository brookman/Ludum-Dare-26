package eu32k.ludumdare.ld26;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.rendering.Textures;

public class Player extends GameObject {

   public static final float WIDTH = 4, HEIGHT = 4;
   public static final float SPEED = 30;

   public Player(float x, float y) {
      super(new Vector2(x - WIDTH / 2.0f, y - WIDTH / 2.0f), WIDTH, HEIGHT);
   }

   @Override
   public Sprite loadSprite() {
      Texture texture = Textures.get("textures/shroom.png");
      Sprite sprite = new Sprite(texture);
      sprite.setSize(WIDTH, HEIGHT);

      return sprite;
   }

   public void move(Vector2 velocity, List<Tile> tiles) {
      Vector2 scaledVelocity = velocity.cpy().mul(SPEED);

      Vector2 xPart = new Vector2(scaledVelocity.x, 0.0f);
      Vector2 yPart = new Vector2(0.0f, scaledVelocity.y);

      Vector2 newPos = position.cpy().add(xPart);

      if (canMove(newPos, tiles)) {
         position = newPos;
      }

      newPos = position.cpy().add(yPart);

      if (canMove(newPos, tiles)) {
         position = newPos;
      }
   }

   private boolean canMove(Vector2 newPos, List<Tile> tiles) {
      Rectangle playerBounds = getBounds(newPos);
      for (Tile tile : tiles) {
         for (Rectangle tileBound : tile.getBounds()) {
            if (playerBounds.overlaps(tileBound)) {
               return false;
            }

         }
      }
      return true;
   }
}
