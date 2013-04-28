package eu32k.ludumdare.ld26;

public enum Direction {
    N, E, S, W; //North, East, South, West
    
    public static Direction getOpposite(Direction dir) {
       switch(dir) {
       case N:
          return S;
       case E:
          return W;
       case S:
          return N;
       case W:
       default:
          return E;
       }
    }
 }