package mandelbrot.events;

import java.util.EventObject;

public class DrawEndEvent extends EventObject {
	public DrawEndEvent(Object source) {
		super(source);
	}
}