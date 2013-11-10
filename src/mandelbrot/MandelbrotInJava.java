package mandelbrot;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MandelbrotInJava extends JFrame {
	Screen screen;
	ControlPanel ctrlPanel;
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

	    JPanel operationPanel = new JPanel();
	    JButton button = new JButton("Run");
	    operationPanel.add(button);
	    getContentPane().add(operationPanel, BorderLayout.NORTH);
	    
	    chckbxShowAxes = new JCheckBox("show axes");
	    operationPanel.add(chckbxShowAxes);
	    
	    screen = new Screen(scr_width, scr_height);
	    ctrlPanel = new ControlPanel(scr_width, scr_height);
	    screen.add(ctrlPanel, BorderLayout.CENTER);
	    JPanel graphicsPanel = new JPanel();
	    graphicsPanel.add(screen);

	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);

	    // event listener
	    chckbxShowAxes.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent arg0) {
	    		ctrlPanel.drawAxes(chckbxShowAxes.isSelected());
	    	}
	    });
	}
    
	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}