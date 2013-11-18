package mandelbrot.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mandelbrot.core.DrawThread;
import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.DrawEndEvent;
import mandelbrot.events.DrawStartEvent;
import mandelbrot.events.ZoomEventListener;
import mandelbrot.save.SaveImage;
import mandelbrot.utils.GraphArea;
import mandelbrot.utils.Utils;

public class MandelbrotPanel extends JPanel implements ZoomEventListener {
	private GraphArea defaultGraphArea;
	public GraphArea currentGraphArea; 
	public ChangeProgressListener changeProgressListener = null;
	public NotifyDrawEvent notifyDrawEvent;
	public Stack<GraphArea> graphAreaStack = new Stack<GraphArea>();
	public boolean antiAliasing = false;
	public boolean smoothing = true;
	public boolean drawing = false;
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
		defaultGraphArea = new GraphArea(-2.0, 1.0, -1.5, 1.5);

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
	
	public void zoomIn(int x1, int x2, int y1, int y2) {
		if (x2 != -1 && y2 != -1 ){
			graphAreaStack.push(currentGraphArea);
			double tmp_r1 = Utils.map((double) x1, 0.0, (double) width, currentGraphArea.xs, currentGraphArea.xe);
			double tmp_r2 = Utils.map((double) x2, 0.0, (double) width, currentGraphArea.xs, currentGraphArea.xe);
			double tmp_i1 = Utils.map((double) y1, 0.0, (double) height, currentGraphArea.ys, currentGraphArea.ye);
			double tmp_i2 = Utils.map((double) y2, 0.0, (double) height, currentGraphArea.ys, currentGraphArea.ye);
			currentGraphArea = new GraphArea(tmp_r1, tmp_r2, tmp_i1, tmp_i2);
			changeDrawingArea();
		}
	}
	
	public void zoomOut() {
		if ( currentGraphArea.eql(defaultGraphArea) == false && drawing == false ) {
			currentGraphArea = graphAreaStack.pop();
			changeDrawingArea();
		}
	}

	public void changeDrawingArea() {
		currentGraphArea = new GraphArea(currentGraphArea.xs, currentGraphArea.xe, currentGraphArea.ys, currentGraphArea.ye);
		viewX += zoom * Math.min(currentGraphArea.xe, currentGraphArea.xs) / Math.min(width, height);
		viewY += zoom * Math.min(currentGraphArea.ye, currentGraphArea.ys) / Math.min(width, height);
		zoom *= Math.max((double)Math.abs(currentGraphArea.xe-currentGraphArea.xs)/width, (double)Math.abs(currentGraphArea.ye-currentGraphArea.ys)/height);
		draw();
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
		currentGraphArea = defaultGraphArea;
		viewX = viewY = zoom = 1.0;
	}
	
	public class NotifyDrawEvent extends Observable {
		public void drawStart() {
			drawing = true;
			setChanged();
			notifyObservers(new DrawStartEvent(this));
		}

		public void drawEnd() {
			drawing = false;
			setChanged();
			notifyObservers(new DrawEndEvent(this));
		}
	}
}