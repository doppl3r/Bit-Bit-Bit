import buttons.Button;

import java.awt.*;

public class GUI {
    private Button iContinue;

    public GUI(){
        iContinue = new Button(Window.tt.iContinue, Window.getWidth()/2, 480, true);

    }
    public void draw(Graphics2D g){
        iContinue.draw(g);
        g.setColor(Color.white);
        switch (Window.panel.getPanelState()){
            case(0):
                if (iContinue.isHidden()) iContinue.reveal();
                g.drawString("Click the grid to customize your war ship!", 60,100);
                g.drawString("Bitcoin: "+Game.player.getMoney(),151,220);
            break;
            case(1):
                g.drawString("Bitcoin: "+Game.player.getMoney(),4,Window.getHeight()-4);
            break;
        }
        //show pause
        if (Window.panel.isPaused()) g.drawString("Paused",210,340);
    }
    public void update(){}
    public void down(int x1, int y1){
        iContinue.down(x1,y1);
    }
    public void move(int x1, int y1){
        iContinue.move(x1,y1);
    }
    public void up(int x1, int y1){
        if (iContinue.up(x1,y1)){
            Game.player.setMatrix(Window.panel.editor.getMatrix());
            Game.waveHandler.startWave();
            iContinue.hide();
            Window.panel.setPanelState(1);
        }
    }
    public void hover(int x1, int y1){ }
}
