public class Enemy2 extends Enemy {
    public Enemy2() {
        //set up enemy properties
        //rebuildMatrix(7,7);
        setBulletSize(8);
        setBulletYSpeed(5);
        setMaxFireTime(75);
        setGuided(false);
        setSpeedX(2);
        setSpeedY(2);

        //initMatrix();
        int[][] mat = {
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {3,1,0,0,0,0,0,1,3},
        };
        setMatrix(mat);
        setHeartPosition("top");
        //create custom path
        getPathNodes().add(220,100);
        getPathNodes().add(260,150);
        getPathNodes().add(220,200);
        getPathNodes().add(260,250);
        getPathNodes().add(220,300);
        getPathNodes().add(260,350);
        getPathNodes().add(220,400);
        getPathNodes().add(260,350);
        getPathNodes().add(220,300);
        getPathNodes().add(260,250);
        getPathNodes().add(220,200);
        getPathNodes().add(260,150);
    }
}
