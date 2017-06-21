package resources;
import java.awt.Image;

import javax.swing.ImageIcon;


public class ImageContainer {
	private Image circle, cross, blank;
	
	
	public ImageContainer(){
		ImageIcon ii = new ImageIcon("images\\circle1.png");
		circle = ii.getImage();
		ii = new ImageIcon("images\\cross1.png");
		cross = ii.getImage();
		ii = new ImageIcon("images\\blank1.png");
		blank = ii.getImage();

	}
	
	public Image getCircle() {
		return circle;
	}
	public Image getCross() {
		return cross;
	}
	public Image getBlank() {
		return blank;
	}
}
