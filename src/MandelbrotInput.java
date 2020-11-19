import java.awt.*;
import java.util.Scanner;

public class MandelbrotInput implements Runnable {

    Thread mainThread;
    String threadName;

    public ColorScheme colorScheme;

    public boolean running = true;

    public MandelbrotInput (String threadName, ColorScheme colorScheme) {
        this.threadName = threadName;
        this.colorScheme = colorScheme;
    }

    public void run () {
        while (running) {
            System.out.println("Write here for new mandelbrot");
            Mandelbrot.current = getMandelbrot(colorScheme);
            Mandelbrot.current.draw();
            System.out.println("Mandelbrot drawn\n");
        }
    }

    public void start () {
        if (mainThread == null) {
            mainThread = new Thread(this, threadName);
            mainThread.start();
        }
    }


    Mandelbrot getMandelbrot (ColorScheme colorScheme) {
        double x = getInput("Enter x: ", "Not a double try again:");

        // If x is designated code, then load specific preset
        Mandelbrot brot = loadedFromCode((int)x, colorScheme);
        if (brot != null) return brot;

        double y = getInput("Enter y: ", "Not a double try again:");
        double width = getInput("Enter width (0 to start all over2): ", "Not a double try again:");
        if (width <= 0) return getMandelbrot(colorScheme);


        return new Mandelbrot(x, y, width, colorScheme);
    }

    Mandelbrot loadedFromCode(int code, ColorScheme colorScheme) {
        Mandelbrot brot = null;
        switch (code) {
            case 42 -> brot = new Mandelbrot(0.10684, -0.63675, 0.0085, colorScheme);
            case 420-> brot = new Mandelbrot(0.10259, -0.641, 0.0086, colorScheme);
            case 1337 -> brot = new Mandelbrot(-.5, 0, 2, colorScheme);
            case 314 -> brot = new Mandelbrot(0.10087,-0.63198, 0.0003, colorScheme);
        }
        return brot;
    }



    // Input processing
    Scanner console;
    double getInput (String s, String err) {
        console = new Scanner(System.in);

        System.out.print(s);
        while (!console.hasNextDouble()){
            System.out.print(err);
            console.nextLine();
        }
        return console.nextDouble();
    }



}
