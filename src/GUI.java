import buttons.Button;

import java.awt.*;

public class GUI {
    private Button iContinue;
    private boolean activateButton;

    public GUI(){
        iContinue = new Button(Window.tt.iContinue, Window.getWidth()/2, 480, true);

    }
    public void draw(Graphics2D g){
        iContinue.draw(g);
        g.setColor(Color.white);
        switch (Window.panel.getPanelState()){
            case(0): //edit mode
                if (iContinue.isHidden()) iContinue.reveal();
                if (!Window.panel.game.isGameOver()){
                    g.drawString("Click the grid to customize your war ship!", 60,100);
                    g.drawString("Bitcoin: "+Game.player.getMoney(),151,220);
                }
                else{  //say game Over!
                    g.drawString("Game Over!", 186,320);
                }
            break;
            case(1): //in game overlay
                g.drawString("Bitcoin: "+Game.player.getMoney(),4,Window.getHeight()-4);
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
