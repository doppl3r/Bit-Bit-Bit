import java.awt.*;

public class Game {
    public static Player player;
    public static Bullets bullets;
    public static EnemyHandler enemies;

    public Game(){
        player = new Player();
        bullets = new Bullets();
        enemies = new EnemyHandler();
    }
    public void draw(Graphics2D g){
        player.draw(g);
        bullets.draw(g);
        enemies.draw(g);
    }
    public void update(){
        player.update();
        bullets.update();
        enemies.update();
    }
    public void down(int x1, int y1){
        player.down(x1, y1);
    }
    public void move(int x1, int y1){
        player.move(x1, y1);
    }
    public void up(int x1, int y1){
        player.up(x1, y1);
    }
    public void hover(int x1, int y1){
        player.hover(x1, y1);
    }
    //key pressed
    public void keyUpPressed(){}
    public void keyDownPressed(){}
    public void keyLeftPressed(){ player.moveLeft(true); }
    public void keyRightPressed(){ player.moveRight(true); }
    //key released
    public void keyUpReleased(){}
    public void keyDownReleased(){}
    public void keyLeftReleased(){ player.moveLeft(false); }
    public void keyRightReleased(){ player.moveRight(false); }
}
