
package LeaderBoard;

import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.State;
import gamestates.Statemethods;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import ui.DifficultyButton;
import ui.LeaderboardButton;
import utilz.LoadSave;


public class Leaderboard extends State implements Statemethods{
    
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    public Ranking rank;
    private LeaderboardButton[] dbuttons = new LeaderboardButton[5];
    private BufferedImage diffbg = LoadSave.GetSpriteAtlas(LoadSave.Difficulty_Background);
    
    public Leaderboard(Games game) {
        super(game);
        loadButtons();
        titleAnimation();
        rank = new Ranking(game);
    }
    private void loadButtons(){
        dbuttons[0] = new LeaderboardButton(305,145,0,Gamestate.RANKING);
        dbuttons[1] = new LeaderboardButton(810,145,1,Gamestate.RANKING);
        dbuttons[2] = new LeaderboardButton(305,510,2,Gamestate.RANKING);
        dbuttons[3] = new LeaderboardButton(550,70,3,Gamestate.RANKING);
        dbuttons[4] = new LeaderboardButton(810,510,4,Gamestate.OPTIONS);
    }

 

    @Override
    public void update() {
    for(LeaderboardButton db : dbuttons){
            db.update();
        }
    updateAnimationTick();
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
    public void draw(Graphics g) {
    g.drawImage(diffbg,0,0,Games.Game_Width,Games.Game_Height,null);
         
         for(LeaderboardButton db : dbuttons){
            db.draw(g);
        }
    g.drawImage(idleAni[aniIndex],300, 305, 400, 80,  null);
    }

  
    @Override
    public void mousePressed(MouseEvent me) {
     for(LeaderboardButton db : dbuttons){
            if(isInL(me,db)){
                db.setMousePressed(true);
                game.getAudioPlayer().Buttonclick();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
     for(LeaderboardButton db : dbuttons){
             if(isInL(me,db)){
                if(db.isMousePressed())
                    if(dbuttons[0].isMousePressed()){
                        Ranking.easy=true;
                        Ranking.hard = false;
                        Ranking.expert = false;
                        Ranking.overall = false;
                        Ranking.diff ="EASY";
                        rank.DisplayLeader("Easy");
                        db.applyGamestate();
                        break;
                    }else if(dbuttons[1].isMousePressed() /*&& Playing.e5c == true*/){
                        
                        Ranking.hard=true;
                        Ranking.easy = false;
                        Ranking.expert = false;
                        Ranking.overall = false;
                        Ranking.diff ="HARD";
                        rank.DisplayLeader("Hard");
                         db.applyGamestate();
                         break;
                    }
                    else if(dbuttons[2].isMousePressed() /*&& Playing.h5c == true*/){
                        Ranking.expert=true;
                        Ranking.easy = false;
                        Ranking.hard = false;
                        Ranking.overall = false;
                        Ranking.diff ="HARD";
                        rank.DisplayLeader("Expert");
                         db.applyGamestate();
                         break;
                   }else if(dbuttons[3].isMousePressed()){
                        Ranking.diff ="OVERALL";
                        Ranking.overall = true;
                        Ranking.easy = false;
                        Ranking.expert = false;
                        Ranking.hard = false;
                        rank.DisplayLeader("Overall");
                     db.applyGamestate();
                         break;
                   }else if(dbuttons[4].isMousePressed()){
                     db.applyGamestate();
                         break;
                   }
            }
        }
        
        resetButtons();
    }
    
    private void resetButtons(){
        for(LeaderboardButton db : dbuttons){
            db.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    for(LeaderboardButton db : dbuttons){
            db.setMouseOver(false);
        }
        
        for(LeaderboardButton db : dbuttons){
            if(isInL(me,db)){
                db.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
   }
}
