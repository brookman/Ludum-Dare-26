package eu32k.ludumdare.ld26.state;

public class LevelLostState extends GameState {

   @Override
   public void init() {
      transitions.add(LevelState.class);
      transitions.add(MenuState.class);
      transitions.add(LevelInitState.class);
   }

   @Override
   public void destroy() {
      
   }
   
   

}
