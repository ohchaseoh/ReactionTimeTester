import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
//instantiates ALL OF ZE VARIABLES MWAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA
	int current_iter = 0, choose = 0, numMode = 0;
	int screen_width = 900, screen_height = 900;
	int max_iter 	 = 20;
	int diameter 	 = 26;
	int max_vals 	 = 200;
	int cap 		 = 999999999;
	
	long start 		 = System.currentTimeMillis();
	long finish 	 = System.currentTimeMillis();
	long current 	 = System.currentTimeMillis();
	long restart 	 = 0;
	long total 		 = 0;
	
	long countdown;
	long highScoreDefault 	= cap;
	long highScoreSingle 	= cap;
	
	boolean inTrans = true;
	boolean gameOn  = true;
	
	String indic 	= "";
	String bg 		= "gameover.png";
	String t1 		= "startingin.png";
	
	//instantiates all the text and stuff
	JLabel trans;
	JLabel background;
	JLabel itDisplay 	= new JLabel("Iteration: " + current_iter + "/" + max_iter);
	JLabel timeHigh 	= new JLabel("Highscore: " + "N/A" + "ms");
	JLabel timeTaken 	= new JLabel("Time taken:"+ total + "ms");
	JLabel timeSofar 	= new JLabel("Timer: " + (current - start) + "ms");
	JLabel newModeCount = new JLabel("");
	
	//instantiates all of the objects
	//please dont ask why theyre all in arrays, code was originally based off of agario with classes
	greenin[] 	greenInd 	= new greenin[1];
	purplein[] 	purpleInd 	= new purplein[1];
	tealin[] 	tealInd 	= new tealin[1];
	orangein[] 	orangeInd 	= new orangein[1];
	GreenB[] 	GreenB 		= new GreenB[1];
	PurpleB[] 	PurpleB 	= new PurpleB[1];
	OrangeB[] 	OrangeB 	= new OrangeB[1];
	TealB[] 	TealB 		= new TealB[1];
	boverlay[] 	bOverlay 	= new boverlay[1];
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		g.setColor(Color.black);
	}

	Font font 			= new Font("Courier New", 1, 50);
	Font countdowntext 	= new Font("Courier New", 1, 500);
	
	public void update() {
		
		current = System.currentTimeMillis(); //sets current to the current time
		timeSofar.setText("Timer: "+ (current - start) + "ms"); //displays the amount of time elapsed
		newModeCount.setFont(countdowntext);
		
		if(inTrans == true){ //if its been 3 seconds, it will set the transition scene to invisible
			
			//changes text for newModeCount for countdown transition
			if(current - restart >= 0){ 
				newModeCount.setText("3");
				
				if(current - restart >= 1000){ 
					newModeCount.setText("2");
					
					if(current - restart >= 2000){
						newModeCount.setText("1");
						
						if(current - restart >= 3000){
							
							start = System.currentTimeMillis();
							
							trans.setVisible(false);
							inTrans = false;
							newModeCount.setVisible(false);
							gameOn = true;
						}
					}
				}
			}
		}
		
		if (gameOn == true){ //checks if the game is currently being run
			
			if (numMode == 0) max_iter = 20;
			if (numMode == 1) max_iter = 1;
			
			if(current_iter == max_iter){ //game finished condition
				finish = System.currentTimeMillis();
				total = finish - start;
				gameOn = false;
				timeTaken.setText("Time taken: "+ total +" ms"); //updates the time taken 60 times a second
				setHighScore();
				
				timeTaken.setVisible(true);
				background.setVisible(true);

			}
			
			//this mess checks if the x and y values for ANY of the buttons overlap at any point and moves the buttons to avoid overlap 
			//It prioritizes moving green, then orange, then purple. Teal will not move.
			if(((GreenB[0].getLX() <= OrangeB[0].getLX() + diameter) && ((GreenB[0].getLX() + diameter) >= OrangeB[0].getLX())) || ((GreenB[0].getLY() <= (OrangeB[0].getLY() + diameter)) && ((GreenB[0].getLY() + diameter) >= OrangeB[0].getLY()))){								
				GreenB[0].setL();
			}
			
			else if(((GreenB[0].getLX() <= (TealB[0].getLX() + diameter)) && ((GreenB[0].getLX() + diameter) >= TealB[0].getLX())) || ((GreenB[0].getLY() <= (TealB[0].getLY() + diameter)) && ((GreenB[0].getLY() + diameter) >= TealB[0].getLY()))){
				GreenB[0].setL();
			}
			
			else if(((GreenB[0].getLX() <= (PurpleB[0].getLX() + diameter)) && ((GreenB[0].getLX() + diameter) >= PurpleB[0].getLX())) || ((GreenB[0].getLY() <= (PurpleB[0].getLY() + diameter)) && ((GreenB[0].getLY() + diameter) >= PurpleB[0].getLY()))){
				GreenB[0].setL();
			}
			
			if(((OrangeB[0].getLX() <= (PurpleB[0].getLX() + diameter)) && ((OrangeB[0].getLX() + diameter) >= PurpleB[0].getLX())) || ((OrangeB[0].getLY() <= (PurpleB[0].getLY() + diameter)) && ((OrangeB[0].getLY() + diameter) >= PurpleB[0].getLY()))){
				OrangeB[0].setL();
			}
			
			else if(((OrangeB[0].getLX() <= (TealB[0].getLX() + diameter)) && ((OrangeB[0].getLX() + diameter) >= TealB[0].getLX())) || ((OrangeB[0].getLY() <= (TealB[0].getLY() + diameter)) && ((OrangeB[0].getLY() + diameter) >= TealB[0].getLY()))){
				OrangeB[0].setL();
			}
			
			if(((PurpleB[0].getLX() <= (TealB[0].getLX() + diameter)) && ((PurpleB[0].getLX() + diameter) >= TealB[0].getLX())) || ((PurpleB[0].getLY() <= (TealB[0].getLY() + diameter)) && ((PurpleB[0].getLY() + diameter) >= TealB[0].getLY()))){
				PurpleB[0].setL();
			}
			
			//checks choose variable, which is a random number generated from 0-3 
			//setvis makes sure that only a certain button press is valid 
			//makes sure that only the correct indicator is shown according to the random choose value
			
			//0 = green, 1 = orange, 2 = purple, 3 = teal
			
			switch(choose) {
			
				//green
				case 0:
					if (GreenB[0].getVis() == false) visReset();
					greenInd[0].setVis2();
					orangeInd[0].setVis(); purpleInd[0].setVis(); tealInd[0].setVis();
					break;
			
				//orange
				case 1:
					if (OrangeB[0].getVis() == false) visReset();
					orangeInd[0].setVis2();
					purpleInd[0].setVis(); greenInd[0].setVis(); tealInd[0].setVis();
					break;
			
				//purple
				case 2:
					if (PurpleB[0].getVis() == false) visReset();
					purpleInd[0].setVis2();
					tealInd[0].setVis(); greenInd[0].setVis(); orangeInd[0].setVis();
					break;
				
				//teal
				case 3:
					if (TealB[0].getVis() == false) visReset();
					tealInd[0].setVis2();
					greenInd[0].setVis(); orangeInd[0].setVis(); purpleInd[0].setVis();
					break;
				
				default:
					break;
			}
		}
	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	public int getChoose(){
		return this.choose;
	}
	
	public void setChoose(){
		choose = (int)(Math.random()*4); //determines which button will be the correct one to click
	}
	
	public void setIteration(){
		itDisplay.setText("Iteration: " + current_iter + "/20"); //updates iteration counter
	}
	
	public void setHighScore(){
		//sets the high (technically low score) by checking if your score is lower than all previous recorded scores.
		if(total < highScoreDefault){ //stores high score for normal mode
			highScoreDefault = total;
			timeHigh.setText("Highscore: " + highScoreDefault + "ms"); //officially updates high score
		}	
	}
	
	//method for checking if the invisible boolean in the individual classes is false. 
	//invisible boolean checks if the button was clicked in that iteration.
	//if it has been clicked, it moves to the next iteration, where locations are reset
	
	public void visReset() {
		current_iter +=1;
		GreenB[0].setVis(); OrangeB[0].setVis(); PurpleB[0].setVis(); TealB[0].setVis();
		
		setChoose();
		GreenB[0].setL(); OrangeB[0].setL(); PurpleB[0].setL(); TealB[0].setL();
		setIteration();
	}
	
	public void keyReset() {
		
		current_iter = 0;
		start = System.currentTimeMillis();
		restart = System.currentTimeMillis(); //try restart if no worky
		gameOn = false;
		inTrans = true; 
		trans.setVisible(true);
		background.setVisible(false);
		timeTaken.setVisible(false);
		newModeCount.setVisible(true);
		setIteration();
		//GreenB[0].setVis3(); OrangeB[0].setVis3(); PurpleB[0].setVis3(); TealB[0].setVis3();
		
		update();
	}
	
	
	public Driver() {
		
		JFrame f = new JFrame();
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon transit1 = new ImageIcon(src+t1);
		ImageIcon backg = new ImageIcon(src+bg); 
		//initializes all of the transition scenes, text, etc.
		newModeCount.setBounds(300,350, 600,500);
		newModeCount.setForeground(Color.black);
		newModeCount.setVisible(false);
		f.add(newModeCount);
		
		trans = new JLabel(transit1);
		trans.setBounds(0,0,900,900);
		trans.setVisible(false);
		f.add(trans);
		
		timeTaken.setBounds(0,0,500,100);
		timeTaken.setForeground(Color.black);
		timeTaken.setVisible(false);
		f.add(timeTaken);
	
		timeHigh.setBounds(0,0,500,150);
		timeHigh.setForeground(Color.black);
		timeHigh.setVisible(true);
		f.add(timeHigh);
		
		background = new JLabel(backg);
		background.setBounds(0,0,900,900);
		background.setVisible(false);
		f.add(background);
		
		itDisplay.setBounds(0,0,500,50);//set location and size of icon
		itDisplay.setForeground(Color.black);
		itDisplay.setVisible(true);
		f.add(itDisplay);
		
		timeSofar.setBounds(0,0,500,100);
		timeSofar.setForeground(Color.black);
		timeSofar.setVisible(true);
		f.add(timeSofar);

		f.setTitle("APM - Beta");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.setLayout(null);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.add(this);
		
		setChoose();

		if(gameOn == true){
			//adds all of the buttons
			for (int i = 0; i < bOverlay.length; i++) {
				
				greenInd[i] = new greenin("indicatorGreen.png");
				f.add(greenInd[i].icon);
				
				purpleInd[i] = new purplein("indicatorPurple.png");
				f.add(purpleInd[i].icon);
				
				tealInd[i] = new tealin("indicatorTeal.png");
				f.add(tealInd[i].icon);
				
				orangeInd[i] = new orangein("indicatorOrange.png");
				f.add(orangeInd[i].icon);
				
				bOverlay[i] = new boverlay("black3.jpg");
				f.add(bOverlay[i].icon);
				
				GreenB[i] = new GreenB("buttonGreen.png");
				f.add(GreenB[i].icon);
				
				OrangeB[i] = new OrangeB("buttonOrange.png");
				f.add(OrangeB[i].icon);
				
				PurpleB[i] = new PurpleB("buttonPurple.png");
				f.add(PurpleB[i].icon);
				
				TealB[i] = new TealB("buttonTeal.png");
				f.add(TealB[i].icon);
			}
		}
		
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override //resets all the variables to its initial value the first time the game is run
	public void keyPressed(KeyEvent e) {
		
		/*
		 * Gamemode toggle works by temporarily setting the gamemode tracker integer to 1, before changing it to 2 and changing the number of iterations needed to complete a round to 1.
		 * Gamemode tracker integer is first set to 1 so the code does not run more than once.
		 * It sets restart, a millisecond tracker as soon as t is pressed. When the gamemode integer is 1, restart begins to be updated 60 times a second. The countdown checks how much time has passed and changes it to 3 > 2 > 1 
		 * depending on the miliseconds passed 
		 * When T is pressed, the transition scene starts
		 * When restart determines that 3 seconds has passed, the transition scene becomes invisible and the actual timer begins
		 */
		
		if(e.getKeyCode() == 32){ //resets if space is pressed
			
			numMode = 0;
			if(highScoreSingle == cap) setHighScore();
			else timeHigh.setText("Highscore: " + highScoreSingle + "ms");
			
			keyReset();
		}
		
		if(e.getKeyCode() == 84){ //switches gamemode to reaction time
			numMode = 1;
			
			keyReset();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { 
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}