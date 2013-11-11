package mandelbrot;

import java.util.EventObject;

public class EnlargeEvent extends EventObject {
	public int x1, x2, y1, y2;

	public void setPoints(int a, int b, int c, int d){
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
	}

	public EnlargeEvent(Object source){
		super(source);
	}
}