import java.awt.*;

public class MandelbrotControls implements Runnable {
    Thread mainThread;
    String threadName;

    static MandelbrotDraw mandelbrotDraw = new MandelbrotDraw("Incremental draw");
    public boolean running = true;

    public MandelbrotControls (String threadName) {
        this.threadName = threadName;
    }

    public void run () {
        while (running) {

            if(StdDraw.mousePressed()) {                    // Mousedown
                if (Mandelbrot.current == null) continue;

                double x1 = StdDraw.mouseX();
                double y1 = StdDraw.mouseY();

                while (StdDraw.mousePressed()) continue;    // Mouseup

                double x2 = StdDraw.mouseX();
                double y2 = StdDraw.mouseY();

                double r = Math.max(Math.abs(y2-y1), Math.abs(x2-x1)) / 2.0;
                double x = (x2+x1) / 2.0;
                double y = (y2+y1) / 2.0;

                zoom(x, y, r);

            }
        }
    }

    public void start () {
        if (mainThread == null) {
            mainThread = new Thread(this, threadName);
            mainThread.start();
        }
    }

    void drawZoomArea (double x, double y, double r) {
        StdDraw.setPenColor(Color.green);
        int thickness = 2;
        double radius = r + thickness;
        StdDraw.filledRectangle(x + radius, y, thickness, radius + thickness);
        StdDraw.filledRectangle(x - radius, y, thickness, radius + thickness);
        StdDraw.filledRectangle(x, y + radius, radius + thickness, thickness);
        StdDraw.filledRectangle(x, y - radius, radius + thickness, thickness);

        StdDraw.show(0);
        // System.out.println("Zooming to x:" + x + " y:" + y + " r:" + r);
    }

    void zoom (double x, double y, double r) {
        drawZoomArea(x, y, r);

        double[] coord = Mandelbrot.current.screenToWorldSpace(x, y);
        double width = Mandelbrot.current.screenToWorldSpace(r * 2);

        Mandelbrot.current.setCenter(coord[0], coord[1]);
        Mandelbrot.current.setWidth(width);
        Mandelbrot.current.draw();
    }
}
