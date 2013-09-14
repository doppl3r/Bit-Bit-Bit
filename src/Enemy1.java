public class Enemy1 extends Enemy {

    public Enemy1() {
        //set up enemy properties
        //rebuildMatrix(7,7);
        setBulletSize(8);
        setBulletYSpeed(5);
        setMaxFireTime(250);
        setGuided(false);
        setSpeedX(1);
        setSpeedY(2);

        //initMatrix();
        int[][] mat = {
            {0,1,1,1,1,1,1,1,0},
            {1,1,0,0,1,0,0,1,1},
            {1,1,0,1,1,1,0,1,1},
            {1,0,0,0,1,0,0,0,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        /*int[][] mat = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };*/
        setMatrix(mat);
        setHeartPosition("top");
        //create custom path
        getPathNodes().add(Window.getWidth()/2,100);
        getPathNodes().add(240,240);
        getPathNodes().add(100,100);
        getPathNodes().add(240,240);
        getPathNodes().add(100,380);
        getPathNodes().add(240,240);
        getPathNodes().add(380,380);
        getPathNodes().add(240,240);
        getPathNodes().add(380,100);
    }
}
