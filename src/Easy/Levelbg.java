
package Easy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import stacking_rectangle.Games;
import utilz.LoadSave;


public class Levelbg {
    
    private BufferedImage levelbg=LoadSave.GetSpriteAtlas(LoadSave.Easy_Background);
    public Levelbg(){
        
    }

    public void draw(Graphics g){
        g.drawImage(levelbg, 0, 0, Games.Game_Width, Games.Game_Height, null);
    }
}
