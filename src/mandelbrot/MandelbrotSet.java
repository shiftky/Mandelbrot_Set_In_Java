package mandelbrot;

import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

class MandelbrotSet extends JPanel implements Observer {
	int width, height;
	
	public MandelbrotSet(int w, int h){
		width = w;
		height = h;

		setBackground(Color.black);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	
	public void update(Observable o, Object event){
		int x1 = ((EnlargeEvent) event).x1;
		int x2 = ((EnlargeEvent) event).x2;
		int y1 = ((EnlargeEvent) event).y1;
		int y2 = ((EnlargeEvent) event).y2;

		System.out.print("mouse points: ");
		System.out.println(x1 + "," +  x2 + " " + y1 + "," + y2);
	}
	
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    draw((Graphics2D) g);
	}

	public void draw(Graphics2D g2d){
		for(int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				Complex c = new Complex(Utils.map((double) x, 0.0, (double) width, -2.0, 1.0),
									    Utils.map((double) y, 0.0, (double) height, -1.5, 1.5));

				if (mandel(c, 20) == true){
					g2d.setColor(new Color(102, 153, 255));
					g2d.drawLine(x, y, x, y);
				}
			}
		}	
	}

	private boolean mandel(Complex z, int max){
		int score = 0;
		Complex c = new Complex(z.re, z.im);

		while (score < max){
			z = z.sqr();
			z = z.add(c);
			if (z.abs() >= 3.5){
				return true;
			}
			score += 1;
		}
		return false;
	}
}