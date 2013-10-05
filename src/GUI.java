import buttons.Button;

import java.awt.*;

public class GUI {
    private Button iContinue;
    private boolean activateButton;

    public GUI(){
        iContinue = new Button(Window.tt.iContinue, Window.getWidth()/2, 490, true);

    }
    public void draw(Graphics2D g){
        iContinue.draw(g);
        g.setColor(Color.white);
        switch (Window.panel.getPanelState()){
            case(0): //edit mode
                if (iContinue.isHidden()) iContinue.reveal();
                if (!Window.panel.game.isGameOver()){
                    g.drawString("Instructions: Left click to build armor or right click", 20,34);
                    g.drawString("to build a cannon!", 20,54);
                    g.drawString("Armour = 1,000 Cash", 134,120);
                    g.drawString("Cannon = 7,500 Cash", 134,140);
                    g.drawString("Current Cash: "+Game.player.getMoney(),134,200);
                    /*g.drawString("Left Click: Add Armor", 134,52);
                    g.drawString("Cost: "+Game.player.getWallCost()+" Bitcoins", 134,76);
                    g.drawString("Right Click: Add Cannon", 134,126);
                    g.drawString("Cost: "+Game.player.getCannonCost()+" Bitcoins", 134,150);
                    g.drawString("Bitcoin: "+Game.player.getMoney(),134,200);*/
                }
                else{  //say game Over!
                    g.drawString("Game Over!", 186,320);
                }
            break;
            case(1): //in game overlay
                g.setColor(new Color(255,0  ,  0));
                g.fillRect(4,620,(int)((472*Game.player.getShield())/100),16);
                g.setColor(new Color(255,255,255));
                g.drawString("Cash: "+Game.player.getMoney(),4,Window.getHeight()-5);
                g.drawRect(4,620,472,16);
                g.drawLine(Window.getWidth()/2, 620, Window.getWidth()/2, 636);
            break;
        }
        //show pause
        if (Window.panel.isPaused()) g.drawString("Paused",210,340);
    }
    public void update(){}
    public void down(int x1, int y1){
        if (iContinue.down(x1,y1)) activateButton=true;
    }
    public void move(int x1, int y1){
        iContinue.move(x1,y1);
    }
    public void up(int x1, int y1){
        if (iContinue.up(x1,y1) && activateButton){
            if (!Window.panel.game.isGameOver()){
                Game.player.setMatrix(Window.panel.editor.getMatrix());
                Game.waveHandler.startWave();
                iContinue.hide();
                Window.panel.setPanelState(1);
                activateButton = false;
            }
            else {
                Window.panel.game.setGameOver(false);
                System.out.println(Window.panel.game.isGameOver());
                activateButton = false;
            }
        }
    }
    public void hover(int x1, int y1){ }
}
