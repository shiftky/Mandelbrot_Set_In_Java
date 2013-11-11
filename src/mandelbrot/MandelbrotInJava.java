package mandelbrot;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class MandelbrotInJava extends JFrame implements ItemListener  {
	MandelbrotSet mandelbrotSet;
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
	    

	    mandelbrotSet = new MandelbrotSet(scr_width, scr_height);
	    ctrlPanel = new ControlPanel(scr_width, scr_height);
	    ctrlPanel.notifyObservers.addObserver(mandelbrotSet);
	    mandelbrotSet.add(ctrlPanel, BorderLayout.CENTER);

	    JPanel graphicsPanel = new JPanel();
	    graphicsPanel.add(mandelbrotSet);

	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);

	    // event listener
	    chckbxShowAxes.addItemListener(this);
	}

  	public void itemStateChanged(ItemEvent arg0) {
		ctrlPanel.drawAxes = chckbxShowAxes.isSelected();
		ctrlPanel.repaint();
	}  

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}