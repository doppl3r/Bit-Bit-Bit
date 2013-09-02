import java.awt.*;
import java.util.LinkedList;

public class Bullets {
    private LinkedList<Bullet> bullets;
    public Bullets(){
        bullets = new LinkedList<Bullet>();
    }
    public void draw(Graphics2D g){
        int size = bullets.size();
        for (int i = 0; i < size; i++){
            bullets.get(i).draw(g);
        }
    }
    public void update(){
        //int size = bullets.size();
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).update();
            if (!bullets.get(i).isActive()) bullets.remove(i);
        }
    }
    public void addBullet(double x, double y, double speedX, double speedY, int size, boolean hostile, boolean guided){
        bullets.add(new Bullet(x,y,speedX,speedY,size,hostile, guided));
    }
    public void removeAll(){
        while (bullets.size()>0) bullets.remove(0);
    }

    public class Bullet {
        private double x;
        private double y;
        private double speedX; //set negative to go the other direction
        private double speedY;
        private int size;
        private boolean hostile; //true = hurt player
        private boolean active; //false = remove from the list
        private boolean guided;

        public Bullet(double x, double y, double speedX, double speedY, int size, boolean hostile, boolean guided){
            this.x=x;
            this.y=y;
            this.speedX =speedX;
            this.speedY =speedY;
            this.size=size;
            this.hostile=hostile;
            this.guided=guided;
            active = true;
        }
        public void draw(Graphics2D g){
            g.setColor(new Color(255,255,255)); //color it white
            g.fillRect((int)(x-(size/2)), (int)(y-(size/2)), size, size); //center the bullet
            g.setColor(new Color(100,100,100));
            g.fillRect((int)(x-(size/2)), (int)(y-(size/2))+size, size, size/2); //center the bullet
        }
        public void update(){
            if (x+(size/2) > 0 && x-(size/2) < Window.getWidth() && //update the bullet if it's still in the window
                y+(size/2) > 0 && y-(size/2) < Window.getHeight()){
                //move bullet in the speed direction. ex: x = x + (-2); new x = x minus 2
                x += speedX;
                y += speedY;
                //check guided bullets
                if (guided){
                    if (Math.abs(x-Game.player.getX()) > (size/2)){
                        if (x > Game.player.getX()) x-=2;
                        else x+=2;
                    }
                }
                //check collision with player
                if (hostile){
                    if (Math.abs(x - Game.player.getX()) < Game.player.getWidth()/2 &&
                        Math.abs(y - Game.player.getY()) < Game.player.getHeight()/2){
                        if (Game.player.isVisible(x,y)){
                            Game.player.hurt(x,y);
                            active = false;
                        }
                    }
                }
                //check collision with enemies
                else {
                    for (int i = 0; i < Game.enemies.size(); i++){ //check every enemy
                        for (double tempY = Math.abs(speedY); tempY > 0;
                        tempY -= Game.enemies.getEnemyAt(i).getPixelSize()/2){ //fixes pixel skipping
                            //System.out.println(tempY);
                            if (Math.abs(x - (Game.enemies.getEnemyAt(i).getX())) <
                                Game.enemies.getEnemyAt(i).getWidth()/2 &&
                                Math.abs(y - (Game.enemies.getEnemyAt(i).getY()) + tempY) <
                                Game.enemies.getEnemyAt(i).getHeight()/2){
                                //damage the enemy
                                if (Game.enemies.getEnemyAt(i).isVisible(x,y+tempY)){
                                    Game.player.addMoney(50);
                                    Game.enemies.getEnemyAt(i).hurt(x,y+tempY);
                                    active = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else active = false;
        }
        //getters
        public double getX(){ return x; }
        public double getY(){ return y; }
        public double getSpeedX(){ return speedX; }
        public double getSpeedY(){ return speedY; }
        public int getSize(){ return size; }
        public boolean isHostile(){ return hostile; }
        public boolean isActive(){ return active; }
    }
}
