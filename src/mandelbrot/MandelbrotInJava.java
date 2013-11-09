package mandelbrot;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MandelbrotInJava extends JFrame {
	Screen screen;
	JCheckBox chckbxShowAxes;
	
	public MandelbrotInJava(String title){
		int width = 640, height = 620;
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
	    
	    chckbxShowAxes = new JCheckBox("show axes");
	    ctrlPanel.add(chckbxShowAxes);
	    
	    screen = new Screen(scr_width, scr_height);
	    screen.setPreferredSize(new Dimension(scr_width, scr_height));

	    JPanel screenPanel = new JPanel();
	    screenPanel.add(screen);
	    getContentPane().add(screenPanel, BorderLayout.CENTER);

	    // event listener
	    chckbxShowAxes.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent arg0) {
	    		screen.mandelbrotSet.showAxes = chckbxShowAxes.isSelected();
	    		screen.repaint();
	    	}
	    });
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}