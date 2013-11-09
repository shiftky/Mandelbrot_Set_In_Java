package mandelbrot;

import java.awt.*;
import java.awt.Graphics2D;
import javax.swing.*;

class Screen extends JPanel{
	private MandelbrotSet mandelbrotSet;

	public Screen(){
		mandelbrotSet = new MandelbrotSet();
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		mandelbrotSet.draw((Graphics2D) g);
	}
}