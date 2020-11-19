public class Complex {


    private double re = 0.0, im = 0.0;

    public Complex() {

    }
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public Complex(Complex z) {
        re = z.getRe();
        im = z.getIm();
    }

    public double getRe () {
        return re;
    }
    public double getIm() {
        return im;
    }


    public double abs () {
        return Math.sqrt(re*re+im*im);
    }
    public Complex plus (Complex other) {
        return new Complex (re + other.getRe(), im + other.getIm());
    }
    public Complex times (Complex other){
        return new Complex (re * other.getRe() - im * other.getIm(), im * other.getRe() + re * other.getIm());
    }

    public String toString() {
        String sign = Math.signum(im) < 0 ? "" : "+";
        return "(" + re + " "+ sign + " " + im + "i" + ")";
    }
}
