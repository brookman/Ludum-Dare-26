package eu32k.ludumdare.ld26.level;

import com.badlogic.gdx.math.Rectangle;

import eu32k.ludumdare.ld26.level.Tile.Rotation;
import eu32k.ludumdare.ld26.level.Tile.Type;

public class TileBoundingBoxes {

   private static Bounds[][] bounds = new Bounds[4][4];

   public static Bounds getNormalizedBounds(Type type, Rotation rotation) {
      return bounds[type.ordinal()][rotation.ordinal()];
   }

   // ------------------------------------------------------------

   private static float BOUND_SIZE = Tile.SIZE / 3.0f; // 0.333333333

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

   public static void init() {
      for (Type type : Type.values()) {
         for (Rotation rotation : Rotation.values()) {

            Bounds boundsObject = new Bounds();

            for (int i = 0; i < 9; i++) {
               boolean isPath = boxes[type.ordinal()][rotation.ordinal()][i];
               if (!isPath) {
                  int xPos = i % 3;
                  int yPos = 2 - i / 3;
                  boundsObject.boundingBoxes.add(new Rectangle(xPos * BOUND_SIZE, yPos * BOUND_SIZE, BOUND_SIZE, BOUND_SIZE));
               }
            }

            bounds[type.ordinal()][rotation.ordinal()] = boundsObject;
         }
      }
   }
}
