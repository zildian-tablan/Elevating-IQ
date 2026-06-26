
package gamestates;


import Easy.ELevelButton;
import Easy.EchoiceButton;
import Hard.HLevelButton;
import PkmnAudio.AudioPlayer;
import expert.ExlevelButton;
import static gamestates.Gamestate.EASY;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import java.awt.event.MouseEvent;
import stacking_rectangle.Games;
import ui.DifficultyButton;
import ui.LeaderboardButton;
import ui.LogInButton;
import ui.MenuButton;
import ui.OptionButton;
import ui.RankButton;


public class State {
    
    protected Games game;
    public State(Games game){
        this.game = game;
    }

    public boolean isIn(MouseEvent me,MenuButton mb){
        return mb.getBounds().contains(me.getX(),me.getY());
    }
    
    public boolean isInl(MouseEvent me,LogInButton lib){
     
        return lib.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInEB(MouseEvent me,EchoiceButton eb){
         return eb.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInD(MouseEvent me,DifficultyButton db){
        
        return db.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInEL(MouseEvent me,ELevelButton elb){
        
        return elb.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInHL(MouseEvent me,HLevelButton hlb){
        
        return hlb.getBounds().contains(me.getX(),me.getY());
    }
     public boolean isInEXL(MouseEvent me,ExlevelButton exlb){
        
        return exlb.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInOpt(MouseEvent me,OptionButton ob){
        
        return ob.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInL(MouseEvent me,LeaderboardButton db){
        
        return db.getBounds().contains(me.getX(),me.getY());
    }
    public boolean isInR(MouseEvent me,RankButton db){
        
        return db.getBounds().contains(me.getX(),me.getY());
    }
    
    public Games getGame(){
        return game;
    }
    
    public void setGameState(Gamestate state){
        switch(state){
            case SIGNLOG:
                game.getAudioPlayer().playSong(AudioPlayer.BACKGROUND);
                break;
            case MENU :
                game.getAudioPlayer().playSong(AudioPlayer.BACKGROUND);
                break;
            case DIFFICULTY :
                game.getAudioPlayer().playSong(AudioPlayer.BACKGROUND);
                break;
            case EASY :
                game.getAudioPlayer().playSong(AudioPlayer.EASY);
                break;
            case HARD :
                game.getAudioPlayer().playSong(AudioPlayer.HARD);
                break;
            case EXPERT :
                game.getAudioPlayer().playSong(AudioPlayer.EXPERT);
                break;
           
        }
        Gamestate.state = state;
    }
}
