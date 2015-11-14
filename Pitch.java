import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pitch extends JPanel{
	private static final long serialVersionUID = -1919479720666985238L;
	//Array of pitch objects (the two slimes and the ball)
	PitchObject objects[] = new PitchObject[3];
	
	public Pitch(){
		//Fills the objects array
		objects[0] = new Slime(Color.CYAN, Color.WHITE, 105, 257.5, 75, 75, 1);
		objects[1] = new Slime(Color.RED, Color.BLACK, 545, 257.5, 75, 75, 2);
		//objects[2] = new Ball(Color.YELLOW, 350, 220, 20, 20);
		objects[2] = new Ball(Color.YELLOW, 350, 205, 20, 20);
		
		this.setBackground(Color.BLUE);
		
		//Listener to handle keyboard input
		class PitchListener extends KeyAdapter{
			public void keyPressed(KeyEvent key) {
				//Adds that key to the keys being pressed
			    int keyCode = key.getKeyCode();
			    switch( keyCode )
			    {
				 case KeyEvent.VK_W:
					 ((Slime) objects[0]).setUp(true);
					 break;   
				 case KeyEvent.VK_A:
					 ((Slime) objects[0]).setLeft(true);
					 break;   
				 case KeyEvent.VK_D:
					 ((Slime) objects[0]).setRight(true);
					 break;  
			    case KeyEvent.VK_UP:
			    	((Slime) objects[1]).setUp(true);
			    	break;   
			    case KeyEvent.VK_LEFT:    
			    	((Slime) objects[1]).setLeft(true);
			    	break;     
			    case KeyEvent.VK_RIGHT :     
			    	((Slime) objects[1]).setRight(true);
			    	break;    
			    }
			}

			public void keyReleased(KeyEvent key) {
				 //Removes that key from the keys being pressed
				 int keyCode = key.getKeyCode();
				 switch( keyCode )
				 {
				 case KeyEvent.VK_W:
					 ((Slime) objects[0]).setUp(false);
					 break;   
				 case KeyEvent.VK_A:
					 ((Slime) objects[0]).setLeft(false);
					 break;   
				 case KeyEvent.VK_D:
					 ((Slime) objects[0]).setRight(false);
					 break;   
				 case KeyEvent.VK_UP:
					 ((Slime) objects[1]).setUp(false);
					 break;   
				 case KeyEvent.VK_LEFT:    
					 ((Slime) objects[1]).setLeft(false);
					 break;     
				 case KeyEvent.VK_RIGHT :     
					 ((Slime) objects[1]).setRight(false);
					 break;    
				 }
			}	
		}
		this.addKeyListener(new PitchListener());
		
		//Action to update locations on a fixed time interval (3 milliseconds)
		Action updateLocationAction = new AbstractAction() {
			private static final long serialVersionUID = 2857695103005750482L;
		    public void actionPerformed(ActionEvent e) {
		    	//Calls update location methods in each pitch object
		    	objects[0].updateLocation();
		    	objects[1].updateLocation();
		    	objects[2].updateLocation();
		    	//Calls ball detect collision method
		    	((Ball) objects[2]).detectCollison((Slime) objects[0], (Slime) objects[1]);
		    	repaint();
		    }
		};
		new Timer(2, updateLocationAction).start();
		
		//Allows the panel to receive focus for input
		this.setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Draws the objects
		for (PitchObject o: objects){
			for (ShapeColor s: o.drawObject()){
				g2.setColor(s.colour);
				g2.fill(s.shape);
			}
		}
		
		//Draws the gray floor
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 295, 725, 65);
		
		//Draws the two goalhanging bars at the bottom
		g2.setColor(objects[0].getColor());
		g2.fill(new Rectangle2D.Double(0, 360, 362.5, 5));
		
		g2.setColor(objects[1].getColor());
		g2.fill(new Rectangle2D.Double(362.5, 360, 362.5, 5));
		
		//Draws the goal
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 297, 75, 2);
		for(int i = 0; i < 8; i++){
			g2.fillRect(i * 5, 222, 1, 75);
		}
		for(int i = 0; i < 16; i++){
			g2.fillRect(0, 222 + 5 * i, 36, 1);
		}
		g2.fillRect(35, 222, 5, 73);
		
		g2.fillRect(650, 297, 75, 2);
		for(int i = 0; i < 8; i++){
			g2.fillRect(725 - i * 5, 222, 1, 75);
		}
		for(int i = 0; i < 16; i++){
			g2.fillRect(689, 222 + 5 * i, 36, 1);
		}
		g2.fillRect(685, 222, 5, 73);
	}
}
