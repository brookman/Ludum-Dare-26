package eu32k.ludumdare.ld26.rendering;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.common.Assets;

public class TextRenderer {

   private SpriteBatch textBatch;

   private BitmapFont font;

   private Map<String, Vector2> textPositions;
   private Map<String, String> textLines;

   public TextRenderer() {
      textBatch = new SpriteBatch();
      textLines = new HashMap<String, String>();
      textPositions = new HashMap<String, Vector2>();
      font = Assets.MANAGER.get("fonts/calibri.fnt", BitmapFont.class);
   }

   public void render() {
      textBatch.begin();
      textBatch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      Set<String> keys = textLines.keySet();
      for (String text : keys) {
         Vector2 pos = textPositions.get(text);
         font.draw(textBatch, textLines.get(text), pos.x, pos.y);
      }
      textBatch.end();
   }

   public Map<String, String> getTextLines() {
      return textLines;
   }

   public Map<String, Vector2> getTextPositions() {
      return textPositions;
   }

   public void addText(String textKey, String text, float x, float y) {
      textLines.put(textKey, text);
      textPositions.put(textKey, new Vector2(x, y));
   }

   public void removeText(String string) {
      textLines.remove(string);
      textPositions.remove(string);
   }

}
