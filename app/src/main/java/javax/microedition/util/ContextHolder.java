package javax.microedition.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ContextHolder {

    private static MainActivity context;
    private static Display display;

    public static void setContext(MainActivity cx) {
        context = cx;
    }

    public static MainActivity getContext() {
        if (context == null) {
            throw new IllegalStateException("call setContext() before calling getContext()");
        }

        return context;
    }

    public static Display getDisplay() {
        if (display == null) {
            WindowManager manager = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                display = manager.getDefaultDisplay();
            }
        }
        return display;
    }

    @SuppressWarnings("unused")
    public static int getDisplayWidth() {
        Point point = new Point();
        getDisplay().getSize(point);
        return point.x;
    }

    public static int getDisplayHeight() {
        Point point = new Point();
        getDisplay().getSize(point);
        return point.y;
    }

    public static InputStream getResourceAsStream(String filename) throws IOException {
        if (filename.startsWith("/")) {
            filename = filename.substring(1);
        }
        return getContext().getAssets().open(filename);
    }

    public static File getCacheDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return getContext().getExternalCacheDir();
        } else {
            return getContext().getCacheDir();
        }
    }
}