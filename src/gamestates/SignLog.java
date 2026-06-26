
package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import ui.LogInButton;
import utilz.LoadSave;


public class SignLog extends State implements Statemethods{
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;

    public static String user,pass,cpass,gender;
    private BufferedImage signupbg = LoadSave.GetSpriteAtlas(LoadSave.SignLog_Background);
    private LogInButton[] libbuttons = new LogInButton[3];
    
    public SignLog(Games game) {
        super(game);
        
        loadButtons();  
        titleAnimation();
        
    }
    
     private void loadButtons(){
        libbuttons[0] = new LogInButton(580,288,1,Gamestate.SIGNUP);
        libbuttons[1] = new LogInButton(580,338,0,Gamestate.LOGIN);
        libbuttons[2] = new LogInButton(580,388,2,Gamestate.CANCELS);
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
         for(LogInButton lib : libbuttons){    
            
            lib.update();
        }
        updateAnimationTick(); 
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(signupbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
        for(LogInButton lib : libbuttons){
           lib.draw(g);
        }
            g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
     }


    @Override
    public void mousePressed(MouseEvent me) {
         for(LogInButton lib : libbuttons){
         if(isInl(me,lib)){
                lib.setMousePressed(true);
                break;
            }
          }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
          for(LogInButton lib : libbuttons){
            if(isInl(me,lib)){
               if(libbuttons[0].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                  lib.applyGamestate();
                   break;
               }else if(libbuttons[1].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                   lib.applyGamestate();
                   break;
               }else if(libbuttons[2].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                   lib.applyGamestate();
                   break;
               }
            }
        }
       resetButtons(); 
    }
    
     private void resetButtons(){
        for(LogInButton lib : libbuttons){
            lib.resetBool();
          
        }
    }


    @Override
    public void mouseMoved(MouseEvent me) {
          for(LogInButton lib : libbuttons)
           lib.setMouseOver(false); 
      
      for(LogInButton lib : libbuttons)
            if(isInl(me,lib)){
                lib.setMouseOver(true);
                break;
            }
     }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
