import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class TealB implements MouseListener {
	//fields aka properties of the object
	int x,y; //where is it drawn on the screen
	boolean isVisible;
	JLabel icon;
	
	//constructor
	public TealB(String filename){ //constructor requires filename of image
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*700);
		isVisible = true;
			
		//setup for image
		String src = new File("").getAbsolutePath()+"/src/ButtonIMG/"; //path to image setup
		ImageIcon TealB = new ImageIcon(src+filename); //setups icon image
		icon = new JLabel(TealB);
		icon.setBounds(x, y, 50, 50); //set location and size of icon
			
		//listen for click on icon
		icon.addMouseListener(this);
	}
	
	public int getLX(){
		return x;
	}
	
	public int getLY(){
		return y;
	}
		
	public void setVis(){
		isVisible = true;
	}
	
	public boolean getVis(){
		return isVisible;
	}
	
	public void setVis2(){
		icon.setVisible(false);
	}
		
	public void setVis3(){
		icon.setVisible(true);
	}
		
	public void setL(){
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*700);
		icon.setBounds(x, y, 50, 50);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
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

