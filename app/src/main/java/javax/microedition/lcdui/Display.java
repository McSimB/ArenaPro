package javax.microedition.lcdui;

import android.view.ViewGroup;

import com.elkware.midp.games.Arena1;

public class Display {

	public static final int WIDTH = 132;
	public static final int HEIGHT = 176;
	private Displayable current;
	private Arena1 arena;

	public Display(Arena1 arena) {
		this.arena = arena;
	}

	public static Display getDisplay(Arena1 arena) {
		return arena.display;
	}

	public void setCurrent(final Displayable nextDisplayable) {
		current = nextDisplayable;
		final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(WIDTH, HEIGHT);
		if (Thread.currentThread().getName().equals("main")){
			arena.setContentView(nextDisplayable.getView(), params);
		} else {
			arena.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					arena.setContentView(nextDisplayable.getView(), params);
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