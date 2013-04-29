package eu32k.ludumdare.ld26.state;

public class LevelWinningState extends GameState {

   @Override
   public void init() {
      transitions.add(LevelWonState.class);
      transitions.add(MenuState.class);      
   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub
      
   }

}
