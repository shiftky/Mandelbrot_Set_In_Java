package mandelbrot;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.MouseMovedListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JPanel btnPanel;
	private JPanel sliderPanel;
	private JSlider loopSlider;
	private JSlider colorSlider;
	private JLabel lblLoop;
	private JLabel lblColor;
	private JTextField colorTextField;
	private JTextField loopTextField;
	
	public MandelbrotInJava(String title){
		int width = 640, height = 760;
		int scr_width = width - 40;
		int scr_height = height - 160;

		windowSettings(title, width, height);

		// operation panel
	    JPanel operationPanel = new JPanel();
	    getContentPane().add(operationPanel, BorderLayout.NORTH);

	    btnPanel = new JPanel();
	    FlowLayout flowLayout_1 = (FlowLayout) btnPanel.getLayout();
	    flowLayout_1.setVgap(2);
	    JButton btnReset = new JButton("Reset");
	    btnPanel.add(btnReset);
	    JButton btnSave = new JButton("Save");
	    btnPanel.add(btnSave);
	    GroupLayout gl_operationPanel = new GroupLayout(operationPanel);
	    gl_operationPanel.setHorizontalGroup(
	    	gl_operationPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_operationPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(457, Short.MAX_VALUE))
	    );
	    gl_operationPanel.setVerticalGroup(
	    	gl_operationPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_operationPanel.createSequentialGroup()
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    			.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    );
	    operationPanel.setLayout(gl_operationPanel);
	    
	    
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
	    getContentPane().add(graphicsPanel, BorderLayout.CENTER);
	    
	    JPanel parameterPanel = new JPanel();
	    getContentPane().add(parameterPanel, BorderLayout.SOUTH);
	    parameterPanel.setLayout(new BorderLayout(0, 0));

	    // status panel
	    lblPanel = new JPanel();
	    FlowLayout flowLayout = (FlowLayout) lblPanel.getLayout();
	    flowLayout.setVgap(2);
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
	    parameterPanel.add(statusPanel, BorderLayout.NORTH);
	    GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
	    gl_statusPanel.setHorizontalGroup(
	    	gl_statusPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(Alignment.LEADING, gl_statusPanel.createSequentialGroup()
	    			.addGap(20)
	    			.addComponent(lblPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
	    			.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
	    			.addGap(20))
	    );
	    gl_statusPanel.setVerticalGroup(
	    	gl_statusPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_statusPanel.createSequentialGroup()
	    			.addGap(10)
	    			.addGroup(gl_statusPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(8, Short.MAX_VALUE))
	    );
	    statusPanel.setLayout(gl_statusPanel);
	    
	    sliderPanel = new JPanel();
	    parameterPanel.add(sliderPanel, BorderLayout.SOUTH);
	    
	    JPanel colorPanel = new JPanel();
	    sliderPanel.add(colorPanel);
	    
	    lblColor = new JLabel("Color");
	    colorPanel.add(lblColor);
	    
	    colorTextField = new JTextField();
	    colorTextField.setText("0");
	    colorPanel.add(colorTextField);
	    colorTextField.setColumns(3);
	    
	    colorSlider = new JSlider();
	    colorPanel.add(colorSlider);
	    colorSlider.setValue(0);
	    
	    JPanel loopPanel = new JPanel();
	    sliderPanel.add(loopPanel);
	    
	    lblLoop = new JLabel("Loop");
	    loopPanel.add(lblLoop);
	    
	    loopTextField = new JTextField();
	    loopTextField.setText("20");
	    loopPanel.add(loopTextField);
	    loopTextField.setColumns(3);
	    
	    loopSlider = new JSlider();
	    loopPanel.add(loopSlider);
	    loopSlider.setValue(20);

	    // event listener
	    btnReset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		mandelbrotPanel.reset();
	    	}
	    });

	    btnSave.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent arg0) {
	    		mandelbrotPanel.save();
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