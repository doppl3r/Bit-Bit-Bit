
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
            Game.bullets.addBullet(getX(),getY()+3*getPixelSize(),
                getBulletXSpeed(),getBulletYSpeed(),getBulletSize(),true,isGuided());
        }
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
