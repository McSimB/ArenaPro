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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.HashMap;

import javax.microedition.lcdui.event.CanvasEvent;
import javax.microedition.util.ContextHolder;

public abstract class Canvas extends Displayable {

    public static final int KEY_POUND = 35;
    public static final int KEY_STAR = 42;
    public static final int KEY_NUM0 = 48;
    public static final int KEY_NUM1 = 49;
    //public static final int KEY_NUM2 = 50;
    //public static final int KEY_NUM3 = 51;
    //public static final int KEY_NUM4 = 52;
    //public static final int KEY_NUM5 = 53;
    //public static final int KEY_NUM6 = 54;
    //public static final int KEY_NUM7 = 55;
    //public static final int KEY_NUM8 = 56;
    //public static final int KEY_NUM9 = 57;
    public static final int KEY_UP = 1; // -1;
    public static final int KEY_DOWN = 6; // -2;
    public static final int KEY_LEFT = 2; // -3;
    public static final int KEY_RIGHT = 5; // -4;
    public static final int KEY_FIRE = 8; // -5;
    public static final int KEY_SOFT_LEFT = -12; // -6;
    public static final int KEY_SOFT_RIGHT = -4;// -7;
    //public static final int KEY_CLEAR = -8;
    public static final int KEY_SEND = -10;
    public static final int KEY_END = -11;
    //public static final int UP = 1;
    //public static final int LEFT = 2;
    //public static final int RIGHT = 5;
    //public static final int DOWN = 6;
    //public static final int FIRE = 8;
    //public static final int GAME_A = 9;
    //public static final int GAME_B = 10;
    //public static final int GAME_C = 11;
    //public static final int GAME_D = 12;
    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, Integer> androidToMIDP = new HashMap<>();
    private static int virtualWidth;
    private static int virtualHeight;
    private static boolean scaleToFit;
    private static boolean keepAspectRatio;
    private static boolean filter;
    private static int backgroundColor;
    static {

        mapKeyCode(KeyEvent.KEYCODE_0, 48);
        mapKeyCode(KeyEvent.KEYCODE_1, 49);
        mapKeyCode(KeyEvent.KEYCODE_2, 50);
        mapKeyCode(KeyEvent.KEYCODE_3, 51);
        mapKeyCode(KeyEvent.KEYCODE_4, 52);
        mapKeyCode(KeyEvent.KEYCODE_5, 53);
        mapKeyCode(KeyEvent.KEYCODE_6, 54);
        mapKeyCode(KeyEvent.KEYCODE_7, 55);
        mapKeyCode(KeyEvent.KEYCODE_8, 56);
        mapKeyCode(KeyEvent.KEYCODE_9, 57);
        mapKeyCode(KeyEvent.KEYCODE_STAR, 42);
        mapKeyCode(KeyEvent.KEYCODE_POUND, 35);
        mapKeyCode(KeyEvent.KEYCODE_DPAD_UP, -1);
        mapKeyCode(KeyEvent.KEYCODE_DPAD_DOWN, -2);
        mapKeyCode(KeyEvent.KEYCODE_DPAD_LEFT, -3);
        mapKeyCode(KeyEvent.KEYCODE_DPAD_RIGHT, -4);
        mapKeyCode(KeyEvent.KEYCODE_DPAD_CENTER, -5);
        mapKeyCode(KeyEvent.KEYCODE_SOFT_LEFT, -6);
        mapKeyCode(KeyEvent.KEYCODE_SOFT_RIGHT, -7);
        mapKeyCode(KeyEvent.KEYCODE_CLEAR, -8);
        mapKeyCode(KeyEvent.KEYCODE_CALL, -10);
        mapKeyCode(KeyEvent.KEYCODE_ENDCALL, -11);
    }

    private final Object paintSync = new Object();
    private PaintEvent paintEvent = new PaintEvent();
    private boolean surfaceValid = false;
    private boolean continuePaint = true;
    private InnerView view;
    private SurfaceHolder holder;
    private Graphics graphics = new Graphics();
    private int width, height;
    private int displayWidth;
    private int displayHeight;
    private Image offscreen;
    private int onX, onY, onWidth, onHeight;
    private Overlay overlay;

    public Canvas() {
        displayWidth = ContextHolder.getDisplayWidth();
        displayHeight = ContextHolder.getDisplayHeight();

        updateSize(false);
    }

    private static void mapKeyCode(int androidKeyCode, int midpKeyCode) {
        androidToMIDP.put(androidKeyCode, midpKeyCode);
    }

    public static int convertAndroidKeyCode(int keyCode) {
        Integer res = androidToMIDP.get(keyCode);

        if (res != null) {
            return res;
        } else {
            return -(keyCode << 8);
        }
    }

    @SuppressWarnings("SameParameterValue")
    public static void setVirtualSize(int virtualWidth, int virtualHeight,
                                      boolean scaleToFit, boolean keepAspectRatio) {
        Canvas.virtualWidth = virtualWidth;
        Canvas.virtualHeight = virtualHeight;

        Canvas.scaleToFit = scaleToFit;
        Canvas.keepAspectRatio = keepAspectRatio;
    }

    @SuppressWarnings("SameParameterValue")
    public static void setBackgroundColor(int color) {
        backgroundColor = color | 0xFF000000;
    }

    @SuppressWarnings("SameParameterValue")
    public static void setFilterBitmap(boolean filter) {
        Canvas.filter = filter;
    }

    public void setOverlay(Overlay ov) {
        if (overlay != null) {
            overlay.setTarget(null);
        }

        if (ov != null) {
            ov.setTarget(this);
        }

        overlay = ov;
    }

    private void updateSize(boolean post) {
        if (virtualWidth < 0) {
            if (virtualHeight < 0) {
                width = displayWidth;
                height = displayHeight;
            } else {
                width = displayWidth * virtualHeight / displayHeight;
                height = virtualHeight;
            }
        } else if (virtualHeight < 0) {
            width = virtualWidth;
            height = displayHeight * virtualWidth / displayWidth;
        } else {
            width = virtualWidth;
            height = virtualHeight;
        }

        if (scaleToFit) {
            if (keepAspectRatio) {
                onWidth = displayWidth;
                onHeight = height * displayWidth / width;

                if (onHeight > displayHeight) {
                    onHeight = displayHeight;
                    onWidth = width * displayHeight / height;
                }
            } else {
                onWidth = displayWidth;
                onHeight = displayHeight;
            }
        } else {
            onWidth = width;
            onHeight = height;
        }

        onX = (displayWidth - onWidth) / 2;
        onY = (displayHeight - onHeight) / 2;

        RectF screen = new RectF(0, 0, displayWidth, displayHeight);
        RectF virtualScreen = new RectF(onX, onY, onX + onWidth, onY + onHeight);

        if (post) {
            if ((offscreen == null || offscreen.getWidth() != width || offscreen.getHeight() != height)) {
                offscreen = Image.createImage(width, height);
            }
        }

        if (overlay != null) {
            overlay.resize(screen, virtualScreen);
        }

//		System.out.println();
//		System.out.println("Canvas size updated:");
//		System.out.println("Virtual = " + virtualWidth + " x " + virtualHeight);
//		System.out.println("Display = " + displayWidth + " x " + displayHeight);
//		System.out.println("Canvas = " + width + " x " + height);
//		System.out.println("Offscreen = " + onWidth + " x " + onHeight);
//		System.out.println();
    }

    public View getDisplayableView() {
        // Naik add if hasParentActivity...
        if (view == null && hasParentActivity()) {
            view = new InnerView(getParentActivity());

            holder = view.getHolder();
            holder.addCallback(new SurfaceCallback());

            view.setFocusableInTouchMode(true);
        }

        return view;
    }

    public void clearDisplayableView() {
        synchronized (paintSync) {
            view = null;
            holder = null;
        }
    }

    public void setFullScreenMode() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void paint(Graphics g);

    public void repaint() {
        getEventQueue().postEvent(paintEvent);
    }

    public void serviceRepaints() {
        EventQueue queue = getEventQueue();

        synchronized (queue) {
            if (queue.currentEvent() == paintEvent) {
                try {
                    queue.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            } else if (queue.removeEvents(paintEvent)) {
                paintEvent.run();
            }
        }
    }

    public void showNotify() {
    }

    public void hideNotify() {
    }

    public void keyPressed(int keyCode) {
    }

    public void keyReleased(int keyCode) {
    }

    private class InnerView extends SurfaceView {

        public InnerView(Context context) {
            super(context);
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    if (overlay != null) {
                        overlay.show();
                    }

                case MotionEvent.ACTION_POINTER_DOWN:
                    int index = event.getActionIndex();

                    if (overlay != null) {
                        overlay.pointerPressed(index, event.getX(index), event.getY(index));
                    }

                    break;

                case MotionEvent.ACTION_UP:
                    if (overlay != null) {
                        overlay.hide();
                    }

                case MotionEvent.ACTION_POINTER_UP:
                    index = event.getActionIndex();

                    if (overlay != null) {
                        overlay.pointerReleased(index, event.getX(index), event.getY(index));
                    }

                    break;

                default:
                    return super.onTouchEvent(event);
            }

            return true;
        }
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        public void surfaceChanged(SurfaceHolder holder, int format, int newwidth, int newheight) {
            synchronized (paintSync) {
                displayWidth = newwidth;
                displayHeight = newheight;

                updateSize(true);
            }

            postEvent(paintEvent);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            synchronized (paintSync) {
                surfaceValid = true;
                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.SHOW_NOTIFY));
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            synchronized (paintSync) {
                surfaceValid = false;
                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.HIDE_NOTIFY));
            }
        }
    }

    private class PaintEvent extends Event implements EventFilter {

        public void process() {
            synchronized (paintSync) {
                if (!surfaceValid) {
                    return;
                }

                graphics.setCanvas(offscreen.getCanvas());

                try {
                    paint(graphics);
                } catch (Throwable t) {
                    t.printStackTrace();

                    graphics.resetTranslation();
                    graphics.resetClip();
                }
                // Naik add if !null
                if (holder != null) {
                    graphics.setCanvas(holder.lockCanvas());
                } else {
                    Log.d("Canvas", "holder = null");
                }

                if (graphics.hasCanvas()) {
                    graphics.clear(backgroundColor);
                    graphics.drawImage(offscreen, onX, onY, onWidth, onHeight, filter, 255);

                    if (overlay != null) {
                        overlay.paint(graphics);
                    }
                    // Naik add if !null
                    if (holder != null) {
                        // McSimB add try
                        //try {
                            holder.unlockCanvasAndPost(graphics.getCanvas());
                        //} catch (IllegalStateException ise) {
                        //    ise.printStackTrace();
                        //}
                    }
                }

                continuePaint = true;
            }
        }

        public void recycle() {
        }

        public boolean placeableAfter(Event event) {
            if (event == this) {
                if (continuePaint) {
                    continuePaint = false;
                    return true;
                } else {
                    return false;
                }
            }

            return true;
        }

        public boolean accept(Event event) {
            return event == this;
        }
    }
}