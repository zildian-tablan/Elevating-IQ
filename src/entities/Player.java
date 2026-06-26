
package entities;

import Easy.LevelFive;
import Easy.LevelFour;
import Easy.LevelOne;
import Easy.LevelThree;
import Easy.LevelTwo;
import Hard.HardLevelFive;
import Hard.HardLevelFour;
import Hard.HardLevelOne;
import Hard.HardLevelThree;
import Hard.HardLevelTwo;
import expert.Expertlevelfive;
import expert.Expertlevelfour;
import expert.Expertlevelone;
import expert.Expertlevelthree;
import expert.Expertleveltwo;
import gamestates.LogIn;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import stacking_rectangle.Games;
import utilz.LoadSave;


public class Player {
    
    private Playing playing;
    public static int pxpos = 0,speed = 4,sub=0;     
    public static int yheight=0,ypos=0,xpos=475,ydelta =580,xwidth=110,yposw=0,yheightw=0,yhh=110,ypp;
    public static int checker = 0,checks=0;
    public static int enemychecker = 0;
    public static int xp = 515,yp =265,xw = 30,yh=50;
    private BufferedImage[] idleAni,FidleAni;
    private int aniTick,aniIndex,aniSpeed = 40,FaniTick,FaniIndex,FaniSpeed=40;
    public static int ywp ;
    public static int yindex = 2;
    public static Rectangle hitbox;
    public static int count = 0;
    public static boolean stop = false;
    Random rand = new Random();
  
    public Player() {
              
        initHitbox();
        loadAnimations();
    }
    
    public void update(){   
              loadAnimations(); 
              updateAnimationTick();
   }
  
    
    private void initHitbox() {
     hitbox = new Rectangle(xp,yp,xw,yh);
    }
 
    public Rectangle getHitbox(){
        return hitbox;
    }
    
    public void render(Graphics g){
        
        ywp = 380+yposw;
        Pxpos();
      
        if(Playing.easy == true && Playing.gameOver == false || Playing.hard == true && Playing.gameOver == false ||
                Playing.expert == true && Playing.gameOver == false ){
        ypp= 235 + ypos;
        g.drawImage(idleAni[aniIndex], xpos+pxpos,ypp ,xwidth,yhh, null);
        }else if(Playing.gameOver == true){
            
        }
      //  g.drawRect(495, 250, 70, 70);
     // g.setColor(Color.red);//135
      //g.drawRect(hitbox.x, hitbox.y+ypos, hitbox.width, hitbox.height);
    }
    
     //to stay
   public void changeyheight(int value){
       this.yheight +=value;    
   }
   public void changeypos(int value){
       this.ypos +=value; 
       checker+=1;
       checks+=1;
   }
    public void changewaterypos(int value){
         this.yposw += value;
         if(stop == false){
          if(Playing.easy == true){
         enemychecker = rand.nextInt(15);
          }else if(Playing.hard == true){
         enemychecker = rand.nextInt(8);
          }else if(Playing.expert == true){
         enemychecker = rand.nextInt(6);
          }
         System.out.println(enemychecker);
         }
         
     }
     public void changewateryheight(int value){
         
         this.yheightw += value;
     }
    
   public void loadAnimations(){
       
       BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Player_Atlas);
       
       idleAni = new BufferedImage[3]; 
       for(int i = 0 ; i < idleAni.length; i++){
           idleAni[i] = img.getSubimage(i*128, yindex*125, 128, 128); 
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
   

   public void resetAll(){
        ypos = 0;
        yposw = 0;
        yheightw = 0;
         checker = 0;
         enemychecker=0;
         checks=0;
         ypp=0;
         ywp=0;
         count = 0;
         pxpos = 0;
         yindex = 2;
         stop = false;
   }
   
   private void changePXpos(){ 
       
             pxpos += speed;
            if(pxpos >= 1100)
                pxpos = 1100;
            
     }
   
   public void Pxpos(){
       if(LevelOne.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(LevelTwo.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(LevelThree.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(LevelFour.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(LevelFive.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }if(HardLevelOne.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(HardLevelTwo.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(HardLevelThree.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(HardLevelFour.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(HardLevelFive.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }if(Expertlevelone.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(Expertleveltwo.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(Expertlevelthree.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(Expertlevelfour.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }else if(Expertlevelfive.xpos <= - 635){
            yindex = 3;
            changePXpos();
        }
   }
   
  
  
}
