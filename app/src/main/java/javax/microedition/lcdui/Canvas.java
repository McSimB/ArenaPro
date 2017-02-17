package javax.microedition.lcdui;

public abstract class Canvas extends Displayable {

	protected Canvas() {
	}

	public int getGameAction(int keyCode) {
		int n = Display.getGameAction(keyCode);
		if (n == -1)
			throw new IllegalArgumentException();
		else
			return n;
	}

	public void setFullScreenMode(boolean mode) {
		if (mode == super.fullScreenMode)
			return;
		super.grabFullScreen(mode);
		synchronized (Display.LCDUILock) {
			super.fullScreenMode(mode);
		}
	}

	protected void keyPressed(int i) {
	}

	protected void keyRepeated(int i) {
	}

	protected void keyReleased(int i) {
	}

	protected void pointerPressed(int i, int j) {
	}

	protected void pointerReleased(int i, int j) {
	}

	protected void pointerDragged(int i, int j) {
	}

	public final void repaint() {
		synchronized (Display.LCDUILock) {
			callRepaint(viewport[0], viewport[1], viewport[2], viewport[3], null);
		}
	}

	public final void serviceRepaints() {
	}

	protected void showNotify() {
	}

	protected void hideNotify() {
	}

	protected abstract void paint(Graphics g);

	protected void sizeChanged(int i, int j) {
	}

	void callShowNotify(Display d) {
		super.callShowNotify(d);
		super.layout();
	}

	void callPaint(Graphics g, Object target) {
		super.callPaint(g, target);
		if (g.getClipY() + g.getClipHeight() <= viewport[1])
			return;
		g.clipRect(viewport[0], viewport[1], viewport[2], viewport[3]);
	}

	void callSizeChanged(int w, int h) {
		super.callSizeChanged(w, h);
	}

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

}