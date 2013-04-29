package eu32k.ludumdare.ld26.effects;

import eu32k.ludumdare.ld26.events.EventBase;

public class MusicEvent extends EventBase {

   private boolean playMusic = false;

   public MusicEvent(boolean playMusic) {
      this.playMusic = playMusic;
   }

   public boolean getPlayMusic() {
      return playMusic;
   }
}
