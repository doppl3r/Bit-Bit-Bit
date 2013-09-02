public class Player extends Ship {
    private boolean left;
    private boolean right;
    private boolean fireBullet;
    private double speed;

    public Player(){
        speed = 10.0;
        setBulletSize(8);
        setBulletSpeed(-25); //direction speed
        setMaxFireTime(5);
        //rebuildMatrix(9,9);
        int[][] mat = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,0,0,0},
            {0,0,0,1,0,1,0,0,0},
            {0,0,1,1,1,1,1,0,0},
        };
        setMatrix(mat);
        setHeartPosition("bottom");
        setXY(Window.getWidth() / 2, Window.getHeight() - getWidth());
        //initMatrix();
    }
    public void down(int x1, int y1){ //use 'x1' to avoid interference with 'x'
        fireBullet(true, true);
    }
    public void move(int x1, int y1){ //drag
        setX(x1); //lock y axis
    }
    public void up(int x1, int y1){
        fireBullet(false, true);
    }
    public void hover(int x1, int y1){
        setX(x1); //lock y axis
    }
    public void update(){
        //update the player x position
        int direction = 0;
        double tempX = getX();
        //check direction
        if (left) direction = -1;
        else if (right) direction = 1;
        //keep in bounds
        if (tempX+(speed*direction) < 0) tempX = 0;
        else if (tempX+(speed*direction) > Window.getWidth()) tempX = Window.getWidth();
        else tempX += (speed*direction);
        //adjust X
        if (direction != 0) setX(tempX);
        //update fireBullet time
        if (fireBullet){
            if (getFireTime() > 0) setFireTime(getFireTime()-1);
            else {
                resetFireTime();
                Game.bullets.addBullet(getX(),getY()+3*getPixelSize(),
                    0,getBulletSpeed(),getBulletSize(),false);
            }
        }
    }
    public void fireBullet(boolean fire, boolean force){
        fireBullet = fire;
        if (force) setFireTime(0);
    }
    public void moveLeft(boolean left){ this.left=left; }
    public void moveRight(boolean right){ this.right=right; }
}
