package javax.microedition.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.pointer.VirtualKeyboard;
import javax.microedition.midlet.MIDlet;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContextHolder.setContext(this);

        MIDlet midlet = (MIDlet) getApplication();
        try {
            Font.setSize(Font.SIZE_SMALL, 13);
            Font.setSize(Font.SIZE_MEDIUM, 15);
            Font.setSize(Font.SIZE_LARGE, 20);
            Font.setApplyDimensions(false);

            Canvas.setVirtualSize(132, 176, true, true); //screenScaleToFit, screenKeepAspectRatio
            Canvas.setFilterBitmap(true);
            Canvas.setBackgroundColor(0xFFD0D0D0);

            loadVirtualKeyboard();

            midlet.startApp();
            finish();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void loadVirtualKeyboard() {
        VirtualKeyboard vk = new VirtualKeyboard();

        vk.setOverlayAlpha(64);
        vk.setHideDelay(-1);
        vk.setLayoutEditKey(Canvas.convertAndroidKeyCode(KeyEvent.KEYCODE_MENU));

        vk.setColor(VirtualKeyboard.BACKGROUND, 0xD0D0D0);
        vk.setColor(VirtualKeyboard.FOREGROUND, 0x000080);
        vk.setColor(VirtualKeyboard.BACKGROUND_SELECTED, 0x000080);
        vk.setColor(VirtualKeyboard.FOREGROUND_SELECTED, 0xFFFFFF);
        vk.setColor(VirtualKeyboard.OUTLINE, 0xFFFFFF);

        Display.getDisplay((MIDlet) getApplication()).setOverlay(vk);
    }
}
