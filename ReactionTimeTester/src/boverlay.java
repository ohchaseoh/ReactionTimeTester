import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class boverlay {
	int width, height, x, y;
	boolean isVisible;
	JLabel icon;
	
	public boverlay(String filename){
		x = 0;
		y = 375;
		width = 200;
		height = 300;
		isVisible = true;
		
		//setup for image
		String src = new File("").getAbsolutePath()+"/src/BlackIMG/"; //path to image setup
		ImageIcon boverlay = new ImageIcon(src+filename); //setups icon image
		icon = new JLabel(boverlay);
		icon.setBounds(x, y, 900, 900); //set location and size of icon
	}
}