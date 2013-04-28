package eu32k.ludumdare.ld26.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextConsole {
   private class TextBoxLine {
      public String text;
      public Color color;

      public TextBoxLine(String text, Color color) {
         this.text = text;
         this.color = color;
      }
   }

   private float x;
   private float y;
   private float lineHeight;
   private int lines;
   private BitmapFont font;
   private boolean enabled;
   private Color defaultColor;
   private TextBoxLine[] linesArray;
   private int currentIndex = -1;

   public TextConsole(BitmapFont font, float x, float y, float lineHeight, int lines, Color defaultColor) {
      this.font = font;
      this.x = x;
      this.y = y;
      this.lineHeight = lineHeight;
      this.lines = lines;
      this.defaultColor = defaultColor;
      this.enabled = true;
      this.linesArray = new TextBoxLine[lines];
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public void addLine(String text) {
      addLine(text, defaultColor);
   }

   public void addLine(String text, Color color) {
      currentIndex++;
      if (currentIndex >= lines)
         currentIndex = 0;
      linesArray[currentIndex] = new TextBoxLine(text, color);
   }

   public void draw(SpriteBatch batch) {
      for (int index = 0; index < lines; index++) {
         int lineIndex = (currentIndex) - index % lines;
         while(lineIndex < 0)
         {
            lineIndex += lines;
         }
         TextBoxLine line = linesArray[lineIndex];
         if (line != null) {
            font.draw(batch, line.text, x, y - index * lineHeight);
         }
      }
   }

}
