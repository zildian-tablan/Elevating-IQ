
package expert;

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
/*import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;*/

public class ExpertWindow extends State implements Statemethods{

    private ExlevelButton[] exlbuttons = new ExlevelButton[6];
    private BufferedImage hlbbg = LoadSave.GetSpriteAtlas(LoadSave.Lava_PlatformBg);
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
     public static boolean drawLock = false;
    public int countdown = 0,count = 1;
    private boolean timerInitialized = false;
    private Timer timer;
    
    
    public ExpertWindow(Games game) {
        super(game);
        
        loadButtons();
        titleAnimation();
    }
    
    private void loadButtons(){
        exlbuttons[0] = new ExlevelButton(240,35,0,Gamestate.PLAYING);
        exlbuttons[1] = new ExlevelButton(780,50,1,Gamestate.PLAYING);
        exlbuttons[2] = new ExlevelButton(240,410,2,Gamestate.PLAYING);
        exlbuttons[3] = new ExlevelButton(780,410,3,Gamestate.PLAYING);
        exlbuttons[4] = new ExlevelButton(510,245,4,Gamestate.PLAYING);
        exlbuttons[5] = new ExlevelButton(120,600,5,Gamestate.DIFFICULTY);
    }

    @Override
    public void update() {
    updateAnimationTick();
    for(ExlevelButton exlb : exlbuttons){
            exlb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(hlbbg,0,0,Games.Game_Width,Games.Game_Height,null);
         for(ExlevelButton exlb : exlbuttons){
            exlb.draw(g);
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
      for(ExlevelButton exlb  : exlbuttons){
            if(isInEXL(me,exlb)){
                exlb.setMousePressed(true);
                game.getAudioPlayer().Buttonclick();
                break;
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
     for(ExlevelButton exlb: exlbuttons){
             if(isInEXL(me,exlb)){
                if(exlb.isMousePressed()){
                    if(exlbuttons[0].isMousePressed() /*&& Playing.h5c == true*/){
                        Expertlevelone.terminator = 5;
                        Playing.exlevel1 = true;
                         Playing.exlevel2 = false;
                        Playing.exlevel3 = false;
                        Playing.exlevel4 = false;
                        Playing.exlevel5 = false;
                        Expertlevelone.countdown = 15;
                         exlb.applyGamestate();
                         
                         break;
                    }else if(exlbuttons[1].isMousePressed()){
                       if(Playing.ex1c == true){
                            
                        Expertleveltwo.terminator = 5;
                        Playing.exlevel1 = false;
                         Playing.exlevel2 = true;
                        Playing.exlevel3 = false;
                        Playing.exlevel4 = false;
                        Playing.exlevel5 = false;
                        Expertleveltwo.countdown = 15;
                         exlb.applyGamestate();
                       }
                       else if(Playing.ex1c == false){
                           drawLock = true;
                            count = 1;
                       }
                        
                         break;
                    }else if(exlbuttons[2].isMousePressed()){
                       if(Playing.ex2c == true){
                            
                        Expertlevelthree.terminator = 5;
                        Playing.exlevel1 = false;
                         Playing.exlevel2 = false;
                        Playing.exlevel3 = true;
                        Playing.exlevel4 = false;
                        Playing.exlevel5 = false;
                        Expertlevelthree.countdown = 15;
                         exlb.applyGamestate();
                       }
                       else if(Playing.ex2c == false){
                           drawLock = true;
                            count = 1;
                     }
                    }else if(exlbuttons[3].isMousePressed()){
                        if(Playing.ex3c == true){
                            
                        Expertlevelfour.terminator = 5;
                        Playing.exlevel1 = false;
                         Playing.exlevel2 = false;
                        Playing.exlevel3 = false;
                        Playing.exlevel4 = true;
                        Playing.exlevel5 = false;
                        Expertlevelfour.countdown = 15;
                         exlb.applyGamestate();
                       }
                       else if(Playing.ex3c == false){
                           drawLock = true;
                            count = 1;
                }
                    }else if(exlbuttons[4].isMousePressed()){
                        if(Playing.ex4c == true){
                            
                        Expertlevelfive.terminator = 5;
                        Playing.exlevel1 = false;
                         Playing.exlevel2 = false;
                        Playing.exlevel3 = false;
                        Playing.exlevel4 = false;
                        Playing.exlevel5 = true;
                        Expertlevelfive.countdown = 15;
                         exlb.applyGamestate();
                       }
                       else if(Playing.ex4c == false){
                           drawLock = true;
                            count = 1;
                }
                       
                         break;
                    }   else if(exlbuttons[5].isMousePressed()){
                        Playing.expert = false;
                         exlb.applyGamestate();
                         PlayMusic();
                         break;
                    }     
                    
                }
            }
        }
        
        resetButtons();
    }
    
     private void resetButtons(){
        for(ExlevelButton exlb : exlbuttons){
            exlb.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
      for(ExlevelButton exlb : exlbuttons){
            exlb.setMouseOver(false);
        }
        for(ExlevelButton exlb: exlbuttons){
            if(isInEXL(me,exlb)){
                exlb.setMouseOver(true);
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
      if(exlbuttons[5].getState() == Gamestate.DIFFICULTY){
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
