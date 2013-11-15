package mandelbrot;

public class Mandelbrot {
	public static int mandel(Complex z, int max){
		Complex c = new Complex(z.re, z.im);
		double abs = z.abs();
		int score = 0;

		while (abs < 3.5 && score < max){
			z = z.sqr();
			z = z.add(c);
			abs = z.abs();
			score++;
		}

		if ( score == max ) {
			return -1;
		} else {
			abs += 0.000000001;
			return 256*score + (int)(255.0 * Math.log(3.5/abs) / Math.log((z.re*z.re + z.im*z.im) /abs));
		}
	}
}
