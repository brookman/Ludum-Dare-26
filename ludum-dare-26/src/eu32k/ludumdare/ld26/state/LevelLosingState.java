package eu32k.ludumdare.ld26.state;

public class LevelLosingState extends GameState{

   @Override
   public void init() {
      transitions.add(LevelLostState.class);
      transitions.add(MenuState.class);      
   }

   @Override
   public void destroy() {
      
   }

}
