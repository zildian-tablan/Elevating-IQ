
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import ui.PauseButton;
import ui.PlayPauseButton;
import ui.SoundButton;
import ui.UrmButton;
import static utilz.Constants.UI.PauseButtons.Sound_Size;
import utilz.LoadSave;


public class Settings extends State implements Statemethods{
    
   private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    private BufferedImage setbg = LoadSave.GetSpriteAtlas(LoadSave.Setting_Background_W);
  private BufferedImage  setcon= LoadSave.GetSpriteAtlas(LoadSave.Setting_Background);
  
  private UrmButton menuB,pvolume,mvolume;
  private PlayPauseButton help;
   private SoundButton musicbutton,sfxbutton;
    private int bgx,bgy,bgwidth,bgheight;
     public static int vollvl=50;
    public static String svollvl="50";

    public Settings(Games game) {
        super(game);
        
       createButtons();
       titleAnimation();
    }
    
    private void createButtons(){
        bgwidth = 287;
        bgheight = 483;
        bgx = Games.Game_Width/2 - bgwidth/2+25;
        bgy = (int)(25*Games.SCALE+30);
       
        int menuX = 450;
        int powerx = 550;
        int bY = 460;
        int pvolx = 420;
        int mvolx = 580;
        int voly = 380;
        int soundX = (int)(450*Games.SCALE-110);
        int musicY = (int) (142*Games.SCALE);
        int sfxY =(int)(179*Games.SCALE);
        
        musicbutton = new SoundButton(soundX-30,musicY+30,Sound_Size-15,Sound_Size-15);
        sfxbutton = new SoundButton(soundX-30,sfxY+55,Sound_Size-15,Sound_Size-15);
        pvolume = new UrmButton(pvolx-30,voly+20,55,55,3);
        mvolume = new UrmButton(mvolx-30,voly+20,55,55,4); 
        menuB = new UrmButton(menuX-30,bY+20,55,55,1);
        help = new PlayPauseButton(powerx-30,bY+20,55,55,2);
        
    }
    public void  titleAnimation(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Title_Sprite);
       
       idleAni = new BufferedImage[3]; 
       for(int i = 0 ; i < idleAni.length; i++){
           idleAni[i] = img.getSubimage(i*400, 0, 400, 90);
       }    
    }
  
    public void updateAnimationTick(){
       
       aniTick++;
       if(aniTick >= aniSpeed){
           aniTick = 0;
           aniIndex++;
           if (aniIndex >= idleAni.length){
               aniIndex = 0;
           }
       }
    }

    @Override
    public void update() {
        
         musicbutton.update();
        sfxbutton.update();
        
        menuB.update();
        help.update();
        
        pvolume.update();
        mvolume.update();
        updateAnimationTick(); 
        
  }

    @Override
    public void draw(Graphics g) {
        g.drawImage(setbg, 0, 0,Games.Game_Width,Games.Game_Height,null);
        g.drawImage(setcon, bgx-30, bgy+20, bgwidth,bgheight,null);
         g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
        musicbutton.draw(g);
        sfxbutton.draw(g);
        menuB.draw(g);
        help.draw(g);
        pvolume.draw(g);
        mvolume.draw(g);
        drawVolLvl(g);
    }

    @Override
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
        }else if(IsIn(me,help)){
            help.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,pvolume)){
            pvolume.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,mvolume)){
            mvolume.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }
        

   }

    @Override
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
          Gamestate.state = Gamestate.MENU;
           }
        }else if(IsIn(me,help)){
           if(help.isMousePressed()){
             Gamestate.state = Gamestate.HELP;
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
        help.resetBools();
        pvolume.resetBools();
        mvolume.resetBools();
   }

    @Override
    public void mouseMoved(MouseEvent me) {
        musicbutton.setMouseOver(false);
        sfxbutton.setMouseOver(false);
        menuB.setMouseOver(false);
        help.setMouseOver(false);
        pvolume.setMouseOver(false);
        mvolume.setMouseOver(false);
        
        if(IsIn(me,musicbutton)){
            musicbutton.setMouseOver(true);
        }else if(IsIn(me,sfxbutton)){
            sfxbutton.setMouseOver(true);
        }else if(IsIn(me,menuB)){
            menuB.setMouseOver(true);
        }else if(IsIn(me,help)){
            help.setMouseOver(true);
        }else if(IsIn(me,pvolume)){
            pvolume.setMouseOver(true);
        }else if(IsIn(me,mvolume)){
            mvolume.setMouseOver(true);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
   }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
     private boolean IsIn(MouseEvent me, PauseButton pb){
        return pb.getBounds().contains(me.getX(),me.getY());
           
    }
    
    public void drawVolLvl(Graphics g){
        g.setColor(Color.black);
       g.setFont(new Font("Dialog",Font.BOLD,30));
       if(vollvl < 100){
        g.drawString(svollvl,510-30,420+20);
       }else if(vollvl == 100){
        g.drawString(svollvl,500-30,420+20);
       }else if(vollvl == 0){
        g.drawString(svollvl,550-30,420+20);
       }
    }
    
    
}
