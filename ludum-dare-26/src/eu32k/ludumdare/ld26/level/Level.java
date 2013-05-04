package eu32k.ludumdare.ld26.level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import eu32k.ludumdare.ld26.Direction;
import eu32k.ludumdare.ld26.level.Tile.Rotation;
import eu32k.ludumdare.ld26.level.Tile.Type;
import eu32k.ludumdare.ld26.objects.GameObject;
import eu32k.ludumdare.ld26.objects.Goal;
import eu32k.ludumdare.ld26.objects.Player;
import eu32k.ludumdare.ld26.state.GlobalState;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.PlayerState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class Level {

   private Tile[][] tileMatrix;

   private int width;

   private int height;

   private int dufficulty;

   private Random tileRandom;

   private List<Tile> tiles;

   private Tile nextTile;

   public Level(int width, int height) {
      tileMatrix = new Tile[height][width];
      this.height = height;
      this.width = width;
      dufficulty = 20;
      GlobalState globalState = StateMachine.instance().getState(GlobalState.class);
      tileRandom = globalState.createNewRandom("tiles");
      tiles = new ArrayList<Tile>();
   }

   public void generateRandomTiles() {
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            Tile tile = createRandomTile(j, i);
            tiles.add(tile);
            tileMatrix[i][j] = tile;
         }
      }
      placeNeighbors();
   }

   private Tile createRandomTile(float x, float y) {
      int randType = tileRandom.nextInt(4);
      int randRot = tileRandom.nextInt(4);
      Tile tile = new Tile();
      tile.init(x, y, Type.values()[randType], Rotation.values()[randRot]);
      return tile;
   }

   private void placeNeighbors() {
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

   public boolean isNearGameobject(GameObject object, Tile tile, int distance)
   {
      if(object == null || tile == null)
         return false;
      if(distance < 0)
         distance = 0;
      int ox = (int)object.getX();
      int oy = (int)object.getY();
      int tx = (int)tile.getX();
      int ty = (int)tile.getY();
      
      int xDistance = Math.abs(ox - tx);
      int yDistance = Math.abs(oy - ty);
      return (xDistance <= distance || yDistance <= distance);
   }
   
   public Tile spawnTile() {
      Player player = StateMachine.instance().getState(PlayerState.class).getPlayer();
      Goal goal = StateMachine.instance().getState(LevelState.class).getGoal();
      List<Tile> edgeTiles = new ArrayList<Tile>();
      for (Tile tile : tiles) {
         if (tile.getNeighbors().size() < 4 
               && (isNearGameobject(player, tile, 0)
               || isNearGameobject(goal, tile, 0))) {
            edgeTiles.add(tile);
         }
      }
      if(edgeTiles.size() == 0)
      {
         for (Tile tile : tiles) {
            if (tile.getNeighbors().size() < 4) {
               edgeTiles.add(tile);
            }
         }
      }
      int randomTile = tileRandom.nextInt(edgeTiles.size());
      
      Tile target = edgeTiles.get(randomTile);
      float xRand = target.getX();
      float yRand = target.getY();
      Set<Direction> dirs = target.getNeighbors().keySet();
      List<Direction> allDirs = new ArrayList<Direction>();
      allDirs = Arrays.asList(Direction.values());
      Iterator<Direction> it = allDirs.iterator();
      List<Direction> freeDirs = new ArrayList<Direction>();
      while (it.hasNext()) {
         Direction dir = it.next();
         if (!dirs.contains(dir)) {
            freeDirs.add(dir);
         }
      }
      int dirRand = tileRandom.nextInt(freeDirs.size());
      Direction dir = freeDirs.get(dirRand);
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
      tiles.add(nextTile);
      return nextTile;
   }

   public void popTile(Tile tile) {
      tiles.remove(tile);
   }

   public List<Tile> getTiles() {
      return tiles;
   }

   public void setTiles(List<Tile> tiles) {
      this.tiles = tiles;
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
      tiles.add(nextTile);
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
}
