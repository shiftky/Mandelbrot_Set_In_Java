package mandelbrot;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MandelbrotInJava extends JFrame {
	MandelbrotSet mandelbrotSet;
	ControlPanel ctrlPanel;
	JCheckBox chckbxShowAxes;
	private JPanel statusPanel;
	
	public MandelbrotInJava(String title){
		int width = 640, height = 620;
		int scr_width = width - 40;
		int scr_height = height - 80;

		// window settings
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// operation panel
	    JPanel operationPanel = new JPanel();
	    JButton btnReset = new JButton("Reset");
	    chckbxShowAxes = new JCheckBox("show axes");
	    operationPanel.add(btnReset);
	    operationPanel.add(chckbxShowAxes);

	    // Mandelbrot set panel
	    JPanel graphicsPanel = new JPanel();
	    mandelbrotSet = new MandelbrotSet(scr_width, scr_height);
	    ctrlPanel = new ControlPanel(scr_width, scr_height);
	    ctrlPanel.notifyObservers.addObserver(mandelbrotSet);
	    mandelbrotSet.add(ctrlPanel, BorderLayout.CENTER);
	    graphicsPanel.add(mandelbrotSet);

	    // status panel
	    statusPanel = new JPanel();

	    getContentPane().add(operationPanel, BorderLayout.NORTH);
	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);
	    getContentPane().add(statusPanel, BorderLayout.SOUTH);

	    // event listener
	    chckbxShowAxes.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent arg0) {
	    		ctrlPanel.drawAxes = chckbxShowAxes.isSelected();
	    		ctrlPanel.repaint();
	    	}   	
	    });

	    btnReset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		mandelbrotSet.reset();
	    	}
	    });
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}