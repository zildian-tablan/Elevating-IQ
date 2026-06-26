
package ui;

import choice.RChoiceButton;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.RankButtons.*;
import utilz.LoadSave;


public class RankChoiceButton extends RChoiceButton{
    
    private BufferedImage[] imgs;
    private int rowIndex,index;
    private boolean mouseOver,mousePressed;
    
    public RankChoiceButton(int x, int y, int width, int height,int rowIndex){
        
        super(x, y, width, height);
        
        this.rowIndex=rowIndex;
        loadImgs();
    }
    
    private void loadImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Rank_Buttons);
        
        imgs = new BufferedImage[3];
        
        for(int i = 0; i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*RB_WIDTH_DEFAULT, rowIndex*RB_HEIGHT_DEFAULT, RB_WIDTH_DEFAULT, RB_HEIGHT_DEFAULT);
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
        
        g.drawImage(imgs[index], x, y, 40, 40, null);
        
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
