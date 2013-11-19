package mandelbrot.gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.DrawEndEvent;
import mandelbrot.events.DrawStartEvent;
import mandelbrot.events.MouseMovedListener;
import mandelbrot.utils.Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainWindow extends JFrame implements MouseMovedListener,
	ChangeProgressListener, Observer {
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
	private JPanel sliderPanel;
	private JSlider loopSlider;
	private JLabel lblLoop;
	private JLabel loopValueLabel;
	private JButton btnChangePalette;
	private	JButton btnReset;
	private JButton btnSave;
	private JCheckBox chckbxSmooth;
	private JCheckBox chckbxAntialias;
	private boolean drawing = false;
	private JButton btnAbout;
	private JPanel mainButtonPanel;
	private JPanel AboutButtonPanel;

	public MainWindow(String title) {
		int width = 640, height = 740;
		int scr_width = width - 40;
		int scr_height = height - 140;

		windowSettings(title, width, height);

		JPanel operationPanel = new JPanel();
		getContentPane().add(operationPanel, BorderLayout.NORTH);
		
		mainButtonPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) mainButtonPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(0);
		btnReset = new JButton("Reset");
		mainButtonPanel.add(btnReset);
		btnSave = new JButton("Save");
		mainButtonPanel.add(btnSave);
		btnSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if ( drawing == false ) {
					mandelbrotPanel.save();
				}
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( drawing == false ) {
					mandelbrotPanel.reset();
				}
			}
		});
		
		AboutButtonPanel = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) AboutButtonPanel.getLayout();
		flowLayout_5.setVgap(0);
		
		btnAbout = new JButton("About");
		AboutButtonPanel.add(btnAbout);
		GroupLayout gl_operationPanel = new GroupLayout(operationPanel);
		gl_operationPanel.setHorizontalGroup(
			gl_operationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_operationPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(mainButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
					.addComponent(AboutButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_operationPanel.setVerticalGroup(
			gl_operationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_operationPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_operationPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(mainButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AboutButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		operationPanel.setLayout(gl_operationPanel);
		btnAbout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AboutWindow about = new AboutWindow();
				about.setVisible(true);
			}
		});

		// Mandelbrot set panel
		JPanel graphicsPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) graphicsPanel.getLayout();
		flowLayout_3.setHgap(0);
		flowLayout_3.setVgap(0);
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
		lblRe = new JLabel("Re:");
		lblPanel.add(lblRe);
		lblReValue = new JLabel("0.000000000000");
		lblPanel.add(lblReValue);
		lblIm = new JLabel(" Im:");
		lblPanel.add(lblIm);
		lblImValue = new JLabel("0.000000000000");
		lblPanel.add(lblImValue);

		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		statusPanel = new JPanel();
		parameterPanel.add(statusPanel, BorderLayout.NORTH);
		GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
		gl_statusPanel.setHorizontalGroup(
			gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
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
						FlowLayout flowLayout_1 = (FlowLayout) colorPanel.getLayout();
						flowLayout_1.setHgap(0);
						flowLayout_1.setVgap(0);
						
						btnChangePalette = new JButton("ChangePalette");
						btnChangePalette.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								if ( drawing == false ) {
									mandelbrotPanel.changePalette();
								}
							}
						});
						colorPanel.add(btnChangePalette);
						
						chckbxSmooth = new JCheckBox("Smooth");
						chckbxSmooth.setSelected(true);
						chckbxSmooth.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if ( drawing == false ) {
									mandelbrotPanel.setSmoothing(chckbxSmooth.isSelected());
								}
							}
						});
						chckbxSmooth.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
						colorPanel.add(chckbxSmooth);
		
				JPanel loopPanel = new JPanel();
				FlowLayout flowLayout_2 = (FlowLayout) loopPanel.getLayout();
				flowLayout_2.setHgap(0);
				flowLayout_2.setVgap(0);
				
								lblLoop = new JLabel("Iterations: ");
								lblLoop.setVerticalAlignment(SwingConstants.BOTTOM);
								lblLoop.setPreferredSize(new Dimension(70, 17));
								loopPanel.add(lblLoop);
						
								loopValueLabel = new JLabel("30");
								loopValueLabel.setVerticalAlignment(SwingConstants.BOTTOM);
								loopValueLabel.setPreferredSize(new Dimension(35, 17));
								loopPanel.add(loopValueLabel);
								
										loopSlider = new JSlider();
										loopSlider.addMouseListener(new MouseAdapter() {
											public void mouseReleased(MouseEvent e) {
												if ( drawing == false ) {
													mandelbrotPanel.draw();
												}
											}
										});
										loopSlider.setValue(30);
										loopSlider.setMinimum(10);
										loopSlider.setMaximum(1000);
										loopSlider.addChangeListener(new ChangeListener() {
											public void stateChanged(ChangeEvent e) {
												int val = 10 * (loopSlider.getValue() / 10);
												loopValueLabel.setText(Integer.toString(val));
												mandelbrotPanel.loopCount = val;
											}
										});
										loopPanel.add(loopSlider);
										GroupLayout gl_sliderPanel = new GroupLayout(sliderPanel);
										gl_sliderPanel.setHorizontalGroup(
											gl_sliderPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING, gl_sliderPanel.createSequentialGroup()
													.addGap(15)
													.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(loopPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addContainerGap(8, Short.MAX_VALUE))
										);
										gl_sliderPanel.setVerticalGroup(
											gl_sliderPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_sliderPanel.createSequentialGroup()
													.addGroup(gl_sliderPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(loopPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										);
										
										chckbxAntialias = new JCheckBox("Anti-alias");
										chckbxAntialias.addMouseListener(new MouseAdapter() {
											public void mouseClicked(MouseEvent e) {
												if ( drawing == false ) {
													mandelbrotPanel.setAntialiasing(chckbxAntialias.isSelected());
												}
											}
										});
										colorPanel.add(chckbxAntialias);
										sliderPanel.setLayout(gl_sliderPanel);

		mandelbrotPanel.notifyDrawEvent.addObserver(this);
		mandelbrotPanel.notifyDrawEvent.addObserver(ctrlPanel);
		mandelbrotPanel.draw();
	}

	protected void windowSettings(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			URL url = this.getClass().getResource("/mandelbrot/gui/icon.png");
		    this.setIconImage(java.awt.Toolkit.getDefaultToolkit().createImage(url));
		} catch ( Exception ex ) {
			System.out.println("Error: Failed set icon.");
		}
	}

	public void changeCursorPosition(int x, int y) {
		double real = Utils.map(x, 0.0, mandelbrotPanel.width, mandelbrotPanel.currentGraphArea.xs, mandelbrotPanel.currentGraphArea.xe);
		double img = Utils.map(y, 0.0, mandelbrotPanel.width, mandelbrotPanel.currentGraphArea.ys, mandelbrotPanel.currentGraphArea.ye);
		lblReValue.setText(String.format("%.012f", real));
		lblImValue.setText(String.format("%.012f", img));
	}

	public void changeProgress(int value) {
		if (progressBar != null) {
			progressBar.setValue(value);
			if ( value == progressBar.getMaximum() ){
				progressBar.setString("");
			} else {
				progressBar.setString("Rendering... " + value + "%");
			}
		}
	}

	public void update(Observable o, Object event) {
		if (event instanceof DrawStartEvent) {
			drawing = true;
			loopSlider.setEnabled(false);
			btnReset.setEnabled(false);
			btnSave.setEnabled(false);
			btnAbout.setEnabled(false);
			btnChangePalette.setEnabled(false);
			chckbxSmooth.setEnabled(false);
			chckbxAntialias.setEnabled(false);
		} else if (event instanceof DrawEndEvent) {
			drawing = false;
			loopSlider.setEnabled(true);
			btnReset.setEnabled(true);
			btnSave.setEnabled(true);
			btnAbout.setEnabled(true);
			btnChangePalette.setEnabled(true);
			chckbxSmooth.setEnabled(true);
			chckbxAntialias.setEnabled(true);
		}
	}
}