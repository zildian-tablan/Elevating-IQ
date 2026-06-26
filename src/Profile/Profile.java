
package Profile;

import gamestates.Gamestate;
import gamestates.LogIn;
import gamestates.Playing;
import gamestates.State;
import gamestates.Statemethods;
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
import ui.DifficultyButton;
import utilz.LoadSave;


public class Profile extends State implements Statemethods{
    
    private DifficultyButton[] dbuttons = new DifficultyButton[1];
    private BufferedImage profbg = LoadSave.GetSpriteAtlas(LoadSave.ProfileM_Background);
    private BufferedImage profbgF = LoadSave.GetSpriteAtlas(LoadSave.ProfileF_Background);
    public static String username,gender,shighest,sdiff;
    public static int highest,et,ht,ext;
    public static String quote = "ElevatingIQ: Elevate your wisdom while having fun";

    public Profile(Games game) {
        super(game);
        
         loadButtons();
    }
    
    private void loadButtons(){
        dbuttons[0] = new DifficultyButton(900,550,3,Gamestate.OPTIONS);
    }

    @Override
    public void update() {
      for(DifficultyButton db : dbuttons){
            db.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        if(gender.equalsIgnoreCase("male")){
         g.drawImage(profbg,0,0,Games.Game_Width,Games.Game_Height,null);
        }else if(gender.equalsIgnoreCase("female")){
         g.drawImage(profbgF,0,0,Games.Game_Width,Games.Game_Height,null);
        }
         for(DifficultyButton db : dbuttons){
            db.draw(g);
        }
        drawData(g);
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
                        username = "";
                        gender ="";
                        shighest = "";
                        sdiff ="";
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
    
    public void retrieveData(){
        try{
                       Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String data = "Select accounts.username,gender,EasyTotal,HardTotal,ExpertTotal From Accounts,easyscore,hardscore,expertscore"
                               + " where accounts.username = '"+LogIn.user+"' "
                               + "and accounts.username = easyscore.username and accounts.username = hardscore.username "
                               + "and accounts.username = expertscore.username";
                       Statement state = connect.createStatement();
                       ResultSet getData = null;
                       getData = state.executeQuery(data);
                       while(getData.next()){
                           username = getData.getString("username");
                           gender = getData.getString("gender");
                           et = getData.getInt("EasyTotal");
                           ht = getData.getInt("HardTotal");
                           ext = getData.getInt("ExpertTotal");
                         }
                       getData.close();
                       state.close();
                       connect.close();
                       if(et > ht){
                           highest = et;
                           if(et > ext){
                               highest = et;
                               sdiff = "Easy: ";
                           }else if(et < ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }else if(et == ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }
                       }else if(et < ht){
                           highest = ht;
                           if(ht > ext){
                               highest = ht;
                               sdiff = "Hard: ";
                           }else if(ht < ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }else if(ht == ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }
                       }else if(et == ht){
                           highest = ht;
                           sdiff = "Hard: ";
                           if(ht == ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }else if(ht > ext){
                               highest = ht;
                               sdiff = "Hard: ";
                           }else if(ht < ext){
                               highest = ext;
                               sdiff = "Expert: ";
                           }   
                       }
                       shighest = String.valueOf(highest);
                   }catch(SQLException e){
                       e.printStackTrace();
                       
                   }
    }
    
    public void drawData(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,14));
        g.drawString("UserName:", 403, 285);
        g.drawString(username, 410, 344);
        g.drawString("Gender:", 280, 420);
        g.drawString(gender, 280, 476);
        g.drawString("Best Difficulty", 490, 420);
        g.drawString(sdiff+shighest, 490, 476);
        g.drawString(quote, 315, 547);
        
    }
    
}
