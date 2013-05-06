package eu32k.ludumdare.ld26.recording;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PoolablePixmap extends Pixmap implements Poolable {

   public PoolablePixmap(int width, int height, Pixmap.Format format) {
      super(width, height, format);
   }

   @Override
   public void reset() {
      getPixels().clear();
   }
}
