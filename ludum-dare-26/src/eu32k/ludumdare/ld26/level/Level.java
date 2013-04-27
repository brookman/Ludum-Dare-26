package eu32k.ludumdare.ld26.level;

import eu32k.ludumdare.ld26.Tile2;

public class Level {

   enum Edge {
      TOP, RIGHT, BOTTOM, LEFT;
   }

   private Tile2[][] tiles;

   private int width;

   private int height;

   private int dufficulty;

   public Level(int width, int height) {
      tiles = new Tile2[height][width];
      this.height = height;
      this.width = width;
   }

   public void insertTile(Tile2 tile, Edge edge, int position) {
      int x = 0, y = 0;
      Tile2 popped;
      switch (edge) {
      case TOP:
         x = position;
         y = 0;
         popped = shiftDown(x, y);
         break;
      case RIGHT:
         x = this.width - 1;
         y = position;
         popped = shiftLeft(x, y);
         break;
      case BOTTOM:
         x = position;
         y = this.height - 1;
         popped = shiftUp(x, y);
         break;
      case LEFT:
         x = 0;
         y = position;
         popped = shiftRight(x, y);
         break;
      }
      tiles[y][x] = tile;
   }

   private Tile2 shiftDown(int x, int y) {
      int toPopX = x;
      int toPopY = height - 1;
      Tile2 popped = tiles[toPopY][toPopX];
      for (int i = height - 1; i > 0; i--) {
         int toShiftY = i - 1;
         tiles[i][x] = tiles[toShiftY][x];
      }
      return popped;
   }

   private Tile2 shiftLeft(int x, int y) {
      int toPopX = width - 1;
      int toPopY = y;
      Tile2 popped = tiles[toPopY][toPopX];
      for (int i = 0; i < width - 1; i++) {
         int toShiftX = i + 1;
         tiles[y][i] = tiles[y][toShiftX];
      }
      return popped;
   }

   private Tile2 shiftUp(int x, int y) {
      int toPopX = x;
      int toPopY = 0;
      Tile2 popped = tiles[toPopY][toPopX];
      for (int i = 0; i < height - 1; i++) {
         int toShiftY = i + 1;
         tiles[i][x] = tiles[toShiftY][x];
      }
      return popped;
   }

   private Tile2 shiftRight(int x, int y) {
      int toPopX = 0;
      int toPopY = y;
      Tile2 popped = tiles[toPopY][toPopX];
      for (int i = width - 1; i > 0; i--) {
         int toShiftX = i - 1;
         tiles[y][i] = tiles[y][toShiftX];
      }
      return popped;
   }

   public Tile2[][] getTiles() {
      return tiles;
   }

   public void setTiles(Tile2[][] tiles) {
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

//   public static void main(String[] args) {
//      Level l = new Level(5, 4);
//      l.tiles[0][0] = new Tile(Type.L, Rotation.R);
//      l.tiles[0][1] = new Tile(Type.X, Rotation.U);
//      l.tiles[0][2] = new Tile(Type.L, Rotation.D);
//      l.tiles[0][3] = new Tile(Type.T, Rotation.R);
//      l.tiles[0][4] = new Tile(Type.L, Rotation.L);
//
//      l.tiles[1][0] = new Tile(Type.T, Rotation.D);
//      l.tiles[1][1] = new Tile(Type.X, Rotation.R);
//      l.tiles[1][2] = new Tile(Type.L, Rotation.L);
//      l.tiles[1][3] = new Tile(Type.I, Rotation.L);
//      l.tiles[1][4] = new Tile(Type.L, Rotation.R);
//
//      l.tiles[2][0] = new Tile(Type.T, Rotation.U);
//      l.tiles[2][1] = new Tile(Type.I, Rotation.L);
//      l.tiles[2][2] = new Tile(Type.T, Rotation.U);
//      l.tiles[2][3] = new Tile(Type.L, Rotation.D);
//      l.tiles[2][4] = new Tile(Type.L, Rotation.R);
//
//      l.tiles[3][0] = new Tile(Type.X, Rotation.R);
//      l.tiles[3][1] = new Tile(Type.L, Rotation.U);
//      l.tiles[3][2] = new Tile(Type.X, Rotation.R);
//      l.tiles[3][3] = new Tile(Type.T, Rotation.D);
//      l.tiles[3][4] = new Tile(Type.L, Rotation.D);
//
//      l.print();
//
//      l.insertTile(new Tile(Type.X, Rotation.U), Edge.TOP, 0);
//      System.out.println("\n\n\n");
//
//      l.print();
//
//      l.insertTile(new Tile(Type.X, Rotation.U), Edge.RIGHT, 1);
//      System.out.println("\n\n\n");
//
//      l.print();
//
//      l.insertTile(new Tile(Type.T, Rotation.U), Edge.BOTTOM, 2);
//      System.out.println("\n\n\n");
//
//      l.print();
//
//      l.insertTile(new Tile(Type.X, Rotation.U), Edge.LEFT, 3);
//      System.out.println("\n\n\n");
//
//      l.print();
//
//   }
//
//   public void print() {
//      for (int i = 0; i < height; i++) {
//         for (int j = 0; j < width; j++) {
//            System.out.print("[" + tiles[i][j].getType().toString() + "]");
//         }
//         System.out.println();
//      }
//   }

}
