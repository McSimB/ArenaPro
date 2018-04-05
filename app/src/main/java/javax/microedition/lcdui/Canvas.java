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

import java.util.HashMap;

import javax.microedition.lcdui.event.CanvasEvent;
import javax.microedition.lcdui.event.SimpleEvent;
import javax.microedition.util.ContextHolder;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public abstract class Canvas extends Displayable {

    public static final int KEY_POUND = 35;
    public static final int KEY_STAR = 42;
    public static final int KEY_NUM0 = 48;
    public static final int KEY_NUM1 = 49;
    public static final int KEY_NUM2 = 50;
    public static final int KEY_NUM3 = 51;
    public static final int KEY_NUM4 = 52;
    public static final int KEY_NUM5 = 53;
    public static final int KEY_NUM6 = 54;
    public static final int KEY_NUM7 = 55;
    public static final int KEY_NUM8 = 56;
    public static final int KEY_NUM9 = 57;
    public static final int KEY_UP = -1;
    public static final int KEY_DOWN = -2;
    public static final int KEY_LEFT = -3;
    public static final int KEY_RIGHT = -4;
    public static final int KEY_FIRE = -5;
    public static final int KEY_SOFT_LEFT = -6;
    public static final int KEY_SOFT_RIGHT = -7;
    public static final int KEY_CLEAR = -8;
    public static final int KEY_SEND = -10;
    public static final int KEY_END = -11;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 5;
    public static final int DOWN = 6;
    public static final int FIRE = 8;
    public static final int GAME_A = 9;
    public static final int GAME_B = 10;
    public static final int GAME_C = 11;
    public static final int GAME_D = 12;
    private static HashMap<Integer, Integer> androidToMIDP;
    private static HashMap<Integer, Integer> midpToAndroid;
    private static HashMap<Integer, Integer> keyCodeToGameAction;
    private static HashMap<Integer, Integer> gameActionToKeyCode;
    private static HashMap<Integer, String> keyCodeToKeyName;
    private static int virtualWidth;
    private static int virtualHeight;
    private static boolean scaleToFit;
    private static boolean keepAspectRatio;
    private static boolean filter;
    private static int backgroundColor;

    static {
        androidToMIDP = new HashMap();
        midpToAndroid = new HashMap();
        keyCodeToGameAction = new HashMap();
        gameActionToKeyCode = new HashMap();
        keyCodeToKeyName = new HashMap();

        mapKeyCode(KeyEvent.KEYCODE_0, 48, 0, "0");
        mapKeyCode(KeyEvent.KEYCODE_1, 49, 0, "1");
        mapKeyCode(KeyEvent.KEYCODE_2, 50, 1, "2");
        mapKeyCode(KeyEvent.KEYCODE_3, 51, 0, "3");
        mapKeyCode(KeyEvent.KEYCODE_4, 52, 2, "4");
        mapKeyCode(KeyEvent.KEYCODE_5, 53, 8, "5");
        mapKeyCode(KeyEvent.KEYCODE_6, 54, 5, "6");
        mapKeyCode(KeyEvent.KEYCODE_7, 55, 9, "7");
        mapKeyCode(KeyEvent.KEYCODE_8, 56, 6, "8");
        mapKeyCode(KeyEvent.KEYCODE_9, 57, 10, "9");
        mapKeyCode(KeyEvent.KEYCODE_STAR, 42, 11, "ASTERISK");
        mapKeyCode(KeyEvent.KEYCODE_POUND, 35, 12, "POUND");
        mapKeyCode(KeyEvent.KEYCODE_DPAD_UP, -1, 1, "UP");
        mapKeyCode(KeyEvent.KEYCODE_DPAD_DOWN, -2, 6, "DOWN");
        mapKeyCode(KeyEvent.KEYCODE_DPAD_LEFT, -3, 2, "LEFT");
        mapKeyCode(KeyEvent.KEYCODE_DPAD_RIGHT, -4, 5, "RIGHT");
        mapKeyCode(KeyEvent.KEYCODE_DPAD_CENTER, -5, 8, "SELECT");
        mapKeyCode(KeyEvent.KEYCODE_SOFT_LEFT, -6, 0, "SOFT1");
        mapKeyCode(KeyEvent.KEYCODE_SOFT_RIGHT, -7, 0, "SOFT2");
        mapKeyCode(KeyEvent.KEYCODE_CLEAR, -8, 0, "CLEAR");
        mapKeyCode(KeyEvent.KEYCODE_CALL, -10, 0, "SEND");
        mapKeyCode(KeyEvent.KEYCODE_ENDCALL, -11, 0, "END");

        mapGameAction(1, -1, "UP");
        mapGameAction(2, -3, "LEFT");
        mapGameAction(5, -4, "RIGHT");
        mapGameAction(6, -2, "DOWN");
        mapGameAction(8, -5, "SELECT");
        mapGameAction(9, 55, "7");
        mapGameAction(10, 57, "9");
        mapGameAction(11, 42, "ASTERISK");
        mapGameAction(12, 35, "POUND");
    }

    private final Object waiter = new Object();
    private final Object paintsync = new Object();
    private PaintEvent paintEvent = new PaintEvent();
    private boolean surfacevalid = false;
    private boolean continuepaint = true;
    private InnerView view;
    private SurfaceHolder holder;
    private SimpleEvent initViewEvent = new SimpleEvent() {
        public void process() {
            view = new InnerView(getParentActivity());

            holder = view.getHolder();
            holder.addCallback(new SurfaceCallback());

            view.setFocusableInTouchMode(true);

            synchronized (waiter) {
                waiter.notifyAll();
            }
        }
    };
    private Graphics graphics = new Graphics();
    private int width, height;
    private int displayWidth;
    private int displayHeight;
    private Image offscreen;
    private int onX, onY, onWidth, onHeight;
    private boolean useOffscreen;
    private Overlay overlay;
    public Canvas() {
        displayWidth = ContextHolder.getDisplayWidth();
        displayHeight = ContextHolder.getDisplayHeight();

        updateSize(false);
    }

    private static void mapKeyCode(int androidKeyCode, int midpKeyCode, int gameAction, String keyName) {
        androidToMIDP.put(androidKeyCode, midpKeyCode);
        midpToAndroid.put(midpKeyCode, androidKeyCode);

        keyCodeToGameAction.put(midpKeyCode, gameAction);
        keyCodeToKeyName.put(midpKeyCode, keyName);
    }

    private static void mapGameAction(int gameAction, int keyCode, String keyName) {
        gameActionToKeyCode.put(gameAction, keyCode);
        keyCodeToKeyName.put(keyCode, keyName);
    }

    public static int convertAndroidKeyCode(int keyCode) {
        Integer res = androidToMIDP.get(keyCode);

        if (res != null) {
            return res;
        } else {
            return -(keyCode << 8);
        }
    }

    public static int getKeyCode(int gameAction) {
        Integer res = gameActionToKeyCode.get(gameAction);

        if (res != null) {
            return res;
        } else {
            throw new IllegalArgumentException("unknown game action " + gameAction);
        }
    }

    public static int getGameAction(int keyCode) {
        Integer res = keyCodeToGameAction.get(keyCode);

        if (res != null) {
            return res;
        } else {
            return 0;
        }
    }

    public static String getKeyName(int keyCode) {
        return keyCodeToKeyName.get(keyCode);
    }

    public static void setVirtualSize(int virtualWidth, int virtualHeight, boolean scaleToFit, boolean keepAspectRatio) {
        Canvas.virtualWidth = virtualWidth;
        Canvas.virtualHeight = virtualHeight;

        Canvas.scaleToFit = scaleToFit;
        Canvas.keepAspectRatio = keepAspectRatio;
    }

    public static void setBackgroundColor(int color) {
        backgroundColor = color | 0xFF000000;
    }

    public static void setFilterBitmap(boolean filter) {
        Canvas.filter = filter;
    }

    public static boolean hasRepeatEvents() {
        return true;
    }

    public static boolean isDoubleBuffered() {
        return true;
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

        useOffscreen = true; // onWidth != width || onHeight != height;

        if (post) {
            if (useOffscreen && (offscreen == null || offscreen.getWidth() != width || offscreen.getHeight() != height)) {
                offscreen = Image.createImage(width, height);
            }

            postEvent(CanvasEvent.getInstance(this, CanvasEvent.SIZE_CHANGED, width, height));
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
        synchronized (paintsync) {
            view = null;
            holder = null;
        }
    }

    public void setFullScreenMode(boolean flag) {
    }

    public boolean hasPointerEvents() {
        return overlay == null;
    }

    public boolean hasPointerMotionEvents() {
        return overlay == null;
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

    public void repaint(int x, int y, int width, int height) {
        getEventQueue().postEvent(paintEvent);
    }

    public void serviceRepaints() {
        EventQueue queue = getEventQueue();

        synchronized (queue) {
            if (queue.currentEvent() == paintEvent) {
                try {
                    queue.wait();
                } catch (InterruptedException ie) {
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

    public void sizeChanged(int w, int h) {
    }

    public void keyPressed(int keyCode) {
    }

    public void keyRepeated(int keyCode) {
    }

    public void keyReleased(int keyCode) {
    }

    public void pointerPressed(int pointer, float x, float y) {
        if (pointer == 0) {
            pointerPressed(Math.round(x), Math.round(y));
        }
    }

    public void pointerDragged(int pointer, float x, float y) {
        if (pointer == 0) {
            pointerDragged(Math.round(x), Math.round(y));
        }
    }

    public void pointerReleased(int pointer, float x, float y) {
        if (pointer == 0) {
            pointerReleased(Math.round(x), Math.round(y));
        }
    }

    public void pointerPressed(int x, int y) {
    }

    public void pointerDragged(int x, int y) {
    }

    public void pointerReleased(int x, int y) {
    }

    private class InnerView extends SurfaceView {

        public InnerView(Context context) {
            super(context);
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            keyCode = convertAndroidKeyCode(keyCode);

            if (event.getRepeatCount() == 0) {
                if (overlay != null) {
                    overlay.keyPressed(keyCode);
                }

                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.KEY_PRESSED, keyCode));
            } else {
                if (overlay != null) {
                    overlay.keyRepeated(keyCode);
                }

                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.KEY_REPEATED, keyCode));
            }

            return super.onKeyDown(keyCode, event);
        }

        public boolean onKeyUp(int keyCode, KeyEvent event) {
            keyCode = convertAndroidKeyCode(keyCode);

            if (overlay != null) {
                overlay.keyReleased(keyCode);
            }

            postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.KEY_RELEASED, keyCode));

            return super.onKeyUp(keyCode, event);
        }

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
                    } else {
                        postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.POINTER_PRESSED, index, event.getX(), event.getY()));
                    }

                    break;

                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    int historySize = event.getHistorySize();

                    //System.out.println("Pointer moved, historySize = " + historySize);

                    for (int h = 0; h < historySize; h++) {
                        for (int p = 0; p < pointerCount; p++) {
                            if (overlay != null) {
                                overlay.pointerDragged(p, event.getHistoricalX(p, h), event.getHistoricalY(p, h));
                            } else {
                                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.POINTER_DRAGGED, p, event.getHistoricalX(p, h), event.getHistoricalY(p, h)));
                            }
                        }
                    }

                    for (int p = 0; p < pointerCount; p++) {
                        if (overlay != null) {
                            overlay.pointerDragged(p, event.getX(p), event.getY(p));
                        } else {
                            postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.POINTER_DRAGGED, p, event.getX(p), event.getY(p)));
                        }
                    }

                    //System.out.println("Pointer event processed");

                    break;

                case MotionEvent.ACTION_UP:
                    if (overlay != null) {
                        overlay.hide();
                    }

                case MotionEvent.ACTION_POINTER_UP:
                    index = event.getActionIndex();

                    if (overlay != null) {
                        overlay.pointerReleased(index, event.getX(index), event.getY(index));
                    } else {
                        postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.POINTER_RELEASED, index, event.getX(), event.getY()));
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
            synchronized (paintsync) {
                displayWidth = newwidth;
                displayHeight = newheight;

                updateSize(true);
            }

            postEvent(paintEvent);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            synchronized (paintsync) {
                surfacevalid = true;
                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.SHOW_NOTIFY));
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            synchronized (paintsync) {
                surfacevalid = false;
                postEvent(CanvasEvent.getInstance(Canvas.this, CanvasEvent.HIDE_NOTIFY));
            }
        }
    }

    private class PaintEvent extends Event implements EventFilter {

        public void process() {
            synchronized (paintsync) {
                if (!surfacevalid) {
                    return;
                }

                if (useOffscreen) {
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
                            holder.unlockCanvasAndPost(graphics.getCanvas());
                        }
                    }
                } else {
                    graphics.setCanvas(holder.lockCanvas());

                    if (graphics.hasCanvas()) {
                        graphics.clear(backgroundColor);
                        graphics.setWindow(onX, onY, onWidth, onHeight);

                        try {
                            paint(graphics);
                        } catch (Throwable t) {
                            t.printStackTrace();

                            graphics.resetTranslation();
                            graphics.resetClip();
                        }

                        graphics.resetWindow();

                        if (overlay != null) {
                            overlay.paint(graphics);
                        }

                        holder.unlockCanvasAndPost(graphics.getCanvas());
                    }
                }

                continuepaint = true;
            }
        }

        public void recycle() {
        }

        public boolean placeableAfter(Event event) {
            if (event == this) {
                if (continuepaint) {
                    continuepaint = false;
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
//		public String toString()
//		{
//			return "repaint()";
//		}
    }
}