package mandelbrot;

import java.awt.Color;
import java.awt.Graphics;

import mandelbrot.events.ChangeProgressListener;

public class DrawThread extends Thread {
	private int loopCount = 20;
	private double r1, r2, i1, i2;
	private int width, height;
	private Graphics bfg;
	private ChangeProgressListener changeProgressListener = null;
	private MandelbrotPanel panel;

	public DrawThread(MandelbrotPanel mandelbrotSetPanel) {
		bfg = mandelbrotSetPanel.bfg;
		r1 = mandelbrotSetPanel.r1; r2 = mandelbrotSetPanel.r2;
		i1 = mandelbrotSetPanel.i1; i2 = mandelbrotSetPanel.i2;
		width = mandelbrotSetPanel.width;
		height = mandelbrotSetPanel.height;
		loopCount = mandelbrotSetPanel.loopCount;
		changeProgressListener = mandelbrotSetPanel.changeProgressListener;

		panel = mandelbrotSetPanel;
	}

	public void run(){
		if (changeProgressListener != null){
			changeProgressListener.changeProgress(0);
		}

		bfg.setColor(Color.black);
		bfg.fillRect(0, 0, width, height);

		int prog_cnt = 0;
		for(int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				Complex c = new Complex(Utils.map((double) x, 0.0, (double) width, r1, r2),
									    Utils.map((double) y, 0.0, (double) height, i1, i2));

				bfg.setColor(colorGenerator(Mandelbrot.mandel(c, loopCount)));
			    bfg.fillRect(x, y, 1, 1);

				if (changeProgressListener != null){
					int prog = (int) Utils.map(prog_cnt++, 0.0, width*height, 0.0, 100.0) + 1;
					changeProgressListener.changeProgress(prog);
				}
			}
		}
		
		prog_cnt = 0;
		panel.repaint();

		if ( panel.isVisible() == false ) { 
			panel.setVisible(true);
		}
	}

	private Color colorGenerator(int mandel) {
		Color color;
		if ( mandel == loopCount ) {
			color = Color.black;
		} else {
			int x = mandel*16 % 255;
			color = new Color(x, x, x);
		}
		return color;
	}
}
