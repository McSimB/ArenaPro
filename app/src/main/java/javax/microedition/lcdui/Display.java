package javax.microedition.lcdui;

import android.app.Activity;
import android.view.ViewGroup;

public class Display {

	public static final int WIDTH = 132;
	public static final int HEIGHT = 176;
	private Displayable current;
	private Activity activity;

	Display(Activity activity) {
		this.activity = activity;
	}

	public static Display getDisplay(Activity activity) {
		return new Display(activity);
	}

	public void setCurrent(final Displayable nextDisplayable) {
		current = nextDisplayable;
		if (Thread.currentThread().getName().equals("main")){
			activity.setContentView(nextDisplayable.getView());
		} else {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(WIDTH, HEIGHT);
					activity.setContentView(nextDisplayable.getView(), params);
				}
			});
		}
	}

	public Displayable getCurrent() {
		return current;
	}

	public void setCurrent(Alert alert, Displayable nextDisplayable) {
	}

	public void vibrate(int var1) {
	}

	public boolean isShown() {
		return false;
	}

	public void updateCommandSet() {
		Command screenCommands[] = current.getCommands();
		int screenComCount = current.getCommandCount();
		for (int i = 0; i < screenComCount; i++)
			screenCommands[i].setInternalID(i);
	}

}