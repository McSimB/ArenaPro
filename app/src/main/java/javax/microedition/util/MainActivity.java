package javax.microedition.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elkware.midp.games.colorng.arena.high.R;

import javax.microedition.midlet.MIDlet;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContextHolder.setContext(this);

        setContentView(R.layout.main);
        Button b1 = findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display.getCurrent().callKeyPressed(Canvas.KEY_DISPLAY1);
            }
        });
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display.getCurrent().callKeyPressed(Canvas.KEY_DISPLAY2);
            }
        });

        MIDlet midlet = (MIDlet) getApplication();
        midlet.startApp();
    }
}
