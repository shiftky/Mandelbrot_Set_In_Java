package mandelbrot.events;

import java.util.EventListener;

public interface ZoomEventListener extends EventListener {
	public void zoomIn(int x1, int x2, int y1, int y2);
	public void zoomOut();
}
