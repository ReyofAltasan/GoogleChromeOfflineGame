package gamescreen;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 190; //by pixel
    
    private MainCharacter mainCharacter;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemiesManager enemiesManager;
    private int score;
    
    private int gameState = GAME_FIRST_STATE;
    
    private BufferedImage imageGameOverTex;
    private BufferedImage imageGameOverTex2;
    private AudioClip scoreUpSound;
    
    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(100);
        land = new Land(this);
        clouds = new Clouds();
        enemiesManager = new EnemiesManager(mainCharacter, this);
        imageGameOverTex = Resource.getResourceImage("data/GAMEOVER.png");
        imageGameOverTex2 = Resource.getResourceImage("data/rplay bytton.png");
        try {
            scoreUpSound = Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void startGame(){
        thread.start();
    }
    
    @Override
    public void run() {
    while(true){
        try {
            update();
            repaint();
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    }
    
    public void update(){
        switch(gameState){
            case GAME_PLAY_STATE:
               mainCharacter.update();
               land.update();
               clouds.update();
               enemiesManager.update(); 
               if(!mainCharacter.getAlive()){
                   gameState = GAME_OVER_STATE;
               }
               break;
        }
    }
    
    public void plusScore(int score){
        this.score += score;
        scoreUpSound.play();
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
       // g.setColor(Color.red);
       // g.drawLine(0, (int) GROUNDY, getWidth(), (int) GROUNDY);
        
        switch(gameState){
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawString("HI " + String.valueOf(score), 500, 20);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawImage(imageGameOverTex, 200, 50, null);
                g.drawImage(imageGameOverTex2, 280, 80, null);
                break;
        }
    }
    
    private void resetGame(){
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(100);
        enemiesManager.reset();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == GAME_FIRST_STATE){
                    gameState = GAME_PLAY_STATE;
                }else if(gameState == GAME_PLAY_STATE){
                    mainCharacter.jump();
                }else if(gameState == GAME_OVER_STATE)
                resetGame();
                    gameState = GAME_PLAY_STATE;
                break;
        }
    }

}