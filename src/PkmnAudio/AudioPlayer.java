
package PkmnAudio;

import gamestates.Gamestate;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioPlayer {

      //SONGS
    public static int EASY = 0;
    public static int HARD = 1;
    public static int EXPERT = 2;
    public static int BACKGROUND = 3;
    //EFFECTS
    public static int GAMEOVER = 0;
    public static int LVL_COMPLETED = 1;
    public static int CORRECT = 2;
    public static int WRONG = 3;
    public static int CLICK = 4;
    
    private Clip[] songs,effects;
    private int currentSongId;
    private float volume = 0.75f;
    private boolean songMute, effectMute;
   // private Random rand = new Random();
    public AudioPlayer(){
        loadSongs();
        loadEffects();
       
    }
    private void loadSongs(){
        String[] names = {"easy","hard","expert","bgmusic"};
        songs  = new Clip[names.length];
        for(int i = 0; i < songs.length; i++)
            songs[i] = getClip(names[i]);
    }
    private void loadEffects(){
        String[] effectNames = {"gameover","lvlcompleted","correct","wrong","click"};
        effects  = new Clip[effectNames.length];
        for(int i = 0; i < effects.length; i++)
            effects[i] = getClip(effectNames[i]);
        
        updateEffectsVolume();
        
    }
   private Clip getClip(String name) {
		URL url = getClass().getResource("/audio/"+name+".wav");
                if (url == null) {
                System.err.println("Resource not found: /audio/" + name + ".wav");
                   return null;
                 }

		AudioInputStream audio;
		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		return null;
   }
   public void setVolume(float volume){
       this.volume = volume;
       updateSongVolume();
       updateEffectsVolume();
   }
   public void stopSong(){
       if(songs[currentSongId].isActive())
          songs[currentSongId].stop();
   }
   public void setDifficultySong(int diffIndex){
       if(diffIndex == 0){
           playSong(EASY);
       }else if(diffIndex == 1){
           playSong(HARD);
       }else if(diffIndex == 2){
           playSong(EXPERT);
       }
   }
   public void lvlCompleted(){
       stopSong();
       playEffect(LVL_COMPLETED);
   }
   public void buttonpressed(){
       playEffect(CORRECT);
   }
   public void powerupused(){
       playEffect(CORRECT);
   }
   public void answerCorrect(){
       playEffect(CORRECT);
   }
   public void Buttonclick(){
       playEffect(CLICK);
   }
   public void answerWrong(){
       playEffect(WRONG);
   }
    public void gameOver(){
       stopSong();
       playEffect(GAMEOVER);
       
       
   }
 
   public void playEffect(int effect){
       effects[effect].setMicrosecondPosition(0);
       effects[effect].start();
   }
   public void playSong (int song){
       stopSong();    
       currentSongId = song;
       updateSongVolume();
       songs[currentSongId].setMicrosecondPosition(0);
       songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
   }
   public void toggleSongMute(){
       this.songMute = !songMute;
       for(Clip c : songs){
           BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
           booleanControl.setValue(songMute);
       }
   }
   public void toggleEffectMute(){
       this.effectMute = !effectMute;
       for(Clip c : effects){
           BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
           booleanControl.setValue(effectMute);
       }
       if(!effectMute)
            playEffect(CORRECT);
   }
   private void updateSongVolume(){
       FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
       float range = gainControl.getMaximum()-gainControl.getMinimum();
       float gain = (range * volume) + gainControl.getMinimum();
       gainControl.setValue(gain);
   }
   private void updateEffectsVolume(){
       for(Clip c : effects){
       FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
       float range = gainControl.getMaximum()-gainControl.getMinimum();
       float gain = (range * volume) + gainControl.getMinimum();
       gainControl.setValue(gain);
       }
   }
}
