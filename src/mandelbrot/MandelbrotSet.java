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
	double r1, r2, i1, i2;

	public MandelbrotSet(int w, int h){
		width = w;
		height = h;

		setBackground(Color.black);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		r1 = -2.0; r2 = 1.0;
		i1 = -1.5; i2 = 1.5;
	}
	
	public void update(Observable o, Object event){
		int x1 = ((EnlargeEvent) event).x1;
		int x2 = ((EnlargeEvent) event).x2;
		int y1 = ((EnlargeEvent) event).y1;
		int y2 = ((EnlargeEvent) event).y2;

		double tmp_r1 = Utils.map((double) x1, 0.0, (double) width, r1, r2);
		double tmp_r2 = Utils.map((double) x2, 0.0, (double) width, r1, r2);
		double tmp_i1 = Utils.map((double) y1, 0.0, (double) height, i1, i2);
		double tmp_i2 = Utils.map((double) y2, 0.0, (double) height, i1, i2);

		r1 = tmp_r1; r2 = tmp_r2;
		i1 = tmp_i1; i2 = tmp_i2;

		repaint();
	}
	
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    draw((Graphics2D) g);
	}

	public void draw(Graphics2D g2d){
		for(int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				Complex c = new Complex(Utils.map((double) x, 0.0, (double) width, r1, r2),
									    Utils.map((double) y, 0.0, (double) height, i1, i2));

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