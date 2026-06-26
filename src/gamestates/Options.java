
package gamestates;

import Profile.Profile;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import ui.OptionButton;
import utilz.LoadSave;


public class Options extends State implements Statemethods{
    
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    
    private OptionButton[] buttons = new OptionButton[3];
    
    private BufferedImage optionbg=LoadSave.GetSpriteAtlas(LoadSave.Option_Background);
    
    private Profile profile;

    public Options(Games game) {
        super(game);
        
        loadButtons();
        titleAnimation();
        profile = new Profile(game);
    }
    
    private void loadButtons(){
        buttons[0] = new OptionButton(Games.Game_Width/2+50,270,0,Gamestate.PROFILE);
        buttons[1] = new OptionButton(Games.Game_Width/2+50,345,1,Gamestate.LEADERBOARD);
        buttons[2] = new OptionButton(Games.Game_Width/2+50,420,2,Gamestate.MENU);
    }

    @Override
    public void update() {
    for(OptionButton mb : buttons){
            mb.update();
        }
    updateAnimationTick();
    }

    @Override
    public void draw(Graphics g) {
     g.drawImage(optionbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
        
       for(OptionButton mb : buttons){
           mb.draw(g);
       }
       
        g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
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
     for(OptionButton mb : buttons){
            if(isInOpt(me,mb)){
                mb.setMousePressed(true);
                 game.getAudioPlayer().Buttonclick();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    for(OptionButton mb : buttons){
            if(isInOpt(me,mb)){
                if(buttons[0].isMousePressed()){
                    System.out.println("profile");
                    profile.retrieveData();
                    mb.applyGamestate();
                    break;
                }if(buttons[1].isMousePressed()){
                    System.out.println("leader");
                    mb.applyGamestate();
                    break;
                }if(buttons[2].isMousePressed()){
                    mb.applyGamestate();
                    LogIn.term = 0;
                    break;
                }
            }
        }
       resetButtons();
    }
    
      private void resetButtons(){
        for(OptionButton mb : buttons){
            mb.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
 
     for(OptionButton mb : buttons)
           mb.setMouseOver(false); 
      
      for(OptionButton mb : buttons)
            if(isInOpt(me,mb)){
                mb.setMouseOver(true);
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
