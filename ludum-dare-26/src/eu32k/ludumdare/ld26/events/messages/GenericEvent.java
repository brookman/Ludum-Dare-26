package eu32k.ludumdare.ld26.events.messages;

import com.badlogic.gdx.math.Vector2;

import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.events.EventBase;
import eu32k.ludumdare.ld26.level.Tile;

public class GenericEvent extends EventBase{
   public enum GenericEventType{
      NONE, FADE_COMPLETE
   };
   
   public GenericEventType type;
   public Vector2 vecA;
   public Vector2 vecB;
   public int intA;
   public int intB;
   public Tile tile;
   public TileFade fade;
   
   public GenericEvent(){
      type = GenericEventType.NONE;
      vecA = new Vector2();
      vecB = new Vector2();
   }
   
   public void init(){
      type = GenericEventType.NONE;
      vecA.set(0f, 0f);
      vecB.set(0f, 0f);
      intA = 0;
      intB = 0;
   }
}
