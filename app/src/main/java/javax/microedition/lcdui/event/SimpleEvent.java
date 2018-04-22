package javax.microedition.lcdui.event;

import javax.microedition.lcdui.Event;

public abstract class SimpleEvent extends Event {

    public void recycle() {
    }

    public boolean placeableAfter(Event event) {
        return true;
    }
}