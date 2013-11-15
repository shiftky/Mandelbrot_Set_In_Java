package mandelbrot;

class Complex{
	double re, im;
	
	public Complex(){
		re = 0.0;
		im = 0.0;
	}

	public Complex(double r, double i){
		re = r;
		im = i;
	}

	public Complex add (Complex a){
		return new Complex(this.re + a.re, this.im + a.im);
	}
	
	public Complex sqr(){
		return new Complex(this.re * this.re-this.im*this.im, 2.0*this.re*this.im);
	}
	
	public double abs(){
		return Math.sqrt(this.re * this.re + this.im * this.im);
	}
}