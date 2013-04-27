package eu32k.ludumdare.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    public Vector2 position;
    public List<Rectangle> bounds;
    public float width, height;
    private Sprite sprite;
    private float speed;

    public GameObject(Vector2 position, float width, float height) {
        this.position = position;
        this.bounds = new ArrayList<Rectangle>();
        this.width = width;
        this.height = height;
        this.speed = getSpeed();

        this.sprite = loadSprite();
        this.sprite.setPosition(position.x, position.y);
    }

    public abstract Sprite loadSprite();

    public abstract float getSpeed();

    public Sprite getSprite() {
        return sprite;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update() {
        sprite.setPosition(position.x, position.y);
        for (Rectangle r : bounds) {
            r.x = position.x - width / 2;
            r.y = position.y - height / 2;
        }
    }

    public void moveDown() {
        position.y -= Gdx.graphics.getDeltaTime() * speed;
    }

    public void moveUp() {
        position.y += Gdx.graphics.getDeltaTime() * speed;
    }

    public void moveLeft() {
        position.x -= Gdx.graphics.getDeltaTime() * speed;
    }

    public void moveRight() {
        position.x += Gdx.graphics.getDeltaTime() * speed;
    }

    public boolean
}
