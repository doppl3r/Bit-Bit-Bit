
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import textures.Textures;

/**
 */
public class Window {
	static JFrame jf;
	static Textures tt;
	static Panel panel;
	static int width;
	static int height;
	static String title;
	static String version;
	
	public Window(){
		//change ui for jFileChooser + other UI settings
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (InstantiationException e) { e.printStackTrace(); } 
		catch (IllegalAccessException e) { e.printStackTrace(); } 
		catch (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		//construct frame and panel
		tt = new Textures();
		jf = new JFrame();
        width = 480;
        height = 640;
		panel = new Panel();
		title = "Bit Bit Bit - Doppler Indie Games";
		version = "v0.1";
		//build window
		panel.setPreferredSize(new Dimension(width,height));
		jf.setIconImage(tt.icon_hd);
		jf.setTitle(title+" ["+version+"]");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(panel);
		jf.setResizable(false);
		jf.pack();
		jf.add(panel);
		jf.setLocationRelativeTo(null);
		jf.requestFocusInWindow();
		jf.setVisible(true);
	}
    public static int getWidth(){ return width; }
    public static int getHeight(){ return height; }
}
