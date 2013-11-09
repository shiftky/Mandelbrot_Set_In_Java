package mandelbrot;

import java.awt.*;
import javax.swing.*;

public class MandelbrotInJava extends JFrame {
	public MandelbrotInJava(String title, int width, int height){
		int scr_width = width - 40;
		int scr_height = height - 80;

		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel ctrlPanel = new JPanel();
	    JButton button = new JButton("Run");
	    ctrlPanel.add(button);
	    getContentPane().add(ctrlPanel, BorderLayout.NORTH);
	    
	    Screen screen = new Screen(scr_width, scr_height);
	    screen.setPreferredSize(new Dimension(scr_width, scr_height));

	    JPanel screenPanel = new JPanel();
	    screenPanel.add(screen);
	    getContentPane().add(screenPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java", 640, 620);
		mandelbrot.setVisible(true);
	}
}