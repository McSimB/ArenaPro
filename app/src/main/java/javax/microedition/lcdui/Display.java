/*
 * Copyright 2012 Kulikov Dmitriy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.microedition.lcdui;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;

import javax.microedition.midlet.MIDlet;
import javax.microedition.util.ContextHolder;

public class Display {

    public static class ScreenActivity extends MicroActivity {

        public void onResume() {
            super.onResume();
            Display.getDisplay(null).changeActivity(this);
        }
    }

    public static class CanvasActivity extends MicroActivity {

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setFullScreenMode();
        }

        public void onResume() {
            super.onResume();
            Display.getDisplay(null).changeActivity(this);
        }
    }

    private static class AlertAdvancer implements CommandListener {

        public void commandAction(Command c, Displayable d) {
            Display.getDisplay(null).showCurrent();
        }
    }

    private static Display instance;

    private MIDlet context;
    private Displayable current;
    private MicroActivity activity;

    private static PowerManager powermanager;
    private static PowerManager.WakeLock wakelock;
    private static Vibrator vibrator;

    private AlertAdvancer advancer;
    private Overlay overlay;

    public static Display getDisplay(MIDlet midlet) {
        if (instance == null) {
            if (midlet == null) {
                if (ContextHolder.getContext() instanceof MIDlet) {
                    midlet = (MIDlet) ContextHolder.getContext();
                } else {
                    throw new IllegalStateException("could not find default MIDlet");
                }
            }

            instance = new Display(midlet);
        }

        return instance;
    }

    private Display(MIDlet midlet) {
        context = midlet;
        advancer = new AlertAdvancer();
    }

    public void setCurrent(Displayable disp) {
        changeCurrent(disp);
        showCurrent();
    }

    public void setCurrent(Alert alert, Displayable disp) {
        changeCurrent(disp);

        alert.showDialog(activity != null ? activity : context);

        if (alert.finiteTimeout()) {
            alert.setCommandListener(advancer);
            (new Thread(alert)).start();
        }
    }

    public void setOverlay(Overlay overlay) {
        this.overlay = overlay;
    }

    private void changeCurrent(Displayable disp) {
        if (current instanceof Canvas) {
            ((Canvas) current).setOverlay(null);
        }

        if (disp instanceof Canvas) {
            ((Canvas) disp).setOverlay(overlay);
        }

        current = disp;
    }

    private void changeActivity(MicroActivity activity) {
        this.activity = activity;
        showCurrent();
    }

    private void showCurrent() {
        /*if (activity == null) {
            activity = ContextHolder.getCurrentActivity();
        }*/

        if (activity != null) {
            if (current instanceof Canvas) {
                if (activity instanceof CanvasActivity) {
                    activity.setCurrent(current);
                } else {
                    activity.startActivity(CanvasActivity.class);
                }
            } else {
                if (activity instanceof ScreenActivity) {
                    activity.setCurrent(current);
                } else {
                    activity.startActivity(ScreenActivity.class);
                }
            }
        } else {
            if (current instanceof Canvas) {
                context.startActivity(CanvasActivity.class);
            } else {
                context.startActivity(ScreenActivity.class);
            }
        }
    }

    public Displayable getCurrent() {
        return current;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean vibrate(int duration) {
        try {
            if (vibrator == null) {
                vibrator = (Vibrator) ContextHolder.getContext()
                        .getSystemService(Context.VIBRATOR_SERVICE);
            }

            if (vibrator != null) {
                vibrator.vibrate(duration);
            }

            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}