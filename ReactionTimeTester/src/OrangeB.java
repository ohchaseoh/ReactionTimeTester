import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class OrangeB implements MouseListener {
	//fields aka properties of the object
	int x,y; //where is it drawn on the screen
	boolean isVisible;
	JLabel icon;
	
	//constructor
	public OrangeB(String filename){ //constructor requires filename of image
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*700);
		isVisible = true;
			
		//setup for image
		String src = new File("").getAbsolutePath()+"/src/ButtonIMG/"; //path to image setup
		ImageIcon OrangeB = new ImageIcon(src+filename); //setups icon image
		icon = new JLabel(OrangeB);
		icon.setBounds(x, y, 50, 50); //set location and size of icon
			
		//listen for click on icon
		icon.addMouseListener(this);
	}
		
	public int getLX(){ //returns x value
		return x;
	}
	
	public int getLY(){ //returns y value
		return y;
	}
	
	public void setVis(){ //sets isVisible variable to true
		isVisible = true;
	}
	
	public boolean getVis(){ //returns the isVisible variable
		return isVisible;
	}
	
	public void setVis2(){	//unused, used to make the icon invisible
		icon.setVisible(false);
	}
		
	public void setVis3(){	//unused, used to make the icon visible
		icon.setVisible(true);
	}
		
	public void setL(){	//method randomly sets the buttons location on the screen
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*700);
		icon.setBounds(x, y, 50, 50);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { //when icon is clicked, it sets the isVisible variable to false so driver can keep track of which buttons were pressed.
		// TODO Auto-generated method stub
		isVisible = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
			
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
			
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
			
	}
		
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
			
	}
}