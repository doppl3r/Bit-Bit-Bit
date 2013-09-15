import audio.AudioHandler;
import sun.audio.AudioPlayer;

import java.awt.*;
import java.util.LinkedList;

public class Ship {
    private double x;
    private double y;
    private double bulletXSpeed;
    private double bulletYSpeed;
    private int heartX; //based off cols
    private int heartY; //based off rows
    private int rows;
    private int cols;
    private int pixelSize;
    private int[][] matrix;
    private int bulletSize;
    private int fireTime;
    private int maxFireTime;
    private int money;
    private int startMoney;
    private int wallCost;
    private int cannonCost;
    private int selectX;
    private int selectY;
    private boolean showGrid;

    public Ship(){
        //default ship
        pixelSize = 8;
        money = startMoney = 5000;
        wallCost = 1000;
        cannonCost = 7500;
        rebuildMatrix(13,13);
        selectX = -1;
        selectY = -1;
    }
    public void draw(Graphics2D g){
        if (!Window.panel.game.isGameOver()){ //hide ship if it's dead
            for (int row = 0; row < rows; row++){
                for (int col = 0; col < cols; col++){

                    //set color
                    switch(matrix[row][col]){
                        case(1): g.setColor(new Color(255,255,255)); break;
                        case(2): g.setColor(new Color(255,  0,  0)); break;
                        case(3): g.setColor(new Color(  0,  0,255)); break;
                    }
                    //draw ship (centered)
                    if (matrix[row][col] > 0){
                        g.fillRect((int)((col*pixelSize)+x-((cols*pixelSize)/2)+1), //the (+1) is the spacing
                            (int)((row*pixelSize)+y-((rows*pixelSize)/2)+1),
                            pixelSize-2, //the (-2) is also the spacing
                            pixelSize-2);
                        //set color shadow
                        switch(matrix[row][col]){
                            case(1): g.setColor(new Color(100,100,100)); break;
                            case(2): g.setColor(new Color(150,  0,  0));break;
                            case(3): g.setColor(new Color(  0,  0,180));break;
                        }
                        g.fillRect((int)((col*pixelSize)+x-((cols*pixelSize)/2)+1), //the (+1) is the spacing
                            (int)((row*pixelSize)+y-((rows*pixelSize)/2)+pixelSize-1),
                            pixelSize-2, //the (-2) is also the spacing
                            pixelSize/2);
                    }
                }
            }
            //show grid
            if (showGrid){
                for (int row = 0; row < rows; row++){
                    for (int col = 0; col < cols; col++){
                        g.setColor(new Color(75,75,75));
                        g.drawRect((col*pixelSize)+(int)(x-(getWidth()/2))+1,
                                (row*pixelSize)+(int)(y-(getHeight()/2))+1,
                                pixelSize-3,pixelSize-3);
                    }
                }
                //draw selector
                if (selectX >= 0 && selectY >= 0){
                    g.setColor(new Color(255,0,0));
                    g.drawRect((selectX*pixelSize)+(int)(x-(getWidth()/2))+2,
                            (selectY*pixelSize)+(int)(y-(getHeight()/2))+2,
                            pixelSize-5,pixelSize-5);
                }
            }
        }
    }
    public void update(){

    }
    public void initMatrix(){
        //0 = empty pixel
        //1 = basic pixel
        //2 = heart pixel
        matrix = new int[rows][cols];
        //set up an empty matrix
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                matrix[row][col] = 1;
            }
        }
        //set the heart position
        matrix[heartY][heartX] = 2;
    }
    public void rebuildMatrix(int rows, int cols){
        //try to keep the rows and columns are odd for so you can center the heart better
        this.rows=rows;
        this.cols=cols;
        matrix = new int[rows][cols];
    }
    public void setMatrixValue(int col, int row, int value){
        if (col < 0) col = 0;
        else if (col > cols-1) col = cols-1;
        if (row < 0) row = 0;
        else if (row > rows-1) row = rows-1;
        matrix[row][col] = value;
    }
    public void setMatrix(int[][] mat){
        rows = mat.length;
        cols = mat[0].length;
        matrix = new int[rows][cols];
        matrix = mat;
        //outputString();
    }
    public void setHeartPosition(String position){
        if (position.equals("top")){
            heartX = cols/2;
            heartY = 0;
        }
        else if (position.equals("bottom")){ //bottom
            heartX = cols/2;
            heartY = rows - 1;
        }
        matrix[heartY][heartX] = 2;
    }
    public void resetHeart(){
        matrix[heartY][heartX] = 2;
    }
    public void outputString(){
        //test
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                System.out.print(matrix[row][col]);
            }   System.out.println("");
        }
    }
    public boolean setShipWall(double x, double y, int type){
        boolean build = true;
        int row = ((int)y-(int)getY()+(getHeight()/2))/getPixelSize();
        int col = ((int)x-(int)getX()+(getWidth()/2))/getPixelSize();
        //fix bounds
        if (col >= 0 && col <= cols-1 && row >= 0 && row <= rows-1){
            //toggle changes
            if (matrix[row][col] == 0){ //if the space is empty
                if (type == 1){
                    if (money - wallCost >= 0){
                        money -= wallCost;
                        setMatrixValue(col,row,type);
                        AudioHandler.POP1.play();
                    }
                }
                else if (type == 3){
                    if (money - cannonCost >= 0){
                        money -= cannonCost;
                        setMatrixValue(col,row,type);
                        AudioHandler.POP1.play();
                    }
                }
            }
            else {
                if (matrix[row][col]==1){
                    money += wallCost;
                    setMatrixValue(col,row,0);
                    AudioHandler.THUNK2.play();
                    Window.panel.particles.addClusterAt(x,y,10);
                }
                else if (matrix[row][col]==3){
                    money += cannonCost;
                    setMatrixValue(col,row,0);
                    AudioHandler.THUNK2.play();
                    Window.panel.particles.addClusterAt(x,y,10);
                }
            }
        }
        else build = false;
        return build;
    }
    public boolean setSelector(double x, double y){
        boolean build = true;
        int row = ((int)y-(int)getY()+(getHeight()/2))/getPixelSize();
        int col = ((int)x-(int)getX()+(getWidth()/2))/getPixelSize();
        if (col >= 0 && col <= cols-1 && row >= 0 && row <= rows-1){
            selectX = col;
            selectY = row;
            build = true;
        }
        else{
            build = false;
            selectX = -1;
            selectY = -1;
        }
        return build;
    }
    public void hurt(double x, double y){
        if (!isDead()){
            int row = ((int)y-(int)getY()+(getHeight()/2))/getPixelSize();
            int col = ((int)x-(int)getX()+(getWidth()/2))/getPixelSize();
            setMatrixValue(col,row,0);
            //System.out.println("row: "+row+", col: "+col);

            AudioHandler.THUNK2.play();
            Window.panel.particles.addClusterAt(x,y,10);
        }
    }
    public boolean isVisible(double x, double y){
        /*int col = (((int)(x-getX()))/(getPixelSize()))+(getCols()/2);
        int row = (((int)(y-getY()))/(getPixelSize()))+(getRows()/2);*/
        int row = ((int)y-(int)getY()+(getHeight()/2))/getPixelSize();
        int col = ((int)x-(int)getX()+(getWidth()/2))/getPixelSize();
        return matrix[row][col] != 0;
    }
    //getters
    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getBulletXSpeed(){ return bulletXSpeed; }
    public double getBulletYSpeed(){ return bulletYSpeed; }
    public int getHeartX(){ return heartX; }
    public int getHeartY(){ return heartY; }
    public int getRows(){ return rows; }
    public int getCols(){ return cols; }
    public int getWidth(){ return cols*pixelSize; }
    public int getHeight(){ return rows*pixelSize; }
    public int getPixelSize(){ return pixelSize; }
    public int getBulletSize(){ return bulletSize; }
    public int getFireTime(){ return fireTime; }
    public int getMaxFireTime(){ return maxFireTime; }
    public int getMoney(){ return money; }
    public boolean isDead(){ return matrix[heartY][heartX] == 0; }
    public boolean isShowGrid(){ return showGrid; }
    public int[][] getMatrix(){ return matrix; }
    public int getWallCost(){ return wallCost; }
    public int getCannonCost(){ return cannonCost; }
    //setters
    public void setX(double x){ this.x=x; }
    public void setY(double y){ this.y=y; }
    public void setXY(double x, double y) { this.x=x; this.y=y; }
    public void setHeartX(int heartX){ this.heartX=heartX; }
    public void setHeartY(int heartY){ this.heartY=heartY; }
    public void setHeartXY(int heartX, int heartY){ this.heartX=heartX; this.heartY=heartY; }
    public void setRows(int rows){ this.rows=rows; } //warning, consider rebuildMatrix
    public void setCols(int cols){ this.cols=cols; } //warning, consider rebuildMatrix
    public void setPixelSize(int pixelSize){ this.pixelSize=pixelSize; }
    public void setBulletSize(int bulletSize){ this.bulletSize=bulletSize; }
    public void setBulletXSpeed(double bulletXSpeed){ this.bulletXSpeed=bulletXSpeed; }
    public void setBulletYSpeed(double bulletXSpeed){ this.bulletYSpeed=bulletXSpeed; }
    public void setFireTime(int fireTime){ this.fireTime=fireTime; }
    public void setMaxFireTime(int maxFireTime){ this.maxFireTime=maxFireTime; }
    public void resetFireTime(){ fireTime = maxFireTime; }
    public void tickFireTime(){ fireTime -= 1; }
    public void setGrid(boolean showGrid){ this.showGrid=showGrid; }
    public void addMoney(int amount){ money+=amount; }
    public void setMoney(int money){ this.money=money; }
    public void resetMoney(){ money = startMoney; }
    public void setSelectX(int selectX){ this.selectX=selectX; }
    public void setSelectY(int selectY){ this.selectY=selectY; }
}
