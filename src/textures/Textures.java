package textures;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Textures {
    public Image iContinue;
    public Image icon_hd;
    public Image star;
	
	public Textures(){
		addResources();
	}
	public void addResources(){
		iContinue = new ImageIcon(this.getClass().getResource("/gui/continue.png")).getImage();
        icon_hd = new ImageIcon(this.getClass().getResource("/gui/icon_hd.png")).getImage();
        star = new ImageIcon(this.getClass().getResource("/graphics/star.png")).getImage();
	}
}
