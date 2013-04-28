package eu32k.ludumdare.ld26.effects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

public class ColorRange {
   public List<Color> colors;
   
   public ColorRange()
   {
      colors = new ArrayList<Color>();
   }
   
   public void addColor(Color c)
   {
      colors.add(c);
   }
   
   public Color getColor(float value)
   {
      if(colors.size() == 0)
         return null;
      if(colors.size() == 1)
         return colors.get(0);
      if(value < 0f)
         value = 0f;
      if(value >= 1f)
         value = 0.99999f;
      return colors.get((int)(value * colors.size()));
   }
}
