/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import utilz.LoadSave;

public class expertbg {
    private BufferedImage expert = LoadSave.GetSpriteAtlas(LoadSave.Expert_Backkground);
    
    public expertbg(){
        
    }
    public void draw(Graphics g){
        g.drawImage(expert, 0, 0, Games.Game_Width, Games.Game_Height, null);
    }
}
    

