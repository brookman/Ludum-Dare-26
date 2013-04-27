package eu32k.ludumdare.ld26;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import eu32k.libgdx.rendering.Textures;

public class Tile {
   enum Type {
      L, I, X, T // L Shape, I Shape, X Shape (+ Shape), T Shape
   }

   enum Rotation {
      R, L, U, D // Right, Left, Up, Down
   }

   private static Rectangle[][] rects = new Rectangle[][] { //
   //
         { new Rectangle(54, 27, 27, 27), new Rectangle(54, 81, 27, 27), new Rectangle(54, 0, 27, 27), new Rectangle(54, 54, 27, 27) }, //
         { new Rectangle(0, 27, 27, 27), new Rectangle(0, 27, 27, 27), new Rectangle(0, 0, 27, 27), new Rectangle(0, 0, 27, 27) }, //
         { new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27) }, //
         { new Rectangle(27, 54, 27, 27), new Rectangle(27, 0, 27, 27), new Rectangle(27, 27, 27, 27), new Rectangle(27, 81, 27, 27) } //
   };

   private static Rectangle[][] collisionBounds = new Rectangle[][] {};

   private Type type;
   private Rotation rotation;
   private Sprite sprite;

   public Tile(Type type, Rotation rotation) {
      this.type = type;
      this.rotation = rotation;

      Texture tex = Textures.get("textures/tiles_round.png");

      if (type == null) {
         sprite = new Sprite(new TextureRegion(tex, 0, 81, 27, 27)); // empty
         return;
      }
      Rectangle rect = rects[type.ordinal()][rotation.ordinal()]; // get texture position from 2D array

      sprite = new Sprite(new TextureRegion(tex, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight()));
   }

   public Sprite getSprite() {
      return sprite;
   }
}