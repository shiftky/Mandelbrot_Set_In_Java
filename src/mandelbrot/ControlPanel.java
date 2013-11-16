package mandelbrot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mandelbrot.events.DrawEndEvent;
import mandelbrot.events.DrawStartEvent;
import mandelbrot.events.EnlargeListener;
import mandelbrot.events.MouseMovedListener;

public class ControlPanel extends JPanel implements Observer {
	private EnlargeListener enlargeListener = null;
	private MouseMovedListener mouseMovedListener = null;
	private int width, height;
	private int mouse_x1, mouse_x2, mouse_y1, mouse_y2;
	private int x1, x2, y1, y2;
	private int start_x, start_y;
	private int old_mouse_y = -1;
	private boolean enlargeEnabled;

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
		mouse_x1 = mouse_x2 = mouse_y1 = mouse_y2 = -1;
		start_x = start_y = old_mouse_y = -1;
	}

	public void paintComponent(Graphics g){
		if(mouse_x1 > mouse_x2 && mouse_y1 > mouse_y2) {
			x1 = mouse_x2;
			x2 = mouse_x1;
			y1 = mouse_y2;
			y2 = mouse_y1;
		} else if(mouse_x1 > mouse_x2) {
			x1 = mouse_x2;
			x2 = mouse_x1;
			y1 = mouse_y1;
			y2 = mouse_y2;
		} else if(mouse_y1 > mouse_y2) {
			x1 = mouse_x1;
			x2 = mouse_x2;
			y1 = mouse_y2;
			y2 = mouse_y1;
		} else {
			x1 = mouse_x1;
			x2 = mouse_x2;
			y1 = mouse_y1;
			y2 = mouse_y2;
		}
		
		if (start_x > 0 && start_y > 0) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.white);
			g2d.drawLine(start_x-5, start_y, start_x+5, start_y);
			g2d.drawLine(start_x, start_y-5, start_x, start_y+5);
			g2d.drawRect(x1, y1 , y2 - y1, y2 - y1);
		}
	}

	class inMouseMotionListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			if (enlargeEnabled) {
				mouseMovedListener.changeCursorPosition(e.getX(), e.getY());
			}
		}

		public void mouseDragged(MouseEvent e){
			if (enlargeEnabled) {
				updateMouseCoordinates(e);
			}
		}

		private void updateMouseCoordinates(MouseEvent e){
			 if( old_mouse_y == -1 ) {
				 old_mouse_y = e.getY();
				 return;
			 }

			 int up;
			 if( old_mouse_y > e.getY() ) {
				 up = e.getY() - old_mouse_y;
				 mouse_x1 += up;
				 mouse_x2 -= up;
				 mouse_y1 += up;
				 mouse_y2 -= up;
			 } else if( old_mouse_y < e.getY() ) { 
				 up = old_mouse_y - e.getY();
				 mouse_x1 -= up;
				 mouse_x2 += up;
				 mouse_y1 -= up;
				 mouse_y2 += up;
			 }
			 old_mouse_y = e.getY();
			 repaint();
		}
	}

	public void update(Observable o, Object event) {
		if (event instanceof DrawStartEvent) {
			enlargeEnabled = false;
		} else if (event instanceof DrawEndEvent) {
			enlargeEnabled = true;
		}
	}

	class inMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if (enlargeEnabled) {
				start_x = mouse_x1 = mouse_x2 = e.getX();
				start_y = mouse_y1 = mouse_y2 = e.getY();
				repaint();
			}
		}
		
		public void mouseReleased(MouseEvent e){
			if (enlargeEnabled) {
				if ( x1 == x2 && y1 == y2 ) {
					initMousePoint();
					repaint();
					return;
				}
	
				enlargeListener.changeDrawingArea(x1, x1+(y2-y1), y1, y2);
				initMousePoint();
			}
		}
	}
}