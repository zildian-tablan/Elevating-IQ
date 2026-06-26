
package LeaderBoard;

import choice.ChoiceButton;
import choice.RChoiceButton;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import stacking_rectangle.Games;
import ui.DifficultyButton;
import ui.RankButton;
import ui.RankChoiceButton;
import static utilz.Constants.UI.RankButtons.RB_HEIGHT;
import static utilz.Constants.UI.RankButtons.RB_WIDTH;
import utilz.LoadSave;


public class Ranking extends State implements Statemethods{
    
    private RankChoiceButton up,down,quit;
    private BufferedImage leadbg = LoadSave.GetSpriteAtlas(LoadSave.Rank_Background);
    private BufferedImage leadbg2= LoadSave.GetSpriteAtlas(LoadSave.Rank_Background2);
    public static boolean easy = false,hard = false,expert =false,overall = false;
    public static String p1,p2="---",p3="---",p4="---",p5="---",s1,s2="---",s3="---",s4="---",s5="---";
    public static String store;
    public static String diff;
    public static int x,y,count = 0,y2,y3,y4,y5,x1;
    int buttonx,upy,downy,quity;
    public static boolean top5 = true ,top10 =false;
    
    public Ranking(Games game) {
        super(game);
        initClasses();
        loadButtons();
    }
    
    public void initClasses(){
        
    }
    
    private void loadButtons(){
        
        buttonx = 660;
        upy = 225;
        downy = (upy+120)+RB_HEIGHT;
        quity = 490;
        
        up = new RankChoiceButton(buttonx,upy,40,40,0);
        down = new RankChoiceButton(buttonx,downy,40,40,1);
        quit = new RankChoiceButton(buttonx,quity,40,40,2);
    }


    private boolean IsIn(MouseEvent me, RChoiceButton cb){
        return cb.getBounds().contains(me.getX(),me.getY());
           
    }
    
    public void DisplayLeader(String diff){
     try{
           Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");

           Statement leader = connect.createStatement();

           
           String seltop5 = "Select username,(Coalesce(SUM("+diff+"Total),0)) AS Total From "+diff+"score Group by username order by Total desc,username asc";
           String seltop10 = "Select username,(Coalesce(SUM("+diff+"Total),0)) AS Total From "+diff+"score Group by username order by Total desc,username asc offset 5 rows Fetch next 5 rows only";
           String oseltop5 = "Select accounts.username,(Coalesce(SUM(EasyTotal),0)+Coalesce(SUM(HardTotal),0) + Coalesce(SUM(ExpertTotal),0)) "
                   + "AS Total From accounts Left Join EasyScore on accounts.username = easyscore.username "
                   + "Left join HardScore on accounts.username = hardscore.username "
                   + "Left join ExpertScore on accounts.username = expertscore.username "
                   + "group by accounts.username order by Total desc,username asc Fetch first 5 rows only";
           String oseltop10 = "Select accounts.username,(Coalesce(SUM(EasyTotal),0)+Coalesce(SUM(HardTotal),0) + Coalesce(SUM(ExpertTotal),0)) "
                   + "AS Total From accounts Left Join EasyScore on accounts.username = easyscore.username "
                   + "Left join HardScore on accounts.username = hardscore.username "
                   + "Left join ExpertScore on accounts.username = expertscore.username "
                   + "group by accounts.username order by Total desc,username asc offset 5 rows Fetch next 5 rows only";
            
           ResultSet getScore = null;
           if(easy == true || hard == true || expert == true && overall == false){
             if(top5 == true && top10 == false){
             getScore  = leader.executeQuery(seltop5);
             } else if(top5 == false && top10 == true){
             getScore  = leader.executeQuery(seltop10);
                 System.out.println("here");
             }
           }else if(easy == false || hard == false || expert == false && overall == true){
             if(top5 == true && top10 == false){
             getScore  = leader.executeQuery(oseltop5);
             } else if(top5 == false && top10 == true){
             getScore  = leader.executeQuery(oseltop10);
             }
           }
           
           count = 0;
           while(getScore.next() && count<6){
               if(count == 0){
                   if(getScore != null){
                      p1= getScore.getString("username")+":";
                      s1=String.valueOf(getScore.getInt("Total"));
                   }else if(getScore == null){
                      p1 = "---";
                      s1 = "---";
                   }
                System.out.println(p1);
               }if(count == 1){
                   if(getScore != null){
                      p2= getScore.getString("username")+":";
                      s2=String.valueOf(getScore.getInt("Total"));
                   }else if(getScore == null){
                      p2 = "---";
                      s2 = "---";
                   }
               System.out.println(p2);
               }if(count == 2){
                   if(getScore != null){
                      p3= getScore.getString("username")+":";
                      s3=String.valueOf(getScore.getInt("Total"));
                   }else if(getScore == null){
                      p3 = "---";
                      s3 = "---";
                   }
               System.out.println(p3);
               }if(count == 3){
                   if(getScore != null){
                      p4= getScore.getString("username")+":";
                      s4=String.valueOf(getScore.getInt("Total"));
                   }else if(getScore == null){
                      p4 = "---";
                      s4 = "---";
                   }
               System.out.println(p4);
               }if(count == 4){
                   if(getScore != null){
                      p5= getScore.getString("username")+":";
                      s5=String.valueOf(getScore.getInt("Total"));
                   }else if(getScore == null){
                      p5 = "---";
                      s5 = "---";
                   }
               System.out.println(p5);
               }
               
              count++;
               if(count == 5){
                   break;
               }
           }
           getScore.close();
           connect.close();
           leader.close();
           count = 6;
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void drawLeader(Graphics g){
      x=458;
      x1=570;
      y = 288;
      y2=321;
      y3=352;
      y4 = 384;
      y5 = 415;
      g.setColor(Color.black);
      g.setFont(new Font("Times New Roman",Font.BOLD,14));
      g.drawString(p1,x,y);
      g.drawString(p2,x,y2);
      g.drawString(p3,x,y3);
      g.drawString(p4,x,y4);
      g.drawString(p5,x,y5);
      g.drawString(s1,x1,y);
      g.drawString(s2,x1,y2);
      g.drawString(s3,x1,y3);
      g.drawString(s4,x1,y4);
      g.drawString(s5,x1,y5);
    }
    
   /* public void drawLeader2(Graphics g){
      for(int i=5; i<10;i++){
      int y2=50;
      y = y + y2;
      g.setColor(Color.white);
      g.setFont(new Font("Times New Roman",Font.BOLD,25));
      g.drawString(distop[i],x,y);
      }
    }*/

    @Override
    public void update() {
        up.update();
        down.update();
        quit.update();
        if(count < 6){
        if(easy == true){
        diff="EASY";
        DisplayLeader(diff);
        }
        if(overall == false && hard == true && easy == false && expert == false){
        diff="HARD";
        System.out.println("here2");
        DisplayLeader(diff);
        }
        if(overall == false && expert == true && easy == false && hard == false){
        diff="EXPERT";
        DisplayLeader(diff);
        }
        if(overall == true && expert == false && easy == false && hard == false){
        diff="EXPERT";
        DisplayLeader(diff);
        }
        }
    }

    @Override
    public void draw(Graphics g) {
    if(top5 == true && top10 == false){
    g.drawImage(leadbg,0,0,Games.Game_Width,Games.Game_Height,null);
    }else if(top5 == false && top10 == true){
      g.drawImage(leadbg2,0,0,Games.Game_Width,Games.Game_Height,null);
    }
    up.draw(g);
    down.draw(g);
    quit.draw(g);
    drawLeader(g); 
    }

    @Override
    public void mousePressed(MouseEvent me) {
    if(IsIn(me,up)){
            up.setMousePressed(true);
        game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,down)){
            down.setMousePressed(true);
          game.getAudioPlayer().Buttonclick();
        }else if(IsIn(me,quit)){
            quit.setMousePressed(true);
            game.getAudioPlayer().Buttonclick();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(IsIn(me,up)){
            if(up.isMousePressed()){
                top5 = true;
                top10 = false;
                count = 0;
                System.out.println("up");
            }
        }else if(IsIn(me,down)){
            if(down.isMousePressed()){
                top5 = false;
                top10 = true;
                count = 0;
                p1 = "---";p2 = "---";p3 = "---";p4 = "---";p5 = "---";
                s1 = "---";s2 = "---";s3 = "---";s4 = "---";s5 = "---";
                System.out.println("down");
            }
        }else if(IsIn(me,quit)){
            if(quit.isMousePressed()){
                Gamestate.state = Gamestate.LEADERBOARD;
                up.resetBools(false);
                down.resetBools(false);
                quit.resetBools(false);
                top5=true;
                top10=false;
                count = 0;
            }
        }
        
        up.resetBools(false);
        down.resetBools(false);
        quit.resetBools(false);
   }

    @Override
    public void mouseMoved(MouseEvent me) {
        up.setMouseOver(false);
        down.setMouseOver(false);
        quit.setMouseOver(false);
        
        if(IsIn(me,up)){
            up.setMouseOver(true);
        }else if(IsIn(me,down)){
            down.setMouseOver(true);
        }else if(IsIn(me,quit)){
            quit.setMouseOver(true);
        }
     }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
  
}
