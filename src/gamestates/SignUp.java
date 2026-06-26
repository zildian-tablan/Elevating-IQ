
package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import stacking_rectangle.Games;
import ui.LogInButton;
import utilz.LoadSave;


public class SignUp extends State implements Statemethods{

    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;

    public static String user,pass,cpass,gender;
    private BufferedImage signupbg = LoadSave.GetSpriteAtlas(LoadSave.Signup_Background);
    private LogInButton[] libbuttons = new LogInButton[2];
    public static boolean error = false,error2 = false,error3 = false,error4 = false;
    
    public SignUp(Games game) {
        super(game);
        
        loadButtons();  
        titleAnimation();
        
    }

    private void loadButtons(){
        libbuttons[0] = new LogInButton(520,470,1,Gamestate.LOGIN);
        libbuttons[1] = new LogInButton(620,470,2,Gamestate.CANCEL);
        //libbuttons[2] = new LogInButton(620,470,0,Gamestate.CANCELS);
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
    public void update() {
        
        for(LogInButton lib : libbuttons){    
            
            lib.update();
        }
        updateAnimationTick(); 
     }

    @Override
    public void draw(Graphics g) {
        g.drawImage(signupbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
        for(LogInButton lib : libbuttons){
           lib.draw(g);
        }
            g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
        if(error == true){
            drawError(g);
        }if(error2 == true){
            drawError2(g);
        }if(error3 == true){
            drawError3(g);
        }if(error4 == true){
            drawError4(g);
        }
    }

  
    @Override
    public void mousePressed(MouseEvent me) {
        for(LogInButton lib : libbuttons){
         if(isInl(me,lib)){
                lib.setMousePressed(true);
                break;
            }
          }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        for(LogInButton lib : libbuttons){
            if(isInl(me,lib)){
               if(libbuttons[0].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                   user = Games.suserfield.getText();
                   pass = new String (Games.spassfield.getPassword());
                   cpass = new String (Games.scpassfield.getPassword());
                   if(Games.male.isSelected()){
                       gender = "Male";
                   }else if(Games.female.isSelected()){
                       gender = "Female";
                   }
                   if(Games.suserfield.getText().isEmpty() ||  pass.isEmpty() || !Games.male.isSelected() && !Games.female.isSelected()){
                       error3 = true;
                       error2 = false;
                       error = false;
                       error4 = false;
                          if(Games.suserfield.getText().length() > 13 ||  pass.length() > 13){
                       error4 = true;
                       error3 = false;
                       error2 = false;
                       error = false;
                          }
                   }else{
                   if(pass.equals(cpass)){
                   try{
                       Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String insertu = "Insert into accounts (username,password,gender) values('"+user+"','"+pass+"','"+gender+"')";
                       String insertES = "Insert into easyscore (username,level1,level2,level3,level4,level5) values('"+user+"',0,0,0,0,0)";
                       String insertHS = "Insert into hardscore (username,level1,level2,level3,level4,level5) values('"+user+"',0,0,0,0,0)";
                       String insertEXS = "Insert into expertscore (username,level1,level2,level3,level4,level5) values('"+user+"',0,0,0,0,0)";
                       Statement state1 = connect.createStatement();
                    
                       state1.executeUpdate(insertu);
                       state1.executeUpdate(insertES);
                       state1.executeUpdate(insertHS);
                       state1.executeUpdate(insertEXS);
                       
                       state1.close();
                       connect.close();
                       lib.applyGamestate();
                       error2 = false;
                       error = false;
                        error3 = false;
                        error4 = false;
                       Games.suserfield.setText("");
                       Games.spassfield.setText("");
                       Games.scpassfield.setText("");
                       Games.rgroup.clearSelection();
                   }catch(SQLException e){
                       e.printStackTrace();
                       error = true;
                       error2 = false;
                       error3 = false;
                       error4 = false;
                   }
                   }else{
                       error2 = true;
                       error = false;
                       error3 = false;
                       error4 = false;
                   }
                   }
                   break;
               }else if(libbuttons[1].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                   lib.applyGamestate();
                   Games.suserfield.setText("");
                   Games.spassfield.setText("");
                   Games.scpassfield.setText("");
                   Games.rgroup.clearSelection();
                   error2 = false;
                   error = false;
                   error3 = false;
                   error4 = false;
                   break;
               }
            }
        }
       resetButtons(); 
        
    }
    
    private void resetButtons(){
        for(LogInButton lib : libbuttons){
            lib.resetBool();
          
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
        for(LogInButton lib : libbuttons)
           lib.setMouseOver(false); 
      
      for(LogInButton lib : libbuttons)
            if(isInl(me,lib)){
                lib.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        
        if(ke.getKeyCode() == KeyEvent.VK_L){
            Gamestate.state = Gamestate.LOGIN;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
   
    }
    public void drawError2(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,12));
        g.drawString("Please Check your Password!!!",420,550);
    }
    public void drawError(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,12));
        g.drawString("Username already in use!!!",425,550);
    }
    public void drawError3(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,12));
        g.drawString("Please Fill Up Everything!!!",425,550);
    }
    public void drawError4(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,12));
        g.drawString("Your Username or Password",425,550);
        g.drawString("             is too long!!!",425,562);
    }
    
}
