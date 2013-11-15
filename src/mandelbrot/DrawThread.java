package mandelbrot;

import java.awt.Color;

public class DrawThread extends Thread {
	private MandelbrotPanel panel;

	public DrawThread(MandelbrotPanel mandelbrotSetPanel) {
		panel = mandelbrotSetPanel;
	}

	public void run() {
		if ( panel.changeProgressListener != null ) {
			panel.changeProgressListener.changeProgress(0);
		}

		Mandelbrot mandelbrot = new Mandelbrot(panel.loopCount);
		ColorPalette colorPalette = new ColorPalette(panel.palette, panel.smoothing);

		int progress_cnt = 0;
		for (int x = 0; x < panel.width; x++) {
			for (int y = 0; y < panel.height; y++) {
				Complex z = new Complex(Utils.map((double) x, 0.0, (double) panel.width, panel.r1, panel.r2),
										Utils.map((double) y, 0.0, (double) panel.height, panel.i1, panel.i2));

				Color color = colorPalette.color(mandelbrot.calc(z));
				if ( panel.antialiasing ) {
					color = antialiasing(z, mandelbrot, colorPalette, color);
				}

				panel.bfg.setColor(color);
				panel.bfg.fillRect(x, y, 1, 1);

				if ( panel.changeProgressListener != null ) {
					int progress = (int) Utils.map(progress_cnt++, 0.0, panel.width*panel.height, 0.0, 100.0) + 1;
					panel.changeProgressListener.changeProgress(progress);
				}
			}
		}

		panel.repaint();
		if (panel.isVisible() == false) {
			panel.setVisible(true);
		}
	}
	
	private Color antialiasing(Complex z, Mandelbrot mandelbrot, ColorPalette colorPalette, Color color){
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