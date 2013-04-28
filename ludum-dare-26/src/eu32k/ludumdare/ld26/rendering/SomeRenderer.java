package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class SomeRenderer {

   public static FrameBuffer makeFrameBuffer() {
      FrameBuffer frameBuffer = new FrameBuffer(Format.RGBA8888, 800, 600, false);
      frameBuffer.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
      frameBuffer.getColorBufferTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
      return frameBuffer;
   }

   protected FrameBuffer frameBuffer = makeFrameBuffer();

   // public void render() {
   // Gdx.gl.glActiveTexture(GL20.GL_TEXTURE1);
   // getPortValue(imageB).bind();
   //
   // Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
   // getPortValue(imageA).bind();
   //
   // shader.begin();
   //
   // shader.setUniformi("uTexture2", 1);
   // shader.setUniformi("uTexture1", 0);
   //
   // shader.renderToQuad(frameBuffer);
   // }

}
