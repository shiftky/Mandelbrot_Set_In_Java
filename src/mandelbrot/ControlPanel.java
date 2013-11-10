package mandelbrot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
	int width;
	int height;
	
	public ControlPanel(int w, int h){
		width = w;
		height = h;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
	}

	public void drawAxes(boolean checkbox){
		if (checkbox) {
			int x = (int) Utils.map(0.0, -2.0, 1.0, 0.0, width);
			int y = (int) Utils.map(0.0, -1.5, 1.5, 0.0, height);
			Graphics2D g2d = (Graphics2D) this.getGraphics();
			g2d.setColor(Color.white);
			g2d.drawLine(x, 0, x, height);
			g2d.drawLine(0, y, width, y);
		} else {
			repaint();
		}
	}
}