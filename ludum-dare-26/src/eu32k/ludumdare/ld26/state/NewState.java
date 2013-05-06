package eu32k.ludumdare.ld26.state;

public enum NewState {
   MAIN_MENU, RUNNING, PAUSED, LOST, WON;

   public static NewState currentState = MAIN_MENU;

   public static void pause() {
      if (currentState == RUNNING) {
         currentState = PAUSED;
      } else if (currentState == PAUSED) {
         currentState = RUNNING;
      }
   }

   public static void mainMenu() {
      currentState = MAIN_MENU;
   }

   public static void lost() {
      currentState = LOST;
   }

   public static void won() {
      currentState = WON;
   }
}
