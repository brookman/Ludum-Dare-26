package eu32k.ludumdare.ld26.effects;

import eu32k.ludumdare.ld26.events.EventBase;

public class FadeComplete extends EventBase {
   
   public TileFade fade;
   
   public FadeComplete(TileFade fade) {
      this.fade = fade;
   }

}
