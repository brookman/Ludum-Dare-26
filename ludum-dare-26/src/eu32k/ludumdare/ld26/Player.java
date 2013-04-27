package eu32k.ludumdare.ld26;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import eu32k.ludumdare.ld26.util.CollisionUtil;

public class Player extends GameObject {

   public static final float WIDTH = 4, HEIGHT = 4;
   public static final float SPEED = 30;

   public Player(int x, int y) {
      super(new Vector2(x, y), WIDTH, HEIGHT);
   }

   @Override
   public Sprite loadSprite() {
      Texture texture = new Texture(Gdx.files.internal("textures/shroom.png"));
      Sprite sprite = new Sprite(texture);
      sprite.setSize(WIDTH, HEIGHT);

      return sprite;
   }

   public void update(List<Tile2> tiles) {
      // super.update();
      //
      // for (Tile2 tile : tiles) {
      // for (Rectangle tileBound : tile.getBounds()) {
      // for (Rectangle playerBound : bounds) {
      // if (CollisionUtil.rectanglesOverlap(tileBound, playerBound)) {
      //
      // System.out.println("COLLISION");
      // }
      // }
      // }
      // }
   }

   public void move(Vector2 velocity, Tile2[][] tiles) {
      Vector2 scaledVelocity = velocity.cpy().mul(SPEED);

      Vector2 xPart = new Vector2(scaledVelocity.x, 0.0f);
      Vector2 yPart = new Vector2(0.0f, scaledVelocity.y);

      if (tryToMove(position.cpy().add(xPart), tiles)) {
         position.add(scaledVelocity);
      }

      if (tryToMove(position.cpy().add(yPart), tiles)) {
         position.add(scaledVelocity);
      }
   }

   private boolean tryToMove(Vector2 newPos, Tile2[][] tiles) {
      Rectangle bounds = getBounds(newPos);

      for (Tile2[] tile1 : tiles) {
         for (Tile2 tile : tile1) {
            for (Rectangle tileBound : tile.getBounds()) {
               if (CollisionUtil.rectanglesOverlap(tileBound, bounds)) {
                  return false;
               }
            }
         }
      }
      return true;
   }
}
