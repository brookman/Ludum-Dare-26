package eu32k.ludumdare.ld26.events.messages;

import eu32k.ludumdare.ld26.events.EventBase;

public class PlayPartEvent extends EventBase {
   public int song;
   public int part;

   public PlayPartEvent(float time, int song, int part) {
      this.song = song;
      this.part = part;
      setTime(time);
   }
}
