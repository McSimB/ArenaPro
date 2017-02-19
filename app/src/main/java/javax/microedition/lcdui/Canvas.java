package javax.microedition.lcdui;

import android.view.View;

import com.elkware.midp.games.colorng.arena.high.Arena;

public abstract class Canvas extends Displayable {

	public static final int UP = 1;
	public static final int DOWN = 6;
	public static final int LEFT = 2;
	public static final int RIGHT = 5;
	public static final int FIRE = 8;
	public static final int GAME_A = 9;
	public static final int GAME_B = 10;
	public static final int GAME_C = 11;
	public static final int GAME_D = 12;
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
	public static final int KEY_STAR = 42;
	public static final int KEY_POUND = 35;

	public Arena arena;

	public Canvas(Arena var1) {
		this.arena = var1;
	}

	protected void keyPressed(int i) {
	}

	protected void keyReleased(int i) {
	}

	protected abstract void paint(Graphics g);

	public final void repaint() {
	}

	public final void serviceRepaints() {
		arena.canvasView.postInvalidate();
	}

	protected void showNotify() {
	}

	protected void hideNotify() {
	}

	void callShowNotify(Display d) {
		super.callShowNotify(d);
	}

	@Override
	public View getView() {
		return arena.canvasView;
	}

}