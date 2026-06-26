
package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public interface Statemethods {
    
    
    public void update() ;
    public void draw(Graphics g);
    public void mousePressed(MouseEvent me) ;
    public void mouseReleased(MouseEvent me); 
    public void mouseMoved(MouseEvent me) ;
    public void keyPressed(KeyEvent ke);
    public void keyReleased(KeyEvent ke) ;


}
