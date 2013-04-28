package eu32k.ludumdare.ld26.effects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;

public class ColorRange {
   public List<Color> colors;

   public ColorRange() {
      colors = new ArrayList<Color>();
   }

   public void addColor(Color c) {
      colors.add(c);
   }

   public Color getColor(float value) {
      Color c = new Color();
      getColor(c, value);
      return c;
   }

   public void getColor(Color result, float value) {
      if (colors.size() == 0) {
         return;
      }
      if (colors.size() == 1) {
         result.set(colors.get(0));
         return;
      }
      if (value <= 0f)
         colors.get(0);
      if (value > 1f)
         colors.get(colors.size() - 1);
      
      
      int part = (int) (value * colors.size() - 1);
      Color colorA = colors.get(part);
      Color colorB = colors.get(part + 1);
      result.r = Interpolation.linear.apply(colorA.r, colorB.r, value);
      result.g = Interpolation.linear.apply(colorA.g, colorB.g, value);
      result.b = Interpolation.linear.apply(colorA.b, colorB.b, value);
      result.a = Interpolation.linear.apply(colorA.a, colorB.a, value);
   }
}
