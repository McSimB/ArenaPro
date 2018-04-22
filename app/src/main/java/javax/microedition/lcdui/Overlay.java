package javax.microedition.lcdui;

import android.graphics.RectF;

public interface Overlay {

    void setTarget(Canvas canvas);

    void resize(RectF screen, RectF virtualScreen);

    void paint(Graphics g);

    void pointerPressed(int pointer, float x, float y);

    void pointerReleased(int pointer, float x, float y);

    void show();

    void hide();
}