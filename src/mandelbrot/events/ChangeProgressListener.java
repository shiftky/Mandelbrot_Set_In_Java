package mandelbrot.events;

import java.util.EventListener;

public interface ChangeProgressListener extends EventListener {
	public void changeProgress(int value);
}