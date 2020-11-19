import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.io.FileNotFoundException;

public class MandelbrotTest {


    // COLOR SCHEME
    static final boolean LOAD = true;
    static final String schemePath = "volcano";                     // Path for colors

    // MANDELBROT CALCULATION
    static final int MAX = 255;
    static final int GRID_RADIUS = 512;

    // WINDOW SIZE
    static final int CANVAS_SIZE = 1024 + 1;


    static boolean running = true;
    static ColorScheme colorScheme;

    public static void main (String[] args) throws FileNotFoundException, EnumConstantNotPresentException {
        // Open window
        openWindow();
        Mandelbrot.gridRadius = GRID_RADIUS;

        // Load Colors
        if (LOAD) colorScheme = new ColorScheme(schemePath, MAX);
        else colorScheme = new ColorScheme(ColorScheme.Type.NONE, MAX);

        System.out.println("Creating initial mandelbrot");
        Mandelbrot.current = new Mandelbrot(0.10259, -0.641, 0.0086, colorScheme);
        Mandelbrot.current.draw();

        // Create new mandelbrot from console
        new MandelbrotInput("Mandelbrot input processing",  colorScheme).start();

        // Manipulate mandelbrut with mouse
        new MandelbrotControls("Mandelbrot Controls").start();

    }



    // Window
    static void openWindow(){
        int windowRadius = GRID_RADIUS +1;
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setScale(-windowRadius, windowRadius);
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius( 1 / (float)(windowRadius * 3) );
        StdDraw.show(0);
    }
}
