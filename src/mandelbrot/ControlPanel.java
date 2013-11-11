package mandelbrot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ControlPanel extends JPanel {
	public boolean drawAxes = false;
	private EnlargeListener enlargeListener = null;
	private int width, height;
	private int x1, x2, y1, y2;
	private int current_x, current_y;

	public ControlPanel(int w, int h){
		width = w;
		height = h;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		
		addMouseListener(new inMouseListener());
		addMouseMotionListener(new inMouseMotionListener());
	}

	public void setEnlergeListener(EnlargeListener listener)
	{
		this.enlargeListener = listener;
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.drawRect(x1, y1 , x2 - x1, y2 - y1);

		if (drawAxes){
			drawAxesLine(g2d);
		}
	}

	public void drawAxesLine(Graphics2D g2d){
		int x = (int) Utils.map(0.0, -2.0, 1.0, 0.0, width);
		int y = (int) Utils.map(0.0, -1.5, 1.5, 0.0, height);
		g2d.setColor(Color.white);
		g2d.drawLine(x, 0, x, height);
		g2d.drawLine(0, y, width, y);
	}

	private void initMousePoint(){
		x1 = y1 = x2 = y2 = -1;
	}

	class inMouseMotionListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			current_x = e.getX();
			current_y = e.getY();
			System.out.println(current_x);
			System.out.println(current_y);
		}

		public void mouseDragged(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
			repaint();
		}
	}

	class inMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			x1 = e.getX();
			y1 = e.getY();
			repaint();
		}
		
		public void mouseReleased(MouseEvent e){
			enlargeListener.changeDrawingArea(x1, x2, y1, y2);
			initMousePoint();
			repaint();
		}
	}
}