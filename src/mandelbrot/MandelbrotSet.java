package mandelbrot;

import java.awt.Color;
import java.awt.Graphics2D;

class MandelbrotSet{
	int width;
	int height;
	
	public MandelbrotSet(int w, int h){
		width = w;
		height = h;
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