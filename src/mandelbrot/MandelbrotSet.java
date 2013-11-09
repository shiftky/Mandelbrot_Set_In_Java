package mandelbrot;

import java.awt.Color;
import java.awt.Graphics2D;

class MandelbrotSet{
	public int width;
	public int height;
	
	public MandelbrotSet(int w, int h){
		width = w;
		height = h;
	}
		
	public void draw(Graphics2D g2d){
		g2d.setColor(new Color(102, 153, 255));

	    for(int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				Complex c = new Complex(map(x, 0.0, width, -2.0, 1.0), map(y, 0.0, height, -1.5, 1.5));

				if(mandel(c, 30) == true){
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

	private double map(double value, double start1, double stop1, double start2, double stop2){
		return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
	}
}