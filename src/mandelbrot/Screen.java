package mandelbrot;

import java.awt.*;

import javax.swing.*;

class Screen extends JPanel{
	MandelbrotSet mandelbrotSet;
	int width;
	int height;

	public Screen(int w, int h){
		width = w;
		height = h;
		mandelbrotSet = new MandelbrotSet(width, height);
		setBackground(Color.black);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
		mandelbrotSet.draw((Graphics2D) g);
	}
}