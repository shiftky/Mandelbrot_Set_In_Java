package mandelbrot;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.MouseMovedListener;

public class MandelbrotInJava extends JFrame implements MouseMovedListener, ChangeProgressListener {
	MandelbrotPanel mandelbrotPanel;
	ControlPanel ctrlPanel;
	JCheckBox chckbxShowAxes;
	private JPanel statusPanel;
	private JLabel lblRe;
	private JLabel lblIm;
	private JLabel lblReValue;
	private JLabel lblImValue;
	private JPanel lblPanel;
	private JProgressBar progressBar;
	
	protected void createOperatoinPanel(){
		
	}

	public MandelbrotInJava(String title){
		int width = 640, height = 680;
		int scr_width = width - 40;
		int scr_height = height - 80;

		windowSettings(title, width, height);

		// operation panel
	    JPanel operationPanel = new JPanel();
	    JButton btnReset = new JButton("Reset");
	    operationPanel.add(btnReset);

	    // Mandelbrot set panel
	    JPanel graphicsPanel = new JPanel();
	    mandelbrotPanel = new MandelbrotPanel(scr_width, scr_height);
	    mandelbrotPanel.setChangeProgressListener(this);
	    mandelbrotPanel.setVisible(false);
	    ctrlPanel = new ControlPanel(scr_width, scr_height);
	    ctrlPanel.setEnlergeListener(mandelbrotPanel);
	    ctrlPanel.setMouseMovedListener(this);
	    mandelbrotPanel.add(ctrlPanel, BorderLayout.CENTER);
	    graphicsPanel.add(mandelbrotPanel);

	    // status panel
	    lblPanel = new JPanel();
	    FlowLayout flowLayout = (FlowLayout) lblPanel.getLayout();
	    flowLayout.setVgap(0);
	    lblRe = new JLabel("Re: ");
	    lblPanel.add(lblRe);
	    lblReValue = new JLabel("0.000000000000");
	    lblPanel.add(lblReValue);
	    lblIm = new JLabel("  Im: ");
	    lblPanel.add(lblIm);
	    lblImValue = new JLabel("0.000000000000");
	    lblPanel.add(lblImValue);
	    
	    progressBar = new JProgressBar();
	    progressBar.setValue(0);

	    statusPanel = new JPanel();
	    GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
	    gl_statusPanel.setHorizontalGroup(
	    	gl_statusPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, gl_statusPanel.createSequentialGroup()
	    			.addGap(20)
	    			.addComponent(lblPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
	    			.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(20))
	    );
	    gl_statusPanel.setVerticalGroup(
	    	gl_statusPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_statusPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_statusPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(lblPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(12, Short.MAX_VALUE))
	    );
	    statusPanel.setLayout(gl_statusPanel);

	    // add panels
	    getContentPane().add(operationPanel, BorderLayout.NORTH);
	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);
	    getContentPane().add(statusPanel, BorderLayout.SOUTH);
	    
	    // event listener
	    btnReset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		mandelbrotPanel.reset();
	    	}
	    });

		mandelbrotPanel.draw();
	}
	
	protected void windowSettings(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void changeCursorPosition(int x, int y) {
		double real = Utils.map(x, 0.0, mandelbrotPanel.width, mandelbrotPanel.r1, mandelbrotPanel.r2);
		double img = Utils.map(y, 0.0, mandelbrotPanel.width, mandelbrotPanel.r1, mandelbrotPanel.r2);
		lblReValue.setText(String.format("%.012f", real));
		lblImValue.setText(String.format("%.012f", img));
	}

	public void changeProgress(int value) {
		if (progressBar != null) {
			progressBar.setValue(value);
		}
	}

	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}