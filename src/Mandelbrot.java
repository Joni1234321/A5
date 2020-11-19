import java.awt.*;

public class Mandelbrot {

    public static volatile Mandelbrot current;
    public static int gridRadius = 512;  // Default

    ColorScheme scheme;

    double cx, cy, width;
    int max = 255;

    public Mandelbrot (double cx, double cy, double width, ColorScheme scheme) {
        this.cx = cx;
        this.cy = cy;
        this.width = width;

        this.scheme = scheme;
    }
    public Mandelbrot (double cx, double cy, double width, int max, ColorScheme scheme) {
        this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.max = max;

        this.scheme = scheme;
    }

    public void setCenter (double cx, double cy) {
        this.cx = cx;
        this.cy = cy;
    }
    public void setWidth (double width) {
        this.width = width;
    }

    public void setScheme(ColorScheme scheme) {
        this.scheme = scheme;
    }


    public void draw () {
        draw(0);
    }
    public void draw (int resolution) {
        StdDraw.clear();

        Color[] colors = scheme.getScheme();

        int increment = 1 << resolution;
        for (int y = -gridRadius ; y <= gridRadius; y += increment) {
            for (int x = -gridRadius; x <= gridRadius; x += increment){
                //System.out.println(cx + "|" + width / 2.0 + "|" + width*x + "|" + (width * x) / (windowScaleRadius * 2.0));

                Complex z = new Complex(
                        cx + (width * x) / (gridRadius * 2.0),
                        cy + (width * y) / (gridRadius * 2.0));

                StdDraw.setPenColor(colors[iterate(z)]);
                //StdDraw.setPenColor(colorNormal(iterate(z)));

                StdDraw.filledSquare(x+.5f,y+.5f, 1/2f);
            }
        }

        StdDraw.show(0);
    }

    int iterate (Complex z0) {
        Complex z = new Complex(z0);
        for (int i = 0; i < max; i++) {
            if (z.abs() > 2.0) return i;
            z = z.times(z).plus(z0); // z^2 + z_0
        }
        return max;
    }

    public double[] screenToWorldSpace (double x, double y){
        double[] coord = new double[2];
        coord[0] = cx + (width * x) / (gridRadius * 2.0);
        coord[1] = cy + (width * y) / (gridRadius * 2.0);
        return coord;
    }

    // Convert a distance between two coords in screen to the same distance but in worldspace
    public double screenToWorldSpace (double width) {
        return (width * this.width) / (gridRadius * 2.0);
    }
}
