import buttons.Button;

public class ShipEditor extends Player {
    public ShipEditor(){
        setXY(Window.getWidth()/2, Window.getHeight()/2);
        setPixelSize(20);
        setGrid(true);
    }
    public void down(int x, int y){
        setShipWall(x, y);
        Game.player.setMoney(getMoney());
    }
}
