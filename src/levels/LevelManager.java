
package levels;

import entities.Player;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import utilz.LoadSave;


public class LevelManager {
    
    private Games game;
   
    private BufferedImage ground = LoadSave.GetSpriteAtlas(LoadSave.Ground_Atlas);
    private BufferedImage waterSprite = LoadSave.GetSpriteAtlas(LoadSave.Water_Atlas);
    private BufferedImage lavaSprite = LoadSave.GetSpriteAtlas(LoadSave.Lava_Block);
    private BufferedImage iceplat = LoadSave.GetSpriteAtlas(LoadSave.Ice_Atlas);
    private BufferedImage lavaplat = LoadSave.GetSpriteAtlas(LoadSave.Lava_Atlas);
     //private BufferedImage lavaflow = LoadSave.GetSpriteAtlas(LoadSave.Lava_Flow);
    private BufferedImage[] idleAni,lidleAni;
    private int aniTick,aniIndex,aniSpeed = 45,laniTick,laniIndex,laniSpeed=100;
    
    
    public LevelManager(Games game){
        
           this.game =  game;
           
           waveAnimation();
           lavaAnimation();
   
    }
    
    public void update(){
        if(Playing.easy == true || Playing.hard == true){
      updateAnimationTick();  
        }else if(Playing.expert == true){
            lupdateAnimationTick(); 
        }
    }
    
    public void  waveAnimation(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Wave_Atlas);
       idleAni = new BufferedImage[3]; 
       for(int i = 0 ; i < idleAni.length; i++){
           idleAni[i] = img.getSubimage(i*100, 2*30, 130, 30);
       }    
    }
    
    public void lavaAnimation(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Lava_Flow);
       lidleAni = new BufferedImage[3]; 
       for(int i = 0 ; i < lidleAni.length; i++){
           lidleAni[i] = img.getSubimage(i*102, 0*30, 102, 30);
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
     
     public void lupdateAnimationTick(){
       
       laniTick++;
       if(laniTick >= laniSpeed){
           laniTick = 0;
           laniIndex++;
           if (laniIndex >= lidleAni.length){
               laniIndex = 0;
           }
       }
       
   }
     
    public void draw(Graphics g){
     
       if(Playing.easy == true){
        g.drawImage(ground,390,305+Player.ypos,275,1500,null);
        g.drawImage(idleAni[aniIndex],0,Player.ywp,1000,50,null);
        g.drawImage(waterSprite,0,420+Player.yposw,1000,310+Player.yheightw,null);
        g.setColor(Color.red);
       }else if(Playing.hard == true){
        g.drawImage(iceplat,390,305+Player.ypos,275,1500,null);
        g.drawImage(idleAni[aniIndex],0,Player.ywp,1000,50,null);
        g.drawImage(waterSprite,0,420+Player.yposw,1000,310+Player.yheightw,null);
       }
        else if(Playing.expert == true){
        g.drawImage(lavaplat,390,305+Player.ypos,275,1500,null);
        g.drawImage(lidleAni[laniIndex],-20,Player.ywp,1300,50,null);
        g.drawImage(lavaSprite,0,420+Player.yposw,1000,310+Player.yheightw,null);
       }
        
    }
    
    
     
}
