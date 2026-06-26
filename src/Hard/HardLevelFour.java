
package Hard;

import choice.SubmitButton;
import entities.Player;
import gamestates.Playing;
import gamestates.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import stacking_rectangle.Games;
import static utilz.Constants.UI.LevelHSButtons.LHSB_HEIGHT;
import static utilz.Constants.UI.LevelHSButtons.LHSB_WIDTH;
import utilz.LoadSave;


public class HardLevelFour extends State{
    
    private Playing playing;
    private Player player;
    private Games game;
    private BufferedImage questionbar = LoadSave.GetSpriteAtlas(LoadSave.Question_Bar);
    private BufferedImage dgroundimg = LoadSave.GetSpriteAtlas(LoadSave.FIceplat_Atlas);
    
    public static String[] question={"It is how a website interacts with the user"};
    public static String[] question2={"Good UI Traits where people can feel","their way around quite easily"};
    public static String[] question3 ={"Bad UI Traits where it is unclear about","where the visitor should go next"};
    public static String[] question4 = {"Bad UI Traits where there is no clear","audience for the website"};
    public static String[] question5 = {"In terms of ____, UI has nothing to do","with attractiveness or aesthetics"};
    public static int score=000;
    public static String oscore = "000";
    public static boolean next = true ,next2=false,next3=false,next4=false,next5=false,next6=false;
    private HSubmitButton sbutton;
    public int ypos = 0;      
    public static int xpos = 0,speed = 4,sub=0;  
    public static int terminator =0;
    public static String[] fanswer = {"User Interface","Intuitive","Confusing","No Target","usability"};
    public static String[] sanswer={"UI"};
   public static String panswer; 
   public static int wrongcount = 0;
   
     private Timer timer;
    int divider=0;
     public static int countdown = 20,count = 1;
    private boolean timerInitialized = false;
    
    public HardLevelFour(Games game) {
        super(game);
        
         this.game = game;
        
        player = new Player();
        
        createHSBButtons();
        
    }
    
     private void createHSBButtons(){    
        int sbuttonx = 465;
        int sbuttony = 630;
        sbutton = new HSubmitButton(sbuttonx,sbuttony,LHSB_WIDTH,LHSB_HEIGHT,1);
 
    }
     
     public void update(){
        if(terminator == 5 && Playing.paused == false){
        game.createEnumField();
        terminator = 0 ;
        }if(Playing.paused == true){
            game.removeEnumField();
            terminator = 5;
        }
        sbutton.update();
        
         setYpos();
    }
    
    public void draw(Graphics g){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
         g.setFont(new Font("Times New Roman",Font.BOLD,30));
         g.drawString(oscore, 10, 70);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        if(next == true){
        g.drawString(question[0], 350, 470);
        if(count == 1){
        QCountdown();
        }
        }else if(next2 == true){
         g.drawString(question2[0], 350, 470);
         g.drawString(question2[1], 350, 490);
         if(count == 2){
        QCountdown();
        }
        }else if(next3 == true){
         g.drawString(question3[0], 350, 470);
          g.drawString(question3[1], 350, 490);
          if(count == 3){
        QCountdown();
        }
        }else if(next4 == true){
         g.drawString(question4[0], 350, 470);
          g.drawString(question4[1], 350, 490);
          if(count == 4){
        QCountdown();
        }
        }else if(next5 == true){
         g.drawString(question5[0], 350, 470);
          g.drawString(question5[1], 350, 490);
          if(count == 5){
        QCountdown();
        }
        }else if(next6 == true){
            changeXpos();
           g.drawImage(dgroundimg,1300+xpos,  ypos, 334,50,null);
            game.removeEnumField();
        }
        g.drawImage(questionbar, 390, 570, 250,50,null);
        sbutton.draw(g);
         if(next6 == false){
         drawCountdown(g);
    }
        
    }
    
    public void mousePressed(MouseEvent me) {     
        if(IsIn(me,sbutton)){
            sbutton.setMousePressed(true); 
            panswer = Games.enumfield.getText();
        }
    }
    
    public void mouseReleased(MouseEvent me) {
          if (timer != null) {
            timer.stop();
        }
 
         if(IsIn(me,sbutton)){
             countdown=20;
             if(sbutton.isMousePressed()){
                 if(next == true){
                 if(panswer.equalsIgnoreCase(fanswer[0])  || panswer.equalsIgnoreCase(sanswer[0])){
                     Playing.power = 1;
                     game.getAudioPlayer().answerCorrect();
                     player.changeypos(-50);
                     score +=100;
                    oscore=Integer.toString(score);
                     panswer="";
                     Games.enumfield.setText("");
                     next = false;
                     next2 = true;
                 }else{
                     Playing.power = 1;
                      game.getAudioPlayer().answerWrong();
                     player.changewaterypos(-30);
                     player.changewateryheight(30);
                     panswer = "";
                     Games.enumfield.setText("");
                      next = false;
                     next2 = true;
                     wrongcount = wrongcount+1;
                 }
                 }else if (next2 == true){
                     if(panswer.equalsIgnoreCase(fanswer[1])){
                         game.getAudioPlayer().answerCorrect();
                     player.changeypos(-50);
                       score +=100;
                    oscore=Integer.toString(score);
                     panswer="";
                     Games.enumfield.setText("");
                      next2 = false;
                     next3 = true;
                 }else{
                          game.getAudioPlayer().answerWrong();
                     player.changewaterypos(-30);
                     player.changewateryheight(30);
                     panswer = "";
                     Games.enumfield.setText("");
                      next2 = false;
                     next3= true;
                     wrongcount = wrongcount+1;
                 }
                 }else if (next3 == true){
                     if(panswer.equalsIgnoreCase(fanswer[2])){
                         game.getAudioPlayer().answerCorrect();
                     player.changeypos(-50);
                       score +=100;
                    oscore=Integer.toString(score);
                     panswer="";
                     Games.enumfield.setText("");
                      next3 = false;
                     next4 = true;
                 }else{
                          game.getAudioPlayer().answerWrong();
                     player.changewaterypos(-50);
                     player.changewateryheight(50);
                     panswer = "";
                     Games.enumfield.setText("");
                      next3 = false;
                     next4= true;
                     wrongcount = wrongcount+1;
                 }
                 }else if (next4 == true){
                     if(panswer.equalsIgnoreCase(fanswer[3])){
                         game.getAudioPlayer().answerCorrect();
                     player.changeypos(-50);
                       score +=100;
                    oscore=Integer.toString(score);
                     panswer="";
                     Games.enumfield.setText("");
                      next4 = false;
                     next5 = true;
                 }else{
                          game.getAudioPlayer().answerWrong();
                     player.changewaterypos(-75);
                     player.changewateryheight(75);
                     panswer = "";
                     Games.enumfield.setText("");
                      next4 = false;
                     next5= true;
                     wrongcount = wrongcount+1;
                 }
                 }else if (next5 == true){
                     if(panswer.equalsIgnoreCase(fanswer[4])){
                         game.getAudioPlayer().answerCorrect();
                     player.changeypos(-50);
                       score +=100;
                    oscore=Integer.toString(score);
                     panswer="";
                     Games.enumfield.setText("");
                      next5 = false;
                     next6 = true;
                 }else{
                          game.getAudioPlayer().answerWrong();
                     player.changewaterypos(-75);
                     player.changewateryheight(75);
                     panswer = "";
                     Games.enumfield.setText("");
                      next5 = false;
                     next6= true;
                     wrongcount = wrongcount+1;
                 }
                 }
             }
         }
         sbutton.resetBools(false);
            QCountdown();
    }
    
    public void mouseMoved(MouseEvent me) {
   
       sbutton.setMouseOver(false);
    
        if(IsIn(me,sbutton)){
            sbutton.setMouseOver(true);
        }
    }   
    
    private boolean IsIn(MouseEvent me, SubmitButton cb){
        return cb.getBounds().contains(me.getX(),me.getY());
           
    }
    
    public void setYpos(){
         if(Player.checks == 0 && next6 == true){
              ypos =305;
         }
         if(Player.checks==1 && next6 == true){
             ypos =305-50;
         }else if(Player.checks==2 && next6 == true){
             ypos = 305-100;
         }else if(Player.checks==3 && next6 == true){
             ypos =305- 150;
         }else if(Player.checks==4 && next6 == true){
             ypos =305-200;
         }else if(Player.checks==5 && next6 == true){
             ypos = 305- 250;
         }
     }
     
     private void changeXpos(){
         if(sub == 0){
       xpos -= speed;
            if(xpos<=-635){
                sub =1;
            }
         }
         
     }
     
     public void resetAll(){
         next =true;
         next2=false;
         next3=false;
         next4=false;
         next5=false;
         next6=false;
         game.removeEnumField();
         terminator = 5;
         sub = 0;
         xpos = 0;
         score = 0;
         oscore = "000";
         sbutton.resetBools(false);
         wrongcount = 0;
         countdown=20;
         count=1;
    }
     
     public void QCountdown(){
      if (!timerInitialized) {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Playing.paused == false){
            countdown--;
            if (countdown <= 0) {
                  player.changewaterypos(-50);
                  player.changewateryheight(50);
                    if(next == true){
                     divider = 2;
                     next = false;
                     next2 = true;
                     count = 2;
                     }else if(next2== true){
                      next2 = false;
                      divider = 3;
                      next3 = true;
                      count = 3;
                     }else if(next3== true){
                      next3 = false;
                      divider = 4;
                      next4 = true;
                      count = 4;
                     }else if(next4== true){
                      next4 = false;
                      divider = 5;
                      next5 = true;
                      count = 5;
                     }else if(next5== true){
                      next5 = false;
                      divider = 6;
                      next6 = true;
                     }
                wrongcount ++;
                   countdown=20;
                timer.stop();
            }
           }
        }
    });
    timerInitialized = true;
    }
     if (timer.isRunning() == false) {
            timer.start();
            System.out.println("ss");
        }
    }
    private void drawCountdown(Graphics g) {
    g.setColor(Color.red);
    g.setFont(new Font("Times New Roman", Font.BOLD, 20));
    g.drawString("Time left: " + countdown, 10, 100); // Adjust the position as needed
    } 
}
