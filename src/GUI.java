import buttons.Button;

import java.awt.*;

public class GUI {
    private Button iContinue;
    private Font font;
    public GUI(){
        iContinue = new Button(Window.tt.iContinue, Window.getWidth()/2, 480, true);
        font = new Font ("Arial", Font.BOLD, 18);
    }
    public void draw(Graphics2D g){
        iContinue.draw(g);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Money: "+Game.player.getMoney(),4,Window.getHeight()-4);
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
            iContinue.hide();
            Window.panel.setPanelState(1);
        }
    }
    public void hover(int x1, int y1){ }
}
