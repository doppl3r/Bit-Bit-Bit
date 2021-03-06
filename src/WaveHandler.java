import javax.swing.*;
import java.awt.*;

public class WaveHandler {
    private String message;
    private int currentWave;
    private int messageX;
    private int messageY;
    private int messageFade;

    public WaveHandler(){
        //testing purposes
        currentWave = 1; //subtract 1 to get level
        currentWave--; //don't make fun of me
    }
    public void startWave(){
        currentWave++; //starts at 1
        setMessage("Level "+currentWave,-1);

        switch(currentWave){
            case(1): //6 enemy1
                Game.enemies.addEnemy(Window.getWidth()/2, -500, new Enemy1());
                Game.enemies.addEnemy(-1, -1000, new Enemy1());
                Game.enemies.addEnemy(-1, -1500, new Enemy1());
                Game.enemies.addEnemy(-1, -2000, new Enemy1());
            break;
            case(2): //8 enemy2
                Game.enemies.addEnemy(100,-500, new Enemy2());
                Game.enemies.addEnemy(380,-750, new Enemy2());
                Game.enemies.addEnemy(100,-1000, new Enemy2());
                Game.enemies.addEnemy(380,-1250, new Enemy2());
            break;
            case(3):
                Game.enemies.addEnemy(-1,-300, new Enemy3());
                Game.enemies.addEnemy(-1,-400, new Enemy3());
                Game.enemies.addEnemy(-1,-500, new Enemy3());
                Game.enemies.addEnemy(-1,-600, new Enemy3());
            break;
            case(4):
                Game.enemies.addEnemy(-1,-500, new Enemy4());
                Game.enemies.addEnemy(-1,-1000, new Enemy4());
                Game.enemies.addEnemy(-1,-1500, new Enemy4());
                Game.enemies.addEnemy(-1,-2000, new Enemy4());
            break;
            case(5):
                for (int i = 0; i < 15; i++){
                    Game.enemies.addEnemy((Window.getWidth()/2), -(i*50), new Enemy5());
                }
                Game.enemies.addEnemy(Window.getWidth()/2,-500,new EnemyBoss1());
            break;
            case(6):
                for (int i = 0; i < 10; i++){
                    Game.enemies.addEnemy((Window.getWidth()/2), -(i*250), new Enemy6());
                }
            break;
            case(7):

            break;
            case(8):

            break;
            case(9):

            break;
            case(10):

            break;
        }
    }
    public void endWave(){
        Window.panel.setPanelState(0);
        Window.panel.editor.setMoney(Game.player.getMoney());
        Game.player.resetFireBullet();
        Game.player.setShieldOn(false);
        Game.bullets.removeAll();
    }
    public void gameOver(){
        endWave();
        currentWave--;
        Window.panel.game.setGameOver(true);
        //reset player gameOver state to end loop
        //Game.player.setHeartPosition("bottom");
        //Game.player.resetPlayerMatrix();
        Window.panel.editor.setMatrix(Game.player.getMatrix());
        //Game.player.resetMoney();
        Game.player.resetHeart();
        Game.player.setShield(0);
        //Game.player.resetCannon();
        Game.enemies.removeAll();
    }
    public void draw(Graphics2D g){
        if (messageFade > 0){
            g.setColor(new Color(255,255,255,messageFade));
            g.drawString(message,messageX,messageY);
        }
    }
    public void update(){
        if (messageFade-2 > 0) messageFade-=2;
        else messageFade = 0;
        messageY--;
    }
    public void setMessage(String message, int x){
        this.message=message;
        if (x == -1) messageX = 210;
        else messageX = x;
        messageY = Window.getHeight()/2;
        messageFade = 255;
    }
}
