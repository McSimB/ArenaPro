package javax.microedition.lcdui;

public abstract class Screen extends Displayable {

	Screen() {
		this(null);
	}

	Screen(String title) {
		resetToTop = true;
		view = new int[4];
		view[0] = 0;
		view[1] = 0;
		view[2] = 0;
		view[3] = 0;
		super.setTitleImpl(title);
	}

	void layout() {
		super.layout();
		translateViewport();
	}

	private void translateViewport() {
		if (paintBorder != 0) {
			viewport[0] += 4;
			viewport[1] += 4;
			viewport[2] -= 8;
			viewport[3] -= 8;
		}
	}

	void callPaint(Graphics g, Object target) {
		super.callPaint(g, target);
	}

	static final Font CONTENT_FONT;
	static final int CONTENT_HEIGHT;
	int paintBorder;
	static final int BORDER_NONE = 0;
	static final int BORDER_SOLID = 1;
	static final int BORDER_GRAY = 2;
	static final boolean SCROLLS_HORIZONTAL = false;
	static final boolean SCROLLS_VERTICAL = true;
	int view[];
	boolean resetToTop;

	static {
		CONTENT_FONT = Font.getDefaultFont();
		CONTENT_HEIGHT = CONTENT_FONT.getHeight();
	}

}