package javax.microedition.lcdui;

import android.app.Activity;

public class Display {

	public static final int WIDTH = 0;
	public static final int HEIGHT = 0;
	private Displayable current;


	Display(Activity m) {
	}

	public static Display getDisplay(Activity m) {
		return new Display(m);
	}

	public void setCurrent(Displayable nextDisplayable) {
		current = nextDisplayable;
	}

	public Displayable getCurrent() {
		return current;
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

	public void updateCommandSet() {
		Command screenCommands[] = current.getCommands();
		int screenComCount = current.getCommandCount();
		for (int i = 0; i < screenComCount; i++)
			screenCommands[i].setInternalID(i);
	}

}