package eu32k.ludumdare.ld26.events.messages;

import com.badlogic.gdx.math.Vector2;

import eu32k.ludumdare.ld26.effects.GameObjectMove;
import eu32k.ludumdare.ld26.effects.TileFade;
import eu32k.ludumdare.ld26.effects.TileMove;
import eu32k.ludumdare.ld26.events.EventBase;
import eu32k.ludumdare.ld26.level.Tile;
import eu32k.ludumdare.ld26.objects.GameObject;

public class GenericEvent extends EventBase{
   public enum GenericEventType{
      NONE, FADE_COMPLETE, GAMEEVENT, MUSICEVENT, MOVE_COMPLETE, OBJECTMOVE_COMPLETE, PLAY_PART
   };
   

   public final static int GAMEEVENT_TYPE_START_GAME = 1;
   public final static int GAMEEVENT_TYPE_PAUSE = 2;
   public final static int GAMEEVENT_TYPE_RESUME = 3;
   public final static int GAMEEVENT_TYPE_WIN = 4;
   public final static int GAMEEVENT_TYPE_LOSE = 5;
   public final static int GAMEEVENT_TYPE_EXIT = 6;
   public final static int GAMEEVENT_TYPE_NEXTLEVEL = 7;
   
   public final static int GAMEEVENT_LOSE_FALLOFFBOARD = 1;
   public final static int GAMEEVENT_LOSE_TIMEOUT = 2;
   public final static int GAMEEVENT_LOSE_SQUASHED = 3;

   public static final int GAMEEVENT_LOSE_TOLOST = 4;

   public GenericEventType type;
   public Vector2 vecA;
   public Vector2 vecB;
   public int intA;
   public int intB;
   public Tile tile;
   public TileFade fade;
   public boolean booleanA;
   public TileMove tileMove;
   public GameObject object;
   public GameObjectMove objectMove;
   
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
      tile = null;
      fade = null;
      booleanA = false;
      tileMove = null;
      object = null;
   }
}
