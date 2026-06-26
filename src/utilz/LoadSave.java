
package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import stacking_rectangle.Games;



public class LoadSave {
    
    public static final String Player_Atlas = "samurai.png";
    public static final String Ground_Atlas = "under_ground_atlas.png";
    public static final String Water_Atlas = "sky_water.png";
    public static final String Wave_Atlas = "true_water.png";
    public static final String Menu_Button = "button_menu.png";
    public static final String Pause_Background = "tpausebg.png";
    public static final String Sound_Button = "sound_button.png";
    public static final String URM_Buttons = "pausebutton.png";
   // public static final String Volume_Button = "volume_buttons.png";
    public static final String Easy_Background ="bg_easy.png";
    public static final String Whole_Menu_Background = "menu_the_bg.png";
    public static final String Login_Button="Login_buttons.png";
    public static final String Login_Background ="background_login.png";
    public static final String Title_Sprite ="true_title_sprite.png";
    public static final String Question_Bar = "question_bar.png";
    public static final String Echoice_Button = "echoice_button.png";
    public static final String Difficulty_Background = "diffbg.png";
    public static final String Difficulty_Buttons ="diffbutton.png";
    public static final String Signup_Background ="signup_background2.png";
    public static final String Enemy_Shark ="the_shark.png";
    public static final String Lava_Enemy_Shark ="lavashark.png";
    public static final String Bird_Entity ="bird_entity.png";
    public static final String FGround_Atlas ="true_ground.png";
    public static final String Health_Bar ="healthbar.png";
    public static final String LevelC_Background="lvl_complete.png";
    public static final String Level_Buttons_E="level_buttons_e.png";
    public static final String LevelBE_Background="levelbutton_bg.png";
    
    public static final String Hard_Backkground ="hard_background.png";
    public static final String Level_Backgorund_H="level_background_h.png";
    public static final String Level_Buttons_H="level_buttons_h.png";
    public static final String Ice_Atlas="ice_platform.png";
    public static final String Submit_Button = "hsubmit_button.png";
    public static final String EXSubmit_Button = "exsubmit.png";
    public static final String FIceplat_Atlas="ficeplat_2.png";
    public static final String lavaplat_Atlas="lavaplat.png";
    
    public static final String Expert_Backkground ="expert_background.png";
    public static final String Lava_Atlas ="lava_platform.png";
    public static final String lava_sprite ="lava_sprite.png";
    public static final String exprtbg ="lavabg.png";
         
    public static final String SignLog_Background = "signlogtbg.png";
    public static final String Option_Button = "Optionbutton.png";
    public static final String Option_Background = "OptionBgt.png";
    public static final String ProfileM_Background = "ProfileM.png";
    public static final String ProfileF_Background = "ProfileF.png";
    
    public static final String Leaderboard_Buttons = "leaderbutton.png";
    public static final String Rank_Buttons = "rankbuttonstr.png";
    public static final String Rank_Background = "rankbg1.png";
    public static final String Setting_Background = "setting_bg.png";
    public static final String Setting_Background_W = "setting_back.png";
    public static final String Rank_Background2 = "rankbg2.png";
    public static final String PSH_Buttons = "paserup.png";
    
    public static final String Lava_Flow = "flowinglava.png";
    public static final String Lava_Block = "lavablock.png";
    public static final String Lava_PlatformBg = "lava_platbg.png";
    public static final String Level_Buttons_Ex = "lava_ex_buttons.png";
     public static final String Help = "about_game.png";
     

     public static BufferedImage GetSpriteAtlas(String filename){
         
         BufferedImage img = null;
        
    InputStream is = LoadSave.class.getResourceAsStream("/" + filename );
    try{
    img = ImageIO.read(is);   
    } catch(IOException e){
        e.printStackTrace();
    }finally{
        try{
         is.close();
    } catch(IOException e){
        e.printStackTrace();
    } 
    }  
    return img;
     }
     
     
    /* public static ArrayList<Shark> getShark(){
      
       ArrayList<Shark> list = new ArrayList<>();
      
               Color color = new Color(0,4,0);
               int value=color.getGreen();
               if(value == SHARK){
                   System.out.println("your in");
               list.add(new Shark(970,370));
               }
               return list;
    }*/
           
       
         
    
     // img.getHeight(),img.getWidth() for size
     //game.height visible
    
}
