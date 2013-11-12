package mandelbrot.events;

import java.util.EventListener;

public interface MouseMovedListener extends EventListener {
	void changeCursorPosition(int x, int y);
}
