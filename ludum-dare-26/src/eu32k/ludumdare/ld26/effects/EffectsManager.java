package eu32k.ludumdare.ld26.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

import eu32k.ludumdare.ld26.events.EventQueue;
import eu32k.ludumdare.ld26.events.IEvent;
import eu32k.ludumdare.ld26.events.IEventHandler;

public class EffectsManager implements IEventHandler {
   public final static int SONG_BITBREAK = 1;
   public final static int PART_BITBREAK_INTRO = 0;
   public final static int PART_BITBREAK_BODY = 1;

   public final static String TRACK_BITBREAK_INTRO = "sound/bitbreak_intro.ogg";
   public final static String TRACK_BITBREAK_BODY = "sound/bitbreak_body.ogg";

   private float currentSongLength = 0f;
   
   private ColorPulseManager colors;
   private EventQueue events;
   private Music music;

   public EffectsManager() {
      events = new EventQueue();
      colors = new ColorPulseManager();
      colors.setMainColor(new Color(41 / 255f, 106 / 255f, 149 / 255f, 1f));
      events.addHandler(this);
   }

   public EventQueue getEvents() {
      return events;
   }

   public void initBitbreak()
   {
      events.enqueue(new PlayPartEvent(0, SONG_BITBREAK, PART_BITBREAK_INTRO));
   }
   
   public void update(float delta) {
      events.tick(delta);
      if (music != null && music.isPlaying()) {

         colors.setTime(music.getPosition());
      }
   }

   @Override
   public void handle(IEvent ev) {
      if (ev instanceof PlayPartEvent) {
         handlePlayPartEvent((PlayPartEvent) ev);
      }
   }

   private void handlePlayPartEvent(PlayPartEvent ev) {
      switch (ev.song) {
      default:
         stop();
         break;
      case SONG_BITBREAK:
         playBitbreakPart(ev);
         break;
      }
   }

   private void playBitbreakPart(PlayPartEvent ev) {
      switch (ev.part) {
      default:
         stop();
         break;
      case PART_BITBREAK_INTRO:
         colors.init(ColorPulseManager.INTENSITY_EMPTY, ColorPulseManager.INTENSITY_FULL);
         play(TRACK_BITBREAK_INTRO, false);
         events.enqueue(new PlayPartEvent(6, SONG_BITBREAK, PART_BITBREAK_BODY));
         break;
      case PART_BITBREAK_BODY:
         colors.init(ColorPulseManager.INTENSITY_BEAT, ColorPulseManager.INTENSITY_FULL);
         play(TRACK_BITBREAK_BODY, false);
         events.enqueue(new PlayPartEvent(72, SONG_BITBREAK, PART_BITBREAK_BODY));
         break;
      }
   }

   private void play(String track, boolean loop) {
      music = Gdx.audio.newMusic(Gdx.files.getFileHandle(track, FileType.Internal));
      music.setLooping(loop);
      music.setVolume(0.5f);
      music.play();
      
   }

   private void stop() {
      colors.stop();
      music.stop();
      currentSongLength = 0;
   }

   public Color getCurrentColor() {
      return colors.getCurrentColor();
   }

}
