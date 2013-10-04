public class Enemy3 extends Enemy {

    public Enemy3(){
        setBulletSize(16);
        setBulletXSpeed(3);
        setBulletYSpeed(10);
        setMaxFireTime(100);
        setGuided(true);
        setSpeedX(5);
        setSpeedY(2);

        int[][] mat = {
                {0,1,1,1,0},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,0,1,0,1},
                {1,1,0,1,1},
                {1,0,1,0,1},
                {1,1,1,1,1},
                {0,1,1,1,0},
        };
        setMatrix(mat);
        setHeartPosition("top");
        //create custom path
        int randomHeight = (int)(Math.random()*400)+64;
        getPathNodes().add(Window.getWidth()/2, randomHeight);
        getPathNodes().add(-100, randomHeight);
        getPathNodes().add(Window.getWidth()+100, randomHeight);
        getPathNodes().add(Window.getWidth()/2, randomHeight);
        getPathNodes().add(Window.getWidth()/2, randomHeight-100);
        getPathNodes().add(Window.getWidth()/2, randomHeight);
    }

}
