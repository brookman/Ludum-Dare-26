package eu32k.ludumdare.ld26.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.MultiLayerSprite;
import eu32k.ludumdare.ld26.level.Tile.Rotation;
import eu32k.ludumdare.ld26.level.Tile.Type;

public class TileSprites {

   private static MultiLayerSprite[][] sprites = new MultiLayerSprite[4][4];

   public static MultiLayerSprite getSprite(Type type, Rotation rotation) {
      return sprites[type.ordinal()][rotation.ordinal()];
   }

   // public static List<Rectangle> getBounds(Sprite sprite, float x, float y) {
   // return sprites[type.ordinal()][rotation.ordinal()];
   // }

   // ------------------------------------------------------------

   private static final float S = 108;
   private static Rectangle[][] rects = new Rectangle[][] { //
   //
         { new Rectangle(2.0f * S, S, S, S), new Rectangle(2.0f * S, 3.0f * S, S, S), new Rectangle(2.0f * S, 0, S, S), new Rectangle(2.0f * S, 2.0f * S, S, S) }, //
         { new Rectangle(0, S, S, S), new Rectangle(0, S, S, S), new Rectangle(0, 0, S, S), new Rectangle(0, 0, S, S) }, //
         { new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S), new Rectangle(0, 2.0f * S, S, S) }, //
         { new Rectangle(S, 2.0f * S, S, S), new Rectangle(S, 0, S, S), new Rectangle(S, S, S, S), new Rectangle(S, 3.0f * S, S, S) } //
   };

   public static void init() {
      initSprites();
      initBoundingBoxes();
   }

   private static void initSprites() {
      Texture layer1tex = Textures.get("textures/tiles2.png");
      Texture layer2tex = Textures.get("textures/tiles3.png");

      for (Type type : Type.values()) {
         for (Rotation rotation : Rotation.values()) {
            Rectangle rect = rects[type.ordinal()][rotation.ordinal()]; // get texture position from 2D array
            TextureRegion layer1reg = new TextureRegion(layer1tex, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
            TextureRegion layer2reg = new TextureRegion(layer2tex, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
            MultiLayerSprite sprite = new MultiLayerSprite(layer1reg, layer2reg);
            sprite.setSize(1.0f, 1.0f);

            sprites[type.ordinal()][rotation.ordinal()] = sprite;
         }
      }
   }

   private static void initBoundingBoxes() {

   }

}
