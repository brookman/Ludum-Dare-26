package eu32k.ludumdare.ld26.recording;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import eu32k.ludumdare.ld26.recorder.Recorder;

public class ScreenshotRecorder implements Runnable, Recorder {

   private static int id = 0;
   private static PixmapPool pool;
   private static ExecutorService executor = Executors.newFixedThreadPool(10);

   private int idCount;
   private PoolablePixmap pixmap;
   private float writeEachNFrame;

   @Override
   public void run() {
      saveScreenshot();
      synchronized (pool) {
         pool.free(pixmap);
      }
   }

   private void getScreenshot(int w, int h) {
      synchronized (pool) {
         pixmap = pool.obtain();
         pixmap.reset();
      }
      ByteBuffer pixels = pixmap.getPixels();

      Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);
      Gdx.gl.glReadPixels(0, 0, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);

      // flip
      final int numBytes = w * h * 4;
      byte[] lines = new byte[numBytes];

      final int numBytesPerLine = w * 4;
      for (int i = 0; i < h; i++) {
         pixels.position((h - i - 1) * numBytesPerLine);
         pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
      }
      pixels.clear();
      pixels.put(lines);
   }

   private void saveScreenshot() {
      String name = "" + idCount;
      int zeros = 6 - name.length();
      for (int i = 0; i < zeros; i++) {
         name = "0" + name;
      }

      PixmapIO.writePNG(new FileHandle("screenshot/" + name + ".png"), pixmap);
   }

   @Override
   public void record(int fps) {
      if (pool == null) {
         pool = new PixmapPool(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
      }
      idCount = id++;
      writeEachNFrame = (int) (60.0f / fps);

      if (idCount % writeEachNFrame == 0) {
         getScreenshot(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
         executor.execute(this);
      }
   }
}