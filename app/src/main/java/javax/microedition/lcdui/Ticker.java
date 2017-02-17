package javax.microedition.lcdui;

public class Ticker {

	public Ticker(String str) {
		setupText(str);
	}

	public void setString(String str) {
		setupText(str);
	}

	public String getString() {
		return message;
	}

	void paintContent(Graphics g) {
	}

	void reset() {
		messageLoc = Display.WIDTH;
	}

	private final void setupText(String message) {
		if (message == null)
			throw new NullPointerException();
		StringBuffer msg = new StringBuffer(message);
		int offset = 0;
		boolean modified;
		for (modified = false; (offset = message.indexOf('\n', offset)) != -1; modified = true) {
			msg.setCharAt(offset, ' ');
			offset++;
		}

		this.message = message;
		displayedMessage = modified ? msg.toString() : message;
		messageWidth = Screen.CONTENT_FONT.stringWidth(displayedMessage);
		if (messageWidth < 5)
			tickSpeed = messageWidth;
		else
			tickSpeed = 5;
		reset();
	}

	private String message;
	private String displayedMessage;
	private int messageLoc;
	private int messageWidth;
	private int tickSpeed;
	private static Image TICKER_IMG;
	static final long TICK_RATE = 250L;
	static final int PREFERRED_HEIGHT;
	static final int DECORATION_HEIGHT = 2;

	static {
		PREFERRED_HEIGHT = Screen.CONTENT_HEIGHT + 4;
	}
}
