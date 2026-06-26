
package gamestates;

import PkmnAudio.AudioPlayer;
import entities.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Timer;
import stacking_rectangle.Games;
import ui.DifficultyButton;
import utilz.LoadSave;


public class Difficulty extends State implements Statemethods {

    private DifficultyButton[] dbuttons = new DifficultyButton[4];
    private BufferedImage diffbg = LoadSave.GetSpriteAtlas(LoadSave.Difficulty_Background);
    public static int level1,level2,level3,level4,level5,level6;
    public static boolean drawLock = false;
    public int countdown = 0,count = 1;
    private boolean timerInitialized = false;
    private Timer timer;
    
    public Difficulty(Games game) {
        super(game);
        
        loadButtons();
    }
    
    private void loadButtons(){
        dbuttons[0] = new DifficultyButton(305,145,0,Gamestate.EASY);
        dbuttons[1] = new DifficultyButton(810,145,1,Gamestate.HARD);
        dbuttons[2] = new DifficultyButton(305,510,2,Gamestate.EXPERT);
        dbuttons[3] = new DifficultyButton(810,510,3,Gamestate.MENU);
    }

    @Override
    public void update() {
        
        for(DifficultyButton db : dbuttons){
            db.update();
        }
      }

    @Override
    public void draw(Graphics g) {
           
         g.drawImage(diffbg,0,0,Games.Game_Width,Games.Game_Height,null);
         
         for(DifficultyButton db : dbuttons){
            db.draw(g);
        }
         
         if(drawLock == true){
                drawLevelLock(g);
                QCountdown();
            }
    }

 

    @Override
    public void mousePressed(MouseEvent me) {
        
        for(DifficultyButton db : dbuttons){
            if(isInD(me,db)){
                db.setMousePressed(true);
                game.getAudioPlayer().Buttonclick();
                break;
            }
        }
     }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        for(DifficultyButton db : dbuttons){
             if(isInD(me,db)){
                if(db.isMousePressed())
                    if(dbuttons[0].isMousePressed()){
                        Playing.easy=true;
                        checkLvlClr("Easy");
                        if(level1 > 200){
                            Playing.e1c=true;
                        }if(level2 > 200){
                            Playing.e2c=true;
                        }if(level3 > 200){
                            Playing.e3c=true;
                        }if(level4 > 200){
                            Playing.e4c=true;
                        }if(level5 > 200){
                            Playing.e5c=true;
                        }
                        db.applyGamestate();
                        if(db.getState() == Gamestate.EASY){
                        game.getAudioPlayer().playSong(AudioPlayer.EASY);
                         }
                        level1=0;
                        level2=0;
                        level3=0;
                        level4=0;
                        level5=0;
                        break;
                    }else if(dbuttons[1].isMousePressed()){
                        checkLvlClr("Easy");
                        if(level5 > 200){
                            Playing.e5c=true;
                            checkLvlClr("Hard");
                        }
                        if(Playing.e5c == true){
                         Playing.hard=true;
                        if(Playing.e5c == true){
                        if(level1 > 300){
                            Playing.h1c=true;
                        }
                        if(level2 > 300){
                            Playing.h2c=true;
                        }
                        if(level3 > 300){
                            Playing.h3c=true;
                        }
                        if(level4 > 300){
                            Playing.h4c=true;
                        }
                        if(level5 > 300){
                            Playing.h5c=true;
                        }
                        }

                         db.applyGamestate();
                         if(db.getState() == Gamestate.HARD){
                       game.getAudioPlayer().playSong(AudioPlayer.HARD);
                         }
                        }else if(Playing.e5c == false){
                            drawLock = true;
                            count = 1;
                        }
                        Playing.e5c = false;
                        level1=0;
                        level2=0;
                        level3=0;
                        level4=0;
                        level5=0;
                         break;
                    }
                    else if(dbuttons[2].isMousePressed() ){
                      
                        checkLvlClr("Hard");
                        if(level5> 300){
                            Playing.h5c = true;
                            checkLvlClr("Expert");
                        }
                        Playing.expert=true;
                        if(Playing.h5c == true){
                        if(level1 > 400){
                            Playing.ex1c=true;
                        }
                        if(level2 > 400){
                            Playing.ex2c=true;
                        }
                        if(level3 > 400){
                            Playing.ex3c=true;
                        }
                        if(level4 > 400){
                            Playing.ex4c=true;
                        }
                        if(level5 > 400){
                            Playing.ex5c=true;
                        }
                        
                        db.applyGamestate();
                         if(db.getState() == Gamestate.EXPERT){
                        game.getAudioPlayer().playSong(AudioPlayer.EXPERT);
                          }
                         
                        }else if(Playing.h5c == false){
                            drawLock = true;
                            count = 1;
                        }
                        Playing.h5c = false;
                        level1=0;
                        level2=0;
                        level3=0;
                        level4=0;
                        level5=0;
                   
                         break;
                   }else if(dbuttons[3].isMousePressed()){
                     db.applyGamestate();
                         break;
                   }
                  
            }
        }
        
        resetButtons();
     }
    
    private void resetButtons(){
        for(DifficultyButton db : dbuttons){
            db.resetBool();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
        for(DifficultyButton db : dbuttons){
            db.setMouseOver(false);
        }
        
        for(DifficultyButton db : dbuttons){
            if(isInD(me,db)){
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
    
    public void checkLvlClr(String diff){
         try{
                Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String getScore= "Select Level1,Level2,Level3,Level4,Level5 From "+diff+"Score where username='"+LogIn.user+"'";
                       Statement state1 = connect.createStatement();
                       ResultSet set = null;
                       set = state1.executeQuery(getScore);
                       while(set.next()){
                           level1 = set.getInt("Level1");
                           level2 = set.getInt("Level2");
                           level3 = set.getInt("Level3");
                           level4 = set.getInt("Level4");
                           level5 = set.getInt("Level5"); 
                          
                       }
                       state1.close();
                       set.close();
                       connect.close();
           }catch(SQLException e){
               e.printStackTrace();
           }
    }
    
    public void QCountdown(){
      if (!timerInitialized) {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Playing.paused == false){
            countdown--;
            if (countdown <= 0 && count == 1) {
                timer.stop();
                countdown = 5;
                count = 2;
                drawLock = false;
            }
           }
        }
    });
    timerInitialized = true;
    }
     if (timer.isRunning() == false) {
            timer.start();
            //System.out.println("ss");
        }
    }
    
    public void drawLevelLock(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Dialog",Font.BOLD,25));
        g.drawString("Difficulty Locked",410,250);
    }
    
}
