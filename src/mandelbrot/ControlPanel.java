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
	private EnlargeListener enlargeListener = null;
	private MouseMovedListener mouseMovedListener = null;
	private int width, height;
	private int x1, x2, y1, y2;

	public ControlPanel(int w, int h){
		width = w;
		height = h;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		
		addMouseListener(new inMouseListener());
		addMouseMotionListener(new inMouseMotionListener());
	}

	public void setEnlergeListener(EnlargeListener listener){
		this.enlargeListener = listener;
	}
	
	public void setMouseMovedListener(MouseMovedListener listener){
		this.mouseMovedListener = listener;
	}

	private void initMousePoint(){
		x1 = y1 = x2 = y2 = -1;
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.drawRect(x1, y1 , y2 - y1, y2 - y1);
	}

	class inMouseMotionListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			mouseMovedListener.changeCursorPosition(e.getX(), e.getY());
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
			enlargeListener.changeDrawingArea(x1, x1+(y2-y1), y1, y2);
			initMousePoint();
			repaint();
		}
	}
}