
package Hard;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import utilz.LoadSave;


public class Hardbg {
    
    private BufferedImage hardbg = LoadSave.GetSpriteAtlas(LoadSave.Hard_Backkground);
    
    public Hardbg(){
        
    }
    public void draw(Graphics g){
        g.drawImage(hardbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
    }
}
