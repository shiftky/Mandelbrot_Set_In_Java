package mandelbrot.events;

import java.util.EventObject;

public class DrawStartEvent extends EventObject {
	public DrawStartEvent(Object source) {
		super(source);
	}
}
