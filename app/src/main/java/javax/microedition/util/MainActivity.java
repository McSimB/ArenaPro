package javax.microedition.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elkware.midp.games.colorng.arena.high.R;

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
    private LifeCycleListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContextHolder.setContext(this);

        setContentView(R.layout.main);

        int permission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
        if (mListener != null) {
            mListener.paused();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mListener != null) {
            mListener.resumed();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mMIDlet.destroyApp();
        super.onDestroy();
    }

    public void setListener(LifeCycleListener listener) {
        mListener = listener;
    }
}
