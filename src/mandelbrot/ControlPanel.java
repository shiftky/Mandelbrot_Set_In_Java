package mandelbrot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ControlPanel extends JPanel {
	public boolean drawAxes = false;
	int width, height;
	int x1, x2, y1, y2;
	NotifyObservers notifyObservers;

	public ControlPanel(int w, int h){
		width = w;
		height = h;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		
		addMouseListener(new inMouseListener());
		addMouseMotionListener(new inMouseMotionListener());

		notifyObservers = new NotifyObservers();
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

	class inMouseMotionListener extends MouseMotionAdapter{
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
			notifyObservers.notifyChangePoints();
			x1 = y1 = x2 = y2 = -1;
			repaint();
		}
	}

	public class NotifyObservers extends Observable{
		public void notifyChangePoints() {
			EnlargeEvent enlargeEvent = new EnlargeEvent(this);
			enlargeEvent.setPoints(x1, x2, y1, y2);
			setChanged();
			notifyObservers(enlargeEvent);	
		}
	}
}