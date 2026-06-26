
package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.LogInButtons.*;
import utilz.LoadSave;



public class LogInButton {
    
    private int xPos,yPos,rowIndex,index;
    private int xOffsetCenter = LIB_WIDTH/2;
    private Gamestate state;
    private BufferedImage[] libimgs;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;
    
    public LogInButton(int xPos, int yPos, int rowIndex,Gamestate state){
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
       // System.out.println(xPos+" "+yPos+" "+rowIndex+" "+state.toString());
        
        loadImgs();
        initBounds();
    }
    
    private void initBounds() {       
         bounds = new Rectangle(xPos-xOffsetCenter, yPos,LIB_WIDTH-142,LIB_HEIGHT-77);
        
     }
    
    private void loadImgs() {
     libimgs =  new BufferedImage[3];
     BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Login_Button);
     
     for(int i = 0; i<libimgs.length;i++)
         libimgs[i]= temp.getSubimage(i*LIB_WIDTH_DEFAULT, rowIndex*LIB_HEIGHT_DEFAULT, LIB_WIDTH_DEFAULT, LIB_HEIGHT_DEFAULT);
        
    }
    
     public void draw(Graphics g){
        
        g.drawImage(libimgs[index], xPos-xOffsetCenter, yPos,80,40, null);
        
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
