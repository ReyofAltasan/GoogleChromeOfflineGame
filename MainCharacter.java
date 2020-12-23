package gamescreen;

import static gamescreen.GameScreen.GRAVITY;
import static gamescreen.GameScreen.GROUNDY;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MainCharacter {
    private float x = 0;
    private float y = 0;
    private float speedY = 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;
    
    public MainCharacter(){
        characterRun = new Animation(100);
        characterRun.addFrame(Resource.getResourceImage("data/1.png"));
        characterRun.addFrame(Resource.getResourceImage("data/2.png"));
        characterRun.addFrame(Resource.getResourceImage("data/3.png"));
        characterRun.addFrame(Resource.getResourceImage("data/4.png"));
        characterRun.addFrame(Resource.getResourceImage("data/5.png"));
        characterRun.addFrame(Resource.getResourceImage("data/6.png"));
        rect = new Rectangle();
    }
    
    public void update(){
        characterRun.update();
        // all thses line code is for jumping
        if(y >= GROUNDY - characterRun.getFrame().getHeight()){
                speedY = 0;
                y = GROUNDY - characterRun.getFrame().getHeight();
            } else {
            speedY+=GRAVITY;
            y+=speedY;
            }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }
    
    public void draw(Graphics g){
        g.setColor(Color.black);
     //   g.drawRect((int) x, (int) y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
    }
    
    public Rectangle getBound(){
        return rect;
    }
    
    public void jump(){
        speedY = -4;
        y += speedY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
    public void setAlive(boolean alive){
        isAlive = alive;
    }
    
    public boolean getAlive(){
        return isAlive;
    }
    
}
