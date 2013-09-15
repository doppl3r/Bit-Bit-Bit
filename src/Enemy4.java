public class Enemy4 extends Enemy {

    public Enemy4(){
        setBulletSize(8);
        setBulletYSpeed(2);
        setMaxFireTime(50);
        setGuided(false);
        setSpeedX(1);
        setSpeedY(4);

        int[][] mat = {
                {1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1},
                {3,1,0,0,0,0,0,0,0,1,3},
                {0,1,1,0,0,0,0,0,1,1,0},
                {0,0,3,1,0,0,0,1,3,0,0},
                {0,0,0,1,1,0,1,1,0,0,0},
                {0,0,0,0,3,1,3,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}
        };
        setMatrix(mat);
        setHeartPosition("top");
        //create custom path
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
        getPathNodes().add(-1, -1);
    }
}
