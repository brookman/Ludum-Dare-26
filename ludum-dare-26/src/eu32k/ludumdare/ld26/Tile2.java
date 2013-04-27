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

public class Tile2 {

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public enum Type {
        L, I, X, T // L Shape, I Shape, X Shape (+ Shape), T Shape
    }

    public enum Rotation {
        R, L, U, D // Right, Left, Up, Down
    }
    
    private Type type;
    private Rotation rotation;
    private int x, y;
    public static final float WIDTH = 27, HEIGHT = 27;
    private Sprite sprite;
    private List<Rectangle> bounds;
    private boolean isMoving;
    private Map<Direction, Tile2> neighbors;

    public Tile2(int x, int y, Type type, Rotation rotation) {
        this.type = type;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
        this.sprite = loadSprite();
        this.bounds = calculateBounds();
        this.neighbors = new HashMap<Direction, Tile2>();
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

        //todo rotate rectangles depending on rotation/orientation

        return tmp;
    }

    public Sprite loadSprite() {
        Sprite sprite;
        TextureRegion textureRegion;
        Texture texture = new Texture(Gdx.files.internal("textures/tiles_round.png"));

        switch (type) {
            case I:
                textureRegion = new TextureRegion(texture, 0, 27, 27, 27);
                break;
            case L:
                textureRegion = new TextureRegion(texture, 54, 27, 27, 27);
                break;
            case T:
                textureRegion = new TextureRegion(texture, 27, 54, 27, 27);
                break;
            case X:
                textureRegion = new TextureRegion(texture, 0, 54, 27, 27);
                break;
            default:
                textureRegion = new TextureRegion(); //todo
        }
        sprite = new Sprite(textureRegion);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setPosition(x, y);

        switch (rotation) {
            case D:
                sprite.rotate(0);
                break;
            case L:
                sprite.rotate(90);
                break;
            case R:
                sprite.rotate(180);
                break;
            case U:
                sprite.rotate(270);
                break;
            default:
                //todo
        }

        return sprite;
    }
    

    private void rotate(Rectangle rectangle) {
        //todo
    }

   public boolean isMoving() {
      return isMoving;
   }

   public void setMoving(boolean isMoving) {
      this.isMoving = isMoving;
   }

   public Map<Direction, Tile2> getNeighbors() {
      return neighbors;
   }

   public void setNeighbors(Map<Direction, Tile2> neighbors) {
      this.neighbors = neighbors;
   }

}
