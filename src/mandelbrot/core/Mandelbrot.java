package mandelbrot.core;

import mandelbrot.utils.Complex;

public class Mandelbrot {
	private int max;
	
	public Mandelbrot(int m) {
		max = m;
	}

	public int calc(Complex z){
		Complex c = new Complex(z.re, z.im);
		Complex z2 = new Complex();

	    int count = 0;
	    while (z2.abs() < 4.0 && count < max) {
	    	z = z.sqr();
	    	z = z.add(c);
	    	z2 = new Complex(z.re*z.re, z.im*z.im);
	    	count++;
	    }

	    if (count == 0 || count == max) {
	      return 0;
	    } else {
		    double zM2 = z.abs();
		    zM2 += 0.000000001;
		    return 256 * count + (int)(255.0 * Math.log(4 / zM2) / Math.log((z2.re + z2.im) / zM2));
		}
	}
}