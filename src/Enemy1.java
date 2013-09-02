public class Enemy1 extends Enemy {

    public Enemy1() {
        //set up enemy properties
        //rebuildMatrix(7,7);
        setSpeedX(2);
        setSpeedY(2);
        setMaxFireTime(20);
        //initMatrix();
        int[][] mat = {
            {0,1,1,1,1,1,1,1,0},
            {1,1,0,0,1,0,0,1,1},
            {1,1,0,1,1,1,0,1,1},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
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
