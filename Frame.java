import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//CREATE THE OBJECTS (STEP 1)
	Background 	bg 	= new Background(0, 0);
	OceanPeppa peppa = new OceanPeppa(10, 300);
	Octopus octo1 = new Octopus (10,0);
	Octopus octo2 = new Octopus (300,0);
	Octopus octo3 = new Octopus (590,0);
	Coin intro = new Coin(30, 490);
	Coin introTop = new Coin(30, 0);
	
	//intro screen 
	boolean gameStart = false;
	
	//score 
	int score = 100;  
	
	//font
	Font f1 = new Font (Font.MONOSPACED, Font.PLAIN, 26);
	Font f2 = new Font (Font.DIALOG, Font.PLAIN, 28);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//bg before game begins
		bg.paint(g);
		intro.paint(g);
		introTop.paint(g);
		if(!gameStart) {
			g.setColor(Color.blue);
			g.setFont(f2);
			g.drawString("welcome. help peppa finness octopus and make bank", 70, 100);
			g.drawString("press enter to commence your ocean adventure", 100, 200);
			g.drawString("with the ultimate G, Peppa", 220, 300);
		return;
		} 
		
		bg.paint(g);
		peppa.paint(g);
		octo1.paint(g);
		octo2.paint(g);
		octo3.paint(g);
		
		//add rectangles
		Rectangle rocto1 = new Rectangle (octo1.x1, octo1.y1, 50, 50);
		Rectangle rocto2 = new Rectangle (octo2.x1, octo2.y1, 50, 50);
		Rectangle rocto3 = new Rectangle (octo3.x1, octo3.y1, 50, 50);
		Rectangle rpeppa = new Rectangle (peppa.x, peppa.y, 50, 50);

		//create array with coin objects
		Coin myShells [] = new Coin[18];
		int x2 = 200;
		int y2 = 200;
		int bvx = 50;
		for (int i = 0; i < myShells.length; i ++) {
		    Coin temp = new Coin(x2, y2);
		    myShells[i] = temp;
		    myShells[i].paint(g);
		    
		    //collision between peppa and coins
		    Rectangle rcoin = new Rectangle (myShells[i].x2, myShells[i].y2, 50, 50);
		    if (rpeppa.intersects(rcoin)) {
				score += 1;
			}
		    
		    //boundaries 
		    x2+= bvx;
		    if (x2 > 450) {
				 x2 = 200;
				 y2 += 50;
			 }
		    
		}
		
		
				
		//score
				g.setColor(Color.BLACK);
				g.setFont(f1);
				g.drawString("Score: " + score, 610, 90);
				

		// game instructions
				g.setColor(Color.blue);
				g.setFont(f2);
				g.drawString("press space to move up and forward. peppa's life and", 55, 25);
				g.drawString("trillionare status are in your hands.good luck", 85, 60);
	   
		//set collision
		if (rpeppa.intersects(rocto1) || rpeppa.intersects(rocto2) || rpeppa.intersects(rocto3)) {
			score -= 5;
		}
		
		
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Crossy Street");
		f.setSize(new Dimension(800, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
			System.out.println(arg0.getKeyCode());
			peppa.flap();
			if (arg0.getKeyCode() == 10) {
				gameStart=true;
			}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
			peppa.fly();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
