package mandelbrot;

import java.awt.*;
import javax.swing.*;

public class MandelbrotInJava extends JFrame {
	public MandelbrotInJava(String title){
		setTitle(title);
		setSize(400, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel ctrlPanel = new JPanel();
	    JButton button = new JButton("Run");
	    ctrlPanel.add(button);
	    getContentPane().add(ctrlPanel, BorderLayout.NORTH);
	    
	    Screen screen = new Screen();
	    screen.setPreferredSize(new Dimension(360, 240));

	    JPanel screenPanel = new JPanel();
	    screenPanel.add(screen);
	    getContentPane().add(screenPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}