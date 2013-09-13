import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener,
	MouseListener, MouseMotionListener, Runnable{
	private static final long serialVersionUID = 1L;
    private boolean paused; //pause option
    private int panelState; //displays menus individually
    private Font font;
    private Background background;
    public Game game;
    public ShipEditor editor;
    public Particles particles;
    public GUI gui;
	private Timer t;
	
	public Panel(){
        font = new Font ("Arial", Font.BOLD, 18);
        background = new Background();
        particles = new Particles();
        game = new Game();
        editor = new ShipEditor();
        gui = new GUI();
		//set listeners and thread
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		run();
	}
	public void run(){
		t = new Timer(1000/60, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		t.start();
	}
	public void paintComponent(Graphics tempG){
        //convert to Graphics2D
        Graphics2D g = (Graphics2D)tempG;
        super.paintComponent(g);
		setBackground(Color.BLACK);
        g.setFont(font);
        //draw components
        if (!paused){
            background.draw(g);
            particles.draw(g);
            switch(panelState){
                case(0): editor.draw(g); break;
                case(1): game.draw(g); break;
            }
        }
        gui.draw(g);
	}
	public void update(){
		//update the components
        if (!paused){
            background.update();
            particles.update();
            switch(panelState){
                case(0): editor.update(); break;
                case(1): game.update(); break;
            }
        }
        gui.update();
	}
	//key bindings
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
        switch(panelState){
            case(0): break;
            case(1): //game keybindings
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { game.keyUpPressed(); }
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { game.keyRightPressed(); }
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { game.keyDownPressed(); }
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { game.keyLeftPressed(); }
                if (key == KeyEvent.VK_SPACE){ Game.player.fireBullet(true, false); }
                if (key == KeyEvent.VK_ESCAPE){ paused = !paused; }
           break;
        }
	}
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(panelState){
            case(0): break;
            case(1): //game keybindings
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { game.keyUpReleased(); }
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { game.keyRightReleased(); }
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { game.keyDownReleased(); }
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { game.keyLeftReleased(); }
                if (key == KeyEvent.VK_SPACE){ Game.player.fireBullet(false, false); }
            break;
        }
    }
	public void keyTyped(KeyEvent arg0) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { //down
		int x1 = e.getX();
		int y1 = e.getY();
        switch(panelState){
            case(0):
                editor.down(x1,y1);

            break;
            case(1): game.down(x1, y1); break;
        }
        gui.down(x1,y1);
	}
	public void mouseDragged(MouseEvent e) { //move
		int x1 = e.getX();
		int y1 = e.getY();
        switch(panelState){
            case(0): break;
            case(1): game.move(x1, y1); break;
        }
        gui.move(x1,y1);
	}
	public void mouseReleased(MouseEvent e) { //up
		int x1 = e.getX();
		int y1 = e.getY();
        switch(panelState){
            case(0): break;
            case(1): game.up(x1, y1);break;
        }
        gui.up(x1,y1);
	}
	public void mouseMoved(MouseEvent e) {
		//update cursor
		int x1 = e.getX();
		int y1 = e.getY();
        switch(panelState){
            case(0): break;
            case(1): game.hover(x1, y1); break;
        }
        gui.hover(x1,y1);
	}
    public void setPanelState(int i){ panelState = i; }
    public int getPanelState(){ return panelState; }
    public void setPause(boolean paused){ this.paused=paused; }
    public boolean isPaused(){ return paused; }
}
