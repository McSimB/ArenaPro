package javax.microedition.lcdui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.elkware.midp.games.colorng.arena.high.R;
import com.elkware.midp.games.colorng.arena.high.MyCanvas;

import javax.microedition.midlet.MIDlet;
import javax.microedition.util.ContextHolder;
import javax.microedition.util.MainActivity;

public class Display {

    public static final int WIDTH = 132;
    public static final int HEIGHT = 176;
    public static int SCALE = 4;

    private static Display instance;
    private Displayable current;
    private boolean firstDisp;

    private Display(MIDlet midlet) {
    }

    public static Display getDisplay(MIDlet midlet) {
        if (instance == null) {
            instance = new Display(midlet);
        }
        return instance;
    }

    public void setCurrent(final Displayable nextDisplayable) {
        MainActivity activity = ContextHolder.getContext();
        final RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(WIDTH * SCALE, HEIGHT * SCALE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        final ViewGroup mainLayout = activity.findViewById(R.id.mainLayout);
        boolean remove = false;
        if (current != null) remove = true;
        current = nextDisplayable;
        if (Thread.currentThread().getName().equals("main")) {
            if (remove) {
                mainLayout.removeViewAt(mainLayout.getChildCount() - 1);
            }
            mainLayout.addView(nextDisplayable.getView(), params);
        } else {
            final boolean finalRemove = remove;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (finalRemove) {
                        mainLayout.removeViewAt(mainLayout.getChildCount() - 1);
                    }
                    mainLayout.addView(nextDisplayable.getView(), params);
                }
            });
        }
        initButtons(mainLayout);
    }

    public Displayable getCurrent() {
        return current;
    }

    public void setCurrent(Alert alert, Displayable nextDisplayable) {
        setCurrent(nextDisplayable);
    }

    public static void vibrate(int var1) {
    }

    public boolean isShown() {
        return false;
    }

    public void updateCommandSet() {
        Command[] screenCommands = current.getCommands();
        int screenComCount = current.getCommandCount();
        for (int i = 0; i < screenComCount; i++)
            screenCommands[i].setInternalID(i);
    }

    private void initButtons(ViewGroup view) {
        final Button b1 = view.findViewById(R.id.button1);
        final Button b2 = view.findViewById(R.id.button2);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        if (current.getCommands() != null) {
            for (int i = 0; i < current.getCommandCount(); i++) {
                if (current.getCommands()[i] != null) {
                    switch (current.getCommands()[i].getCommandType()) {
                        case Command.SCREEN:
                        case Command.OK:
                        case Command.HELP:
                        case Command.ITEM:
                            b1.setVisibility(View.VISIBLE);
                            b1.setText(current.getCommands()[i].getLabel());
                        case Command.BACK:
                        case Command.CANCEL:
                        case Command.STOP:
                        case Command.EXIT:
                            b2.setVisibility(View.VISIBLE);
                            b2.setText(current.getCommands()[i].getLabel());
                    }
                }
            }
        }
        if (current instanceof MyCanvas && firstDisp) {
            b1.setVisibility(View.VISIBLE);
            b1.setText("");
            b2.setVisibility(View.VISIBLE);
            b2.setText("");
        }
        firstDisp = true;
    }
}