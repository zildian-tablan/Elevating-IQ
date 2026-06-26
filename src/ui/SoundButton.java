
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.PauseButtons.*;
import utilz.LoadSave;


public class SoundButton extends PauseButton{
    
    private BufferedImage[][] soundimgs;
    private boolean mouseOver,mousePressed;
    private boolean muted;
    private int rowIndex,columnIndex;
    
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        loadSoundImg();
    }

    private void loadSoundImg() {
    
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Sound_Button);
        soundimgs = new BufferedImage[2][3];
        for(int j = 0; j<soundimgs.length; j++)
            for(int i = 0; i< soundimgs[j].length;i++){
                soundimgs[j][i] = temp.getSubimage(i*Sound_Size_Default,j*Sound_Size_Default, Sound_Size_Default, Sound_Size_Default);
            }
    }
    
    public void update(){
        if(muted){
            rowIndex = 1;   
        }else{
            rowIndex = 0;     
        } 
        columnIndex = 0;
        if(mouseOver){
            columnIndex = 1;  
        }
        if(mousePressed){
            columnIndex =2;       
        }
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    
    public void draw(Graphics g){
        g.drawImage(soundimgs[rowIndex][columnIndex], x, y ,width,height,null);
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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    
    
 
}
