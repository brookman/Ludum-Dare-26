package eu32k.ludumdare.ld26;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.level.Tile;

public class Player extends GameObject {

   public static final float WIDTH = 0.2f, HEIGHT = 0.2f;
   public static final float RADIUS = 0.1f;
   public static final float SPEED = 0.7f;
   private Texture[] textures = new Texture[2];

   public Player(float x, float y) {
      super(new Vector2(x - WIDTH / 2.0f, y - WIDTH / 2.0f), WIDTH, HEIGHT);
   }

   @Override
   public MultiLayerSprite loadSprite() {

      Texture layer1tex = Textures.get("textures/circle.png");
      Texture layer2tex = Textures.get("textures/circle2.png");
      TextureRegion layer1reg = new TextureRegion(layer1tex);
      TextureRegion layer2reg = new TextureRegion(layer2tex);

      MultiLayerSprite sprite = new MultiLayerSprite(layer1reg, layer2reg);
      sprite.setSize(WIDTH, HEIGHT);
      return sprite;
   }

   @Override
   public Texture[] getTextures() {
      return textures;
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
      Vector2 posShifted = new Vector2(newPos.x - RADIUS / 2.0f, newPos.y - RADIUS / 2.0f);
      for (Tile tile : tiles) {
         for (Rectangle tileBound : tile.getBounds()) {
            if (intersects(posShifted, RADIUS, tileBound)) {
               return false;
            }

         }
      }
      return true;
   }

   private boolean intersects(Vector2 circlePos, float radius, Rectangle rect) {
      Vector2 circleDistance = new Vector2(Math.abs(circlePos.x - rect.x), Math.abs(circlePos.y - rect.y));

      if (circleDistance.x > rect.width / 2 + radius) {
         return false;
      }
      if (circleDistance.y > rect.height / 2 + radius) {
         return false;
      }

      if (circleDistance.x <= rect.width / 2) {
         return true;
      }
      if (circleDistance.y <= rect.height / 2) {
         return true;
      }

      float cornerDistanceSq = (circleDistance.x - rect.width / 2) * (circleDistance.x - rect.width / 2) + (circleDistance.y - rect.height / 2) * (circleDistance.y - rect.height / 2);

      return cornerDistanceSq <= radius * radius;
   }
}
