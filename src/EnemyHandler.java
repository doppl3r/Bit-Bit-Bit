import audio.AudioHandler;

import java.awt.*;
import java.util.LinkedList;

public class EnemyHandler {
    private LinkedList<Enemy> enemies;

    public EnemyHandler(){
        enemies = new LinkedList<Enemy>();
        addEnemy(Window.getWidth()/2,-3500, new Enemy1());
        addEnemy(Window.getWidth()/2,-3000, new Enemy1());
        addEnemy(Window.getWidth()/2,-2500, new Enemy1());
        addEnemy(Window.getWidth()/2,-2000, new Enemy1());
        addEnemy(Window.getWidth()/2,-1500, new Enemy1());
        addEnemy(Window.getWidth()/2,-1000, new Enemy1());
        addEnemy(Window.getWidth()/2,-500, new Enemy1());
        addEnemy(Window.getWidth()/2,0, new Enemy1());
    }
    public void draw(Graphics2D g){
        int size = enemies.size();
        for (int i = 0; i < size; i++){
            enemies.get(i).draw(g);
        }
    }
    public void update(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
            if (enemies.get(i).isDead()){
                enemies.remove(i);
                AudioHandler.EXPLODE.play();
            }
        }
    }
    public void addEnemy(double x, double y, Enemy enemy){
        int index = enemies.size();
        enemies.add(enemy);
        enemies.get(index).setXY(x,y);
    }
    public Enemy getEnemyAt(int i){ return enemies.get(i); }
    public int size(){ return enemies.size(); }
}
