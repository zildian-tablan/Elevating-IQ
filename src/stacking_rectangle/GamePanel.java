
package stacking_rectangle;

import gamestates.Gamestate;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;



public class GamePanel extends JPanel{
    
 
    private Games game;
  
  
   public GamePanel(Games game){
       this.game = game;
        
       MouseInputs mouse = new MouseInputs(this);
       
       setPanelSize();
       setLayout(null);
       addKeyListener(new KeyboardInputs(this));
       addMouseListener(mouse);
       addMouseMotionListener(mouse);

    }
   //to stay

   public void setPanelSize(){
       //1280,800
       Dimension size = new Dimension(Games.Game_Width,Games.Game_Height);
       setPreferredSize(size);
       System.out.println(Games.Game_Width+" "+ Games.Game_Height);
      
   }
    
   public void paintComponent(Graphics g){       
       super.paintComponent(g); 
       game.render(g);
   }
   
   public Games getGame(){
       return game;
   }
   
    public void createInput(JTextField field,JPasswordField field2){
 
        add(field); 
         add(field2);
      //  userfield.setVisible(val);
    }
     public void revInput(JTextField field,JPasswordField field2){
         remove(field);
          remove(field2);
     }
     public void createSInput(JTextField field,JPasswordField field2,JPasswordField field3){
 
        add(field); 
         add(field2);
         add(field3);
      //  userfield.setVisible(val);
    }  
     public void revSInput(JTextField field,JPasswordField field2,JPasswordField field3){
         remove(field);
          remove(field2);
           remove(field3);
     }    
     
     public void createRbutton(JRadioButton b1,JRadioButton b2){
         

         add(b1);
         add(b2);
     }
     
      public void revRbutton(JRadioButton b1,JRadioButton b2){
  
         remove(b1);
         remove(b2);
     }
      
     public void creategif(JLabel lbl){  
         add(lbl);
     }   
     public void createEnum(JTextField field){
         add(field);
     }
     
     public void revEnum(JTextField field){
         remove(field);
     }
    
    
}
