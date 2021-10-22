package eu32k.ludumdare.ld26.recording;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Pool;

public class PixmapPool extends Pool<PoolablePixmap> {

   private int width;
   private int height;
   private Pixmap.Format format;

   public PixmapPool(int width, int height, Pixmap.Format format) {
      super(5);
      this.width = width;
      this.height = height;
      this.format = format;
   }

   @Override
   protected PoolablePixmap newObject() {
      return new PoolablePixmap(width, height, format);
   }
}