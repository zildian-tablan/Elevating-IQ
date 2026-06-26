
package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.PSHButtons.*;
import utilz.LoadSave;


public class PlayPauseButton extends PauseButton{
    
     private BufferedImage[] imgs;
    private int rowIndex,index;
    private boolean mouseOver,mousePressed;
    
    public PlayPauseButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        
        this.rowIndex=rowIndex;
        loadImgs();
    }
    
    private void loadImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PSH_Buttons);
        
        imgs = new BufferedImage[3];
        
        for(int i = 0; i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*PSH_WIDTH_DEFAULT, rowIndex*PSH_HEIGHT_DEFAULT, PSH_WIDTH_DEFAULT, PSH_HEIGHT_DEFAULT);
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
    
}
