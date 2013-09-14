public class Enemy5 extends Enemy {

    public Enemy5(){
        setBulletSize(8);
        setBulletYSpeed(4);
        setMaxFireTime(250);
        setGuided(false);
        setSpeedX(2);
        setSpeedY(2);

        int[][] mat = {
                {0,1,0},
                {1,2,1},
                {0,1,0}
        };
        setMatrix(mat);
        setHeartXY(1,1);
        //create custom path
        getPathNodes().add(240, 50);
        getPathNodes().add(50, 240);
        getPathNodes().add(240, 430);
        getPathNodes().add(430, 240);
    }
}
