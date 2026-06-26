
package Easy;

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


public class EasyWindow extends State implements Statemethods {
    
      private ELevelButton[] elbuttons = new ELevelButton[6];
    private BufferedImage elbbg = LoadSave.GetSpriteAtlas(LoadSave.LevelBE_Background);
     private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    public static boolean drawLock = false;
    public int countdown = 0,count = 1;
    private boolean timerInitialized = false;
    private Timer timer;
    
    public EasyWindow(Games game) {
        super(game);
        
        loadButtons();
        titleAnimation();
    }
    
    private void loadButtons(){
        elbuttons[0] = new ELevelButton(240,50,0,Gamestate.PLAYING);
        elbuttons[1] = new ELevelButton(780,50,1,Gamestate.PLAYING);
        elbuttons[2] = new ELevelButton(240,420,2,Gamestate.PLAYING);
        elbuttons[3] = new ELevelButton(780,420,3,Gamestate.PLAYING);
        elbuttons[4] = new ELevelButton(510,265,4,Gamestate.PLAYING);
        elbuttons[5] = new ELevelButton(120,600,5,Gamestate.DIFFICULTY);
    }

    @Override
    public void update() {
        updateAnimationTick();
    for(ELevelButton elb : elbuttons){
            elb.update();
        }
    }
    

    @Override
    public void draw(Graphics g) {
        
         g.drawImage(elbbg,0,0,Games.Game_Width,Games.Game_Height,null);
         for(ELevelButton elb : elbuttons){
            elb.draw(g);
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
        for(ELevelButton elb  : elbuttons){
            if(isInEL(me,elb)){
               elb.setMousePressed(true);
               game.getAudioPlayer().Buttonclick();
                break;
                
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
         for(ELevelButton elb  : elbuttons){
             if(isInEL(me,elb)){
                if(elb.isMousePressed()){
                        if(elbuttons[0].isMousePressed()){
                        Playing.level1 = true;
                        Playing.level3=false;
                        Playing.level4=false;
                        Playing.level5=false;
                        Playing.level2 = false;
                        elb.applyGamestate();
                         
                    break;
                    }
                    else if(elbuttons[1].isMousePressed() ){
                        if(Playing.e1c == true){
                        Playing.level1=false;
                        Playing.level3=false;
                        Playing.level4=false;
                        Playing.level5=false;
                        Playing.level2 = true;       
                            elb.applyGamestate();
                        }else if(Playing.e1c == false){
                            drawLock = true;
                            count = 1;
                        }
                        
                    break;
                    }
                    else if(elbuttons[2].isMousePressed()){
                       if(Playing.e2c == true){
                        Playing.level3 = true;
                        Playing.level1=false;
                        Playing.level4=false;
                        Playing.level5=false;
                        Playing.level2 = false;
                            elb.applyGamestate();
                       }else if (Playing.e2c == false){
                            drawLock = true;
                            count = 1;
                       }
                      
                    break;
                    }else if(elbuttons[3].isMousePressed()){
                        if(Playing.e3c == true){
                         Playing.level3 = false;
                        Playing.level1=false;
                        Playing.level4=true;
                        Playing.level5=false;
                        Playing.level2 = false;      
                            elb.applyGamestate();
                        }else if(Playing.e3c == false){
                            drawLock=true;
                            count = 1;
                        }
               
                    break;
                    }else if(elbuttons[4].isMousePressed()){
                        if(Playing.e4c == true){
                        Playing.level3 = false;
                        Playing.level1=false;
                        Playing.level4=false;
                        Playing.level5=true;
                        Playing.level2 = false;      
                        elb.applyGamestate();
                        }else if(Playing.e4c == false){
                            drawLock = true;
                            count = 1;
                        }
                 
                    break;
                    }else if(elbuttons[5].isMousePressed()){
                        Playing.easy = false;
                        elb.applyGamestate();
                        PlayMusic();
                    }
             
                }
            }
        }
        
        resetButtons();
    }
    
    private void resetButtons(){
        for(ELevelButton elb : elbuttons){
            elb.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
         for(ELevelButton elb  : elbuttons){
            elb.setMouseOver(false);
        }
        for(ELevelButton elb  : elbuttons){
            if(isInEL(me,elb)){
                elb.setMouseOver(true);
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
        
      if(elbuttons[5].getState() == Gamestate.DIFFICULTY){
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
