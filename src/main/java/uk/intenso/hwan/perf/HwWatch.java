package uk.intenso.hwan.perf;

public class HwWatch {

    private long startTime;

    private long stopTime;

    private boolean started, stopped;

    private HwWatch() {
    }

    public HwWatch(long startTime) {
        reset();

    }

    private void reset() {
        this.started = true;
        this.stopped = false;
        this.stopTime = 0L;
        this.startTime = 0L;
    }

    public static HwWatch create() {
        var timer =  new HwWatch(System.nanoTime());
        timer.start();
        return timer;
    }

    public void start() {
        reset();
        start(System.nanoTime());
    }

    public void start(long startTime) {
        reset();
        this.startTime = startTime;
    }

    public void stop() {
        stopTime = System.nanoTime();
    }
    public void stop(long stopTime) {
       this.stopTime = stopTime;
    }

    public String getTimeTakenInSecondsAsString() {
        return String.format("%.2f",timeTakenInSeconds());
    }
    public double timeTakenInSeconds() {
        if (!stopped) stop();
        return (stopTime - startTime) / 1_000_000_000f;
    }

    public String getTimeTakenInMillisecondsAsString() {
        return String.format("%.2f", timeTakenInMilliseconds());
    }

    public double timeTakenInMilliseconds() {
        if (!stopped) stop();
        return (stopTime - startTime) / 1_000_000f;
    }
}
