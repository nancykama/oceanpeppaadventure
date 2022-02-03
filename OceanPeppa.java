import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class OceanPeppa{
	
	//add location attributes
	public int x = 50, y = 300;
	//position of peppa
	private Image img;
	private int vx = 1;
	private int ax = 3;
	private int vy = 1;
	private int ay = 3;
	private AffineTransform tx;

	public OceanPeppa(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/imgs/flipperpepapa.png"); //load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);       
	}
	
	public void paint(Graphics g) {
		//these are   the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actual picture location
		update();
		g2.drawImage(img, tx, null);

	}
	/* update the picture variable location */
	private void update() {
		y += vy;
		vy = ay;
		x += vx;
		vx = ax;
		
		if (x > 790) {
			x = 10;
			vx = 0;
		}
		
		if (x < 10) {
			x = 10;
			vx = 0;
		}
		
		if (y > 590) {
			y = 10;
			vy = 0;
		}
		
		if (y < 10) {
			y = 10;
			vy = 10;
		}
		
		tx.setToTranslation(x, y);
		tx.scale(0.2, 0.2);
	}
	 public void fly () {
		 vy -= 15;
		 vx += 10;
	 }
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}
	
	public void flap () {
		vy -= 75;
	}
	

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = OceanPeppa.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
