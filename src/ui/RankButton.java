
package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.RankButtons.*;
import utilz.LoadSave;


public class RankButton {
    
    private int xPos,yPos,rowIndex,index;
    private int xOffsetCenter = RB_WIDTH/2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;
    
    public RankButton(int xPos,int yPos,int rowIndex,Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        
        loadImgs();
        initBounds();
    }
    
     private void initBounds() {
         bounds = new Rectangle(xPos-xOffsetCenter, yPos,80,80);
    }
    
    private void loadImgs() {
     imgs =  new BufferedImage[3];
     BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Rank_Buttons);
     
     for(int i = 0; i<imgs.length;i++)
         imgs[i]= temp.getSubimage(i*RB_WIDTH_DEFAULT, rowIndex*RB_HEIGHT_DEFAULT, RB_WIDTH_DEFAULT, RB_HEIGHT_DEFAULT);
        
    }
    
    public void draw(Graphics g){
        
        g.drawImage(imgs[index], xPos-xOffsetCenter, yPos,80,80, null);
        
    }
    
    public void update(){
        
        index=0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
        
    }
    
    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    public Rectangle getBounds(){
        return bounds;
    }

    public void applyGamestate(){
        
      
       Gamestate.state = state;
    }
   
    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }
    
}
