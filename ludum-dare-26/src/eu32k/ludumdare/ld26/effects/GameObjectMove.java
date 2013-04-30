package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.math.Vector2;

import eu32k.libgdx.common.TempVector2;
import eu32k.ludumdare.ld26.level.ObjectMoveComplete;
import eu32k.ludumdare.ld26.objects.GameObject;
import eu32k.ludumdare.ld26.state.LevelState;
import eu32k.ludumdare.ld26.state.StateMachine;

public class GameObjectMove implements IRunningEffect {
   private boolean complete;
   private GameObject object;
   private float targetX;
   private float targetY;
   private float speedX;
   private float speedY;
   private LevelState levelState;

   @Override
   public boolean complete() {
      return complete;
   }

   public GameObjectMove() {
   }

   public void initMove(GameObject object, float targetX, float targetY, float speed) {
      levelState = StateMachine.instance().getState(LevelState.class);
      this.object = object;
      this.targetX = targetX;
      this.targetY = targetY;
      complete = false;
      Vector2 t1 = TempVector2.tmp;
      Vector2 t2 = TempVector2.tmp2;
      t1.set(object.getX(), object.getY());
      t2.set(targetX, targetY);
      t2.sub(t1).nor().mul(speed);
      speedX = t2.x;
      speedY = t2.y;
      object.setFreeMovement(true);
   }

   @Override
   public void update(float delta) {
      float x = object.getX();
      float y = object.getY();

      x += speedX * delta;
      y += speedY * delta;
      if (speedX > 0 && x >= targetX || speedX < 0 && x <= targetX || speedY > 0 && y >= targetY || speedY < 0 && y <= targetY) {
         levelState.getEvents().enqueue(new ObjectMoveComplete(this));
         object.setPosition(targetX, targetY);
         complete = true;
         object.setFreeMovement(false);
         return;
      }
      object.setPosition(x, y);
   }

   public GameObject getObject() {
      return object;
   }

   public void setTile(GameObject object) {
      this.object = object;
   }

}
