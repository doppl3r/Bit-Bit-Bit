import audio.AudioHandler;

public class Player extends Ship {
    private boolean left;
    private boolean right;
    private boolean fireBullet;
    private double speed;

    public Player(){
        speed = 5.0;
        setBulletSize(8);
        setBulletXSpeed(0);
        setBulletYSpeed(-30); //direction speed
        setMaxFireTime(5);
        //rebuildMatrix(9,9);
        resetPlayerMatrix();
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
        if (!isDead()){
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
                    AudioHandler.SHOOT2.play();
                    fireBullets();
                    /*Game.bullets.addBullet(getX(),getY()+3*getPixelSize(),
                        getBulletXSpeed(),getBulletYSpeed(),getBulletSize(),false,false);*/
                }
            }
        }
        else{ //kill the player
            Game.waveHandler.gameOver();
        }
    }
    public void fireBullet(boolean fire, boolean force){
        fireBullet = fire;
        if (force){
            setFireTime(0);
        }
    }
    public void fireBullets(){
        int cannonCount = 0;
        //fire from cannon nodes (3=cannon)
        for (int row = 0; row < getRows(); row++){
            for (int col = 0; col < getCols(); col++){
                if (getMatrix()[row][col] == 3){ //3 = cannon
                    cannonCount++;
                    Game.bullets.addBullet(getX()+(col*getBulletSize())-(getWidth()/2)+(getBulletSize()/2), //x
                        getY()+3*getPixelSize()+(row*getBulletSize())-(getHeight()/2),   //y
                        getBulletXSpeed()+(col)-(getCols()/2),
                        getBulletYSpeed(),
                        getBulletSize(),false,false);
                }
            }
        }
        //fire from heart
        Game.bullets.addBullet(getX()+(getHeartX()*getBulletSize())-(getWidth()/2)+(getBulletSize()/2), //x
                getY()+3*getPixelSize()+(getHeartY()*getBulletSize())-(getHeight()/2),
            getBulletXSpeed(),getBulletYSpeed(),getBulletSize(),false,false);
    }
    public void moveLeft(boolean left){ this.left=left; }
    public void moveRight(boolean right){ this.right=right; }
    public void resetFireBullet(){
        fireBullet = false;
        resetFireTime();
    }
    public void resetPlayerMatrix(){
        int[][] mat = {
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,1,0,0,0},
                {0,0,0,1,0,1,0,0,0},
                {0,0,1,1,2,1,1,0,0},
        };
        setMatrix(mat);
        setHeartPosition("bottom");
        setXY(Window.getWidth() / 2, Window.getHeight() - getWidth());
    }
    public boolean isFiring(){ return fireBullet; }
    public void resetCannon(){ setMatrixValue(getHeartX(),getHeartY()-1,3); }
}
