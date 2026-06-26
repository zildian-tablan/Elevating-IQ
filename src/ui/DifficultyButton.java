
package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.DifficultyButtons.*;
import utilz.LoadSave;


public class DifficultyButton {
   
    private int xPos,yPos,rowIndex,index;
    private int xOffsetCenter = DB_WIDTH/2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;
    
    public DifficultyButton(int xPos,int yPos,int rowIndex,Gamestate state){
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        
        loadImgs();
        initBounds();
    }
       
    private void initBounds() {
         
         bounds = new Rectangle(xPos-xOffsetCenter, yPos,120,60);
    }
    
    private void loadImgs() {
     imgs =  new BufferedImage[3];
     BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Difficulty_Buttons);
     
     for(int i = 0; i<imgs.length;i++)
         imgs[i]= temp.getSubimage(i*DB_WIDTH_DEFAULT, rowIndex*DB_HEIGHT_DEFAULT, DB_WIDTH_DEFAULT, DB_HEIGHT_DEFAULT);
        
    }
    
    public void draw(Graphics g){
        
        g.drawImage(imgs[index], xPos-xOffsetCenter, yPos,120,60, null);
        
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
    
    public Gamestate getState(){
        return state;
    }
}
