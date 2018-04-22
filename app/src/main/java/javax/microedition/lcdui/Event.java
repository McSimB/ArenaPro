package javax.microedition.lcdui;

public abstract class Event implements Runnable {

    public abstract void process();

    public abstract void recycle();

    public void run() {
        process();
        recycle();
    }

    public abstract boolean placeableAfter(Event event);
}
