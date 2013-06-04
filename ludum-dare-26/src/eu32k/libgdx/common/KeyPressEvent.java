package eu32k.libgdx.common;

import com.badlogic.gdx.Gdx;
public abstract class KeyPressEvent {
   private boolean armed = true;
   private int key;

   public KeyPressEvent(int key) {
      this.key = key;
   }
   public void update() {
      if (Gdx.input.isKeyPressed(key)) {
         if (armed) {
            onPress();
         }
         armed = false;
      } else {
         if (!armed) {
            onRelease();
         }
         armed = true;
      }
   }
   public abstract void onPress();
   public abstract void onRelease();
}