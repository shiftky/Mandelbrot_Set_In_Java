package mandelbrot;

import java.awt.*;
import javax.swing.*;

public class MandelbrotInJava extends JFrame {
	// コンストラクタ
	public MandelbrotInJava(String title){
		// Setting window
		setTitle(title);
		setSize(400, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Control Panel
	    JPanel ctrlPanel = new JPanel();
	    JButton button = new JButton("自爆Button");
	    ctrlPanel.add(button);
	    getContentPane().add(ctrlPanel, BorderLayout.NORTH);
	    
	    // 描画用 screen
	    Screen screen = new Screen();
	    screen.setPreferredSize(new Dimension(360, 240));

	    // screen 配置用 Panel
	    JPanel screenPanel = new JPanel();
	    screenPanel.add(screen);
	    getContentPane().add(screenPanel, BorderLayout.CENTER);
	}

	// main
	public static void main(String[] args){
		MandelbrotInJava mandelbrot = new MandelbrotInJava("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}