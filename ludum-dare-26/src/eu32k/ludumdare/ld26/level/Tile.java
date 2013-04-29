package eu32k.ludumdare.ld26.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;

import eu32k.ludumdare.ld26.Direction;

public class Tile {

   public enum Type {
      L, I, X, T // L Shape, I Shape, X Shape (+ Shape), T Shape
   }

   public enum Rotation {
      R, L, U, D // Right, Left, Up, Down
   }

   public static float SIZE = 1.0f;
   private static float BOUND_SIZE = SIZE / 3.0f; // 0.333333333

   private static boolean[][][] boxes = new boolean[4][4][9];
   static {
      boxes[Type.L.ordinal()][Rotation.R.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            false, true, true,//
            false, false, false,//
      };
      boxes[Type.L.ordinal()][Rotation.L.ordinal()] = new boolean[] { //
      //
            false, false, false,//
            true, true, false,//
            false, true, false,//
      };
      boxes[Type.L.ordinal()][Rotation.U.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, false,//
            false, false, false,//
      };
      boxes[Type.L.ordinal()][Rotation.D.ordinal()] = new boolean[] { //
      //
            false, false, false,//
            false, true, true,//
            false, true, false,//
      };

      boxes[Type.I.ordinal()][Rotation.R.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            false, true, false,//
            false, true, false,//
      };
      boxes[Type.I.ordinal()][Rotation.L.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            false, true, false,//
            false, true, false,//
      };
      boxes[Type.I.ordinal()][Rotation.U.ordinal()] = new boolean[] { //
      //
            false, false, false,//
            true, true, true,//
            false, false, false,//
      };
      boxes[Type.I.ordinal()][Rotation.D.ordinal()] = new boolean[] { //
      //
            false, false, false,//
            true, true, true,//
            false, false, false,//
      };

      boxes[Type.X.ordinal()][Rotation.R.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, true,//
            false, true, false,//
      };
      boxes[Type.X.ordinal()][Rotation.L.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, true,//
            false, true, false,//
      };
      boxes[Type.X.ordinal()][Rotation.U.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, true,//
            false, true, false,//
      };
      boxes[Type.X.ordinal()][Rotation.D.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, true,//
            false, true, false,//
      };

      boxes[Type.T.ordinal()][Rotation.R.ordinal()] = new boolean[] { //
      //
            false, false, false,//
            true, true, true,//
            false, true, false,//
      };
      boxes[Type.T.ordinal()][Rotation.L.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, true,//
            false, false, false,//
      };
      boxes[Type.T.ordinal()][Rotation.U.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            false, true, true,//
            false, true, false,//
      };
      boxes[Type.T.ordinal()][Rotation.D.ordinal()] = new boolean[] { //
      //
            false, true, false,//
            true, true, false,//
            false, true, false,//
      };
   }
   public Type type;
   public Rotation rotation;

   private float x, y;

   private List<Rectangle> bounds;
   private boolean isMoving;
   private Map<Direction, Tile> neighbors;

   private float alpha;

   public Tile(float x, float y, Type type, Rotation rotation) {
      this.type = type;
      this.rotation = rotation;
      this.x = x;
      this.y = y;
      alpha = 1f;

      bounds = new ArrayList<Rectangle>();

      for (int i = 0; i < 9; i++) {
         boolean isPath = boxes[type.ordinal()][rotation.ordinal()][i];
         if (!isPath) {
            int xPos = i % 3;
            int yPos = 2 - i / 3;
            bounds.add(new Rectangle(x + xPos * BOUND_SIZE, y + yPos * BOUND_SIZE, BOUND_SIZE, BOUND_SIZE));
         }
      }

      neighbors = new HashMap<Direction, Tile>();
   }

   private void updateBounds() {
      int index = 0;
      for (int i = 0; i < 9; i++) {
         boolean isPath = boxes[type.ordinal()][rotation.ordinal()][i];
         if (!isPath) {
            int xPos = i % 3;
            int yPos = 2 - i / 3;
            Rectangle rect = bounds.get(index);
            rect.x = x + xPos * BOUND_SIZE;
            rect.y = y + yPos * BOUND_SIZE;
            index++;
         }
      }

   }

   public List<Rectangle> getBounds() {
      return bounds;
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
      updateBounds();
   }

   public float getY() {
      return y;
   }

   public void setY(float y) {
      this.y = y;
      updateBounds();
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
}
