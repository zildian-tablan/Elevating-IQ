
package ui;

import PkmnAudio.AudioPlayer;
import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
//import static utilz.Constants.UI.VolumeButton.*;
import utilz.LoadSave;


public class PauseOverlay extends State{
    
    private Games game;
    private Playing playing;
    private BufferedImage bgimg,bgimg2;
    private int bgx,bgy,bgwidth,bgheight;
    private SoundButton musicbutton,sfxbutton;
    public static UrmButton menuB,replayB,unpauseB,pvolume,mvolume;
    public static int vollvl=50;
    public static String svollvl="50";
    public static boolean easy,hard,expert;
    //private VolumeButton volumebutton;
  
    
    public PauseOverlay(Playing playing,Games game){
    
       super(game);
       this.game = game;
       this.playing = playing;
       
       loadbackground();   
       createSoundButton();
       createUrmButton();
       createVolumeButton();
    }
    
    private void createVolumeButton(){
        
        int pvolx = 420;
        int mvolx = 580;
        int voly = 380;
        
        pvolume = new UrmButton(pvolx,voly,55,55,3);
        mvolume = new UrmButton(mvolx,voly,55,55,4);
    }
    
    private void createUrmButton(){
        
        int menuX = 420;
        int replayX = 500;
        int unpauseX = 580;
        int bY = 460;
       
        
        menuB = new UrmButton(menuX,bY,55,55,1);
        replayB = new UrmButton(replayX,bY,55,55,2);
        unpauseB = new UrmButton(unpauseX,bY,55,55,0);
        
    }
    private void createSoundButton(){
        
        int soundX = (int)(450*Games.SCALE-110);
        int musicY = (int) (142*Games.SCALE);
        int sfxY =(int)(179*Games.SCALE);
        musicbutton = new SoundButton(soundX,musicY+10,Sound_Size-15,Sound_Size-15);
        sfxbutton = new SoundButton(soundX,sfxY+35,Sound_Size-15,Sound_Size-15);
        
    }
    
    private void loadbackground(){
        
        bgimg = LoadSave.GetSpriteAtlas(LoadSave.Pause_Background);
        bgimg2 = LoadSave.GetSpriteAtlas(LoadSave.Setting_Background);
        bgwidth = 287;
        bgheight = 483;
        bgx = Games.Game_Width/2 - bgwidth/2+25;
        bgy = (int)(25*Games.SCALE+30);
       
    }
    
    public void update(){
        musicbutton.update();
        sfxbutton.update();
        
        menuB.update();
        replayB.update();
        unpauseB.update();
        pvolume.update();
        mvolume.update();
    }
    
    public void draw(Graphics g){
        //bg
       
        
        g.drawImage(bgimg, bgx, bgy, bgwidth,bgheight,null);
        System.out.println("width: "+bgwidth+" height: "+bgheight);
        
        //buttons
        musicbutton.draw(g);
        sfxbutton.draw(g);
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
        pvolume.draw(g);
        mvolume.draw(g);
        drawVolLvl(g);
        //slider
       // volumebutton.draw(g);
    }
   
  
    
    public void mousePressed(MouseEvent me) {
        
        if(IsIn(me,musicbutton)){
            musicbutton.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,sfxbutton)){
            sfxbutton.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,menuB)){
            menuB.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,replayB)){
            replayB.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,unpauseB)){
            unpauseB.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,pvolume)){
            pvolume.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,mvolume)){
            mvolume.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }
   
    }

    public void mouseReleased(MouseEvent me) {
   
        if(IsIn(me,musicbutton)){
           if(musicbutton.isMousePressed()){
               musicbutton.setMuted(!musicbutton.isMuted());
               game.getAudioPlayer().toggleSongMute();
           }
        }else if(IsIn(me,sfxbutton)){
           if(sfxbutton.isMousePressed()){
               sfxbutton.setMuted(!sfxbutton.isMuted());
               game.getAudioPlayer().toggleEffectMute();
           }
        }else if(IsIn(me,menuB)){
           if(menuB.isMousePressed()){
           setGameState(Gamestate.MENU);
           PlayPause.pause = false;
           PlayPause.setting = false;
                playing.resetAll();
                playing.unpauseGame();
           }
        }else if(IsIn(me,replayB)){
           if(replayB.isMousePressed()){
               if(Playing.easy == true){
                  easy = true;
               }else if(Playing.hard == true){
                   hard = true;
               }else if(Playing.expert == true){
                   expert = true;
               }
               playing.resetAll();
               if(easy == true){
                   Playing.easy = true;
                   easy = false;
               }else if(hard == true){
                   Playing.hard = true;
                   hard = false;
               }else if(expert == true){
                   Playing.expert = true;
                   expert = false;
               }
               playing.unpauseGame();
           }
        }else if(IsIn(me,unpauseB)){
           if(unpauseB.isMousePressed()){
               playing.unpauseGame();
           }
        }else if(IsIn(me,pvolume)){
           if(pvolume.isMousePressed()){
             if(vollvl <100){
              vollvl = vollvl+25;
             }
             if(vollvl == 100){
             game.getAudioPlayer().setVolume(1f);
             }else if(vollvl == 75){
              game.getAudioPlayer().setVolume(.8f);
             }else if(vollvl == 50){
              game.getAudioPlayer().setVolume(.75f);
             }else if(vollvl == 25){
              game.getAudioPlayer().setVolume(.6f);
             }else if(vollvl == 0){
              game.getAudioPlayer().setVolume(.0f);
             }
             svollvl = String.valueOf(vollvl);
           }
        }else if(IsIn(me,mvolume)){
           if(mvolume.isMousePressed()){
              if(vollvl > 0){
              vollvl = vollvl-25;
             }
              if(vollvl == 100){
             game.getAudioPlayer().setVolume(1f);
             }else if(vollvl == 75){
              game.getAudioPlayer().setVolume(.8f);
             }else if(vollvl == 50){
              game.getAudioPlayer().setVolume(.75f);
             }else if(vollvl == 25){
              game.getAudioPlayer().setVolume(.6f);
             }else if(vollvl == 0){
              game.getAudioPlayer().setVolume(.0f);
             }
              svollvl = String.valueOf(vollvl);
           }
        }
        
        musicbutton.resetBools();
        sfxbutton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
        pvolume.resetBools();
        mvolume.resetBools();
    }

  
    public void mouseMoved(MouseEvent me) {
   
        musicbutton.setMouseOver(false);
        sfxbutton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        pvolume.setMouseOver(false);
        mvolume.setMouseOver(false);
        
        if(IsIn(me,musicbutton)){
            musicbutton.setMouseOver(true);
        }else if(IsIn(me,sfxbutton)){
            sfxbutton.setMouseOver(true);
        }else if(IsIn(me,menuB)){
            menuB.setMouseOver(true);
        }else if(IsIn(me,replayB)){
            replayB.setMouseOver(true);
        }else if(IsIn(me,unpauseB)){
            unpauseB.setMouseOver(true);
        }else if(IsIn(me,pvolume)){
            pvolume.setMouseOver(true);
        }else if(IsIn(me,mvolume)){
            mvolume.setMouseOver(true);
        }
        
    }   
    private boolean IsIn(MouseEvent me, PauseButton pb){
        return pb.getBounds().contains(me.getX(),me.getY());
           
    }
    
    public void drawVolLvl(Graphics g){
        g.setColor(Color.black);
       g.setFont(new Font("Dialog",Font.BOLD,30));
       if(vollvl < 100){
        g.drawString(svollvl,510,420);
       }else if(vollvl == 100){
        g.drawString(svollvl,500,420);
       }else if(vollvl == 0){
        g.drawString(svollvl,550,420);
       }
    }
    
    
    
}

   

    
    

