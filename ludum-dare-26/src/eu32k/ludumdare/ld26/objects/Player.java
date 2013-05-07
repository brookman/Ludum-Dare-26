package eu32k.ludumdare.ld26.objects;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.common.Assets;
import eu32k.libgdx.common.TempVector2;
import eu32k.ludumdare.ld26.MultiLayerSprite;
import eu32k.ludumdare.ld26.effects.particles.GameObjectParticleEffect;
import eu32k.ludumdare.ld26.level.Bounds;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.level.TileBoundingBoxes;

public class Player extends GameObject {

   public static final float RADIUS = 0.1f;
   public static final float SPEED = 1.4f;

   public static final float WIDTH = RADIUS * 2.0f, HEIGHT = RADIUS * 2.0f;

   private boolean movingWithTile = false;

   private GameObjectParticleEffect particleEffect;

   public Player(float x, float y) {
      super(new Vector2(x - WIDTH / 2.0f, y - WIDTH / 2.0f), WIDTH, HEIGHT);
      particleEffect = new GameObjectParticleEffect(this, "particles/player.txt");
   }

   @Override
   public MultiLayerSprite loadSprite() {

      Texture layer1tex = Assets.MANAGER.get("textures/circle.png", Texture.class);
      Texture layer2tex = Assets.MANAGER.get("textures/circle2.png", Texture.class);
      TextureRegion layer1reg = new TextureRegion(layer1tex);
      TextureRegion layer2reg = new TextureRegion(layer2tex);

      MultiLayerSprite sprite = new MultiLayerSprite(layer1reg, layer2reg);
      sprite.setSize(WIDTH, HEIGHT);
      return sprite;
   }

   public void move(float xVelocity, float yVelocity, List<Tile> tiles) {

      Vector2 xPart = TempVector2.tmp.set(xVelocity * SPEED, 0.0f);
      Vector2 newPos = xPart.add(position);

      if (canMove(newPos, tiles)) {
         position.set(newPos);
      }

      Vector2 yPart = TempVector2.tmp.set(0.0f, yVelocity * SPEED);
      newPos = yPart.add(position);

      if (canMove(newPos, tiles)) {
         position.set(newPos);
      }
   }

   public static boolean canMove(Vector2 newPos, List<Tile> tiles) {
      Vector2 posShifted = TempVector2.tmp2.set(newPos.x - RADIUS / 2.0f, newPos.y - RADIUS / 2.0f);

      for (Tile tile : tiles) {
         if (!canMoveIntoTile(posShifted, tile)) {
            return false;
         }
      }
      return true;
   }

   public static boolean canMoveIntoTile(Vector2 posShifted, Tile tile) {
      if (!tile.isInUse()) {
         return true;
      }
      Bounds bounds = TileBoundingBoxes.getNormalizedBounds(tile.getType(), tile.getRotation());

      if (Math.abs(tile.getX() - posShifted.x) > 1.5f || Math.abs(tile.getY() - posShifted.y) > 1.5f) {
         return true;
      }

      for (Rectangle tileBound : bounds.boundingBoxes) {
         if (intersects(posShifted, RADIUS, tileBound, tile)) {
            return false;
         }
      }
      return true;
   }

   private static boolean intersects(Vector2 circlePos, float radius, Rectangle rect, Tile tile) {

      Vector2 circleDistance = TempVector2.tmp3.set(Math.abs(circlePos.x - (rect.x + tile.getX())), Math.abs(circlePos.y - (rect.y + tile.getY())));

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

   public boolean isMovingWithTile() {
      return movingWithTile;
   }

   public void setMovingWithTile(boolean movingWithTile) {
      this.movingWithTile = movingWithTile;
   }

   public Vector2 getShiftedPosition() {
      return position;
   }

   public GameObjectParticleEffect getEffect() {
      return particleEffect;
   }

   @Override
   public float radius() {
      return RADIUS;
   }
}
