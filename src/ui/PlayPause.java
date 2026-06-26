
package ui;

import PkmnAudio.AudioPlayer;
import entities.Player;
import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.State;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import stacking_rectangle.Games;


public class PlayPause extends State{
    
    private Playing playing;
    private Games game;
    private Player player;
    private PlayPauseButton playpause;
    public static int rindex=2;
    public static boolean setting=false,pause=false;
    
    public PlayPause(Games game,Playing playing) {
        super(game);
        this.game = game;
        this.playing = playing;
        player = new Player();
        createButton();
        
    }
    
    public void createButton(){
        playpause = new PlayPauseButton(925,25,55,55,0);
        //powerup2 = new PlayPauseButton(50,450,55,55,3);
    }
   
    
    public void update(){
      playpause.update();
      //powerup.update();
     // powerup2.update();
    }
    
    public void draw(Graphics g){
        playpause.draw(g);
//        powerup.draw(g);
        
    }
    
    public void mousePressed(MouseEvent me) {
        if(Playing.paused == false && Playing.gameOver == false){
        if(IsIn(me,playpause)){
            playpause.setMousePressed(true);
        }
        }
   
    }

    public void mouseReleased(MouseEvent me) {
   
        if(IsIn(me,playpause)){
           if(playpause.isMousePressed()){
              if(Playing.paused == false && Playing.gameOver == false && Playing.lvlc == false)
               Playing.paused = !Playing.paused;
               pause = true;
           }
        }   
        playpause.resetBools(); 
    }

  
    public void mouseMoved(MouseEvent me) {
   if(Playing.paused == false && Playing.gameOver == false){
        playpause.setMouseOver(false);
        //powerup.setMouseOver(false);
        
        if(IsIn(me,playpause)){
            playpause.setMouseOver(true);
        }
   }
        
    }   
    private boolean IsIn(MouseEvent me, PauseButton pb){
        return pb.getBounds().contains(me.getX(),me.getY());
           
    }
    
}
