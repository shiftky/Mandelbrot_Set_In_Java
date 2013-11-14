package mandelbrot;

public class Mandelbrot {
	public static int mandel(Complex z, int max){
		Complex c = new Complex(z.re, z.im);

		int score = 0;
		while (score < max){
			z = z.sqr();
			z = z.add(c);
			if (z.abs() >= 3.5){
				break;
			}
			score += 1;
		}
		return score;
	}
}
