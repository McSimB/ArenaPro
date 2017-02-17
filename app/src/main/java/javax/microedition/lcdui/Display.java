package javax.microedition.lcdui;

import javax.microedition.midlet.MIDlet;

public class Display {

	public static final int WIDTH = 0;
	public static final int HEIGHT = 0;
	public static final Object LCDUILock = new Object();
	public static int ERASE_COLOR;
	public static int FG_COLOR;
	public static int ADORNEDHEIGHT;


	Display(MIDlet m) {
	}

	public static Display getDisplay(MIDlet m) {
		return null;
	}

	public void setCurrent(Displayable nextDisplayable) {
	}

	public Displayable getCurrent() {
		return null;
	}

	public void setCurrent(Alert alert, Displayable nextDisplayable) {
	}

	public void vibrate(int var1) {
	}

	public static boolean isGraphicsDisplay(Graphics graphics) {
		return false;
	}

	public boolean isShown(Displayable displayable) {
		return false;
	}

	public void repaintImpl(Displayable paintDelegate, int x, int y, int width, int height, Object target) {
	}

	public void setVerticalScroll(int scrollPosition, int scrollProportion) {
	}

	public void updateCommandSet() {
	}

	public static int getGameAction(int keyCode) {
		return 0;
	}
}