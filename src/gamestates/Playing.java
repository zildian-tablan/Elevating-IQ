package gamestates;

import Easy.LevelFive;
import Easy.LevelFour;
import Easy.LevelOne;
import Easy.LevelThree;
import Easy.LevelTwo;
import Easy.Levelbg;
import Hard.HardLevelFive;
import Hard.HardLevelFour;
import Hard.HardLevelOne;
import Hard.HardLevelThree;
import Hard.HardLevelTwo;
import Hard.Hardbg;
import PkmnAudio.AudioPlayer;
import expert.expertbg;
import entities.BgEntity;
import entities.EnemyManager;
import entities.Player;
import expert.Expertlevelone;
import expert.Expertleveltwo;
import expert.Expertlevelthree;
import expert.Expertlevelfour;
import expert.Expertlevelfive;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.Timer;

import levels.LevelManager;
import stacking_rectangle.Games;
import ui.GameOverLay;
import ui.LevelCompletedOverlay;
import ui.PauseButton;
import ui.PauseOverlay;
import ui.PlayPause;
import ui.PlayPauseButton;
import utilz.LoadSave;


public class Playing extends State implements Statemethods{
    
    private Levelbg levelbg;
    private Hardbg hardbg;
    private expertbg expertbg;
    private Player player;
    private LevelManager levelmanager;
    private EnemyManager enemymanager;
    private BgEntity bgentity;
    private PauseOverlay pauseoverlay;
    private GameOverLay gameoverlay;
    private LevelCompletedOverlay lcoverlay;
    private LevelOne levelone;
    private LevelTwo leveltwo;
    private LevelThree levelthree;
    private LevelFour levelfour;
    private LevelFive levelfive;
    private HardLevelOne hlevelone;
    private HardLevelTwo hleveltwo;
    private HardLevelThree hlevelthree;
    private HardLevelFour hlevelfour;
    private HardLevelFive hlevelfive;
    private Expertlevelone exlevelone;
    private Expertleveltwo exleveltwo;
    private Expertlevelthree exlevelthree;
    private Expertlevelfour exlevelfour;
    private Expertlevelfive exlevelfive;
    private PlayPause playpause;
    private PlayPauseButton powerup;
    private AudioPlayer audio;
    
    private BufferedImage  hpbar = LoadSave.GetSpriteAtlas(LoadSave.Health_Bar);
    //hpbar == hp bg
    //health bar == health
    private int hpbarWidth = 192;
    private int hpbarHeight = 25;
    private int hpbarXpos = 10;
    private int hpbarYpos = 10;
    
    private int healthbarWidth = 125;
    private int healthbarHeight = 4;
    private int healthbarXpos = 47;
    private int healthbarYpos = 10;
    
    public static int maxHp = 120;
    public static int currentHp1 = maxHp/3;

    private int healthWidth = healthbarWidth;  
    private int healthWidth2 = healthbarWidth;
    private int healthpos = (healthbarXpos + hpbarXpos);
    
    public static boolean paused = false;
    public static boolean gameOver = false;
    public static boolean level1=false,level2=false,level3=false,level4=false,level5 = false;
    public static boolean e1c=false,e2c=false,e3c=false,e4c=false,e5c=false;
    public static boolean h1c=false,h2c=false,h3c=false,h4c=false,h5c=false;
    public static boolean ex1c=false,ex2c=false,ex3c=false,ex4c=false,ex5c=false;
    public static boolean hlevel1=false,hlevel2=false,hlevel3=false,hlevel4=false,hlevel5=false;
    public static boolean levelCompleted = false,level2Completed = false,level3Completed = false,level4Completed = false,level5Completed = false ;
    public static boolean hlevelCompleted = false,hlevel2Completed = false,hlevel3Completed = false,hlevel4Completed = false,hlevel5Completed = false ;
    public static boolean easy=false,hard=false,expert=false;
    public static boolean exlevelCompleted=false,exlevel2Completed=false,exlevel3Completed=false,exlevel4Completed=false,exlevel5Completed=false;
    public static boolean exlevel1=false,exlevel2=false,exlevel3=false,exlevel4=false,exlevel5=false;
    public static int power = 0;
    public static int cscore,getscore;
    public static String getdiff,getlvl;
    public static int rindex = 0,pindex=2;
    public static int diffIndex = 0;
    public static int sharkb = 1;
    public Random rand = new Random();
    public int countdown = 0,count = 1;
    private boolean timerInitialized = false;
    private Timer timer;
    public static boolean drawPower = false,lvlc=false;

    public Playing(Games game) {
        super(game);
        initClasses();
    }
    
    private void initClasses(){
        levelbg = new Levelbg();
        hardbg = new Hardbg();
         expertbg = new expertbg();
        levelmanager = new LevelManager(game);
        enemymanager = new EnemyManager(this);
        bgentity = new BgEntity(this);
        levelone = new LevelOne(game,this);
        leveltwo = new LevelTwo(game,this);
        levelthree = new LevelThree(game,this);
        levelfour = new LevelFour(game,this);
        levelfive = new LevelFive(game,this);
        hlevelone = new HardLevelOne(game);
        hleveltwo = new HardLevelTwo(game);
        hlevelthree = new HardLevelThree(game);
        hlevelfour = new HardLevelFour(game);
        hlevelfive = new HardLevelFive(game);
        exlevelone = new Expertlevelone(game);
        exleveltwo = new Expertleveltwo(game);
        exlevelthree = new Expertlevelthree(game);
        exlevelfour = new Expertlevelfour(game);
        exlevelfive = new Expertlevelfive(game);
        player = new Player();   
        audio = new AudioPlayer();
        playpause = new PlayPause(game,this);
        pauseoverlay = new PauseOverlay(this,game);
        gameoverlay = new GameOverLay(this);
        lcoverlay = new LevelCompletedOverlay(this);
        createPowerbutton(pindex);
        
    }

    @Override
    public void update() {
    updateHealthBar();
     if(paused){
     pauseoverlay.update();
     } else if (easy == true && level1 == true && levelCompleted){
         lcoverlay.update();
     }else if (easy == true && level2 == true &&level2Completed){
         lcoverlay.update();
     }else if (easy == true && level3 == true && level3Completed){
         lcoverlay.update();
     } else if (easy == true && level4 == true && level4Completed){
         lcoverlay.update();
     } else if (easy == true && level5 == true && level5Completed){
         lcoverlay.update();
     }else if (hard == true && hlevel1 == true && hlevelCompleted ){
         lcoverlay.update();
     }else if (hard == true && hlevel2 == true && hlevel2Completed ){
         lcoverlay.update();
     }else if (hard == true && hlevel3 == true && hlevel3Completed ){
         lcoverlay.update();
     }else if (hard == true && hlevel4 == true && hlevel4Completed ){
         lcoverlay.update();
     }else if (hard == true && hlevel5 == true && hlevel5Completed ){
         lcoverlay.update();
         }else if (expert == true && exlevel1 == true && exlevelCompleted ){
         lcoverlay.update();
     }else if (expert == true && exlevel2 == true && exlevel2Completed ){
         lcoverlay.update();
     }else if (expert == true && exlevel3 == true && exlevel3Completed ){
         lcoverlay.update();
     }else if (expert == true && exlevel4 == true && exlevel4Completed ){
         lcoverlay.update();
     }else if (expert == true && exlevel5 == true && exlevel5Completed ){
         lcoverlay.update();
     }
     else if(!gameOver){
     levelmanager.update();
     player.update();
     enemymanager.update();
     bgentity.update();
     if(easy == true){
     if(level1==true){
     levelone.update();
     }else if(level2==true){
       leveltwo.update();
     }else if(level3==true){
       levelthree.update();
     }else if(level4==true){
       levelfour.update();
     }else if(level5==true){
       levelfive.update();
     }
     }else if(hard ==  true){
         if(hlevel1 == true){
             hlevelone.update();
         }else if(hlevel2 == true){
             hleveltwo.update();
         }else if(hlevel3 == true){
             hlevelthree.update();
         }else if(hlevel4 == true){
             hlevelfour.update();
         }else if(hlevel5 == true){
             hlevelfive.update();
         }
            }else if(expert ==  true){
         if(exlevel1 == true){
             exlevelone.update();
     }if(exlevel2 == true){
             exleveltwo.update();
     }if(exlevel3 == true){
             exlevelthree.update();
     }if(exlevel4 == true){
             exlevelfour.update();
     }if(exlevel5 == true){
             exlevelfive.update();
     }
            }
     }
     playpause.update();
    powerup.update();
     if (easy == true){
     if(currentHp1 <= 0|| Player.ywp < Player.ypp || LevelOne.wrongcount == 4|| LevelTwo.wrongcount == 4|| LevelThree.wrongcount == 4|| LevelFour.wrongcount == 4|| LevelFive.wrongcount == 4){   
         
         setGameOver(true);
         gameOverSEffect();
     }
     }else if(hard == true){
     if(currentHp1 <= 0  || Player.ywp < Player.ypp|| HardLevelOne.wrongcount == 3|| HardLevelTwo.wrongcount == 3|| HardLevelThree.wrongcount == 3|| HardLevelFour.wrongcount == 3|| HardLevelFive.wrongcount == 3){   
          //gameOverSEffect();
         setGameOver(true); 
         gameOverSEffect();
     }
     }else if(expert == true){
     if(currentHp1 <= 0  || Player.ywp < Player.ypp|| Expertlevelone.wrongcount == 2|| Expertleveltwo.wrongcount == 2|| Expertlevelthree.wrongcount == 2|| Expertlevelfour.wrongcount == 2|| Expertlevelfive.wrongcount == 2){   
          //gameOverSEffect();
         setGameOver(true);   
         gameOverSEffect();
     }
     }
     if(easy == true){
       if(LevelOne.xpos <= -635 && Player.pxpos >= 1100){
          //pag sinipag nako gawin player action  need ko lgyan to ng nested if loop para 2 condition bago mag levelcomplete
          if(level1 == true){
      
         levelComplete();
         lvlCompleteSEffect();
         } 
       } else if(LevelTwo.xpos <= -635 && Player.pxpos >= 1100){
          if (level2 == true){    
          level2Complete();
          lvlCompleteSEffect();
         }
       } else if(LevelThree.xpos <= -635 && Player.pxpos >= 1100){
          if (level3 == true){
         level3Complete();
         lvlCompleteSEffect();
         }
        }else if(LevelFour.xpos <= -635 && Player.pxpos >= 1100){
          if (level4 == true){
         level4Complete();
         lvlCompleteSEffect();
         }
        }else if(LevelFive.xpos <= -635 && Player.pxpos >= 1100){
          if (level5 == true){
         level5Complete();
         lvlCompleteSEffect();
         }
        }
     }else if(hard == true){
         if(HardLevelOne.xpos <= -635 && Player.pxpos >= 1100){
             if(hlevel1 == true){
                 hlevelComplete();
                 lvlCompleteSEffect();
             }
         }else if(HardLevelTwo.xpos <= -635 && Player.pxpos >= 1100){
             if(hlevel2 == true){
                 hlevel2Complete();
                 lvlCompleteSEffect();
             }
         }else if(HardLevelThree.xpos <= -635 && Player.pxpos >= 1100){
             if(hlevel3 == true){
                 hlevel3Complete();
                 lvlCompleteSEffect();
             }
         }else if(HardLevelFour.xpos <= -635 && Player.pxpos >= 1100){
             if(hlevel4 == true){
                 hlevel4Complete();
                 lvlCompleteSEffect();
             }
         }else if(HardLevelFive.xpos <= -635&& Player.pxpos >= 1100){
             if(hlevel5 == true){
                 hlevel5Complete();
                 lvlCompleteSEffect();
             }
         }
     }
     else if(expert == true){
         if(Expertlevelone.xpos <= -635 && Player.pxpos >= 1100){
             if(exlevel1 == true){
                 exlevelComplete();
                 lvlCompleteSEffect();
             }
    }else if(Expertleveltwo.xpos <= -635 && Player.pxpos >= 1100){
             if(exlevel2 == true){
                 exlevel2Complete();
                 lvlCompleteSEffect();
             }
         }else if(Expertlevelthree.xpos <= -635 && Player.pxpos >= 1100){
             if(exlevel3 == true){
                 exlevel3Complete();
                 lvlCompleteSEffect();
             }
         }else if(Expertlevelfour.xpos <= -635 && Player.pxpos >= 1100){
             if(exlevel4 == true){
                 exlevel4Complete();
                 lvlCompleteSEffect();
             }
         }else if(Expertlevelfive.xpos <= -635 && Player.pxpos >= 1100){
             if(exlevel5 == true){
                 exlevel5Complete();
                 lvlCompleteSEffect();
             }
         }
     }
     
    }
    @Override
    public void draw(Graphics g) {  
     if(easy == true){
     levelbg.draw(g);
     }else if(hard == true){
         hardbg.draw(g);
     }
     else if(expert == true){
         expertbg.draw(g);
     }
     levelmanager.draw(g);
     player.render(g);
     if(sharkb == 1){
     enemymanager.draw(g); 
     }
     bgentity.draw(g);
     if(easy == true){
        if(level1 == true){
          levelone.draw(g);
        }else if(level2 == true){
          leveltwo.draw(g);
        }else if(level3 == true){
           levelthree.draw(g);
        }else if(level4 == true){
           levelfour.draw(g);
        }else if(level5 == true){
           levelfive.draw(g);
        } 
     }else if(hard == true){
         if(hlevel1 == true){
           hlevelone.draw(g);
         }else if(hlevel2 == true){
           hleveltwo.draw(g);
         }else if(hlevel3 == true){
           hlevelthree.draw(g);
         }else if(hlevel4 == true){
           hlevelfour.draw(g);
         }else if(hlevel5 == true){
           hlevelfive.draw(g);
         }
     }
     else if(expert == true){
         if(exlevel1 == true){
           exlevelone.draw(g);
         }else if(exlevel2 == true){
           exleveltwo.draw(g);
         }else if(exlevel3 == true){
           exlevelthree.draw(g);
         }else if(exlevel4 == true){
           exlevelfour.draw(g);
         }else if(exlevel5 == true){
           exlevelfive.draw(g);
         }
     }
     drawUI(g);
     sharkAttack();
     playpause.draw(g);
     if(power == 0){
     createPowerbutton(pindex);
     power = 2;
     }else if(power == 1){
     rindex = rand.nextInt(3);
     //System.out.println(rindex);
     pindex=3;
     createPowerbutton(pindex);
     power = 3;
     }
     powerup.draw(g);
     if(drawPower == true){
     drawPowerText(g);
      QCountdown();
     }
     if(paused){
     g.setColor(new Color(0,0,0,175));
     g.fillRect(0, 0, Games.Game_Width, Games.Game_Height);
     pauseoverlay.draw(g);
     }else if(gameOver){
         getAudioPlayer().gameOver();
         game.removeEnumField();
      gameoverlay.draw(g);
     }else if(easy == true && level1 == true && levelCompleted){ 
         //getAudioPlayer.
     lcoverlay.draw(g);
     }else if(easy == true && level2 == true && level2Completed){
     lcoverlay.draw(g);
     }else if(easy == true && level3 == true && level3Completed){
     lcoverlay.draw(g);
     }else if(easy == true && level4 == true && level4Completed){
     lcoverlay.draw(g);
     }else if(easy == true && level5 == true && level5Completed){
     lcoverlay.draw(g);
     }else if(hard == true && hlevel1 == true && hlevelCompleted){
         lcoverlay.draw(g);
     }else if(hard == true && hlevel2 == true && hlevel2Completed){
         lcoverlay.draw(g);
     }else if(hard == true && hlevel3 == true && hlevel3Completed){
         lcoverlay.draw(g);
     }else if(hard == true && hlevel4 == true && hlevel4Completed){
         lcoverlay.draw(g);
     }else if(hard == true && hlevel5 == true && hlevel5Completed){
         lcoverlay.draw(g);
     }
     else if(expert == true && exlevel1 == true && exlevelCompleted){
         lcoverlay.draw(g);
     }else if(expert == true && exlevel2 == true && exlevel2Completed){
         lcoverlay.draw(g);
     }else if(expert == true && exlevel3 == true && exlevel3Completed){
         lcoverlay.draw(g);
     }else if(expert == true && exlevel4 == true && exlevel4Completed){
         lcoverlay.draw(g);
     }else if(expert == true && exlevel5 == true && exlevel5Completed){
         lcoverlay.draw(g);
     }
    }

  
    @Override
    public void mousePressed(MouseEvent me) {
    if(!gameOver){
        playpause.mousePressed(me);
        if(power == 3){
        if(IsIn(me,powerup)){
            powerup.setMousePressed(true);
        }
        }
        if(easy == true){
        if(level1 == true){
        levelone.mousePressed(me);
        }else if(level2 == true){
        leveltwo.mousePressed(me);
        }else if(level3 == true){
        levelthree.mousePressed(me);
        }else if(level4 == true){
        levelfour.mousePressed(me);
        }else if(level5 == true){
        levelfive.mousePressed(me);
        }
        }else if(hard == true){
            if(hlevel1 == true){
                hlevelone.mousePressed(me);
            }else if(hlevel2 == true){
                hleveltwo.mousePressed(me);
            }else if(hlevel3 == true){
                hlevelthree.mousePressed(me);
            }else if(hlevel4 == true){
                hlevelfour.mousePressed(me);
            }else if(hlevel5 == true){
                hlevelfive.mousePressed(me);
            }
        }
        else if(expert == true){
            if(exlevel1 == true){
                exlevelone.mousePressed(me);
            }else if(exlevel2 == true){
                exleveltwo.mousePressed(me);
            }else if(exlevel3 == true){
                exlevelthree.mousePressed(me);
            }else if(exlevel4 == true){
                exlevelfour.mousePressed(me);
            }else if(exlevel5 == true){
                exlevelfive.mousePressed(me);
            }
        }
         if(paused){
           pauseoverlay.mousePressed(me);
         }else if(levelCompleted){
             lcoverlay.mousePressed(me);
         }else if(level2Completed){
             lcoverlay.mousePressed(me);
         }else if(level3Completed){
             lcoverlay.mousePressed(me);
         }else if(level4Completed){
             lcoverlay.mousePressed(me);
         }else if(level5Completed){
             lcoverlay.mousePressed(me);
         }else if(hlevelCompleted){
             lcoverlay.mousePressed(me);
         }else if(hlevel2Completed){
             lcoverlay.mousePressed(me);
         }else if(hlevel3Completed){
             lcoverlay.mousePressed(me);
         }else if(hlevel4Completed){
             lcoverlay.mousePressed(me);
         }else if(hlevel5Completed){
             lcoverlay.mousePressed(me);
         }
         else if(exlevelCompleted){
             lcoverlay.mousePressed(me);
         } else if(exlevel2Completed){
             lcoverlay.mousePressed(me);
         } else if(exlevel3Completed){
             lcoverlay.mousePressed(me);
         } else if(exlevel4Completed){
             lcoverlay.mousePressed(me);
         } else if(exlevel5Completed){
             lcoverlay.mousePressed(me);
         }
    }
   
        
    }
    

    @Override
    public void mouseReleased(MouseEvent me) { 
       
    if(!gameOver){   
        playpause.mouseReleased(me);
        if(paused ==false && lvlc == false){
        if(power == 3){
        if(IsIn(me,powerup)){
           if(powerup.isMousePressed()){
               game.getAudioPlayer().answerCorrect();
               sharkb = 1;
               if(rindex == 0){
               player.changewaterypos(50);
               player.changewateryheight(-50);
               pindex = 2;
               power = 0;
               }else if(rindex == 1){
               power2();
               pindex = 2;
               power = 0;
               }else if(rindex == 2){
               sharkb = 0;
               pindex = 2;
               power = 0;
               }
               count = 1;
               drawPower = true;
           }
        }    
        }
        }
        powerup.resetBools();
        if(paused == false){
        if(easy == true){
        if(level1 == true){
         levelone.mouseReleased(me);
        }else if(level2 == true){
        leveltwo.mouseReleased(me);
        }else if(level3 == true){
        levelthree.mouseReleased(me);
        }else if(level4 == true){
        levelfour.mouseReleased(me);
        }else if(level5 == true){
        levelfive.mouseReleased(me);
        }
        }else if(hard == true){
            if(hlevel1 == true){
                hlevelone.mouseReleased(me);
            }else if(hlevel2 == true){
                hleveltwo.mouseReleased(me);
            }else if(hlevel3 == true){
                hlevelthree.mouseReleased(me);
            }else if(hlevel4 == true){
                hlevelfour.mouseReleased(me);
            }else if(hlevel5 == true){
                hlevelfive.mouseReleased(me);
            }
        }
        else if(expert == true){
            if(exlevel1 == true){
                exlevelone.mouseReleased(me);
            }else if(exlevel2 == true){
                exleveltwo.mouseReleased(me);
            }else if(exlevel3 == true){
                exlevelthree.mouseReleased(me);
            }else if(exlevel4 == true){
                exlevelfour.mouseReleased(me);
            }else if(exlevel5 == true){
                exlevelfive.mouseReleased(me);
            }
        }
        }
        
         
          if(paused){
        pauseoverlay.mouseReleased(me);
           } 
         else if(levelCompleted){
             lcoverlay.mouseReleased(me);
             }else if(level2Completed){
             lcoverlay.mouseReleased(me);
             }else if(level3Completed){
             lcoverlay.mouseReleased(me);
             }else if(level4Completed){
             lcoverlay.mouseReleased(me);
             }else if(level5Completed){
             lcoverlay.mouseReleased(me);
             }else if(hlevelCompleted){
             lcoverlay.mouseReleased(me);
             }else if(hlevel2Completed){
             lcoverlay.mouseReleased(me);
             }else if(hlevel3Completed){
             lcoverlay.mouseReleased(me);
             }else if(hlevel4Completed){
             lcoverlay.mouseReleased(me);
             }else if(hlevel5Completed){
             lcoverlay.mouseReleased(me);
             }else if(exlevelCompleted){
             lcoverlay.mouseReleased(me);
             }else if(exlevel2Completed){
             lcoverlay.mouseReleased(me);
             }else if(exlevel3Completed){
             lcoverlay.mouseReleased(me);
             }else if(exlevel4Completed){
             lcoverlay.mouseReleased(me);
             }else if(exlevel5Completed){
             lcoverlay.mouseReleased(me);
             }
    }
    
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
    if(!gameOver){
        playpause.mouseMoved(me);
        powerup.setMouseOver(false);
        if(IsIn(me,powerup)){
            powerup.setMouseOver(true);
        }
        if(easy == true){
     if(level1 == true){
        levelone.mouseMoved(me);
        }else if(level2 == true){
        leveltwo.mouseMoved(me);
        }else if(level3 == true){
        levelthree.mouseMoved(me);
        }else if(level4 == true){
        levelfour.mouseMoved(me);
        }else if(level5 == true){
        levelfive.mouseMoved(me);
        }
        }else if(hard == true){
            if(hlevel1 == true){
                hlevelone.mouseMoved(me);
            }if(hlevel2 == true){
                hleveltwo.mouseMoved(me);
            }if(hlevel3 == true){
                hlevelthree.mouseMoved(me);
            }if(hlevel4 == true){
                hlevelfour.mouseMoved(me);
            }if(hlevel5 == true){
                hlevelfive.mouseMoved(me);
            }
        }
        else if(expert == true){
            if(exlevel1 == true){
                exlevelone.mouseMoved(me);
            }else if(exlevel2 == true){
                exleveltwo.mouseMoved(me);
            }else if(exlevel3 == true){
                exlevelthree.mouseMoved(me);
            }else if(exlevel4 == true){
                exlevelfour.mouseMoved(me);
            }else if(exlevel5 == true){
                exlevelfive.mouseMoved(me);
            }
        }
          if(paused){
         pauseoverlay.mouseMoved(me);
         } else if(levelCompleted){
             lcoverlay.mouseMoved(me);
         }else if(level2Completed){
             lcoverlay.mouseMoved(me);
         }else if(level3Completed){
             lcoverlay.mouseMoved(me);
         }else if(level4Completed){
             lcoverlay.mouseMoved(me);
         }else if(level5Completed){
             lcoverlay.mouseMoved(me);
         }else if(hlevelCompleted){
             lcoverlay.mouseMoved(me);
         }else if(hlevel2Completed){
             lcoverlay.mouseMoved(me);
         }else if(hlevel3Completed){
             lcoverlay.mouseMoved(me);
         }else if(hlevel4Completed){
             lcoverlay.mouseMoved(me);
         }else if(hlevel5Completed){
             lcoverlay.mouseMoved(me);
         }else if(exlevelCompleted){
             lcoverlay.mouseMoved(me);
         }else if(exlevel2Completed){
             lcoverlay.mouseMoved(me);
         }else if(exlevel3Completed){
             lcoverlay.mouseMoved(me);
         }else if(exlevel4Completed){
             lcoverlay.mouseMoved(me);
         }else if(exlevel5Completed){
             lcoverlay.mouseMoved(me);
         }
    }else if(gameOver == true){
        gameoverlay.mousePressed(me);
    }
    }
    
    public void unpauseGame(){
        paused = false;
    }
    
   
    @Override
    public void keyPressed(KeyEvent ke) {
        //gameoverlay.keyPressed(ke);
     switch(ke.getExtendedKeyCode()){
            case KeyEvent.VK_UP:
                player.changeyheight(50);
                player.changeypos(-50);
                break;
            case KeyEvent.VK_DOWN:
                player.changeyheight(-50);
                player.changeypos(50);
                break;
            case KeyEvent.VK_W:
                player.changewateryheight(50);
                player.changewaterypos(-50);
            break;
            case KeyEvent.VK_S:
                player.changewateryheight(-50);
                player.changewaterypos(50);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
                break;
            case KeyEvent.VK_ENTER:
                paused = !paused;
                break;
            case KeyEvent.VK_G:
                gameOver = !gameOver;
                break;
            case KeyEvent.VK_E:
                resetAll();
                Gamestate.state = Gamestate.MENU;
             
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
    
    private boolean IsIn(MouseEvent me, PauseButton pb){
        return pb.getBounds().contains(me.getX(),me.getY());
           
    }
    
    public Player getPlayer(){
       
        return player;
    }
    
    public void resetAll(){
        gameOver = false;
        paused = false;
        lvlc=false;
        currentHp1 = maxHp/3;
        levelCompleted = false;
        level2Completed = false;
        level3Completed = false;
        level4Completed = false;
        level5Completed = false;
        hlevelCompleted = false;
        hlevel2Completed = false;
        hlevel3Completed = false;
        hlevel4Completed = false;
        hlevel5Completed = false;
         exlevelCompleted = false;
         exlevel2Completed = false;
         exlevel3Completed = false;
         exlevel4Completed = false;
         exlevel5Completed = false;
        easy =  false;
        hard = false;
        expert = false;
        drawPower = false;
        rindex = 0;
        pindex = 2;
        power = 0;
        countdown = 5;
        count = 1;
        sharkb = 1;
        player.resetAll();
        enemymanager.resetAll();
        levelone.resetAll();
        leveltwo.resetAll();
        levelthree.resetAll();
        levelfour.resetAll();
        levelfive.resetAll();
        hlevelone.resetAll();
        hleveltwo.resetAll();
        hlevelthree.resetAll();
        hlevelfour.resetAll();
        hlevelfive.resetAll();
          exlevelone.resetAll();
           exleveltwo.resetAll();
            exlevelthree.resetAll();
            exlevelfour.resetAll();
            exlevelfive.resetAll();
        bgentity.resetAll();
        rindex= 2;
    
    }
    
    public void setGameOver(boolean gameOver){
        if(easy == true){
            getdiff = "Easy";
            if(level1 == true){
                getlvl = "1";
                getscore = LevelOne.score;
            }else if(level2 == true){
                getlvl = "2";
                getscore = LevelTwo.score;
            }else if(level3 == true){
                getlvl = "3";
                getscore = LevelThree.score;
            }else if(level4 == true){
                getlvl = "4";
                getscore = LevelFour.score;
            }else if(level5 == true){
                getlvl = "5";
                getscore = LevelFive.score;
            }
        }else if(hard == true){
            game.removeEnumField();
            getdiff = "Hard";
            if(hlevel1 == true){
                getlvl = "1";
                getscore = HardLevelOne.score;
            }else if(hlevel2 == true){
                getlvl = "2";
                getscore = HardLevelTwo.score;
            }else if(hlevel3 == true){
                getlvl = "3";
                getscore = HardLevelThree.score;
            }else if(hlevel4 == true){
                getlvl = "4";
                getscore = HardLevelFour.score;
            }else if(hlevel5 == true){
                getlvl = "5";
                getscore = HardLevelFive.score;
            }   
        }else if(expert == true){
            getdiff = "Expert";
            if(exlevel1 == true){
                getlvl = "1";
                getscore = Expertlevelone.score;
            }else if(exlevel2 == true){
                getlvl = "2";
                getscore = Expertleveltwo.score;
            }else if(exlevel3 == true){
                getlvl = "3";
                getscore = Expertlevelthree.score;
            }else if(exlevel4 == true){
                getlvl = "4";
                getscore = Expertlevelfour.score;
            }else if(exlevel5 == true){
                getlvl = "5";
                getscore = Expertlevelfive.score;
            }
        }
        this.gameOver = gameOver;   
        scorechdata(getlvl,getdiff);
           if(LevelOne.score > cscore){
           scoredata(getscore,getlvl,getdiff);
           tscoredata(getdiff);
        }
          
    }

     public void drawUI(Graphics g){
        g.drawImage(hpbar, hpbarXpos, hpbarYpos, hpbarWidth, hpbarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthpos,healthbarYpos+hpbarYpos,healthWidth+93,healthbarHeight);
        }          
        
       public void sharkAttack(){
           if(sharkb == 1){
           //player xpos = 495 - 75 because of emanager
           if(EnemyManager.xpos >=420 && EnemyManager.xpos <= 490){
              // System.out.println("xok");
               if(EnemyManager.hy <= Player.ypp){
                   System.out.println("yok");
                   currentHp1 = 0;
               }
               
           }
           }
       }
               
                 
       public void updateHealthBar(){
        healthWidth =(int)((currentHp1/(float)maxHp) * healthbarWidth);
      
    }
    
        public void changeHealth(int value){
        
        currentHp1 += value;
         if(currentHp1 <= 0){
             currentHp1 = 0;
             //gameOver();
         }else if (currentHp1 >= maxHp){
             currentHp1 = maxHp;
             
         }
     }
        
    public void levelComplete(){
        if(LevelOne.next6==true){
            lvlc = true;
           levelCompleted = true;
           e1c = true;
           scorechdata("1","Easy");
           if(LevelOne.score > cscore){
           scoredata(LevelOne.score,"1","Easy");
           tscoredata("Easy");
           }
        }
    }
    public void level2Complete(){
        if(LevelTwo.next6==true){
            lvlc = true;
            levelCompleted = false;
            level2Completed = true;
            e2c = true;
            scorechdata("2","Easy");
            if(LevelTwo.score > cscore){
            scoredata(LevelTwo.score,"2","Easy");
            tscoredata("Easy");
            }
        }
    }
    public void level3Complete(){
        if(LevelThree.next6==true){
            lvlc = true;
            level2Completed = false;
            level3Completed = true;
            e3c = true;
            scorechdata("3","Easy");
            if(LevelThree.score > cscore){
            scoredata(LevelOne.score,"3","Easy");
            tscoredata("Easy");
            }
        }
    }
    public void level4Complete(){
        if(LevelFour.next6==true){
            lvlc = true;
            level3Completed = false;
            level4Completed = true;
            e4c = true;
            scorechdata("4","Easy");
           if(LevelFour.score > cscore){
           scoredata(LevelFour.score,"4","Easy");
           tscoredata("Easy");
           }
        }
    }
    public void level5Complete(){
        if(LevelFive.next6==true){
            lvlc = true;
            level4Completed = false;
            level5Completed = true;
            e5c = true;
            scorechdata("5","Easy");
           if(LevelFive.score > cscore){
           scoredata(LevelFive.score,"5","Easy");
           tscoredata("Easy");
           }       
        }
    }
    public void hlevelComplete(){
        if(HardLevelOne.next6==true){
            lvlc = true;
            hlevelCompleted = true;
            h1c = true;
            scorechdata("1","Hard");
           if(HardLevelOne.score > cscore){
           scoredata(HardLevelOne.score,"1","Hard");
           tscoredata("Hard");
           }
        }
    }
    public void hlevel2Complete(){
        if(HardLevelTwo.next6==true){
            lvlc = true;
            hlevelCompleted = false;
            hlevel2Completed = true;
            h2c = true;
            scorechdata("2","Hard");
           if(HardLevelTwo.score > cscore){
           scoredata(HardLevelTwo.score,"2","Hard");
           tscoredata("Hard");
           }
        }
    }
    public void hlevel3Complete(){
        if(HardLevelThree.next6==true){
            lvlc = true;
            hlevel2Completed = false;
            hlevel3Completed = true;
            h3c = true;
            scorechdata("3","Hard");
           if(HardLevelThree.score > cscore){
           scoredata(HardLevelThree.score,"3","Hard");
           tscoredata("Hard");
           }
        }
    }
    
    public void hlevel4Complete(){
        if(HardLevelFour.next6==true){
            lvlc = true;
            hlevel3Completed = false;
            hlevel4Completed = true;
            h4c = true;
            scorechdata("4","Hard");
           if(HardLevelFour.score > cscore){
           scoredata(HardLevelFour.score,"4","Hard");
           tscoredata("Hard");
           }
        }
    }
    
    public void hlevel5Complete(){
        if(HardLevelFive.next6==true){
            lvlc = true;
            hlevel4Completed = false;
            hlevel5Completed = true;
            h5c = true;
            scorechdata("5","Hard");
           if(HardLevelFive.score > cscore){
           scoredata(HardLevelFive.score,"5","Hard");
           tscoredata("Hard");
           }
        }
    }
      public void exlevelComplete(){
        if(Expertlevelone.next6==true){
            lvlc = true;
            exlevelCompleted = true;
            ex1c = true;
            scorechdata("1","Expert");
           if(Expertlevelone.score > cscore){
           scoredata(Expertlevelone.score,"1","Expert");
           tscoredata("Expert");
           }
        }
    }
       public void exlevel2Complete(){
        if(Expertleveltwo.next6==true){
            lvlc = true;
            exlevelCompleted = false;
            exlevel2Completed = true;
            ex2c = true;
            scorechdata("2","Expert");
           if(Expertleveltwo.score > cscore){
           scoredata(Expertleveltwo.score,"2","Expert");
           tscoredata("Expert");
           }
        }
    }
        public void exlevel3Complete(){
        if(Expertlevelthree.next6==true){
            lvlc = true;
            exlevel2Completed = false;
            exlevel3Completed = true;
            ex3c = true;
            scorechdata("3","Expert");
           if(Expertlevelthree.score > cscore){
           scoredata(Expertlevelthree.score,"3","Expert");
           tscoredata("Expert");
           }
        }
    }
        
        public void exlevel4Complete(){
        if(Expertlevelfour.next6==true){
            lvlc = true;
            exlevel3Completed = false;
            exlevel4Completed = true;
            ex4c = true;
            scorechdata("4","Expert");
           if(Expertlevelfour.score > cscore){
           scoredata(Expertlevelfour.score,"4","Expert");
           tscoredata("Expert");
           }
        }
    }
    public void exlevel5Complete(){
        if(Expertlevelfive.next6==true){
            lvlc = true;
            exlevel4Completed = false;
            exlevel5Completed = true;
            ex5c = true;
            scorechdata("5","Expert");
           if(Expertlevelfive.score > cscore){
           scoredata(Expertlevelfive.score,"5","Expert");
           tscoredata("Expert");
           }
        }
    }
    
    public void scoredata(int sdata,String lvl,String diff){
        try{
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String upScore= "UPDATE "+diff+"score set Level"+lvl+" = "+sdata+" where username='"+LogIn.user+"'";
                       Statement state1 = connect.createStatement();
                       
                       state1.executeUpdate(upScore);
                       state1.close();
                       connect.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void scorechdata(String lvl,String diff){
        try{
                Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String getScore= "Select Level"+lvl+" From "+diff+"Score where username='"+LogIn.user+"'";
                       Statement state1 = connect.createStatement();
                       ResultSet set = null;
                       set = state1.executeQuery(getScore);
                       while(set.next()){
                           cscore = set.getInt("Level"+lvl+"");
                       }
                       state1.close();
                       set.close();
                       connect.close();
           }catch(SQLException e){
               e.printStackTrace();
           }
    }
    
    public void tscoredata(String diff){
        try{
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/ElevatingDB","aaron","aaron");
                       String upScore= "UPDATE "+diff+"score set "+diff+"total = Level1 + Level2 + Level3 + Level4 + Level5 where username='"+LogIn.user+"'";
                       Statement state1 = connect.createStatement();
                       
                       state1.executeUpdate(upScore);
                       state1.close();
                       connect.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void createPowerbutton(int rindex){
         powerup = new PlayPauseButton(15,450,55,55,rindex);
    }
    
    public AudioPlayer getAudioPlayer(){
        return audio;
    }
    
    public void power2(){
        if(easy == true){
            if(level1 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(level2 == true){
                LevelTwo.countdown = LevelTwo.countdown +3;
            }else if(level3 == true){
                LevelThree.countdown = LevelThree.countdown +3;
            }else if(level4 == true){
                LevelFour.countdown = LevelFour.countdown +3;
            }else if(level5 == true){
                LevelFive.countdown = LevelFive.countdown +3;
            }
        }else if(hard == true){
            if(hlevel1 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(hlevel2 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(hlevel3 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(hlevel4 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(hlevel5 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }
        }else if(expert == true){
            if(exlevel1 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(exlevel2 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(exlevel3 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(exlevel4 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }else if(exlevel5 == true){
                LevelOne.countdown = LevelOne.countdown +3;
            }
        }
    }
    
    public void drawPowerText(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Dialog",Font.BOLD,30));
        if(rindex == 0){
        g.drawString("     LOW TIDE!!!",400,200);
        }else if(rindex == 1){
        g.drawString(" TIME ELEVATE!!!",400,200);
        }if(rindex == 2){
        g.drawString("SHARK BEGONE!!!",400,200);
        }
    }
    
    public void QCountdown(){
      if (!timerInitialized) {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Playing.paused == false){
            countdown--;
            if (countdown <= 0 && count == 1) {
                timer.stop();
                countdown = 5;
                count = 2;
                drawPower = false;
            }
           }
        }
    });
    timerInitialized = true;
    }
     if (timer.isRunning() == false) {
            timer.start();
            //System.out.println("ss");
        }
    }
    
    public void gameOverSEffect(){
        getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
    }
    public void lvlCompleteSEffect(){
        getGame().getAudioPlayer().playEffect(AudioPlayer.LVL_COMPLETED);
    }
   
}
