
package stacking_rectangle;
import Easy.EasyWindow;
import Hard.HardLevelOne;
import Hard.HardWindow;
import LeaderBoard.Leaderboard;
import LeaderBoard.Ranking;
import PkmnAudio.AudioPlayer;
import Profile.Profile;
import expert.ExpertWindow;
import expert.Expertlevelone;
import entities.Player;
import gamestates.Difficulty;
import gamestates.Gamestate;
import static gamestates.Gamestate.DIFFICULTY;
import static gamestates.Gamestate.HARD;
import static gamestates.Gamestate.EXPERT;
import static gamestates.Gamestate.LEADERBOARD;
import static gamestates.Gamestate.MENU;
import static gamestates.Gamestate.PLAYING;
import static gamestates.Gamestate.PROFILE;
import static gamestates.Gamestate.SETTING;
import gamestates.Help;
import gamestates.LogIn;
import gamestates.Menu;
import gamestates.Options;
import gamestates.Playing;
import gamestates.Settings;
import gamestates.SignLog;
import gamestates.SignUp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utilz.LoadSave;


public class Games implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamepanel;
    private Thread gamethread;
    private final int fps_set = 120;
    private final int ups_set = 200;
    
    private Player player;
    private Playing playing;
    private Difficulty difficulty;
    private EasyWindow ewindow;
    private HardWindow hwindow;
    private ExpertWindow exwindow;
    private HardLevelOne hlevelone;
    private Expertlevelone exlevelone;
    private Menu menu;
    private Options options;
    private Leaderboard leaderboard;
    private Ranking ranking;
    private Profile profile;
    private SignLog signlog;
    private LogIn login;
    private SignUp signup;
    private Settings settings;
    private AudioPlayer audioplayer;
    private Help help;
    public  static JTextField userfield = new JTextField();
    public  static JPasswordField passfield =  new JPasswordField();
    public  static JTextField suserfield = new JTextField();
    public  static JPasswordField spassfield =  new JPasswordField();
    public  static JPasswordField scpassfield =  new JPasswordField();
    public static JRadioButton male = new JRadioButton("Male");
    public static JRadioButton female = new JRadioButton("Female");
    public static ButtonGroup rgroup = new ButtonGroup();
    private BufferedImage[] idleAni;
    private int aniTick,aniIndex,aniSpeed =250;
    private int terminator = 0,term=5;
    public static JTextField enumfield = new JTextField();
    
    
    public final static int Tiles_Default_Size = 32;
    public final static float SCALE = 1.5f;
    public final static float Tiles_Width = 20.83f;
    public final static float Tiles_Height = 16.66f;
    public final static int Tile_Size = (int)(Tiles_Default_Size * SCALE);
    public final static int Game_Width = (int)(Tile_Size*Tiles_Width);
    public final static int Game_Height = (int)(Tile_Size*Tiles_Height)-69;
    
    
    public Games(){
        initClasses();
        gamepanel = new GamePanel(this) ;
        gameWindow = new GameWindow(gamepanel);
        gamepanel.setFocusable(true);
        gamepanel.requestFocus();
        startloop();
        titleAnimation();
        createfield();
        createRadio();
        audioplayer.playSong(AudioPlayer.BACKGROUND);
    }
    
    public void createfield(){
      
        userfield.setBounds(400,310,200,25);
 
        passfield.setBounds(400,360,200,25);
        
        enumfield.setBounds(415,582,200,25);
        
        suserfield.setBounds(390,320,220,25);
 
        spassfield.setBounds(390,360,220,25);
        
        scpassfield.setBounds(390,400,220,25);
        
        userfield.setColumns(15);
        passfield.setColumns(15);
        suserfield.setColumns(15);
        spassfield.setColumns(15);
        scpassfield.setColumns(15);
    }
    
    public void createRadio(){
        rgroup.add(male);
        rgroup.add(female);
        male.setFocusable(false);
        male.setBackground(new Color(0,0,0,0));
        male.setBounds(400,440,55,20);
        female.setFocusable(false);
        female.setBackground(new Color(0,0,0,0));
        female.setBounds(520,440,68,20);
    }
    
    public void createEnumField(){
          gamepanel.createEnum(enumfield);
    }
   
    public void removeEnumField(){
        gamepanel.revEnum(enumfield);
    }
    
    
    public void  titleAnimation(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Title_Sprite);
       
       idleAni = new BufferedImage[3]; 
       for(int i = 0 ; i < idleAni.length; i++){
           idleAni[i] = img.getSubimage(i*400, 0, 400, 90);
       }    
    }
  
    public void updateAnimationTick(){
       
       aniTick++;
       if(aniTick >= aniSpeed){
           aniTick = 0;
           aniIndex++;
           if (aniIndex >= idleAni.length){
               aniIndex = 0;
           }
       }
    }
    private void initClasses(){
        
      signlog = new SignLog(this);
        signup = new SignUp(this);
        login = new LogIn(this);
        menu = new Menu(this);
        audioplayer = new AudioPlayer();
        options = new Options(this);
        settings = new Settings(this);
        leaderboard = new Leaderboard(this);
        ranking = new Ranking(this);
        profile = new Profile(this);
        difficulty = new Difficulty(this);
        ewindow =  new EasyWindow(this);
        help = new Help(this);
        hwindow = new HardWindow(this);
        exwindow = new ExpertWindow(this);
        playing = new Playing(this);   
        player = new Player();
    }
   
    private void startloop(){
        gamethread = new Thread(this);
        gamethread.start();
    }
   
    public void update(){
        
        switch(Gamestate.state){
            case SIGNLOG:
                signlog.update();
                break;
            case SIGNUP:
                signup.update();
                if(term == 5){
                gamepanel.createSInput(suserfield, spassfield, scpassfield);
                gamepanel.createRbutton(male, female);
                term = 0;
                }else{
                    
                }
                break;
            case LOGIN:
                updateAnimationTick();
                login.update();
          if (terminator == 5){
              gamepanel.createInput(userfield, passfield);
              terminator = 0;
          }else{          
          }       
                break;
            case MENU:
                 updateAnimationTick();
                menu.update();
               
                break;
            case DIFFICULTY:
                updateAnimationTick();
                  difficulty.update();
                  break;
            case EXIT:
                 menu.update();
                 break;
            case SETTING:
                  settings.update();
                  break;
            case EASY:
                 ewindow.update();
                 break;
            case HARD:
                 hwindow.update();
                
                 break;
                 case EXPERT:
                 exwindow.update();
                
                 break;
            case PLAYING:
               playing.update();
                break;
            case OPTIONS:
                options.update();
                break;
            case LEADERBOARD:
                leaderboard.update();
                break;
            case PROFILE:
                profile.update();
                break;
            case RANKING:
                 ranking.update();
                break;
            case QUIT:
                 updateAnimationTick();
                 Gamestate.state = Gamestate.LOGIN;
                 break;
            case CANCEL:
                 term=5;
                updateAnimationTick();
                 Gamestate.state = Gamestate.SIGNLOG;
                
                  break;
            case CANCELS:
                   System.exit(0);
                   break;
            case HELP:
                  help.update();
                  break;
            default:
                break;
        }
    }
    
    public void render(Graphics g){
        
        switch(Gamestate.state){
            case SIGNLOG:
                signlog.draw(g);

                gamepanel.revInput(userfield, passfield);
                gamepanel.revSInput(suserfield, spassfield, scpassfield);
                gamepanel.revRbutton(male, female);
                
                terminator = 5;
                break;
            case SIGNUP:
                signup.draw(g);
               
                gamepanel.revInput(userfield, passfield);
                 g.setColor(Color.black);
                 g.setFont(new Font("Dialog",Font.PLAIN,12));
                 g.drawString("UserName:", 390, 317);
                 g.drawString("Password:", 390, 357);
                 g.drawString("Confirm Password:", 390, 397);
                 g.drawString("Gender:", 390, 437);
                //gamepanel.revEnum(enumfield);
                terminator = 5;
               
                break;
            case LOGIN:
                login.draw(g);
                gamepanel.revSInput(suserfield, spassfield, scpassfield);
                gamepanel.revRbutton(male, female);
                g.setColor(Color.black);
                g.setFont(new Font("Dialog",Font.PLAIN,12));
                g.drawString("UserName:", 400, 305);
                 g.drawString("Password:", 400, 355);
                 g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
                break;
            case MENU: 
                menu.draw(g);
           gamepanel.revInput(userfield, passfield);
           terminator = 5;
            g.drawImage(idleAni[aniIndex],300, 50, 400, 80,  null);
                break;
                case DIFFICULTY:
                  difficulty.draw(g);
                  g.drawImage(idleAni[aniIndex],300, 305, 400, 80,  null);
                  break;
                case EASY:
                    ewindow.draw(g);
                    break;
                case HARD:
                   
                 hwindow.draw(g);
               
                 break;
                  case EXPERT:
                   
                 exwindow.draw(g);
               
                 break;
                 
            case PLAYING:
                playing.draw(g);
                break;
            case OPTIONS:
                options.draw(g);
                break;
            case LEADERBOARD:
                leaderboard.draw(g);
                break;
            case PROFILE:
                profile.draw(g);
                break;
            case RANKING:
                ranking.draw(g);
                break;
            case SETTING:
                  settings.draw(g);
                  break;
            case HELP:
                  help.draw(g);
                  break;
            default:
                break;
        }
        
    }
    
    @Override
    public void run() {
   
       double timeperframe = 1000000000/fps_set;
       double timeperupdate = 1000000000.0 / ups_set;
       
       long previoustime = System.nanoTime();
       long lastcheck = System.currentTimeMillis();
       
       int updates = 0;
       int frames = 0;
       
       double deltau = 0;
       double deltaf = 0;
       
        
        while(true){
           long currentTime = System.nanoTime();
           
           deltau += (currentTime - previoustime) / timeperupdate;
           deltaf += (currentTime - previoustime) / timeperframe;
           previoustime = currentTime;
           
           if(deltau >= 1){
               update();
               updates++;
               deltau--;  
           }
           if(deltaf >= 1){
               gamepanel.repaint();
               deltaf--;
               frames++;
           } 
           if(System.currentTimeMillis() - lastcheck >= 1000){
           lastcheck = System.currentTimeMillis();
           System.out.println("FPS: " + frames + " UPS: " +updates);
           updates = 0;
           frames =0;
           
       }
            
       
        }
    }

    public void windowFocusLost(){
     //   if(Gamestate.state == Gamestate.PLAYING){
          //  playing.getPlayer();
    }
    
    public Menu getMenu(){
        return menu;
    }
    public Options getOptions(){
        return options;
    }
    
    public Playing getPlaying(){
        return playing;
    }
    public SignUp getSignUp(){
        return signup;
    }
    public LogIn getLogIn(){
        return login;
    }
    public Difficulty getDifficulty(){
        return difficulty;
    }
    public EasyWindow getEwindow(){
        return ewindow;
    }
    public HardWindow getHwindow(){
        return hwindow;
    }
    public ExpertWindow getExwindow(){
        return exwindow;
    }
    public SignLog getSignLog(){
        return signlog;
    }
    public Leaderboard getLeaderboard(){
        return leaderboard;
    }
    public Profile getProfile(){
        return profile;
    }
    public Ranking getRanking(){
        return ranking;
    }
    public AudioPlayer getAudioPlayer(){
        return audioplayer;
    }
    public Settings getSettings(){
        return settings;
    }
    public Help getHelp(){
        return help;
    }
    
}
