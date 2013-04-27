package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import eu32k.ludumdare.ld26.util.CollisionUtil;

import java.util.List;

public class Player extends GameObject {

    public static final float WIDTH = 4, HEIGHT = 4;
    public static final float SPEED = 30;


    public Player(int x, int y) {
        super(new Vector2(x, y), WIDTH, HEIGHT);

        bounds.add(new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT));
    }

    @Override
    public Sprite loadSprite() {
        Texture texture = new Texture(Gdx.files.internal("textures/shroom.png"));
        Sprite sprite = new Sprite(texture);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setPosition(position.x, position.y);

        return sprite;
    }

    public void update(List<Tile2> tiles) {
        super.update();

        for (Tile2 tile : tiles) {
            for (Rectangle tileBound : tile.getBounds()) {
                for (Rectangle playerBound : bounds) {
                    if (CollisionUtil.rectanglesOverlap(tileBound, playerBound)) {
                        
                        System.out.println("COLLISION");
                    }
                }
            }
        }
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

}
