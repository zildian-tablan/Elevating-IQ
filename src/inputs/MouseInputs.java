
package inputs;

import gamestates.Gamestate;
import static gamestates.Gamestate.DIFFICULTY;
import static gamestates.Gamestate.EASY;
import static gamestates.Gamestate.HELP;
import static gamestates.Gamestate.LEADERBOARD;
import static gamestates.Gamestate.LOGIN;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import static gamestates.Gamestate.SIGNUP;
import static gamestates.Gamestate.OPTIONS;
import static gamestates.Gamestate.PROFILE;
import static gamestates.Gamestate.RANKING;
import static gamestates.Gamestate.SETTING;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import stacking_rectangle.GamePanel;


public class MouseInputs implements MouseListener, MouseMotionListener{

    private GamePanel gamepanel;
    public MouseInputs(GamePanel gamepanel){
        this.gamepanel = gamepanel;
    }
     
    @Override
    public void mouseClicked(MouseEvent me) {
     
    }

    @Override
    public void mousePressed(MouseEvent me) {
        switch(Gamestate.state){
            case SIGNLOG:
                gamepanel.getGame().getSignLog().mousePressed(me);
            case SIGNUP:
                gamepanel.getGame().getSignUp().mousePressed(me);
                break;
            case LOGIN:
                gamepanel.getGame().getLogIn().mousePressed(me);
                break;
           case MENU:
               gamepanel.getGame().getMenu().mousePressed(me);
               break;
           case DIFFICULTY:
               gamepanel.getGame().getDifficulty().mousePressed(me);
               break;
           case EASY:
               gamepanel.getGame().getEwindow().mousePressed(me);
               break;
           case HARD:
               gamepanel.getGame().getHwindow().mousePressed(me);
               break;
                 case EXPERT:
               gamepanel.getGame().getExwindow().mousePressed(me);
               break;
           case PLAYING:
              gamepanel.getGame().getPlaying().mousePressed(me);
               break;
           case OPTIONS:
              gamepanel.getGame().getOptions().mousePressed(me);
              
               break;
           case LEADERBOARD:
              gamepanel.getGame().getLeaderboard().mousePressed(me);
              
               break;
            case PROFILE:
              gamepanel.getGame().getProfile().mousePressed(me);
              
               break;
            case RANKING:
              gamepanel.getGame().getRanking().mousePressed(me);
              
               break;
            case SETTING:
              gamepanel.getGame().getSettings().mousePressed(me);

              break;
            case HELP:
              gamepanel.getGame().getHelp().mousePressed(me);

              break;
           default:
               break;
       }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
          switch(Gamestate.state){
              case SIGNLOG:
                  gamepanel.getGame().getSignLog().mouseReleased(me);
              case SIGNUP:
                gamepanel.getGame().getSignUp().mouseReleased(me);
                break;
              case LOGIN:
                gamepanel.getGame().getLogIn().mouseReleased(me);
                break;
           case MENU:
               gamepanel.getGame().getMenu().mouseReleased(me);
            
               break;
          case DIFFICULTY:
               gamepanel.getGame().getDifficulty().mouseReleased(me);
               break;
           case EASY:
               gamepanel.getGame().getEwindow().mouseReleased(me);
               break;
           case HARD:
               gamepanel.getGame().getHwindow().mouseReleased(me);
               break;
               case EXPERT:
               gamepanel.getGame().getExwindow().mouseReleased(me);
               break;
           case PLAYING:
              gamepanel.getGame().getPlaying().mouseReleased(me);
               break;
           case OPTIONS:
              gamepanel.getGame().getOptions().mouseReleased(me);
              
               break;
            case LEADERBOARD:
              gamepanel.getGame().getLeaderboard().mouseReleased(me);
              
               break;
            case PROFILE:
              gamepanel.getGame().getProfile().mouseReleased(me);
              
               break;
            case RANKING:
              gamepanel.getGame().getRanking().mouseReleased(me);
              
               break;
            case SETTING:
              gamepanel.getGame().getSettings().mouseReleased(me);

              break;
              case HELP:
              gamepanel.getGame().getHelp().mouseReleased(me);

           default:
               break;
       }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
   }

    @Override
    public void mouseDragged(MouseEvent me) {
 
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
       switch (Gamestate.state) {
               case SIGNUP:
                gamepanel.getGame().getSignUp().mouseMoved(me);
                break;
                case LOGIN:
			gamepanel.getGame().getLogIn().mouseMoved(me);
			break;
		case MENU:
			gamepanel.getGame().getMenu().mouseMoved(me);
			break;
                case DIFFICULTY:
               gamepanel.getGame().getDifficulty().mouseMoved(me);
               break;
              case EASY:
               gamepanel.getGame().getEwindow().mouseMoved(me);
               break;
                case HARD:
               gamepanel.getGame().getHwindow().mouseMoved(me);
               break;
                case EXPERT:
               gamepanel.getGame().getExwindow().mouseMoved(me);
               break;
		case PLAYING:
			gamepanel.getGame().getPlaying().mouseMoved(me);
			break;
                        
                case OPTIONS:
              gamepanel.getGame().getOptions().mouseMoved(me);
              
               break;
               case LEADERBOARD:
              gamepanel.getGame().getLeaderboard().mouseMoved(me);
              
               break;
               case PROFILE:
               gamepanel.getGame().getProfile().mouseMoved(me);
              
               break;
               case RANKING:
              gamepanel.getGame().getRanking().mouseMoved(me);
              
               break;
              case SETTING:
              gamepanel.getGame().getSettings().mouseMoved(me);

              break;
               case HELP:
              gamepanel.getGame().getHelp().mouseMoved(me);

              break;

		default:
			break;

		}
       
    }
    
}
