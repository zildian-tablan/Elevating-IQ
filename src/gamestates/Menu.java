
package gamestates;

import entities.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.GamePanel;
import stacking_rectangle.Games;
import ui.MenuButton;
import ui.PauseButton;
import ui.PauseOverlay;
import ui.PlayPause;
import ui.PlayPauseButton;
import utilz.LoadSave;


public class Menu extends State implements Statemethods{

    private MenuButton[] buttons = new MenuButton[3];
    
    private BufferedImage menubg=LoadSave.GetSpriteAtlas(LoadSave.Whole_Menu_Background);
    private PlayPauseButton setting;
    private Player player;
    public Playing playing;


    public Menu(Games game) {
        super(game);
        loadButtons();
        player = new Player();

    }
  
    private void loadButtons(){
        buttons[0] = new MenuButton(Games.Game_Width/2+50,290,0,Gamestate.DIFFICULTY);
        buttons[1] = new MenuButton(Games.Game_Width/2+50,365,1,Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Games.Game_Width/2+50,440,2,Gamestate.LOGIN);
        setting = new PlayPauseButton(925,25,55,55,1);
    }
//OPTIONS
    @Override
    public void update() {
        for(MenuButton mb : buttons){
          mb.update();
        }
        setting.update();
        
    }

    @Override
    public void draw(Graphics g) {
      g.drawImage(menubg, 0, 0, Games.Game_Width, Games.Game_Height, null);    
       for(MenuButton mb : buttons){
         mb.draw(g);
       }   
       setting.draw(g);
  
    }

    @Override
    public void mousePressed(MouseEvent me) {
    
        for(MenuButton mb : buttons){
            if(isIn(me,mb)){
                 
              mb.setMousePressed(true);
              game.getAudioPlayer().Buttonclick();
              break;
            }
        }
        if(IsIn(me,setting)){
            setting.setMousePressed(true);
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
   
       
        for(MenuButton mb : buttons){
            if(isIn(me,mb)){
                if(buttons[0].isMousePressed()){
                   
                    mb.applyGamestate();
                    break;
                }if(buttons[1].isMousePressed()){
                 
                    mb.applyGamestate();
                    break;
                }if(buttons[2].isMousePressed()){
                  
                    mb.applyGamestate();
                    LogIn.term = 0;
                    LogIn.error = false;  
                    break;
                }
                
            }
        }
        if(setting.isMousePressed()){   
            game.getAudioPlayer().Buttonclick();
            Gamestate.state = Gamestate.SETTING;
         }
         setting.resetBools();
       resetButtons();
 
        
    }

    private void resetButtons(){
        
        for(MenuButton mb : buttons){
            mb.resetBool();
        }
        
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
     
        setting.setMouseOver(false);
   
     
      for(MenuButton mb : buttons)
           mb.setMouseOver(false); 
      
           for(MenuButton mb : buttons)
            if(isIn(me,mb)){
                mb.setMouseOver(true);
                break;
            }
        if(IsIn(me,setting)){
            setting.setMouseOver(true);
            PlayPause.setting = true;
       
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            //Gamestate.state = Gamestate.PLAYING;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
  
    }

    private boolean IsIn(MouseEvent me, PauseButton pb){
        return pb.getBounds().contains(me.getX(),me.getY());
           
    }

}
