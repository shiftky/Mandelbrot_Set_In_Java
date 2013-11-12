package mandelbrot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import mandelbrot.events.ChangeProgressListener;
import mandelbrot.events.EnlargeListener;

class MandelbrotPanel extends JPanel implements EnlargeListener {
	public double r1, r2, i1, i2;
	public int width, height;
	public BufferedImage buffimg;
	public Graphics bfg;
	public ChangeProgressListener changeProgressListener = null;

	public MandelbrotPanel(int w, int h){
		width = w;
		height = h;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));

		buffimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bfg = buffimg.createGraphics();

		initRange();
	}
	
	public void setChangeProgressListener(ChangeProgressListener listener){
		this.changeProgressListener = listener;
	}

	public void draw(){
		Thread thread = new DrawThread(this);
		thread.start();
	}

	public void initRange(){
		r1 = -2.0; r2 = 1.0;
		i1 = -1.5; i2 = 1.5;
	}

	public void reset() {
		initRange();
		draw();
	}

	public void paintComponent(Graphics g){
		g.drawImage(buffimg, 0, 0, this);
	}

	public void changeDrawingArea(int x1, int x2, int y1, int y2) {
		if (x2 != -1 && y2 != -1 ){
			double tmp_r1 = Utils.map((double) x1, 0.0, (double) width, r1, r2);
			double tmp_r2 = Utils.map((double) x2, 0.0, (double) width, r1, r2);
			double tmp_i1 = Utils.map((double) y1, 0.0, (double) height, i1, i2);
			double tmp_i2 = Utils.map((double) y2, 0.0, (double) height, i1, i2);
			r1 = tmp_r1; r2 = tmp_r2;
			i1 = tmp_i1; i2 = tmp_i2;
			draw();
		}
	}
}