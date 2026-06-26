
package Easy;

import choice.ChoiceButton;
import gamestates.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.EchoiceButtons.*;

import utilz.LoadSave;



public class EchoiceButton extends ChoiceButton{
    
 
    private BufferedImage[] imgs;
    private int rowIndex,index;
    private boolean mouseOver,mousePressed;
   
    
    public EchoiceButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        
        this.rowIndex=rowIndex;
        loadImgs();
    }
    
    private void loadImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Echoice_Button);
        
        imgs = new BufferedImage[3];
        
        for(int i = 0; i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*ECB_WIDTH_DEFAULT, rowIndex*ECB_HEIGHT_DEFAULT, ECB_WIDTH_DEFAULT, ECB_HEIGHT_DEFAULT);
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
        
        g.drawImage(imgs[index], x, y, 240, 75, null);
        
    }
    
    public void resetBools(boolean bo){
        mouseOver = bo;
        mousePressed = bo;
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
