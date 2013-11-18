package mandelbrot.utils;

public class GraphArea {
	public double xs, xe, ys, ye;
	
	public GraphArea(double x1, double x2, double y1, double y2) {
		xs = x1;
		xe = x2;
		ys = y1;
		ye = y2;
	}
	
	public boolean eql(GraphArea ga) {
		return this.xs == ga.xs && this.xe == ga.xe && this.ys == ga.ys && this.ye == ga.ye;
	}
}