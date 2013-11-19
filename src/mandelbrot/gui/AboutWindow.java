package mandelbrot.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class AboutWindow extends JFrame implements MouseListener{
	
	public AboutWindow() {
		setTitle("About Mandelbrot Set in Java");
		setSize(320, 220);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
    	URL url = this.getClass().getResource("/mandelbrot/gui/icon64.png");
    	ImageIcon icon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(url));
    	JPanel iconPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		JLabel iconLabel = new JLabel(icon);

		FlowLayout fl_iconPanel = new FlowLayout();
		fl_iconPanel.setVgap(10);
		iconPanel.setLayout(fl_iconPanel);
		iconPanel.add(iconLabel);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(iconPanel, BorderLayout.NORTH);
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setVgap(10);
		infoPanel.add(btnPanel, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(this);
		btnPanel.add(btnOk);
		
		JPanel lblPanel = new JPanel();
		infoPanel.add(lblPanel, BorderLayout.CENTER);
		lblPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel titleLabel = new JLabel("Mandelbrot Set in Java");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPanel.add(titleLabel);
		
		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanel.add(lblVersion);
		
		JLabel lblAuthor = new JLabel("201211341 Yuki KIRII");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanel.add(lblAuthor);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}	