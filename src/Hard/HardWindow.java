
package Hard;

import PkmnAudio.AudioPlayer;
import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.State;
import gamestates.Statemethods;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import stacking_rectangle.Games;
import utilz.LoadSave;


public class HardWindow extends State implements Statemethods{

    private HLevelButton[] hlbuttons = new HLevelButton[6];
    private BufferedImage hlbbg = LoadSave.GetSpriteAtlas(LoadSave.Level_Backgorund_H);
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    public static boolean drawLock = false;
    public int countdown = 0,count = 1;
    private boolean timerInitialized = false;
    private Timer timer;
    
    
    public HardWindow(Games game) {
        super(game);
        
        loadButtons();
        titleAnimation();
    }
    
    private void loadButtons(){
        hlbuttons[0] = new HLevelButton(240,35,0,Gamestate.PLAYING);
        hlbuttons[1] = new HLevelButton(780,50,1,Gamestate.PLAYING);
        hlbuttons[2] = new HLevelButton(240,410,2,Gamestate.PLAYING);
        hlbuttons[3] = new HLevelButton(780,410,3,Gamestate.PLAYING);
        hlbuttons[4] = new HLevelButton(510,245,4,Gamestate.PLAYING);
        hlbuttons[5] = new HLevelButton(120,600,5,Gamestate.DIFFICULTY);
    }

    @Override
    public void update() {
    updateAnimationTick();
    for(HLevelButton hlb : hlbuttons){
            hlb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(hlbbg,0,0,Games.Game_Width,Games.Game_Height,null);
         for(HLevelButton hlb : hlbuttons){
            hlb.draw(g);
        }
            g.drawImage(idleAni[aniIndex],330, 10, 300, 60,  null);
            
              if(drawLock == true){
                drawLevelLock(g);
                QCountdown();
            }
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
    public void mousePressed(MouseEvent me) {
      for(HLevelButton hlb  : hlbuttons){
            if(isInHL(me,hlb)){
                hlb.setMousePressed(true);
                game.getAudioPlayer().Buttonclick();
                break;
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
     for(HLevelButton hlb: hlbuttons){
             if(isInHL(me,hlb)){
                if(hlb.isMousePressed()){
                    if(hlbuttons[0].isMousePressed()/*&& Playing.e5c == true*/){
                        HardLevelOne.terminator = 5;
                        Playing.hlevel1 = true;
                         Playing.hlevel2 = false;
                        Playing.hlevel3 = false;
                        Playing.hlevel4 = false;
                        Playing.hlevel5 = false;
                         hlb.applyGamestate();
                        HardLevelOne.countdown = 20;
                         break;
                    }else if(hlbuttons[1].isMousePressed()){
                        if(Playing.h1c == true){
                            
                        HardLevelTwo.terminator = 5;
                        Playing.hlevel2 = true;
                        Playing.hlevel1=false;
                         Playing.hlevel3 = false;
                        Playing.hlevel4 = false;
                        Playing.hlevel5 = false;
                        HardLevelTwo.countdown = 20;
                         hlb.applyGamestate();
                        }
                        else if (Playing.h1c == false){
                             drawLock = true;
                            count = 1;
                        }
                    
                         break;
                    }else if(hlbuttons[2].isMousePressed()){
                      if(Playing.h2c == true){
                            
                        HardLevelThree.terminator = 5;
                        Playing.hlevel2 = false;
                        Playing.hlevel1=false;
                         Playing.hlevel3 = true;
                        Playing.hlevel4 = false;
                        Playing.hlevel5 = false;
                        HardLevelThree.countdown = 20;
                         hlb.applyGamestate();
                        }
                        else if (Playing.h2c == false){
                             drawLock = true;
                            count = 1;
                        }
                    
                    }else if(hlbuttons[3].isMousePressed()){ 
                       if(Playing.h3c == true){
                            
                        HardLevelFour.terminator = 5;
                        Playing.hlevel2 = false;
                        Playing.hlevel1=false;
                         Playing.hlevel3 = false;
                        Playing.hlevel4 = true;
                        Playing.hlevel5 = false;
                        HardLevelFour.countdown = 20;
                         hlb.applyGamestate();
                        }
                        else if (Playing.h3c == false){
                             drawLock = true;
                            count = 1;
                        }
                    
                    }else if(hlbuttons[4].isMousePressed()){
                       if(Playing.h4c == true){
                            
                        HardLevelFive.terminator = 5;
                        Playing.hlevel2 = false;
                        Playing.hlevel1=false;
                         Playing.hlevel3 = false;
                        Playing.hlevel4 = false;
                        Playing.hlevel5 = true;
                        HardLevelFive.countdown = 20;
                         hlb.applyGamestate();
                        }
                        else if (Playing.h4c == false){
                             drawLock = true;
                            count = 1;
                        }
                    
                    }else if(hlbuttons[5].isMousePressed()){
                        Playing.hard = false;
                         hlb.applyGamestate();
                         PlayMusic();
                         break;
                    }       
                    
                }
            }
        }
        
        resetButtons();
    }
    
     private void resetButtons(){
        for(HLevelButton hlb : hlbuttons){
            hlb.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
      for(HLevelButton hlb : hlbuttons){
            hlb.setMouseOver(false);
        }
        for(HLevelButton hlb: hlbuttons){
            if(isInHL(me,hlb)){
                hlb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
     }
    
    public void PlayMusic(){
     if(hlbuttons[5].getState() == Gamestate.DIFFICULTY){
            game.getAudioPlayer().playSong(AudioPlayer.BACKGROUND);
        }
    }
     
    public void drawLevelLock(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Dialog",Font.BOLD,25));
        g.drawString("Level Locked",400,200);
    }
    
    public void QCountdown(){
      if (!timerInitialized) {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Playing.paused == false){
            countdown--;
            if (countdown <= 0 && count == 1) {
                timer.stop();
                countdown = 5;
                count = 2;
                drawLock = false;
            }
           }
        }
    });
    timerInitialized = true;
    }
     if (timer.isRunning() == false) {
            timer.start();
            //System.out.println("ss");
        }
    }
    
}
