
package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import stacking_rectangle.Games;


public class GameOverLay {
    
    private Playing playing;
    public static boolean e,h,ex;
    
    public GameOverLay(Playing playing){
        this.playing = playing;
        
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics g){
        
        g.setColor(new Color(150,0,0));
        g.fillRect(0, 0, Games.Game_Width, Games.Game_Height);
        
        g.setColor(Color.white);
        g.drawString("GAME OVER", Games.Game_Width/2-50, 150);
        g.drawString("Click Anywhere to go back Menu!", Games.Game_Width/2-150, 300);
    }
    
    public void mousePressed(MouseEvent me){   
     
            if(Playing.easy == true){
                e=true;
                Gamestate.state = Gamestate.EASY;
            }else if(Playing.hard == true){
                h=true;
                Gamestate.state = Gamestate.HARD;
            }else if(Playing.expert == true){
                ex=true;
                Gamestate.state = Gamestate.EXPERT;
            } 
            playing.resetAll();     
            if(e == true){
                Playing.easy=true;
                e = false;
            }else if(h == true){
                Playing.hard = true;
                h= false;
               
            }else if(ex == true){
                Playing.expert = true;
                ex = false;
            } 

    }
}
