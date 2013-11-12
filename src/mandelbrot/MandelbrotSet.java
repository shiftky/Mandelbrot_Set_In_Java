package mandelbrot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import mandelbrot.events.EnlargeListener;

class MandelbrotSet extends JPanel implements EnlargeListener {
	public double r1, r2, i1, i2;
	public int width, height;
	private BufferedImage buffimg;
	private Graphics bfg;

	public MandelbrotSet(int w, int h){
		width = w;
		height = h;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));

		buffimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bfg = buffimg.createGraphics();

		initRange();
		drawSet();
	}

	public void drawSet(){
		bfg.setColor(Color.black);
		bfg.fillRect(0, 0, width, height);
		for(int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				Complex c = new Complex(Utils.map((double) x, 0.0, (double) width, r1, r2),
									    Utils.map((double) y, 0.0, (double) height, i1, i2));
				bfg.setColor(new Color(102, 153, 255));
				if (mandel(c, 30) == true){
			        bfg.fillRect(x, y, 1, 1);
				}
			}
		}	
		repaint();
	}

	public void initRange(){
		r1 = -2.0; r2 = 1.0;
		i1 = -1.5; i2 = 1.5;
	}

	public void reset() {
		initRange();
		drawSet();
	}

	public void paintComponent(Graphics g){
	    g.drawImage(buffimg, 0, 0, this);
	}

	public void changeDrawingArea(int x1, int x2, int y1, int y2) {
		if (x2 != -1 && y2 != -1 ){
			double tmp_r1 = Utils.map((double) x1, 0.0, (double) width, r1, r2);
			double tmp_r2 = Utils.map((double) x2, 0.0, (double) width, r1, r2);
			double tmp_i1 = Utils.map((double) y1, 0.0, (double) height, i1, i2);
			double tmp_i2 = Utils.map((double) y2, 0.0, (double) height, i1, i2);
			r1 = tmp_r1; r2 = tmp_r2;
			i1 = tmp_i1; i2 = tmp_i2;
			drawSet();
		}
	}

	public boolean mandel(Complex z, int max){
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