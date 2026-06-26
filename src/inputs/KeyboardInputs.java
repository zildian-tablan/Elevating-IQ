
package inputs;

import gamestates.Gamestate;
import static gamestates.Gamestate.LOGIN;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import static gamestates.Gamestate.SIGNUP;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import stacking_rectangle.GamePanel;


public class KeyboardInputs implements KeyListener{

    private GamePanel gamepanel;
    public KeyboardInputs(GamePanel gamepanel){
        this.gamepanel = gamepanel;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
   }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        switch(Gamestate.state){
            case SIGNUP:
                gamepanel.getGame().getSignUp().keyPressed(ke);
                break;
            case LOGIN:
                gamepanel.getGame().getLogIn().keyPressed(ke);
           case MENU:
               gamepanel.getGame().getMenu().keyPressed(ke);
               break;
           case PLAYING:
              gamepanel.getGame().getPlaying().keyPressed(ke);
               break;
           default:
               break;
       }
        
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
         switch(Gamestate.state){
              case LOGIN:
                gamepanel.getGame().getLogIn().keyPressed(ke);
           case MENU:
               gamepanel.getGame().getMenu().keyReleased(ke);
               break;
           case PLAYING:
              gamepanel.getGame().getPlaying().keyReleased(ke);
               break;
           default:
               break;
       }

    }
    
}
