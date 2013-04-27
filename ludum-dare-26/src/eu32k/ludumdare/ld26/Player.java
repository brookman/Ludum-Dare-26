package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {

    public static final float WIDTH = 8, HEIGHT = 8;
    public static final float SPEED = 20;


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

    @Override
    public float getSpeed() {
        return SPEED;
    }

}
