package mandelbrot;

import java.util.EventListener;

public interface EnlargeListener extends EventListener {
	public void changeDrawingArea(int x1, int x2, int y1, int y2);
}
