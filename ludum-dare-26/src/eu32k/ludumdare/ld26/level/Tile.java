package eu32k.ludumdare.ld26.level;

import java.util.HashMap;
import java.util.Map;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.MultiLayerSprite;

public class Tile {

   public enum Type {
      L, I, X, T // L Shape, I Shape, X Shape (+ Shape), T Shape
   }

   public enum Rotation {
      R, L, U, D // Right, Left, Up, Down
   }

   public static float SIZE = 1.0f;

   public Type type;
   public Rotation rotation;

   private float x, y;

   private boolean isMoving;
   private Map<Direction, Tile> neighbors;

   private float alpha;

   public Tile(float x, float y, Type type, Rotation rotation) {
      this.type = type;
      this.rotation = rotation;
      this.x = x;
      this.y = y;
      alpha = 1f;

      neighbors = new HashMap<Direction, Tile>();
   }

   public boolean isMoving() {
      return isMoving;
   }

   public void setMoving(boolean isMoving) {
      this.isMoving = isMoving;
   }

   public Map<Direction, Tile> getNeighbors() {
      return neighbors;
   }

   public void setNeighbors(Map<Direction, Tile> neighbors) {
      this.neighbors = neighbors;
   }

   public float getX() {
      return x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return y;
   }

   public void setY(float y) {
      this.y = y;
   }

   public Type getType() {
      return type;
   }

   public Rotation getRotation() {
      return rotation;
   }

   public boolean contains(float tx, float ty) {
      return !(tx < x || tx >= x + 1 || ty < y || ty >= y + 1);
   }

   public void setAlpha(float currentAlpha) {
      alpha = currentAlpha;
   }

   public float getAlpha() {
      return alpha;
   }

   public MultiLayerSprite getSprite() {
      MultiLayerSprite sprite = TileSprites.getSprite(type, rotation);
      sprite.setPosition(x, y);
      return sprite;
   }

   public Bounds getBounds() {
      return TileBoundingBoxes.getNormalizedBounds(type, rotation);
   }
}
