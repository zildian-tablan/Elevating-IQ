
package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;


public class UrmButton extends PauseButton{
    
    private BufferedImage[] imgs;
    private int rowIndex,index;
    private boolean mouseOver,mousePressed;
    public static Gamestate state;
    
    public UrmButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        this.rowIndex=rowIndex;
        loadImgs();
    }
    
    private void loadImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_Buttons);
        
        imgs = new BufferedImage[3];
        
        for(int i = 0; i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*URM_DEFAULT_WIDTH, rowIndex*URM_DEFAULT_HEIGHT, URM_DEFAULT_WIDTH, URM_DEFAULT_HEIGHT);
        }
    }
    
    public void update(){
        
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
    }
    
    public void draw(Graphics g){
        
        g.drawImage(imgs[index], x, y, 55, 55, null);
        
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
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
    public Gamestate getState(){
        return state;
    }
    
    
}
