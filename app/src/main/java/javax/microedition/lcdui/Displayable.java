package javax.microedition.lcdui;

import java.util.Timer;

public abstract class Displayable {

	Displayable() {
		vScrollPosition = 0;
		vScrollProportion = 100;
		setupViewport();
		translateViewport();
		paintDelegate = this;
	}

	public void addCommand(Command cmd) {
		if (cmd == null)
			throw new NullPointerException();
		synchronized (Display.LCDUILock) {
			addCommandImpl(cmd);
		}
	}

	public void removeCommand(Command cmd) {
		synchronized (Display.LCDUILock) {
			removeCommandImpl(cmd);
		}
	}

	public void setCommandListener(CommandListener l) {
		synchronized (Display.LCDUILock) {
			listener = l;
		}
	}

	public int getWidth() {
		return viewport[2];
	}

	public int getHeight() {
		return viewport[3];
	}

	protected void sizeChanged(int i, int j) {
	}

	void setTitleImpl(String s) {
		if (title.equals(s) || title != null && title.equals(s))
			return;
		String oldTitle = title;
		title = s;
		if (fullScreenMode)
			return;
		boolean sizeChange = oldTitle != null && title == null || oldTitle == null && title != null;
		if (sizeChange) {
			layout();
			callSizeChanged(viewport[2], viewport[3]);
			callRepaint();
		} else {
			repaintTitle();
		}
	}

	void callSizeChanged(int w, int h) {
		sizeChangeOccurred = currentDisplay == null || !currentDisplay.isShown(this);
	}

	void callPaint(Graphics g, Object target) {
		synchronized (Display.LCDUILock) {
			if (!fullScreenMode && (title != null || ticker != null)) {
				if (g.getClipY() < totalHeight) {
					if (g.getClipY() < tickerHeight)
						paintTicker(g);
					if (title != null && g.getClipY() + g.getClipHeight() > (totalHeight - tickerHeight) + 1) {
						g.translate(0, tickerHeight);
						paintTitle(g);
						g.translate(0, -tickerHeight);
					}
				}
			} else {
				g.setColor(0x606060);
				g.drawLine(0, 0, Display.WIDTH, 0);
				g.setColor(Display.FG_COLOR);
			}
		}
	}

	void paintTicker(Graphics g) {
		if (ticker != null)
			ticker.paintContent(g);
		else if (title != null) {
			g.setColor(0x606060);
			g.drawLine(0, 0, Display.WIDTH, 0);
			g.setColor(Display.ERASE_COLOR);
			g.drawLine(0, 1, Display.WIDTH, 1);
			g.setColor(Display.FG_COLOR);
		}
	}

	void paintTitle(Graphics g) {
		g.setColor(0xafafaf);
		g.fillRect(0, 0, Display.WIDTH, TITLE_HEIGHT - 1);
		g.setColor(Display.FG_COLOR);
		//Text.paint(title, TITLE_FONT, g, Display.WIDTH, TITLE_HEIGHT, 1, 0, null);
		g.setColor(0x606060);
		g.drawLine(0, TITLE_HEIGHT - 1, Display.WIDTH, TITLE_HEIGHT - 1);
		g.setColor(Display.FG_COLOR);
	}

	void layout() {
		setupViewport();
		translateViewport();
	}

	void fullScreenMode(boolean onOff) {
		if (fullScreenMode == onOff)
			return;
		fullScreenMode = onOff;
		layout();
		updateCommandSet();
		callSizeChanged(viewport[2], viewport[3]);
		callRepaint();
		if (fullScreenMode)
			stopTicker();
		else
			startTicker();
	}

	private void setupViewport() {
		if (viewport == null)
			viewport = new int[4];
		viewport[0] = viewport[1] = 0;
		viewport[2] = Display.WIDTH;
		viewport[3] = fullScreenMode ? Display.HEIGHT : Display.ADORNEDHEIGHT;
	}

	private void translateViewport() {
		if (!fullScreenMode && (title != null || ticker != null)) {
			if (ticker != null)
				tickerHeight = Ticker.PREFERRED_HEIGHT;
			else if (title != null)
				tickerHeight = 2;
			else
				tickerHeight = 0;
			totalHeight = title == null ? tickerHeight : TITLE_HEIGHT + tickerHeight;
		} else {
			totalHeight = 1;
		}
		viewport[1] += totalHeight;
		viewport[3] -= totalHeight;
	}

	final void callRepaint(int x, int y, int width, int height, Object target) {
		if (currentDisplay != null)
			currentDisplay.repaintImpl(paintDelegate, x, y, width, height, target);
	}

	final void callRepaint() {
		callRepaint(0, 0, viewport[0] + viewport[2], viewport[1] + viewport[3], null);
	}

	void setVerticalScroll(int scrollPosition, int scrollProportion) {
		synchronized (Display.LCDUILock) {
			vScrollPosition = scrollPosition;
			vScrollProportion = scrollProportion;
			if (currentDisplay != null)
				currentDisplay.setVerticalScroll(scrollPosition, scrollProportion);
		}
	}

	void callShowNotify(Display d) {
		synchronized (Display.LCDUILock) {
			currentDisplay = d;
			grabFullScreen(fullScreenMode);
			if (sizeChangeOccurred)
				callSizeChanged(viewport[2], viewport[3]);
			startTicker();
		}
	}

	void addCommandImpl(Command cmd) {
		for (int i = 0; i < numCommands; i++)
			if (commands[i] == cmd)
				return;

		if (commands == null || numCommands == commands.length) {
			Command newCommands[] = new Command[numCommands + 4];
			if (commands != null)
				System.arraycopy(commands, 0, newCommands, 0, numCommands);
			commands = newCommands;
		}
		commands[numCommands] = cmd;
		numCommands++;
		updateCommandSet();
	}

	void removeCommandImpl(Command cmd) {
		int i = 0;
		do {
			if (i >= numCommands)
				break;
			if (commands[i] == cmd) {
				commands[i] = commands[--numCommands];
				commands[numCommands] = null;
				updateCommandSet();
				break;
			}
			i++;
		} while (true);
	}

	void updateCommandSet() {
		synchronized (Display.LCDUILock) {
			if (currentDisplay != null && currentDisplay.isShown(this))
				currentDisplay.updateCommandSet();
		}
	}

	private void repaintTitle() {
		if (currentDisplay != null)
			currentDisplay.repaintImpl(paintDelegate, 0, ticker == null ? 2 : Ticker.PREFERRED_HEIGHT, viewport[2], TITLE_HEIGHT, title);
	}

	private void startTicker() {
	}

	private void stopTicker() {
	}

	native void grabFullScreen(boolean flag);

	Display currentDisplay;
	Command commands[];
	int numCommands;
	CommandListener listener;
	static final int X = 0;
	static final int Y = 1;
	static final int WIDTH = 2;
	static final int HEIGHT = 3;
	int viewport[];
	boolean fullScreenMode;
	boolean sizeChangeOccurred;
	Displayable paintDelegate;
	private static final Font TITLE_FONT;
	private static final int TITLE_HEIGHT;
	private String title;
	private Ticker ticker;
	private static final Timer tickerTimer = new Timer();
	private int tickerHeight;
	private int totalHeight;
	private int vScrollPosition;
	private int vScrollProportion;

	static {
		TITLE_FONT = Font.getFont(0, 1, 0);
		TITLE_HEIGHT = TITLE_FONT.getHeight() + 1;
	}

}