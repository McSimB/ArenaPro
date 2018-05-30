package javax.microedition.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elkware.midp.games.colorng.arena.high.R;

import java.util.ArrayList;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class MainActivity extends Activity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private MIDlet mMIDlet;
    private ArrayList<LifeCycleListener> mListeners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextHolder.setContext(this);
        setContentView(R.layout.main);
        mListeners = new ArrayList<LifeCycleListener>();

        int permission;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            permission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        }

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display.getDisplay().getCurrent().callKeyPressed(Canvas.KEY_DISPLAY1);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display.getDisplay().getCurrent().callKeyPressed(Canvas.KEY_DISPLAY2);
            }
        });

        mMIDlet = (MIDlet) getApplication();
        mMIDlet.startApp();
    }

    @Override
    protected void onPause() {
        mMIDlet.pauseApp();
        for (LifeCycleListener listener : mListeners) {
            listener.paused();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        for (LifeCycleListener listener : mListeners) {
            listener.resumed();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mListeners != null) {
            for (LifeCycleListener listener : mListeners) {
                listener.closed();
            }
            mListeners = null;
        }
        if (mMIDlet != null) {
            mMIDlet.destroyApp();
            mMIDlet = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
    }

    public void addListener(LifeCycleListener listener) {
        mListeners.add(listener);
    }
}
