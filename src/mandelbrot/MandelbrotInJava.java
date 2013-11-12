package mandelbrot;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MandelbrotInJava extends JFrame implements MouseMovedListener {
	MandelbrotSet mandelbrotSet;
	ControlPanel ctrlPanel;
	JCheckBox chckbxShowAxes;
	private JPanel statusPanel;
	private JLabel lblRe;
	private JLabel lblIm;
	private JLabel lblReValue;
	private JLabel lblImValue;
	
	public MandelbrotInJava(String title){
		int width = 640, height = 680;
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
	    operationPanel.add(btnReset);

	    // Mandelbrot set panel
	    JPanel graphicsPanel = new JPanel();
	    mandelbrotSet = new MandelbrotSet(scr_width, scr_height);
	    ctrlPanel = new ControlPanel(scr_width, scr_height);
	    ctrlPanel.setEnlergeListener(mandelbrotSet);
	    ctrlPanel.setMouseMovedListener(this);
	    mandelbrotSet.add(ctrlPanel, BorderLayout.CENTER);
	    graphicsPanel.add(mandelbrotSet);

	    // status panel
	    statusPanel = new JPanel();
	    statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    
	    lblRe = new JLabel("Re: ");
	    lblReValue = new JLabel("0.000000");
	    lblIm = new JLabel("  Im: ");
	    lblImValue = new JLabel("0.000000");
	    statusPanel.add(lblRe);
	    statusPanel.add(lblReValue);
	    statusPanel.add(lblIm);
	    statusPanel.add(lblImValue);

	    // add panels
	    getContentPane().add(operationPanel, BorderLayout.NORTH);
	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);
	    getContentPane().add(statusPanel, BorderLayout.SOUTH);
	    
	    // event listener
	    btnReset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		mandelbrotSet.reset();
	    	}
	    });
	}

	public void changeCursorPosition(int x, int y) {
		double real = Utils.map(x, 0.0, mandelbrotSet.width, mandelbrotSet.r1, mandelbrotSet.r2);
		double img = Utils.map(y, 0.0, mandelbrotSet.width, mandelbrotSet.r1, mandelbrotSet.r2);
		lblReValue.setText(String.format("%.06f", real));
		lblImValue.setText(String.format("%.06f", img));
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}