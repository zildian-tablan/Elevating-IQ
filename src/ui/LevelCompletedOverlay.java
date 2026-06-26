
package ui;

import Easy.LevelFive;
import Easy.LevelFour;
import Easy.LevelOne;
import Easy.LevelThree;
import Easy.LevelTwo;
import Hard.HardLevelFive;
import Hard.HardLevelFour;
import Hard.HardLevelOne;
import Hard.HardLevelThree;
import Hard.HardLevelTwo;
import expert.Expertlevelfive;
import expert.Expertlevelfour;
import expert.Expertlevelone;
import expert.Expertlevelthree;
import expert.Expertleveltwo;
import gamestates.Gamestate;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import static utilz.Constants.UI.URMButtons.*;
import utilz.LoadSave;


public class LevelCompletedOverlay {
    
    private Playing playing;
    private UrmButton menu,next;
    private BufferedImage lvlimg;
    private int lbgx,lbgy,lbgw,lbgh;
    
    public LevelCompletedOverlay(Playing playing){
        this.playing = playing;
        initImg();
        initButtons();
    }

    private void initImg() {
    lvlimg = LoadSave.GetSpriteAtlas(LoadSave.LevelC_Background);
      lbgw = 287;
        lbgh = 350;
        lbgx = Games.Game_Width/2 - lbgw/2+28;
        lbgy = (int)(25*Games.SCALE+90);
    }

    private void initButtons() {
         
        int menuX = (int)(313*Games.SCALE-20);
        int nextX = (int)(462*Games.SCALE-140);
        int bY = (int)(250*Games.SCALE);
        
        menu = new UrmButton(menuX,bY,70,70,1);
        next = new UrmButton(nextX,bY,70,70,0);
    }
    
    public void update(){
        
        next.update();
        menu.update();
    }
    
    public void draw(Graphics g){
       
        g.drawImage(lvlimg, lbgx, lbgy, lbgw, lbgh, null);
        next.draw(g);
        menu.draw(g);
           g.setColor(Color.black);
         g.setFont(new Font("Times New Roman",Font.BOLD,30));
      
        g.drawString("Score:", 485, 300);
           g.setFont(new Font("Times New Roman",Font.BOLD,45));
           if(Playing.levelCompleted == true){
           g.drawString(LevelOne.oscore, 490, 340);
           }else if (Playing.level2Completed == true){
                g.drawString(LevelTwo.oscore, 490, 340);
           }else if (Playing.level3Completed == true){
                g.drawString(LevelThree.oscore, 490, 340);
           }else if (Playing.level4Completed == true){
                g.drawString(LevelFour.oscore, 490, 340);
           }else if (Playing.level5Completed == true){
                g.drawString(LevelFive.oscore, 490, 340);
           }else if (Playing.hlevelCompleted == true){
                g.drawString(HardLevelOne.oscore, 490, 340);
           }else if (Playing.hlevel2Completed == true){
                g.drawString(HardLevelTwo.oscore, 490, 340);
           }else if (Playing.hlevel3Completed == true){
                g.drawString(HardLevelThree.oscore, 490, 340);
           }else if (Playing.hlevel4Completed == true){
                g.drawString(HardLevelFour.oscore, 490, 340);
           }else if (Playing.hlevel5Completed == true){
                g.drawString(HardLevelFive.oscore, 490, 340);
           }else if (Playing.exlevelCompleted == true){
                g.drawString(Expertlevelone.oscore, 490, 340);
           }else if (Playing.exlevel2Completed == true){
                g.drawString(Expertleveltwo.oscore, 490, 340);
           }else if (Playing.exlevel3Completed == true){
                g.drawString(Expertlevelthree.oscore, 490, 340);
           }else if (Playing.exlevel4Completed == true){
                g.drawString(Expertlevelfour.oscore, 490, 340);
           }else if (Playing.exlevel5Completed == true){
                g.drawString(Expertlevelfive.oscore, 490, 340);
           }
        
    }
    
    public void mousePressed(MouseEvent me) {
        
           if(IsIn(me,next)){
            next.setMousePressed(true);
        }else if(IsIn(me,menu)){
            menu.setMousePressed(true);
        }

    }

    public void mouseReleased(MouseEvent me) {
       if(IsIn(me,next)){
           if(next.isMousePressed()){
               if(Playing.easy == true){
               Gamestate.state = Gamestate.EASY;
               playing.resetAll();
               Playing.easy = true;
               }else if (Playing.hard == true){
                Gamestate.state = Gamestate.HARD;
                playing.resetAll();
                Playing.hard = true;
               }else if (Playing.expert == true){
                Gamestate.state = Gamestate.EXPERT;
                playing.resetAll();
                Playing.expert = true;
               }
               
           }
        }else if(IsIn(me,menu)){
            if(menu.isMousePressed()){
               Gamestate.state = Gamestate.MENU;
               playing.resetAll();
            }
        }
 menu.resetBools();
 next.resetBools();
       
    }

  
    public void mouseMoved(MouseEvent me) {
        next.setMouseOver(false);
        menu.setMouseOver(false);
        
        if(IsIn(me,next)){
            next.setMouseOver(true);
        }else if(IsIn(me,menu)){
            menu.setMouseOver(true);
        }
    }   
    
     private boolean IsIn(MouseEvent me,UrmButton ub){
        return ub.getBounds().contains(me.getX(),me.getY());
           
    }
    
    
}
