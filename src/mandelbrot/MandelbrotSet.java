package mandelbrot;

import java.awt.Graphics2D;

class MandelbrotSet{
	public void drawMandelbrotSet(Graphics2D g2d){
	    for(int i=0; i < 360; i++){
			for (int j=0; j < 240; j++){
				g2d.drawLine(i, j, i, j);
			}
		}	
	}
}