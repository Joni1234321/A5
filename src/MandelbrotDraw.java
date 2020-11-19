public class MandelbrotDraw implements Runnable {
    Thread mainThread;
    String threadName;

    Mandelbrot brot;
    int i = 0;

    public boolean running = true;

    public MandelbrotDraw (String threadName) {
        this.threadName = threadName;
    }

    public void run () {
        while (running) {
            while (i >= 0) {
                System.out.println("Drawing: " + i);
                brot.draw(i);
                i--;
            }
        }
    }

    public void start () {
        System.out.println("Starting " +  threadName );

        if (mainThread == null) {
            mainThread = new Thread(this, threadName);
            mainThread.start();
        }
    }

    public void incrementalDraw (Mandelbrot brot) {
        this.brot = brot;
        i = 3;
        start();
    }
}
