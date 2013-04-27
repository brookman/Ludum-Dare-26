package eu32k.ludumdare.ld26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import eu32k.libgdx.rendering.Textures;

public class Tile {

   public static final float S = 27;
   public static final float T = 9;
   public static final float WIDTH = S, HEIGHT = S;

   public void draw(SpriteBatch batch) {
      sprite.draw(batch);
   }

   public enum Type {
      L, I, X, T // L Shape, I Shape, X Shape (+ Shape), T Shape
   }

   public enum Rotation {
      R, L, U, D // Right, Left, Up, Down
   }

   private static Rectangle[][] rects = new Rectangle[][] { //
   //
         { new Rectangle(2.0f * S, S, S, S), new Rectangle(2.0f * S, 3.0f * S, S, S), new Rectangle(2.0f * S, 0, S, S), new Rectangle(2.0f * S, 2.0f * S, S, S) }, //
         { new Rectangle(0, S, S, S), new Rectangle(0, S, S, S), new Rectangle(0, 0, S, S), new Rectangle(0, 0, S, S) }, //
         { new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S) }, //
         { new Rectangle(S, 2.0f * S, S, S), new Rectangle(S, 0, S, S), new Rectangle(S, S, S, S), new Rectangle(S, 3.0f * S, S, S) } //
   };

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

   private Sprite sprite;
   private List<Rectangle> bounds;
   private boolean isMoving;
   private Map<Direction, Tile> neighbors;

   public Tile(float x, float y, Type type, Rotation rotation) {
      this.type = type;
      this.rotation = rotation;
      this.x = x;
      this.y = y;

      sprite = loadSprite();

      bounds = new ArrayList<Rectangle>();
      for (int i = 0; i < 9; i++) {
         boolean isPath = boxes[type.ordinal()][rotation.ordinal()][i];
         if (!isPath) {
            int xPos = i % 3;
            int yPos = 2 - i / 3;

            bounds.add(new Rectangle(x + xPos * T, y + yPos * T, T, T));
         }
      }

      neighbors = new HashMap<Direction, Tile>();
   }

   public List<Rectangle> getBounds() {
      return bounds;
   }

   public Sprite getSprite() {
      return sprite;
   }

   private List<Rectangle> calculateBounds() {
      List<Rectangle> tmp = new ArrayList<Rectangle>();

      switch (type) {
      case I:
         tmp.add(new Rectangle(x + 0, y + 0, 9, 9));
         tmp.add(new Rectangle(x + 0, y + 9, 9, 9));
         tmp.add(new Rectangle(x + 0, y + 18, 9, 9));
         tmp.add(new Rectangle(x + 18, y + 0, 9, 9));
         tmp.add(new Rectangle(x + 18, y + 9, 9, 9));
         tmp.add(new Rectangle(x + 18, y + 18, 9, 9));
         break;
      case L:
         tmp.add(new Rectangle());
         break;
      case T:
         tmp.add(new Rectangle());
         break;
      case X:
         tmp.add(new Rectangle());
         break;
      default:
         tmp.add(new Rectangle());
      }

      // todo rotate rectangles depending on rotation/orientation

      return tmp;
   }

   private static final Texture tex = new Texture(Gdx.files.internal("textures/tiles_round.png"));

   public Sprite loadSprite() {
      Texture tex = Textures.get("textures/tiles_round.png");
      Rectangle rect = rects[type.ordinal()][rotation.ordinal()]; // get texture position from 2D array
      sprite = new Sprite(new TextureRegion(tex, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight()));
      sprite.setSize(WIDTH, HEIGHT);
      sprite.setPosition(x, y);
      return sprite;
   }

   public boolean isMoving() {
      return isMoving;
   }

   public void setMoving(boolean isMoving) {
      isMoving = isMoving;
   }

   public Map<Direction, Tile> getNeighbors() {
      return neighbors;
   }

   public void setNeighbors(Map<Direction, Tile> neighbors) {
      neighbors = neighbors;
   }

}
