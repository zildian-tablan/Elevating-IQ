
package stacking_rectangle;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {
    
    private  JFrame frame = new JFrame();
    private GamePanel gamepanel;
    
    public GameWindow(GamePanel gamepanel){    
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamepanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent we) {
                
            }

            @Override
            public void windowLostFocus(WindowEvent we) {
                System.out.println("s");
            
           }
            
         
        });
    }
    
}
