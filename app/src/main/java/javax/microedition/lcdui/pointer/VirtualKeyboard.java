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

package javax.microedition.lcdui.pointer;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.KeyEvent;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Overlay;
import javax.microedition.lcdui.event.CanvasEvent;
import javax.microedition.util.ContextHolder;

public class VirtualKeyboard implements Overlay, Runnable {

    public static final String ARROW_LEFT = "\u2190";
    public static final String ARROW_UP = "\u2191";
    public static final String ARROW_RIGHT = "\u2192";
    public static final String ARROW_DOWN = "\u2193";
    public static final int SCREEN = -1;
    public static final int KEY_NUM1 = 0,
            KEY_NUM2 = 1,
            KEY_NUM3 = 2,
            KEY_NUM4 = 3,
            KEY_NUM5 = 4,
            KEY_NUM6 = 5,
            KEY_NUM7 = 6,
            KEY_NUM8 = 7,
            KEY_NUM9 = 8,
            KEY_NUM0 = 9,
            KEY_STAR = 10,
            KEY_POUND = 11,
            KEY_SOFT_LEFT = 12,
            KEY_SOFT_RIGHT = 13,
            KEY_DIAL = 14,
            KEY_CANCEL = 15,
            KEY_UP_LEFT = 16,
            KEY_UP = 17,
            KEY_UP_RIGHT = 18,
            KEY_LEFT = 19,
            KEY_RIGHT = 20,
            KEY_DOWN_LEFT = 21,
            KEY_DOWN = 22,
            KEY_DOWN_RIGHT = 23,
            KEY_FIRE = 24;
    public static final int LAYOUT_EOF = -1;
    public static final int LAYOUT_KEYS = 0;
    public static final int LAYOUT_SCALES = 1;
    public static final int BACKGROUND = 0;
    public static final int FOREGROUND = 1;
    public static final int BACKGROUND_SELECTED = 2;
    public static final int FOREGROUND_SELECTED = 3;
    public static final int OUTLINE = 4;
    public static final int SCALE_JOYSTICK = 0;
    public static final int SCALE_SOFT_KEYS = 1;
    public static final int SCALE_DIAL_KEYS = 2;
    public static final int SCALE_DIGITS = 3;
    public static final int SCALE_FIRE_KEY = 4;
    protected final Object waiter = new Object();
    protected Font font = Font.getDefaultFont();
    protected int delay = -1;
    protected int overlayAlpha = 64;
    protected int[] colors =
            {
                    0xD0D0D0,
                    0x000080,
                    0x000080,
                    0xFFFFFF,
                    0xFFFFFF
            };
    protected float[] keyScales =
            {
                    1,
                    1,
                    1,
                    0.75f,
                    1.5f
            };

    protected int[][] keyScaleGroups =
            {
                    {
                            KEY_UP_LEFT,
                            KEY_UP,
                            KEY_UP_RIGHT,
                            KEY_LEFT,
                            KEY_RIGHT,
                            KEY_DOWN_LEFT,
                            KEY_DOWN,
                            KEY_DOWN_RIGHT
                    },
                    {
                            KEY_SOFT_LEFT,
                            KEY_SOFT_RIGHT
                    },
                    {
                            KEY_DIAL,
                            KEY_CANCEL,
                    },
                    {
                            KEY_NUM1,
                            KEY_NUM2,
                            KEY_NUM3,
                            KEY_NUM4,
                            KEY_NUM5,
                            KEY_NUM6,
                            KEY_NUM7,
                            KEY_NUM8,
                            KEY_NUM9,
                            KEY_NUM0,
                            KEY_STAR,
                            KEY_POUND
                    },
                    {
                            KEY_FIRE
                    }
            };

    protected Canvas target;

    protected Image offscreen;
    protected Graphics offgraphics;
    protected boolean obscuresVirtualScreen;
    protected boolean offscreenChanged;

    protected boolean visible, hiding, skip;
    protected Thread hider;
    protected int[] snapOrigins;
    protected int[] snapModes;
    protected PointF[] snapOffsets;
    protected boolean[] snapValid;
    protected int[] snapStack;
    protected int layoutEditKeyCode;
    protected int layoutEditMode;
    protected int editedIndex;
    protected float offsetX, offsetY;
    protected float prevScale;
    protected int layoutVariant;
    protected RectF screen;
    protected RectF virtualScreen;
    protected float keySize;
    protected float snapRadius;
    protected VirtualKey[] keypad;
    protected VirtualKey[] associatedKeys;

    public VirtualKeyboard() {
        keypad = new VirtualKey[25];
        associatedKeys = new VirtualKey[10]; // у среднестатистического пользователя обычно не более 10 пальцев...

        for (int i = KEY_NUM1; i < 9; i++) {
            keypad[i] = new VirtualKey(Canvas.KEY_NUM1 + i, Integer.toString(1 + i));
        }

        keypad[KEY_NUM0] = new VirtualKey(Canvas.KEY_NUM0, "0");
        keypad[KEY_STAR] = new VirtualKey(Canvas.KEY_STAR, "*");
        keypad[KEY_POUND] = new VirtualKey(Canvas.KEY_POUND, "#");

        keypad[KEY_SOFT_LEFT] = new VirtualKey(Canvas.KEY_SOFT_LEFT, "L");
        keypad[KEY_SOFT_RIGHT] = new VirtualKey(Canvas.KEY_SOFT_RIGHT, "R");

        keypad[KEY_DIAL] = new VirtualKey(Canvas.KEY_SEND, "D");
        keypad[KEY_CANCEL] = new VirtualKey(Canvas.KEY_END, "C");

        keypad[KEY_UP_LEFT] = new VirtualKey(Canvas.KEY_UP, Canvas.KEY_LEFT, ARROW_LEFT + ARROW_UP);
        keypad[KEY_UP] = new VirtualKey(Canvas.KEY_UP, ARROW_UP);
        keypad[KEY_UP_RIGHT] = new VirtualKey(Canvas.KEY_UP, Canvas.KEY_RIGHT, ARROW_UP + ARROW_RIGHT);

        keypad[KEY_LEFT] = new VirtualKey(Canvas.KEY_LEFT, ARROW_LEFT);
        keypad[KEY_RIGHT] = new VirtualKey(Canvas.KEY_RIGHT, ARROW_RIGHT);

        keypad[KEY_DOWN_LEFT] = new VirtualKey(Canvas.KEY_DOWN, Canvas.KEY_LEFT, ARROW_LEFT + ARROW_DOWN);
        keypad[KEY_DOWN] = new VirtualKey(Canvas.KEY_DOWN, ARROW_DOWN);
        keypad[KEY_DOWN_RIGHT] = new VirtualKey(Canvas.KEY_DOWN, Canvas.KEY_RIGHT, ARROW_DOWN + ARROW_RIGHT);

        keypad[KEY_FIRE] = new VirtualKey(Canvas.KEY_FIRE, "F");

        snapOrigins = new int[keypad.length];
        snapModes = new int[keypad.length];
        snapOffsets = new PointF[keypad.length];
        snapValid = new boolean[keypad.length];
        snapStack = new int[keypad.length];

        resetLayout(layoutVariant = 0);

        layoutEditKeyCode = Canvas.convertAndroidKeyCode(KeyEvent.KEYCODE_MENU);
        layoutEditMode = LAYOUT_EOF;

        visible = true;
        offscreenChanged = true;

        hider = new Thread(this);
        hider.start();
    }

    public void resetLayout(int variant) {
        switch (variant) {
            case 0:
                keyScales[SCALE_JOYSTICK] = 1;
                keyScales[SCALE_SOFT_KEYS] = 1;
                keyScales[SCALE_DIAL_KEYS] = 1;
                keyScales[SCALE_DIGITS] = 0.75f;
                keyScales[SCALE_FIRE_KEY] = 1.5f;

                setSnap(KEY_DOWN_LEFT, SCREEN, RectSnap.INT_SOUTHWEST);
                setSnap(KEY_LEFT, KEY_DOWN_LEFT, RectSnap.EXT_NORTH);
                setSnap(KEY_UP_LEFT, KEY_LEFT, RectSnap.EXT_NORTH);
                setSnap(KEY_UP, KEY_UP_LEFT, RectSnap.EXT_EAST);
                setSnap(KEY_UP_RIGHT, KEY_UP, RectSnap.EXT_EAST);
                setSnap(KEY_DOWN, KEY_DOWN_LEFT, RectSnap.EXT_EAST);
                setSnap(KEY_DOWN_RIGHT, KEY_DOWN, RectSnap.EXT_EAST);
                setSnap(KEY_RIGHT, KEY_DOWN_RIGHT, RectSnap.EXT_NORTH);

                setSnap(KEY_NUM3, SCREEN, RectSnap.INT_NORTHEAST);
                setSnap(KEY_NUM2, KEY_NUM3, RectSnap.EXT_WEST);
                setSnap(KEY_NUM1, KEY_NUM2, RectSnap.EXT_WEST);
                setSnap(KEY_NUM6, KEY_NUM3, RectSnap.EXT_SOUTH);
                setSnap(KEY_NUM5, KEY_NUM6, RectSnap.EXT_WEST);
                setSnap(KEY_NUM4, KEY_NUM5, RectSnap.EXT_WEST);
                setSnap(KEY_NUM9, KEY_NUM6, RectSnap.EXT_SOUTH);
                setSnap(KEY_NUM8, KEY_NUM9, RectSnap.EXT_WEST);
                setSnap(KEY_NUM7, KEY_NUM8, RectSnap.EXT_WEST);
                setSnap(KEY_POUND, KEY_NUM9, RectSnap.EXT_SOUTH);
                setSnap(KEY_NUM0, KEY_POUND, RectSnap.EXT_WEST);
                setSnap(KEY_STAR, KEY_NUM0, RectSnap.EXT_WEST);

                setSnap(KEY_DIAL, KEY_UP_LEFT, RectSnap.ALIGN_LEFT | RectSnap.SNAP_TOP);
                setSnap(KEY_CANCEL, KEY_DIAL, RectSnap.EXT_EAST);

                setSnap(KEY_SOFT_RIGHT, KEY_NUM0, RectSnap.RIGHT_HCENTER | RectSnap.SNAP_BOTTOM);
                setSnap(KEY_SOFT_LEFT, KEY_SOFT_RIGHT, RectSnap.EXT_WEST);

                keySize = Math.max(ContextHolder.getDisplayWidth(), ContextHolder.getDisplayHeight()) / 12;
                float keyOffset = keySize * (keyScales[SCALE_JOYSTICK] * 3 - keyScales[SCALE_FIRE_KEY]) / 2;

                snapOrigins[KEY_FIRE] = SCREEN;
                snapModes[KEY_FIRE] = RectSnap.INT_SOUTHEAST;
                snapOffsets[KEY_FIRE] = new PointF(-keyOffset, -keyOffset);
                snapValid[KEY_FIRE] = false;

                break;

            case 1:
                keyScales[SCALE_JOYSTICK] = 1;
                keyScales[SCALE_SOFT_KEYS] = 1;
                keyScales[SCALE_DIAL_KEYS] = 1;
                keyScales[SCALE_DIGITS] = 1;
                keyScales[SCALE_FIRE_KEY] = 1;

                setSnap(KEY_DOWN_RIGHT, SCREEN, RectSnap.INT_SOUTHEAST);
                setSnap(KEY_DOWN, KEY_DOWN_RIGHT, RectSnap.EXT_WEST);
                setSnap(KEY_DOWN_LEFT, KEY_DOWN, RectSnap.EXT_WEST);
                setSnap(KEY_LEFT, KEY_DOWN_LEFT, RectSnap.EXT_NORTH);
                setSnap(KEY_RIGHT, KEY_DOWN_RIGHT, RectSnap.EXT_NORTH);
                setSnap(KEY_UP_RIGHT, KEY_RIGHT, RectSnap.EXT_NORTH);
                setSnap(KEY_UP, KEY_UP_RIGHT, RectSnap.EXT_WEST);
                setSnap(KEY_UP_LEFT, KEY_UP, RectSnap.EXT_WEST);
                setSnap(KEY_FIRE, KEY_DOWN_RIGHT, RectSnap.EXT_NORTHWEST);
                setSnap(KEY_SOFT_LEFT, KEY_UP_LEFT, RectSnap.EXT_NORTH);
                setSnap(KEY_SOFT_RIGHT, KEY_UP_RIGHT, RectSnap.EXT_NORTH);

                setSnap(KEY_STAR, SCREEN, RectSnap.INT_SOUTHWEST);
                setSnap(KEY_NUM0, KEY_STAR, RectSnap.EXT_EAST);
                setSnap(KEY_POUND, KEY_NUM0, RectSnap.EXT_EAST);
                setSnap(KEY_NUM7, KEY_STAR, RectSnap.EXT_NORTH);
                setSnap(KEY_NUM8, KEY_NUM7, RectSnap.EXT_EAST);
                setSnap(KEY_NUM9, KEY_NUM8, RectSnap.EXT_EAST);
                setSnap(KEY_NUM4, KEY_NUM7, RectSnap.EXT_NORTH);
                setSnap(KEY_NUM5, KEY_NUM4, RectSnap.EXT_EAST);
                setSnap(KEY_NUM6, KEY_NUM5, RectSnap.EXT_EAST);
                setSnap(KEY_NUM1, KEY_NUM4, RectSnap.EXT_NORTH);
                setSnap(KEY_NUM2, KEY_NUM1, RectSnap.EXT_EAST);
                setSnap(KEY_NUM3, KEY_NUM2, RectSnap.EXT_EAST);
                setSnap(KEY_DIAL, KEY_NUM1, RectSnap.EXT_NORTH);
                setSnap(KEY_CANCEL, KEY_NUM3, RectSnap.EXT_NORTH);

                break;
        }

    }

    public void setTarget(Canvas canvas) {
        target = canvas;
    }

    public void setSnap(int key, int origin, int mode) {
        snapOrigins[key] = origin;
        snapModes[key] = mode;
        snapOffsets[key] = new PointF();
        snapValid[key] = false;
    }

    public void snapKey(int key, int level) {
        if (level >= snapStack.length) {
            System.out.print("Snap loop detected: ");

            for (int i = 1; i < snapStack.length; i++) {
                System.out.print(snapStack[i]);
                System.out.print(", ");
            }

            System.out.print(key);

            return;
        }

        snapStack[level] = key;

        if (snapOrigins[key] == SCREEN) {
            RectSnap.snap(keypad[key].getRect(), screen, snapModes[key], snapOffsets[key]);
            snapValid[key] = true;
        } else {
            if (!snapValid[snapOrigins[key]]) {
                snapKey(snapOrigins[key], level + 1);
            }

            RectSnap.snap(keypad[key].getRect(),
                    keypad[snapOrigins[key]].getRect(), snapModes[key], snapOffsets[key]);
            snapValid[key] = true;
        }
    }

    public void snapKeys() {
        obscuresVirtualScreen = false;

        for (int i = 0; i < keypad.length; i++) {
            snapKey(i, 0);

            if (RectF.intersects(keypad[i].getRect(), virtualScreen)) {
                obscuresVirtualScreen = true;
            }
        }
    }

    public void highlightGroup(int group) {
        for (VirtualKey aKeypad : keypad) {
            aKeypad.setSelected(false);
        }

        if (group >= 0) {
            for (int key = 0; key < keyScaleGroups[group].length; key++) {
                keypad[keyScaleGroups[group][key]].setSelected(true);
            }
        }
    }

    public void resizeKey(int key, float size) {
        keypad[key].resize(size);
        snapValid[key] = false;
    }

    public void resizeKeyGroup(int group) {
        float size = keySize * keyScales[group];

        for (int key = 0; key < keyScaleGroups[group].length; key++) {
            resizeKey(keyScaleGroups[group][key], size);
        }
    }

    public void resize(RectF screen, RectF virtualScreen) {
        this.screen = screen;
        this.virtualScreen = virtualScreen;

        int width = Math.round(screen.width());
        int height = Math.round(screen.height());

        if (offscreen == null || offscreen.getWidth() != width || offscreen.getHeight() != height) {
            offscreen = Image.createImage(width, height);
            offgraphics = offscreen.getGraphics();
        }

        snapRadius = keyScales[0];

        for (int i = 1; i < keyScales.length; i++) {
            if (keyScales[i] < snapRadius) {
                snapRadius = keyScales[i];
            }
        }

        keySize = (float) Math.max(width, height) / 12;
        snapRadius = keySize * snapRadius / 4;

        for (int group = 0; group < keyScaleGroups.length; group++) {
            resizeKeyGroup(group);
        }

        snapKeys();
    }

    public void paint(Graphics g) {
        if (visible) {
            if (obscuresVirtualScreen && overlayAlpha <= 250) {
                if (offscreenChanged) {
                    offgraphics.clear(0);

                    for (VirtualKey aKeypad : keypad) {
                        aKeypad.paint(offgraphics);
                    }

                    offscreenChanged = false;
                }

                g.drawImage(offscreen, 0, 0, -1, -1, false, overlayAlpha);
            } else {
                for (VirtualKey aKeypad : keypad) {
                    aKeypad.paint(g);
                }
            }
        }
    }

    public void pointerPressed(int pointer, float x, float y) {
        if (skip) {
            return;
        }

        if (layoutEditMode == LAYOUT_EOF) {
            if (pointer > associatedKeys.length) {
                return;
            }

            for (VirtualKey aKeypad : keypad) {
                if (aKeypad.contains(x, y)) {
                    associatedKeys[pointer] = aKeypad;
                    aKeypad.setSelected(true);

                    target.postEvent(CanvasEvent.getInstance(target,
                            CanvasEvent.KEY_PRESSED, aKeypad.getKeyCode()));

                    if (aKeypad.getSecondKeyCode() != 0) {
                        target.postEvent(CanvasEvent.getInstance(target,
                                CanvasEvent.KEY_PRESSED, aKeypad.getSecondKeyCode()));
                    }

                    break;
                }
            }
        } else if (layoutEditMode == LAYOUT_KEYS) {
            editedIndex = -1;

            for (int i = 0; i < keypad.length; i++) {
                if (keypad[i].contains(x, y)) {
                    editedIndex = i;

                    RectF rect = keypad[i].getRect();

                    offsetX = x - rect.left;
                    offsetY = y - rect.top;

                    // offsetX = offsetY = keySize / 2;

                    break;
                }
            }
        } else if (layoutEditMode == LAYOUT_SCALES) {
            int index = -1;

            for (int group = 0; group < keyScaleGroups.length && index < 0; group++) {
                for (int key = 0; key < keyScaleGroups[group].length && index < 0; key++) {
                    if (keypad[keyScaleGroups[group][key]].contains(x, y)) {
                        index = group;
                    }
                }
            }

            if (index >= 0) {
                editedIndex = index;
                highlightGroup(index);
            }

            offsetX = x;
            offsetY = y;

            prevScale = keyScales[editedIndex];
        }
    }

    public void pointerReleased(int pointer, float x, float y) {
        if (skip) {
            skip = false;
            return;
        }

        if (layoutEditMode == LAYOUT_EOF) {
            if (pointer > associatedKeys.length) {
                return;
            }

            if (associatedKeys[pointer] != null) {

                target.postEvent(CanvasEvent.getInstance(target,
                        CanvasEvent.KEY_RELEASED, associatedKeys[pointer].getKeyCode()));

                if (associatedKeys[pointer].getSecondKeyCode() != 0) {
                    target.postEvent(CanvasEvent.getInstance(target,
                            CanvasEvent.KEY_RELEASED, associatedKeys[pointer].getSecondKeyCode()));
                }

                associatedKeys[pointer].setSelected(false);
                associatedKeys[pointer] = null;
            }
        } else if (layoutEditMode == LAYOUT_KEYS) {
            editedIndex = -1;
        }
    }

    public void show() {
        synchronized (waiter) {
            if (hiding) {
                hider.interrupt();
            }
        }

        visible = true;
    }

    public void hide() {
        if (delay >= 0 && obscuresVirtualScreen) {
            synchronized (waiter) {
                waiter.notifyAll();
            }
        }
    }

    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                synchronized (waiter) {
                    hiding = false;

                    waiter.notifyAll();
                    waiter.wait();

                    hiding = true;
                }

                try {
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }

                    visible = false;
                    skip = true;
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void setHideDelay(int delay) {
        this.delay = delay;
    }

    @SuppressWarnings("SameParameterValue")
    public void setOverlayAlpha(int overlayAlpha) {
        this.overlayAlpha = overlayAlpha;
    }

    public void setColor(int color, int value) {
        colors[color] = value;
    }

    public void setLayoutEditKey(int keyCode) {
        layoutEditKeyCode = keyCode;
    }

    protected class VirtualKey {

        protected RectF rect;
        protected int keyCode, secondKeyCode;
        protected String label;
        protected boolean selected;

        public VirtualKey(int keyCode, String label) {
            this.keyCode = keyCode;
            this.label = label;
            rect = new RectF();
        }

        public VirtualKey(int keyCode, int secondKeyCode, String label) {
            this.keyCode = keyCode;
            this.secondKeyCode = secondKeyCode;
            this.label = label;
            rect = new RectF();
        }

        public int getKeyCode() {
            return keyCode;
        }

        public int getSecondKeyCode() {
            return secondKeyCode;
        }

        public void setSelected(boolean flag) {
            selected = flag;
        }

        public RectF getRect() {
            return rect;
        }

        public void resize(float size) {
            rect.right = rect.left + size;
            rect.bottom = rect.top + size;
        }

        public boolean contains(float x, float y) {
            return rect.contains(x, y);
        }

        public void paint(Graphics g) {
            if (label != null) {
                g.setColor(colors[selected ? BACKGROUND_SELECTED : BACKGROUND]);
                g.fillArc(rect, 0, 360);

                g.setFont(font);
                g.setColor(colors[selected ? FOREGROUND_SELECTED : FOREGROUND]);
                g.drawString(label, rect.centerX(), rect.centerY(), Graphics.HCENTER | Graphics.VCENTER);

                g.setColor(colors[OUTLINE]);
                g.drawArc(rect, 0, 360);
            }
        }

        public String toString() {
            return "[" + label + ": " + rect.left + ", " + rect.top + ", " +
                    rect.right + ", " + rect.bottom + "]";
        }

        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + keyCode;
            result = prime * result + secondKeyCode;
            return result;
        }
    }
}