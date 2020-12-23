package gamescreen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemiesManager {
    private List<Enemy> enemies;
    private Random random;
    
    private BufferedImage imageIron1, imageIron2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;
    
    public EnemiesManager(MainCharacter mainCharacter, GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        imageIron1 = Resource.getResourceImage("data/1O.png");
        imageIron2 = Resource.getResourceImage("data/3OO.png");
        random = new Random();
        
        enemies.add(getRandomIron());
        
    }
    
    public void update(){
        for(Enemy e : enemies){
            e.update();
            if(e.isOver() && !e.isScoreGot()){
                gameScreen.plusScore(20);
                e.setIsScoreGot(true);
            }
            if(e.getBound().intersects(mainCharacter.getBound())){
                mainCharacter.setAlive(false);
            }
            }
        Enemy firstEnemy = enemies.get(0);
        if(firstEnemy.isOutOfScreen()){
            enemies.remove(firstEnemy);
            enemies.add(getRandomIron());
        }
    }
    
    public void draw(Graphics g){
        for(Enemy e : enemies){
            e.draw(g);
        }
    }
    
    public void reset(){
        enemies.clear();
        enemies.add(getRandomIron());
    }
    
    private Iron getRandomIron(){
        Iron iron;
            iron = new Iron(mainCharacter);
            iron.setX(600);
        if(random.nextBoolean()){
            iron.setImage(imageIron1);
        }else {
            iron.setImage(imageIron2);
        }
        return iron;
    }
}
