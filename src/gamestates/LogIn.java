
package gamestates;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import stacking_rectangle.Games;
import ui.LogInButton;
import utilz.LoadSave;


public class LogIn extends State implements Statemethods{
    
    
    private BufferedImage loginbg = LoadSave.GetSpriteAtlas(LoadSave.Login_Background);
    private LogInButton[] libbuttons = new LogInButton[2];
    public static String  user,upass,pass;
    public static int term = 0;
    public static boolean error = false;

    
    public LogIn(Games game) {
        super(game);
        
        loadButtons();  

      
    }
    
     
    private void loadButtons(){
        libbuttons[0] = new LogInButton(520,420,0,Gamestate.MENU);
        libbuttons[1] = new LogInButton(620,420,2,Gamestate.CANCEL);
        //libbuttons[2] = new LogInButton(620,420,1,Gamestate.CANCEL);
    }
    
    
    
    @Override
    public void update() {
    
      
        for(LogInButton lib : libbuttons){    
            
            lib.update();
        }
        
    }

    @Override
    public void draw(Graphics g) {
       g.setColor(Color.black);
       g.drawImage(loginbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
        for(LogInButton lib : libbuttons){
           lib.draw(g);
        }
      if(error == true){
          drawError(g);
      }
        
    }

  

    @Override
    public void mousePressed(MouseEvent me) {
        
    for(LogInButton lib : libbuttons){
         if(isInl(me,lib)){
                lib.setMousePressed(true);
              // user= Games.userfield.getText();
              //  pass = new String(Games.passfield.getPassword());
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
                   user = Games.userfield.getText();
                   pass = new String(Games.passfield.getPassword());
                   if(!Games.userfield.getText().isEmpty() && !pass.isEmpty()){
                   try{
                       Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String check = "Select * From Accounts where username = '"+user+"'";
                       Statement state1 = connect.createStatement();
                       ResultSet set = null;
                       set = state1.executeQuery(check);
                       while(set.next()){
                          
                          if(user.equals(set.getString("username")) && pass.equals(set.getString("password"))) {
                         
                           
                              lib.applyGamestate();
                              Games.userfield.setText("");
                              Games.passfield.setText("");
                              term = 0;
                          }else{
                              error = true;
                          }
                       }
                       set.close();
                       state1.close();
                       connect.close();
                       if(term == 0){
                       error = true;
                       }
                   }catch(SQLException e){
                       e.printStackTrace();
                       error = true;
                       
                   }
                   }else{
                       error= true;
                   }
                   break;
               }else if (libbuttons[1].isMousePressed()){
                   game.getAudioPlayer().Buttonclick();
                   lib.applyGamestate();
                   Games.userfield.setText("");
                   Games.passfield.setText("");
                   error = false;
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
    
        if(ke.getKeyCode() == KeyEvent.VK_M){
            Gamestate.state = Gamestate.MENU;
        }
        
   }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    public void drawError(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,12));
        g.drawString("Please Check your Username",420,500);
        g.drawString("or Password!!!",470,512);
    }
}
