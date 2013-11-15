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

		int progress_cnt = 0;
		ColorPalette colorPalette = new ColorPalette(panel.palette, panel.smoothing);
		for (int x = 0; x < panel.width; x++) {
			for (int y = 0; y < panel.height; y++) {
				Complex z = new Complex(Utils.map((double) x, 0.0, (double) panel.width, panel.r1, panel.r2),
										Utils.map((double) y, 0.0, (double) panel.height, panel.i1, panel.i2));

				Color color = colorPalette.color(Mandelbrot.mandel(z, panel.loopCount));
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
}
