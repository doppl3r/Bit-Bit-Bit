
public class Enemy extends Ship{
    private double speedX;
    private double speedY;
    private boolean guided;
    private PathNodes pathNodes;

    public Enemy(){
        pathNodes = new PathNodes(); //let your enemy type add nodes per A.I. request
    }
    public void update(){
        //move around
        if (Math.abs(getX() - pathNodes.getCurrentX()) < speedX &&
            Math.abs(getY() - pathNodes.getCurrentY()) < speedY){
            //go to next node
            pathNodes.setNextNode();
        }
        else {
            //move left or right
            if (getY() >= 0){ //only move left or right if in the window
                if (getX() > pathNodes.getCurrentX()) setX(getX()-speedX);
                if (getX() < pathNodes.getCurrentX()) setX(getX()+speedX);
            }
            //move up or down
            if (getY() > pathNodes.getCurrentY()) setY(getY()-speedY);
            if (getY() < pathNodes.getCurrentY()) setY(getY()+speedY);
        }
        //fire bullet
        if (getFireTime() > 0) tickFireTime();
        else{
            resetFireTime();
            fireBullets();
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
                            getBulletXSpeed(),
                            getBulletYSpeed(),
                            getBulletSize(),true,isGuided());
                }
            }
        }
        //fire from heart
        Game.bullets.addBullet(getX()+(getHeartX()*getBulletSize())-(getWidth()/2)+(getBulletSize()/2), //x
                getY()+3*getPixelSize()+(getHeartY()*getBulletSize())-(getHeight()/2),
                getBulletXSpeed(),getBulletYSpeed(),getBulletSize(),true,isGuided());
    }
    //setters
    public void setSpeedX(double speedX){ this.speedX=speedX; }
    public void setSpeedY(double speedY){ this.speedY=speedY; }
    public void setGuided(boolean guided){ this.guided=guided; }

    //getters
    public double getSpeedX(){ return speedX; }
    public double getSpeedY(){ return speedY; }
    public PathNodes getPathNodes(){ return pathNodes; }
    public boolean isGuided(){ return guided; }
}
