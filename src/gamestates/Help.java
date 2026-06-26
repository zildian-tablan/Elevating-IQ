
package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import ui.PauseButton;
import ui.UrmButton;
import utilz.LoadSave;


public class Help extends State implements Statemethods {
    
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    private BufferedImage help=LoadSave.GetSpriteAtlas(LoadSave.Help);
    private UrmButton menuB;

    public Help(Games game) {
        super(game);
        
        loadButton();
        titleAnimation();
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
    
    private void loadButton(){
        menuB  = new UrmButton(850,570,55,55,1);
    }

    @Override
    public void update() {
        menuB.update();
        updateAnimationTick(); 
   }

    @Override
    public void draw(Graphics g) {
        g.drawImage(help, 0, 0, Games.Game_Width,Games.Game_Height,null);
         g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
        menuB.draw(g);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(IsIn(me,menuB)){
            menuB.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(IsIn(me,menuB)){
           if(menuB.isMousePressed()){
          Gamestate.state = Gamestate.SETTING;
           }
        }
        menuB.resetBools();
   }

    @Override
    public void mouseMoved(MouseEvent me) {
        menuB.setMouseOver(false);
        if(IsIn(me,menuB)){
            menuB.setMouseOver(true);
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
    
    
}
