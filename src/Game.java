import java.awt.*;

public class Game {
    public static Player player;
    public static Bullets bullets;
    public static EnemyHandler enemies;
    public static WaveHandler waveHandler;
    private boolean gameOver;

    public Game(){
        player = new Player();
        bullets = new Bullets();
        enemies = new EnemyHandler();
        waveHandler = new WaveHandler();
    }
    public void draw(Graphics2D g){
        player.draw(g);
        bullets.draw(g);
        enemies.draw(g);
        waveHandler.draw(g);
    }
    public void update(){
        player.update();
        bullets.update();
        enemies.update();
        waveHandler.update();
    }
    public void down(int x1, int y1, boolean left){
        player.down(x1,y1,left);
    }
    public void move(int x1, int y1){
        player.move(x1, y1);
    }
    public void up(int x1, int y1, boolean left){
        player.up(x1,y1,left);
    }
    public void hover(int x1, int y1){
        player.hover(x1,y1);
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

    //setGameOver
    public void setGameOver(boolean gameOver){ this.gameOver=gameOver; }
    public boolean isGameOver(){ return gameOver; }
}
