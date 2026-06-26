
package entities;

import gamestates.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.EntityConstants.*;
import utilz.LoadSave;


public class BgEntity {
    private Playing playing;
    private BufferedImage[][] birdimg;

    private int birdIndex,birdTick,birdSpeed=25,speed=1;
     private int xpos=0;
      private int targetX = 1400,targetY= 30;
      private int state =0,state2=2;
      
      public int ypos=0;
   
    public BgEntity(Playing playing){
        this.playing = playing;
        
        loadEnemy();
       
    }
    
    public void update(){
        enemyActionTick();
         moveBirds();
          changey();
        
    }
    public void draw(Graphics g){
        
        drawBirds(g);
      
    }
    private void loadEnemy(){
        birdimg = new BufferedImage[5][8];
        
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Bird_Entity);
        
        for(int j = 0 ; j < birdimg.length; j++){
            for(int i = 0 ; i < birdimg[j].length; i++){
                birdimg[j][i] = temp.getSubimage(i*BIRD_WIDTH_DEFAULT, j*BIRD_HEIGHT_DEFAULT, BIRD_WIDTH_DEFAULT, BIRD_HEIGHT_DEFAULT);
            }
        }
        
    }
    
    public void enemyActionTick(){
        birdTick++;
           if(birdTick >= birdSpeed){
               birdTick = 0;
               birdIndex++;
               if(birdIndex == 4){
                   birdIndex=0;
               }
           }
    }
    
     
   private void moveBirds() {
   
   if (state == 0 ){
    xpos += speed;

    // Check if the sharks have reached the target position
    
    if (xpos >= targetX) {
         xpos=targetX;
         state=1;
        
    }
    }
    else if(state == 1 ){
       
        xpos -= speed;
        if(xpos <= -400){
          
            xpos = -200;
            state = 0;
            
        }
    }
   
  
}
   public void changey(){
       if (Player.checker == 3){
       
           
           ypos-=speed;
           if(ypos <= -30){
            
               ypos = -40;
               Player.checker = 4;
           }
           
          
       }
   }
        
    private void drawBirds(Graphics g){
      
     
            g.drawImage(birdimg[state][birdIndex],-400 + xpos, 50+ypos,BIRD_WIDTH,BIRD_HEIGHT, null);
           // g.drawImage(birdimg[state2][birdIndex], -100+xpos, 80,BIRD_WIDTH,BIRD_HEIGHT, null);
    }
    public void resetAll(){
        xpos = 0;
        ypos = 0;
        state = 0;
    }
    
}
