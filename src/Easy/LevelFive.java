
package Easy;

import choice.ChoiceButton;
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
import static utilz.Constants.UI.EchoiceButtons.*;
import utilz.LoadSave;

public class LevelFive extends State {
    
    private Playing playing;
    private Player player;
    private Games game;
    private BufferedImage questionbar = LoadSave.GetSpriteAtlas(LoadSave.Question_Bar);
    private BufferedImage dgroundimg = LoadSave.GetSpriteAtlas(LoadSave.FGround_Atlas);
    String[] question1 = {"A toggle button that displays choices that are ","not exclusive and have boolean value."};
    String[] question2 = {"A component for display, input,and editing ","  of multiple lines of text."};
    String[] question3 = {"A single-line field that allows users to select from "," an ordered series of values."};
    String[] question4 = {"A text inside a container."};
    String[] question5 = {"A component for display, input, and editing "," of single line of plain text."};
    String[] choices = {"JTextArea","JRadioButton","JComboBox","JCheckBox"};
    String[] choices2 = {"JTextArea","JRadioButton","JComboBox","JTextField"};
    String[] choices3 ={"JSlider","JSpinner","Jlist","JLabel"};
    String[] choices4 ={"JSlider","JList","JLabel","JButton"};
    String[] choices5 ={"JTextArea","JRadioButton","JComboBox","JTextField"};
    String[] answer = {"JCheckBox","JTextArea","JSpinner","JLabel","JTextField"};
    public static int score=000;
    public static String oscore = "000";
    public static boolean next = true ,next2=false,next3=false,next4=false,next5=false,next6=false;
    private EchoiceButton abutton,bbutton,cbutton,dbutton;
    int divider=0;
    public int ypos = 0;      
    public static int xpos = 0,speed = 4,sub=0;     
    public static int wrongcount = 0;
    private Timer timer;
    public static int countdown = 25,count = 1;
    private boolean timerInitialized = false;

    
    public LevelFive(Games game,Playing playing) {
        super(game);
        
        this.game = game;
        
        player = new Player();
        createECBButtons();
    }
    
    private void createECBButtons(){
        
        int acbuttonx = 275;
        int bdbuttonx = 535;
        int abbuttony = 550;
        int cdbuttony = 630;
        
        
        abutton = new EchoiceButton(acbuttonx,abbuttony,ECB_WIDTH,ECB_HEIGHT,0);
        bbutton = new EchoiceButton(bdbuttonx,abbuttony,ECB_WIDTH,ECB_HEIGHT,1);
        cbutton = new EchoiceButton(acbuttonx,cdbuttony,ECB_WIDTH,ECB_HEIGHT,2);
        dbutton = new EchoiceButton(bdbuttonx,cdbuttony,ECB_WIDTH,ECB_HEIGHT,3);
        
    }
     
      public void update(){
        
       
        abutton.update();
        bbutton.update();
        cbutton.update();
        dbutton.update();
         setYpos();

    }
      
    public void draw(Graphics g){
        
        g.setColor(Color.black);
         g.setFont(new Font("Times New Roman",Font.BOLD,30));
      
         g.drawString(oscore, 10, 70);
        //question 1
      
         drawQuestion(g);
         if(next6 == false){
         drawCountdown(g);
         }
        
    }
        
    
    
    public void mousePressed(MouseEvent me) {
        
        
        if(IsIn(me,abutton)){
            abutton.setMousePressed(true);
        
        }else if(IsIn(me,bbutton)){
            bbutton.setMousePressed(true);
          
        }else if(IsIn(me,cbutton)){
            cbutton.setMousePressed(true);
           
        }else if(IsIn(me,dbutton)){
            dbutton.setMousePressed(true);
          
        }
   
    }
    
    public void mouseReleased(MouseEvent me) {
         if (timer != null) {
            timer.stop();
        }
   //1stchoice
        if(IsIn(me,abutton)){
            countdown = 25;
           if(abutton.isMousePressed()){
              if(divider == 0){
              if(answer[0] != choices[0]){
                  Playing.power = 1;
                    
                 // score +=100;
                 // oscore=Integer.toString(score);
                   // player.changeypos(-25);
                   game.getAudioPlayer().answerWrong();
                    player.changewaterypos(-25);
                  player.changewateryheight(25);
                   next = false;
                   next2 = true;
                   divider = 2;
                   wrongcount = wrongcount+1;
                   
              }
              }
              else if(divider == 2){
              if(answer[1]== choices2[0]){
                  game.getAudioPlayer().answerCorrect();
                  score +=100;
                  oscore=Integer.toString(score);
                  player.changeypos(-50);
                //  player.changewaterypos(-50);
                 // player.changewateryheight(50);
                 
                  next2 = false;
                  next3 = true;
                  divider = 3;
              }
              }
              else if(divider == 3){
                  if(answer[2]!= choices3[0]){
                  //score +=100;
                 // oscore=Integer.toString(score);
                //  player.changeypos(-25);
                game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next3 = false;
                  next4 = true;
                  divider = 4 ;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 4){
                  if(answer[3]!= choices4[0]){
                   //   score +=100;
                  //oscore=Integer.toString(score);
                  //player.changeypos(-25);
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next4 = false;
                  next5 = true;
                  divider = 5 ;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 5){
                  if(answer[4]!= choices5[0]){
                    game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next5 = false;
                  divider = 6 ;
                  wrongcount = wrongcount+1;
              }
              }
            }
           
        }
        //2ndchoice
        else if(IsIn(me,bbutton)){
            countdown = 25;
           if(bbutton.isMousePressed()){
               if(divider == 0){
               if(answer[0] != choices[1]){
                   Playing.power = 1;
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  
                  next = false;
                  next2 = true;
                  divider = 2;
                  wrongcount = wrongcount+1;
               }
               }else if(divider == 2){
              if(answer[1]!= choices2[1]){
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next2 = false;
                  next3 = true;
                  divider = 3;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 3){
                  if(answer[2]== choices3[1]){
                      game.getAudioPlayer().answerCorrect();
                  score += 100;
                    oscore=Integer.toString(score);
                  player.changeypos(-50);
                 // player.changewaterypos(-50);
                  //player.changewateryheight(50);
                  next3 = false;
                  next4 = true;
                  divider = 4;
              }
              }
              else if(divider == 4){
                  if(answer[3]!= choices4[1]){
                    //score += 100;
                   // oscore=Integer.toString(score);
                  // player.changeypos(-25);
                  game.getAudioPlayer().answerWrong();
                    player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next4 = false;
                  next5 = true;
                  divider = 5 ;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 5){
              if(answer[4]!= choices5[1]){
                   // score += 100;
                   //oscore=Integer.toString(score);
               //   player.changeypos(-25);
               game.getAudioPlayer().answerWrong();
                 player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next5 = false;
                  divider = 6;
                  wrongcount = wrongcount+1;
              }
              }
           }
           
        }
        //3rd choice
        else if(IsIn(me,cbutton)){
            countdown = 25;
           if(cbutton.isMousePressed()){
               if(divider == 0){
               if(answer[0] != choices[2]){
                   Playing.power = 1;
                    //score +=100;
                //  oscore=Integer.toString(score);
                 //   player.changeypos(-25);
                 game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                 player.changewateryheight(25);
                 next = false;
                   next2 = true;
                   divider = 2;
                   wrongcount = wrongcount+1;
               }
               }
               else if(divider == 2){
               if(answer[1]!= choices2[2]){
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next2 = false;
                  next3 = true;
                  divider = 3;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 3){
               if(answer[2]!= choices3[2]){
                   // score += 100;
                   // oscore=Integer.toString(score);
                 // player.changeypos(-25);
                 game.getAudioPlayer().answerWrong();
                 player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next3 = false;
                  next4 = true;
                  divider = 4;
                  wrongcount = wrongcount+1;
              }
              }
               else if(divider == 4){
                  if(answer[3]== choices4[2]){
                      game.getAudioPlayer().answerCorrect();
                   score +=100;
                  oscore=Integer.toString(score);
                    player.changeypos(-50);
                 // player.changewaterypos(-50);
                 // player.changewateryheight(50);
                  next4 = false;
                  next5 = true;
                  divider = 5 ;
              }
              }
              else if(divider == 5){
                  if(answer[2]!= choices5[2]){
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next5 = false;
                  divider = 6 ;
                  wrongcount = wrongcount+1;
              }
              }
           }
           }
        //4thchoice
         else if(IsIn(me,dbutton)){
             countdown = 25;
           if(dbutton.isMousePressed()){
               if(divider == 0){
               if(answer[0] == choices[3]){
                   Playing.power = 1;
                   game.getAudioPlayer().answerCorrect();
                    score += 100;
                    oscore=Integer.toString(score);
                  player.changeypos(-50);
                // player.changewaterypos(-50);
                 // player.changewateryheight(50);
                 next = false;
                   next2 = true;
                   divider = 2;
               }
               }
               else if(divider == 2){
              if(answer[1]!= choices2[3]){
                   // score += 100;
                  //  oscore=Integer.toString(score);
                  //player.changeypos(-50);
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next2 = false;
                  next3 = true;
                  divider = 3;
                  wrongcount = wrongcount+1;
              }
              }
              else if(divider == 3){
                  if(answer[2]!= choices3[3]){
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next3 = false;
                  next4 = true;
                  divider = 4;
                  wrongcount = wrongcount+1;
              }
              }
               else if(divider == 4){
                  if(answer[3]!= choices4[3]){
                  game.getAudioPlayer().answerWrong();
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
                  next4 = false;
                  next5 = true;
                  divider = 5 ;
                  wrongcount = wrongcount+1;
              }
              }
               else if(divider == 5){
                  if(answer[4]== choices5[3]){
                      game.getAudioPlayer().answerCorrect();
                   score +=100;
                  oscore=Integer.toString(score);
                    player.changeypos(-50);
                 // player.changewaterypos(-50);
                  //player.changewateryheight(50);
                  next5 = false;
                  next6 = true;
                  divider = 6 ;
              }
              }
           }
        }
        
        abutton.resetBools(false);
        bbutton.resetBools(false);
        cbutton.resetBools(false);
        dbutton.resetBools(false);
        QCountdown();
    }

  
    public void mouseMoved(MouseEvent me) {
   
       abutton.setMouseOver(false);
       bbutton.setMouseOver(false);
       cbutton.setMouseOver(false);
       dbutton.setMouseOver(false);
        
        if(IsIn(me,abutton)){
            abutton.setMouseOver(true);
        }else if(IsIn(me,bbutton)){
            bbutton.setMouseOver(true);
        }else if(IsIn(me,cbutton)){
            cbutton.setMouseOver(true);
        }else if(IsIn(me,dbutton)){
            dbutton.setMouseOver(true);
        }
        
    }   
    
     private boolean IsIn(MouseEvent me, ChoiceButton cb){
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
         divider =0;
         sub = 0;
         xpos = 0;
         score = 0;
         oscore = "000";
         abutton.resetBools(false);
        bbutton.resetBools(false);
        cbutton.resetBools(false);
        dbutton.resetBools(false);
        wrongcount = 0;
        countdown = 25;
     }
    
     public void drawQuestion(Graphics g){
           if(next == true){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(question1[0], 335, 470);
        g.drawString(question1[1],427,490);
        
        abutton.draw(g);
        bbutton.draw(g);
        cbutton.draw(g);
        dbutton.draw(g);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Times New Roman",Font.BOLD,18));
        g.drawString(choices[0], 360, 590);
        g.drawString(choices[1], 615, 590);
        g.drawString(choices[2], 360, 672);
        g.drawString(choices[3], 615, 672);
        if(count == 1){
        QCountdown();
        }
        }
        //question 2
        else if(next2 == true){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(question2[0], 335, 470);
        g.drawString(question2[1],325,490);
        
        abutton.draw(g);
        bbutton.draw(g);
        cbutton.draw(g);
        dbutton.draw(g);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Times New Roman",Font.BOLD,15));
        g.drawString(choices2[0], 360, 590);
        g.drawString(choices2[1], 615, 590);
        g.drawString(choices2[2], 360, 672);
        g.drawString(choices2[3], 615, 672);
        if(count == 2){
        QCountdown();
        }
        }
        //question 3
        else if(next3 == true){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(question3[0], 320, 470);
       g.drawString(question3[1],325,490);
        
        abutton.draw(g);
        bbutton.draw(g);
        cbutton.draw(g);
        dbutton.draw(g);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Times New Roman",Font.BOLD,15));
        g.drawString(choices3[0], 360, 590);
        g.drawString(choices3[1], 615, 590);
        g.drawString(choices3[2], 360, 672);
        g.drawString(choices3[3], 615, 672);
        if(count == 3){
        QCountdown();
        }
        }
        //question 4
        else if(next4 == true){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(question4[0], 335, 470);
      //  g.drawString(question2[1],325,470);
        
        abutton.draw(g);
        bbutton.draw(g);
        cbutton.draw(g);
        dbutton.draw(g);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Times New Roman",Font.BOLD,15));
        g.drawString(choices4[0], 360, 590);
        g.drawString(choices4[1], 615, 590);
        g.drawString(choices4[2], 360, 672);
        g.drawString(choices4[3], 615, 672);
        if(count == 4){
        QCountdown();
        }
        }
        //question 5
        else if(next5 == true){
        g.drawImage(questionbar, 275, 420, 500,125,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(question5[0], 335, 470);
        g.drawString(question5[1],350,490);
        
        abutton.draw(g);
        bbutton.draw(g);
        cbutton.draw(g);
        dbutton.draw(g);
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Times New Roman",Font.BOLD,15));
        g.drawString(choices5[0], 350, 590);
        g.drawString(choices5[1], 615, 590);
        g.drawString(choices5[2], 360, 672);
        g.drawString(choices5[3], 615, 672);
        if(count == 5){
        QCountdown();
        }
        }
        //finished
        else if(next6 = true){
          changeXpos();
           g.drawImage(dgroundimg,1300+xpos,  ypos, 334,50,null);
        }
     }
     
    public void QCountdown(){
      if (!timerInitialized) {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Playing.paused == false){
            countdown--;
            if (countdown <= 0) {
                  player.changewaterypos(-25);
                  player.changewateryheight(25);
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
                countdown = 25;
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
