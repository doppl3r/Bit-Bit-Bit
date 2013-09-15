
public class ShipEditor extends Player {
    public ShipEditor(){
        setXY(Window.getWidth()/2, Window.getHeight()/2);
        setPixelSize(24);
        setGrid(true);
    }
    public void down(int x, int y, boolean left){
        if (!Window.panel.game.isGameOver()){
            if (left){
                if (setShipWall(x, y, 1)){ //if the grid is selected
                    Game.player.setMoney(getMoney());
                    //Window.panel.particles.addClusterAt(250,215,10);
                }
            }
            else { //right
                if (setShipWall(x, y, 3)){ //if the grid is selected
                    Game.player.setMoney(getMoney());
                    //Window.panel.particles.addClusterAt(250,215,10);
                }
            }
        }
    }
    public void hover(int x, int y){
        setSelector(x,y);
    }
}
