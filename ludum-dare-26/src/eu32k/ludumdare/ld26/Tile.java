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
         { new Rectangle(54, 27, 27, 27), new Rectangle(54, 81, 27, 27), new Rectangle(54, 0, 27, 27), new Rectangle(54, 54, 27, 27) }, //
         { new Rectangle(0, 27, 27, 27), new Rectangle(0, 27, 27, 27), new Rectangle(0, 0, 27, 27), new Rectangle(0, 0, 27, 27) }, //
         { new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27) }, //
         { new Rectangle(27, 54, 27, 27), new Rectangle(27, 0, 27, 27), new Rectangle(27, 27, 27, 27), new Rectangle(27, 81, 27, 27) } //
   };

   private static Rectangle[][] b = new Rectangle[][] { //
   //
         { new Rectangle(54, 27, 27, 27), new Rectangle(54, 81, 27, 27), new Rectangle(54, 0, 27, 27), new Rectangle(54, 54, 27, 27) }, //
         { new Rectangle(0, 27, 27, 27), new Rectangle(0, 27, 27, 27), new Rectangle(0, 0, 27, 27), new Rectangle(0, 0, 27, 27) }, //
         { new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27), new Rectangle(0, 54, 27, 27) }, //
         { new Rectangle(27, 54, 27, 27), new Rectangle(27, 0, 27, 27), new Rectangle(27, 27, 27, 27), new Rectangle(27, 81, 27, 27) } //
   };

   private Type type;
   private Rotation rotation;

   private int x, y;
   public static final float WIDTH = 27, HEIGHT = 27;
   private Sprite sprite;
   private List<Rectangle> bounds;
   private boolean isMoving;
   private Map<Direction, Tile> neighbors;

   public Tile(int x, int y, Type type, Rotation rotation) {
      this.type = type;
      this.rotation = rotation;
      this.x = x;
      this.y = y;
      sprite = loadSprite();
      bounds = calculateBounds();
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
      this.isMoving = isMoving;
   }

   public Map<Direction, Tile> getNeighbors() {
      return neighbors;
   }

   public void setNeighbors(Map<Direction, Tile> neighbors) {
      this.neighbors = neighbors;
   }

}
