package eu32k.ludumdare.ld26.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Interpolation;

public class LevelConfigSequence {

   private int levelIndex;
   private List<LevelConfig> levels;

   public LevelConfigSequence() {
      levels = new ArrayList<LevelConfig>();
   }

   public void init(){
      levelIndex = 0;
   }
   public void add(LevelConfig config) {
      levels.add(config);
   }

   public void clear() {
      levels.clear();
      levelIndex = 0;
   }

   public static void addLevelsToSequence(LevelConfigSequence sequence, int sequenceSeed, LevelConfig from, LevelConfig to, int levels) {
      Random random = new Random(sequenceSeed);
      float v = 0;
      float levelsF = levels;
      for (int index = 0; index < levels; index++) {
         v = index / levelsF;
         LevelConfig c = new LevelConfig();
         c.seed = random.nextLong();
         c.width = (int) Interpolation.linear.apply(from.width, to.width, v);
         c.height = (int) Interpolation.linear.apply(from.height, to.height, v);
         c.spawnDistance = (int) Interpolation.linear.apply(from.spawnDistance, to.spawnDistance, v);
         c.firstTileSpawnDelay =  Interpolation.linear.apply(from.firstTileSpawnDelay, to.firstTileSpawnDelay, v);
         c.tileSpawnInterval = Interpolation.linear.apply(from.tileSpawnInterval, to.tileSpawnInterval, v);
         c.tileFadeTime = Interpolation.linear.apply(from.tileFadeTime, to.tileFadeTime, v);
         sequence.add(c);
      }
   }
   public int size(){
      return levels.size();
   }
   /***
    * Sets the level index to the next value (+1)
    * @return Returns true, if the next index is still in level bounds
    */
   public boolean nextLevel(){
      levelIndex++;
      return levelIndex < levels.size();
   }
   
   public void setLevelIndex(int index){
      this.levelIndex = index;
   }
   
   public LevelConfig getCurrentConfig() {
      if(levels.size() == 0 || levelIndex >= levels.size())
         return null;
      return levels.get(levelIndex);
   }

   public void reset() {
      this.levelIndex = 0;
   }

   public int getLevelIndex() {
      return levelIndex;
   }
}
