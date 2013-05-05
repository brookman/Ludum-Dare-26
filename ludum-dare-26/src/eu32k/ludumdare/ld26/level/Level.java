package eu32k.ludumdare.ld26.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.level.Tile.Rotation;
import eu32k.ludumdare.ld26.level.Tile.Type;
import eu32k.ludumdare.ld26.objects.GameObject;
import eu32k.ludumdare.ld26.objects.Goal;
import eu32k.ludumdare.ld26.objects.Player;
import eu32k.ludumdare.ld26.pool.TilePool;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class Level {

   private int width;

   private int height;

   private int dufficulty;

   private Random tileRandom;
   private int spawnDistance;
   // private List<Tile> tiles;

   private Tile nextTile;

   private TilePool pool;

   public Level(TilePool pool, int width, int height) {
      spawnDistance = 1;
      this.pool = pool;
      if (pool == null) {
         this.pool = new TilePool();
      }
      pool.preloadTo(width * height + 2);
      this.height = height;
      this.width = width;
      dufficulty = 20;
      GlobalState globalState = StateMachine.instance().getState(GlobalState.class);
      tileRandom = globalState.createNewRandom("tiles");
   }

   public void generateRandomTiles() {
      Tile[][] tileMatrix = new Tile[height][width];
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            Tile tile = createRandomTile(j, i);
            // tiles.add(tile);
            tileMatrix[i][j] = tile;
         }
      }
      placeNeighbors(tileMatrix);
   }

   private Tile createRandomTile(float x, float y) {
      int randType = tileRandom.nextInt(4);
      int randRot = tileRandom.nextInt(4);
      Tile tile = pool.getFreeItem();
      tile.init(x, y, Type.values()[randType], Rotation.values()[randRot]);
      return tile;
   }

   private void placeNeighbors(Tile[][] tileMatrix) {
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            Tile tile = tileMatrix[i][j];
            Tile north, east, south, west;
            if (i > 0) {
               south = tileMatrix[i - 1][j];
               addNeighbor(tile, south, Direction.S);
            }
            if (j > 0) {
               west = tileMatrix[i][j - 1];
               addNeighbor(tile, west, Direction.W);
            }
            if (i < height - 1) {
               north = tileMatrix[i + 1][j];
               addNeighbor(tile, north, Direction.N);
            }
            if (j < width - 1) {
               east = tileMatrix[i][j + 1];
               addNeighbor(tile, east, Direction.E);
            }
         }
      }
   }

   private void addNeighbor(Tile target, Tile neighbor, Direction direction) {
      if (neighbor != null) {
         target.getNeighbors().put(direction, neighbor);
      }
   }

   public boolean isHorizontalNearGameobject(GameObject object, Tile tile, int distance) {
      if (object == null || tile == null)
         return false;
      if (distance < 0)
         distance = 0;
      int ox = (int) object.getX();
      int tx = (int) tile.getX();

      int xDistance = Math.abs(ox - tx);
      return (xDistance <= distance);
   }

   public boolean isVerticalNearGameobject(GameObject object, Tile tile, int distance) {
      if (object == null || tile == null)
         return false;
      if (distance < 0)
         distance = 0;
      int oy = (int) object.getY();
      int ty = (int) tile.getY();

      int yDistance = Math.abs(oy - ty);
      return (yDistance <= distance);
   }

   public Tile spawnTile() {
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      PlayerState ps = StateMachine.instance().getState(PlayerState.class);
      Player player = ps.getPlayer();
      Goal goal = ls.getGoal();

      ArrayList<Tile> candidates = new ArrayList<Tile>();
      for (Tile candidate : pool.items()) {
         if (candidate.isInUse() && candidate.getNeighbors().size() < 4) {
            candidates.add(candidate);
         }
      }

      Tile target = null;
      List<Direction> availableDirections = null;
      do {
         target = candidates.get(tileRandom.nextInt(candidates.size()));
         candidates.remove(target);
         Map<Direction, Tile> n = target.getNeighbors();
         boolean foundValid = false;
         availableDirections = getAvailableDirections(target);
         if(getXDistance(player, target) <= spawnDistance)
         {
            if(availableDirections.contains(Direction.N) || availableDirections.contains(Direction.S)){
               foundValid = true;
            }
         }
         if(getYDistance(player, target) <= spawnDistance)
         {
            if(availableDirections.contains(Direction.E) || availableDirections.contains(Direction.W)){
               foundValid = true;
            }
         }
         if(getXDistance(goal, target) <= spawnDistance)
         {
            if(availableDirections.contains(Direction.N) || availableDirections.contains(Direction.S)){
               foundValid = true;
            }
         }
         if(getYDistance(goal, target) <= spawnDistance)
         {
            if(availableDirections.contains(Direction.E) || availableDirections.contains(Direction.W)){
               foundValid = true;
            }
         }
         if(!foundValid){
            target = null;
         }
      } while (target == null);

      int size = availableDirections.size();
      Direction targetDir = availableDirections.get(size == 1 ? 0 : tileRandom.nextInt(size));
      float x = target.getX();
      float y = target.getY();

      switch (targetDir) {
      case S:
         y -= Tile.SIZE;
         break;
      case W:
         x -= Tile.SIZE;
         break;
      case N:
         y += Tile.SIZE;
         break;
      case E:
      default:
         x += Tile.SIZE;
         break;
      }
      Tile nextTile = createRandomTile(x, y);
      nextTile.getNeighbors().put(Direction.getOpposite(targetDir), target);
      target.getNeighbors().put(targetDir, nextTile);
      // tiles.add(nextTile);
      return nextTile;

   }

   private int getXDistance(GameObject obj, Tile t) {
      return (int)Math.abs((int)obj.getX() - (int)t.getX());
   }

   private int getYDistance(GameObject obj, Tile t) {
      return (int)Math.abs((int)obj.getY() - (int)t.getY());
   }

   private List<Direction> getAvailableDirections(Tile target) {
      ArrayList<Direction> list = new ArrayList<Direction>();
      Map<Direction, Tile> n = target.getNeighbors();
      if (!n.containsKey(Direction.W)) {
         list.add(Direction.W);
      }
      if (!n.containsKey(Direction.E)) {
         list.add(Direction.E);
      }
      if (!n.containsKey(Direction.S)) {
         list.add(Direction.S);
      }
      if (!n.containsKey(Direction.N)) {
         list.add(Direction.N);
      }
      return list;
   }

   private boolean checkVerticalDirection(Player player, Goal goal, Tile target, Direction targetDir) {
      return (targetDir == Direction.N || targetDir == Direction.S) && (!isVerticalNearGameobject(player, target, spawnDistance) && !isVerticalNearGameobject(goal, target, spawnDistance));
   }

   private boolean checkHorizontalDirection(Player player, Goal goal, Tile target, Direction targetDir) {
      return (targetDir == Direction.E || targetDir == Direction.W) && (!isHorizontalNearGameobject(player, target, spawnDistance) && !isHorizontalNearGameobject(goal, target, spawnDistance));
   }

   private boolean checkTileAndGoal(Player player, Goal goal, Tile t) {
      return isHorizontalNearGameobject(player, t, spawnDistance) || isHorizontalNearGameobject(goal, t, spawnDistance) || isVerticalNearGameobject(goal, t, spawnDistance)
            || isVerticalNearGameobject(player, t, spawnDistance);
   }

   public Tile spawnTileOld() {
      List<Tile> edgeTiles = getEdgeTiles();
      int randomTile = tileRandom.nextInt(edgeTiles.size());

      Tile target = edgeTiles.get(randomTile);
      float xRand = target.getX();
      float yRand = target.getY();
      Set<Direction> dirs = target.getNeighbors().keySet();
      List<Direction> freeDirs = new ArrayList<Direction>();
      LevelState ls = StateMachine.instance().getState(LevelState.class);
      PlayerState ps = StateMachine.instance().getState(PlayerState.class);
      System.out.println("Player: " + ps.getPlayer().getX() + "/" + ps.getPlayer().getY() + " Object: " + ls.getGoal().getX() + "/" + ls.getGoal().getY());
      System.out.println("Tile: " + target.getX() + "/" + target.getY());
      System.out.println("Tile Neighbours:");
      for (Direction key : target.getNeighbors().keySet()) {
         System.out.println(key);
      }
      if (dirs.size() == 3) {
         freeDirs.add(Direction.N);
         freeDirs.add(Direction.E);
         freeDirs.add(Direction.S);
         freeDirs.add(Direction.W);
         for (Direction dir : dirs) {
            freeDirs.remove(dir);
         }
      } else {
         if (isHorizontalNearGameobject(ps.getPlayer(), target, spawnDistance) || isHorizontalNearGameobject(ls.getGoal(), target, spawnDistance)) {
            if (!dirs.contains(Direction.E)) {
               freeDirs.add(Direction.E);
            }
            if (!dirs.contains(Direction.W)) {
               freeDirs.add(Direction.W);
            }
         }
         if (isVerticalNearGameobject(ps.getPlayer(), target, spawnDistance) || isVerticalNearGameobject(ls.getGoal(), target, spawnDistance)) {
            if (!dirs.contains(Direction.getOpposite(Direction.N))) {
               freeDirs.add(Direction.N);
            }
            if (!dirs.contains(Direction.S)) {
               freeDirs.add(Direction.S);
            }
         }
      }
      System.out.println("Free Dirs:");
      printDirs(freeDirs);
      int size = freeDirs.size();
      Direction dir = size <= 1 ? freeDirs.get(0) : freeDirs.get(tileRandom.nextInt(size));
      float x, y;
      switch (dir) {
      case S:
         x = xRand;
         y = yRand - Tile.SIZE;
         break;
      case W:
         x = xRand - Tile.SIZE;
         y = yRand;
         break;
      case N:
         x = xRand;
         y = yRand + Tile.SIZE;
         break;
      case E:
      default:
         x = xRand + Tile.SIZE;
         y = yRand;
         break;
      }
      Tile nextTile = createRandomTile(x, y);
      nextTile.getNeighbors().put(Direction.getOpposite(dir), target);
      target.getNeighbors().put(dir, nextTile);
      // tiles.add(nextTile);
      return nextTile;
   }

   private void printDirs(List<Direction> blabla) {
      for (Direction dir : blabla) {
         System.out.println(blabla);
      }
   }

   private List<Tile> getEdgeTiles() {
      Player player = StateMachine.instance().getState(PlayerState.class).getPlayer();
      Goal goal = StateMachine.instance().getState(LevelState.class).getGoal();
      List<Tile> edgeTiles = new ArrayList<Tile>();
      for (Tile tile : pool.items()) {
         if (tile.isInUse()) {
            Map<Direction, Tile> neighbours = tile.getNeighbors();
            if (((!neighbours.containsKey(Direction.N) || !neighbours.containsKey(Direction.S)) && (isVerticalNearGameobject(player, tile, spawnDistance) || isHorizontalNearGameobject(goal, tile,
                  spawnDistance)))
                  || ((!neighbours.containsKey(Direction.E) || !neighbours.containsKey(Direction.W)) && (isHorizontalNearGameobject(player, tile, spawnDistance) || isHorizontalNearGameobject(goal,
                        tile, spawnDistance)))) {
               edgeTiles.add(tile);
            }
         }
      }
      if (edgeTiles.size() == 0) {
         for (Tile tile : pool.items()) {
            if (tile.isInUse() && tile.getNeighbors().size() < 4) {
               edgeTiles.add(tile);
            }
         }
      }
      return edgeTiles;
   }

   public void popTile(Tile tile) {
      tile.setInUse(false);
   }

   public List<Tile> getTiles() {
      return pool.items();
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getDufficulty() {
      return dufficulty;
   }

   public void setDufficulty(int dufficulty) {
      this.dufficulty = dufficulty;
   }

   public Tile getNextTile() {
      return nextTile;
   }

   public void setNextTile(Tile nextTile) {
      this.nextTile = nextTile;
   }

   public void updateNeighbors(Tile spawned, Direction dir) {
      Tile toShift = spawned;
      do {
         Tile newLeft, newRight;
         Direction dirLeft = CCW(dir);
         Direction dirRight = CW(dir);
         Tile front = toShift.getNeighbors().get(dir);

         if (front != null) {
            newRight = front.getNeighbors().get(dirRight);
            newLeft = front.getNeighbors().get(dirLeft);
            if (newRight != null) {
               newRight.getNeighbors().put(dirLeft, toShift);
               toShift.getNeighbors().put(dirRight, newRight);
            }
            if (newLeft != null) {
               newLeft.getNeighbors().put(dirRight, toShift);
               toShift.getNeighbors().put(dirLeft, newLeft);
            }
            if (front.getNeighbors().get(dir) == null) {
               toShift.getNeighbors().remove(dir);
            }
         }
      } while ((toShift = toShift.getNeighbors().get(dir)) != null);
   }

   public Direction CW(Direction dir) {
      switch (dir) {
      case N:
         return Direction.E;
      case E:
         return Direction.S;
      case S:
         return Direction.W;
      case W:
         return Direction.N;
      default:
         return null;
      }
   }

   public Direction CCW(Direction dir) {
      switch (dir) {
      case N:
         return Direction.W;
      case W:
         return Direction.S;
      case S:
         return Direction.E;
      case E:
         return Direction.N;
      default:
         return null;
      }
   }

   public void setRandom(Random random) {
      this.tileRandom = random;
   }

   public int getSpawnDistance() {
      return spawnDistance;
   }

   public void setSpawnDistance(int spawnDistance) {
      this.spawnDistance = spawnDistance;
   }
}
