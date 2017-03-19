package javax.microedition.lcdui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.elkware.midp.games.Arena1;
import com.elkware.midp.games.colorng.arena.high.MyCanvas;
import com.elkware.midp.games.colorng.arena.high.R;

public class Display {

	public static final int WIDTH = 132;
	public static final int HEIGHT = 176;
	public static int SCALE = 4;
	private Displayable current;
	private Arena1 arena;
	private boolean firstDisp;

	public Display(Arena1 arena) {
		this.arena = arena;
	}

	public static Display getDisplay(Arena1 arena) {
		return arena.display;
	}

	public void setCurrent(final Displayable nextDisplayable) {
		final RelativeLayout.LayoutParams params =
				new RelativeLayout.LayoutParams(WIDTH * SCALE, HEIGHT * SCALE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		final ViewGroup mainLayout = (ViewGroup) arena.findViewById(R.id.mainLayout);
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
			arena.runOnUiThread(new Runnable() {
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
	}

	public void vibrate(int var1) {
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
		final Button b1 = (Button) view.findViewById(R.id.button1);
		final Button b2 = (Button) view.findViewById(R.id.button2);
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