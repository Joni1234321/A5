public class ComplexTest {
    public static void main (String args[]) {
        Complex z1 = new Complex(1,2);
        Complex z2 = new Complex(4,5);

        System.out.println(z1.plus(z2));
        System.out.println(z1.times(z2));

    }

    // MANDELBROT CALCULATION
    private static final int MAX = 20;
    public static int iterate (Complex z0) {
        Complex z = new Complex(z0);
        for (int i = 0; i < MAX; i++) {
            if (z.abs() > 2.0) return i;
            z = z.times(z).plus(z0); // z^2 + z_0
        }
        return MAX;
    }
}
