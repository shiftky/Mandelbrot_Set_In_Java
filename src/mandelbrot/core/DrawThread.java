package mandelbrot.core;

import java.awt.Color;
import mandelbrot.gui.MandelbrotPanel;
import mandelbrot.utils.Complex;
import mandelbrot.utils.Utils;

public class DrawThread extends Thread {
	private MandelbrotPanel panel;
	private double r1, r2, i1, i2;

	public DrawThread(MandelbrotPanel mandelbrotSetPanel) {
		panel = mandelbrotSetPanel;
		r1 = panel.currentGraphArea.xs; r2 = panel.currentGraphArea.xe;
		i1 = panel.currentGraphArea.ys; i2 = panel.currentGraphArea.ye;
	}

	public void run() {
		panel.notifyDrawEvent.drawStart();
		if ( panel.changeProgressListener != null ) {
			panel.changeProgressListener.changeProgress(0);
		}

		Mandelbrot mandelbrot = new Mandelbrot(panel.loopCount);
		ColorPalette colorPalette = new ColorPalette(panel.palette, panel.smoothing);

		int progress_cnt = 0;
		int resolution = panel.width * panel.height;
		for (int x = 0; x < panel.width; x++) {
			for (int y = 0; y < panel.height; y++) {
				Complex z = new Complex(Utils.map((double) x, 0.0, (double) panel.width, r1, r2),
										Utils.map((double) y, 0.0, (double) panel.height, i1, i2));

				Color color = colorPalette.color(mandelbrot.calc(z));
				if ( panel.antiAliasing ) {
					color = antiAliasing(z, mandelbrot, colorPalette, color);
				}

				panel.bfg.setColor(color);
				panel.bfg.fillRect(x, y, 1, 1);

				if ( panel.changeProgressListener != null ) {
					int progress = (int) Utils.map(progress_cnt++, 0.0, resolution, 0.0, 100.0) + 1;
					panel.changeProgressListener.changeProgress(progress);
				}
			}
		}

		panel.repaint();
		if (panel.isVisible() == false) {
			panel.setVisible(true);
		}
		panel.notifyDrawEvent.drawEnd();
	}
	
	private Color antiAliasing(Complex z, Mandelbrot mandelbrot, ColorPalette colorPalette, Color color){
		double r = panel.zoom / Math.min(panel.width, panel.height);
		Color c1 = colorPalette.color(mandelbrot.calc(new Complex(z.re, z.im + 0.5 * r)));
		Color c2 = colorPalette.color(mandelbrot.calc(new Complex(z.re + 0.5 * r, z.im)));
		Color c3 = colorPalette.color(mandelbrot.calc(new Complex(z.re + 0.5 * r, z.im + 0.5 * r)));
		int red = (color.getRed() + c1.getRed() + c2.getRed() + c3.getRed()) / 4;
		int green = (color.getGreen() + c1.getGreen() + c2.getGreen() + c3.getGreen()) / 4;
		int blue = (color.getBlue() + c1.getBlue() + c2.getBlue() + c3.getBlue()) / 4;
		return new Color(red, green, blue);		
	}
}