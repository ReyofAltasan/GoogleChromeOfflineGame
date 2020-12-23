package gamescreen;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Iron extends Enemy{
    
    private BufferedImage image;
    private int posX, posY;
    private Rectangle rect;
    private MainCharacter mainChracter;
    private boolean isScoreGot = false;
    
    public Iron(MainCharacter mainChracter){
        this.mainChracter = mainChracter;
        image = Resource.getResourceImage("data/1O.png");
        image = Resource.getResourceImage("data/3OO.png");
        posX = 200;
        posY = 150;
        rect = new Rectangle();
        
    }
    
    public void update(){
        posX -= 3;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }
    @Override
    public Rectangle getBound() {
        return rect;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, posX, posY, null);
        
    }
    
    public void setX(int x){
        posX = x;
    }
    
    public void setY(int y){
        posY = y;
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
    }
    
    @Override
    public boolean isOutOfScreen(){
        return (posX + image.getWidth() < 0);
    }
    
    @Override
    public boolean isOver(){
        return (mainChracter.getX() > posX);
    }
    
    @Override
    public boolean isScoreGot(){
        return isScoreGot;
    }
    
    @Override
    public void setIsScoreGot(boolean isScoreGot){
        this.isScoreGot = isScoreGot;
    }
}