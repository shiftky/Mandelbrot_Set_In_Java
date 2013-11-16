package mandelbrot.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mandelbrot.core.DrawThread;
import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.DrawEndEvent;
import mandelbrot.events.DrawStartEvent;
import mandelbrot.events.EnlargeListener;
import mandelbrot.save.SaveImage;
import mandelbrot.utils.Utils;

public class MandelbrotPanel extends JPanel implements EnlargeListener {
	public ChangeProgressListener changeProgressListener = null;
	public NotifyDrawEvent notifyDrawEvent;
	public boolean antiAliasing = false;
	public boolean smoothing = true;
	public double viewX = 0.0, viewY = 0.0, zoom = 1.0;
	public double r1, r2, i1, i2;
	public int palette = 0;
	public int loopCount = 30;
	public int width, height;
	public BufferedImage buffimg;
	public Graphics2D bfg;

	public MandelbrotPanel(int w, int h){
		width = w;
		height = h;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));

		buffimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bfg = buffimg.createGraphics();

		notifyDrawEvent = new NotifyDrawEvent();

		initRange();
	}

	public void setChangeProgressListener(ChangeProgressListener listener){
		this.changeProgressListener = listener;
	}

	public void changePalette() {
		palette++;
		if ( palette == 5 ){
			palette = 0;
		}
		draw();
	}
	
	public void setSmoothing(boolean b) {
		smoothing = b;
		draw();
	}
	
	public void setAntialiasing(boolean b) {
		antiAliasing = b;
		draw();
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(buffimg, 0, 0, this);
	}

	public void draw(){
		Thread thread = new DrawThread(this);
		thread.start();
	}

	public void changeDrawingArea(int x1, int x2, int y1, int y2) {
		if (x2 != -1 && y2 != -1 ){
			double tmp_r1 = Utils.map((double) x1, 0.0, (double) width, r1, r2);
			double tmp_r2 = Utils.map((double) x2, 0.0, (double) width, r1, r2);
			double tmp_i1 = Utils.map((double) y1, 0.0, (double) height, i1, i2);
			double tmp_i2 = Utils.map((double) y2, 0.0, (double) height, i1, i2);
			r1 = tmp_r1; r2 = tmp_r2;
			i1 = tmp_i1; i2 = tmp_i2;
			
			viewX += zoom * Math.min(x2, x1) / Math.min(width, height);
			viewY += zoom * Math.min(y2, y1) / Math.min(width, height);
			zoom *= Math.max((double)Math.abs(x2-x1)/width, (double)Math.abs(y2-y1)/height);

			draw();
		}
	}

	public void save() {
		try {
			SaveImage.save(buffimg, this);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save image.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reset() {
		initRange();
		draw();
	}

	private void initRange(){
		r1 = -2.0; r2 = 1.0;
		i1 = -1.5; i2 = 1.5;
		viewX = viewY = zoom = 1.0;
	}
	
	public class NotifyDrawEvent extends Observable {
		public void drawStart() {
			setChanged();
			notifyObservers(new DrawStartEvent(this));
		}

		public void drawEnd() {
			setChanged();
			notifyObservers(new DrawEndEvent(this));
		}
	}
}