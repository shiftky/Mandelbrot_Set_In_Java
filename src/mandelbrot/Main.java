package mandelbrot;

import mandelbrot.gui.MainWindow;

public class Main {
	public static void main(String[] args) {
		MainWindow mandelbrot = new MainWindow("Mandelbrot set in Java");
		mandelbrot.setVisible(true);
	}
}
