package eu32k.libgdx.common;

public class Profile {
   //Seed state
   public int minWidth = 4;
   public int maxWidth = 20;
   public int minHeight = 3;
   public int maxHeight = 15;
   public float speed = 0;
   public long seed = 47289239;
   public int levels;
   
   //game state
   public boolean inGame = false;
   public boolean inChallengeMode = false;
   public int level = 0;

   //player state
   public int retries = 0;
   public int deaths = 0;
   public int totalTime = 0;
   public int successfulTime = 0;
   
}
