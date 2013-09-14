import audio.AudioHandler;

import java.awt.*;
import java.util.LinkedList;

public class EnemyHandler {
    private LinkedList<Enemy> enemies;

    public EnemyHandler(){
        enemies = new LinkedList<Enemy>();
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
                Window.panel.particles.addClusterAt(enemies.get(i).getX(),
                    enemies.get(i).getY(),255,0,0,50);
                enemies.remove(i);
                AudioHandler.EXPLODE.play();
                if (enemies.size()==0){
                    Game.waveHandler.endWave();
                }
            }
        }
    }
    public void addEnemy(double x, double y, Enemy enemy){
        int index = enemies.size();
        if (x == -1){ //randomize the x value
            x = (int)(Math.random()*Window.getWidth());
        }
        enemies.add(enemy);
        enemies.get(index).setXY(x,y);
    }
    public void removeAll(){
        int size = size();
        for (int i = 0; i < size; i++){
            enemies.remove(0);
        }
    }
    public Enemy getEnemyAt(int i){ return enemies.get(i); }
    public int size(){ return enemies.size(); }
}
