package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu32k.libgdx.rendering.Textures;
import eu32k.ludumdare.ld26.events.messages.MusicEvent;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class SoundButton {

   private static boolean soundPlaying = true;
   private static boolean isPressed = false;
   private static SpriteBatch batch;
   private static Sprite sprite;

   private static final float size = 24.0f;

   public static void init() {
      batch = new SpriteBatch();
      sprite = new Sprite(Textures.get("textures/sound.png"));
      sprite.setSize(size, size);
   }

   private static void check() {
      if (Gdx.input.getX() > Gdx.graphics.getWidth() - size && Gdx.input.getY() < size && Gdx.input.isTouched()) {
         isPressed = true;
      } else {
         if (isPressed) {
            soundPlaying = !soundPlaying;
            StateMachine.instance().getState(GlobalState.class).getEvents().enqueue(new MusicEvent(soundPlaying));
            isPressed = false;
         }
      }
   }

   public static void draw() {
      check();
      sprite.setPosition(Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight() - size);
      sprite.setColor(soundPlaying?Color.WHITE:Color.RED);
      batch.begin();
      sprite.draw(batch);
      batch.end();
   }
}
