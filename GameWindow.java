package gamescreen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    
    private GameScreen gameScreen;
    
    
    public GameWindow (){
        super("07 Game");
        setSize(600, 280);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }
    
    public void startGame(){
       gameScreen.startGame();
    }
    
    public static void main (String args[]){
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}