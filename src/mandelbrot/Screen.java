package mandelbrot;

import java.awt.*;
import java.awt.Graphics2D;
import javax.swing.*;

class Screen extends JPanel{
	private MandelbrotSet mandelbrotSet;

	public Screen(int w, int h){
		mandelbrotSet = new MandelbrotSet(w, h);
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		mandelbrotSet.draw((Graphics2D) g);
	}
}