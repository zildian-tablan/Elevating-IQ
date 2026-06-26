
package entities;


import gamestates.Playing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.EnemyConstants.*;
import utilz.LoadSave;


public class EnemyManager {
    
    private Playing playing;
    private BufferedImage[][] sharkimg;
    
    
    private int sharkIndex,sharkTick,sharkSpeed=25,speed=1,yspeed=2;
     public static int xpos=0;
     private int targetX = 810;
     private int state =0;
     private Rectangle hitbox;
     private Rectangle2D.Float attackBox;
     public static int xp = 70,yp =420,xw = SHARK_WIDTH,yh=SHARK_HEIGHT;
     private int ypos;
     public static int hx,hy,hy2;
     public static int health=0;
     public static boolean jump = true;
   
    public EnemyManager(Playing playing){
        this.playing = playing;
        
        loadEnemy();
        initHitbox();
        initAttackBox();
      
    }
    
    public void update(){
        enemyActionTick();
        updateAttackBox();
         moveSharks();
        
       
       
    }
    
    private void updateAttackBox(){
        attackBox.x = hitbox.x;
        attackBox.y = hitbox.y;
    }
    public void draw(Graphics g){
         changey();
        hx=75 + xpos;
        hy=(420+Player.yposw)+ypos;
        drawSharks(g);
        
     //   g.setColor(Color.red);      
       // g.drawRect(hx, hy, hitbox.width, hitbox.height); 
    }
    
    
    
     
   private void loadEnemy(){
        sharkimg = new BufferedImage[3][4];
 
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Enemy_Shark);
      
        for(int j = 0 ; j < sharkimg.length; j++){
            for(int i = 0 ; i < sharkimg[j].length; i++){
               
                sharkimg[j][i] = temp.getSubimage(i*SHARK_WIDTH_DEFAULT, j*SHARK_HEIGHT_DEFAULT, SHARK_WIDTH_DEFAULT, SHARK_HEIGHT_DEFAULT);
                
            }
        }
        
    }
    
    public void enemyActionTick(){
        sharkTick++;
           if(sharkTick >= sharkSpeed){
               sharkTick = 0;
               sharkIndex++;
               if(sharkIndex == 4){
                   sharkIndex=0;
               }
           }
    }
    
   private void initHitbox() {
     hitbox = new Rectangle(xp,yp,xw,yh);
    }
 
    public Rectangle getHitbox(){
        return hitbox;
    }
    
    private void initAttackBox(){
         attackBox = new Rectangle2D.Float(xp,yp,xw,yh);
    }
    
    
   private void moveSharks() {
   
   if (state == 0){
    xpos += speed;
    
    // Check if the sharks have reached the target position
    
    if (xpos >= targetX) {
         xpos=targetX;
       
         state=1;// Ensure xpos doesn't overshoot
    }
   }
   else if(state == 1|| state == 2){
        state=2;
        xpos -= speed;
        if(xpos <= 0){

            xpos = 0;
            state = 0;
        }
    }
   }
   
    public void changey(){
       if (Player.enemychecker == 2 || Player.enemychecker == 3){    
           System.out.println("heyhey");
           if(Playing.easy == true){
               ypos -=yspeed;
               if(ypos <= -150)
                 Player.enemychecker = 16;
           }else if(Playing.hard == true){
                ypos -=yspeed;
               if(ypos <= -200)
                 Player.enemychecker = 16;
           }else if(Playing.expert == true){
                ypos -=yspeed;
               if(ypos <= -250)
                 Player.enemychecker = 16;
           }
            
            Player.stop = true;
       }else if (Player.enemychecker == 16){
           ypos += yspeed;
           Player.stop = true;
           if(ypos == 0){
               ypos = 0;
               Player.enemychecker = 17;
           }
       }
   }
        
    private void drawSharks(Graphics g){

 
            g.drawImage(sharkimg[state][sharkIndex],75 + xpos, hy,SHARK_WIDTH,SHARK_HEIGHT, null);
        
    }
    
    public void resetAll(){
        ypos = 0;
        xpos = 0;
        state = 0;
    }
}
