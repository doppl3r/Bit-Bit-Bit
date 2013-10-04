public class Enemy6 extends Enemy {
    public Enemy6(){
        setBulletSize(12);
        setBulletYSpeed(3);
        setBulletXSpeed(1.0);
        setMaxFireTime(50);
        setGuided(true);
        setSpeedX(2);
        setSpeedY(2);

        int[][] mat = {
                {0,1,2,1,0},
                {1,1,1,1,1},
                {0,1,1,1,0},
                {1,1,0,1,1},
                {1,0,0,0,1}
        };
        setMatrix(mat);
        setHeartXY(2,0);
        //create custom path
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
    }
}
