import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class purplein {
	int width, height, x, y, choose;
	boolean isVisible;
	JLabel icon;

	public purplein(String filename){
		x = 0;
		y = 775;
		width = 200;
		height = 300;
		isVisible = true;
		
		//setup for image
		String src = new File("").getAbsolutePath()+"/src/IndicatorIMG/"; //path to image setup
		ImageIcon boverlay = new ImageIcon(src+filename); //setups icon image
		icon = new JLabel(boverlay);
		icon.setBounds(x, y, 256, 100); //set location and size of icon
	}
	
	public void setVis(){
		icon.setVisible(false);
	}
	
	public void setVis2(){
		icon.setVisible(true);
	}
}
