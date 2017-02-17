package javax.microedition.lcdui;

public class Command {

	public static final int SCREEN = 1;
	public static final int BACK = 2;
	public static final int CANCEL = 3;
	public static final int OK = 4;
	public static final int HELP = 5;
	public static final int STOP = 6;
	public static final int EXIT = 7;
	public static final int ITEM = 8;
	String shortLabel;
	String longLabel;
	int commandType;
	int priority;
	private int id;

	public Command(String label, int commandType, int priority) {
		this(label, null, commandType, priority);
	}

	public Command(String shortLabel, String longLabel, int commandType, int priority) {
		initialize(commandType, priority);
		setLabel(shortLabel, longLabel);
	}

	public String getLabel() {
		return shortLabel;
	}

	public int getCommandType() {
		return commandType;
	}

	void setInternalID(int num) {
		id = num;
	}

	private void setLabel(String shortLabel, String longLabel) {
		if (shortLabel == null) {
			throw new NullPointerException();
		} else {
			this.shortLabel = shortLabel;
			this.longLabel = longLabel;
		}
	}

	private void initialize(int commandType, int priority) {
		if (commandType < 1 || commandType > 8) {
			throw new IllegalArgumentException();
		} else {
			this.commandType = commandType;
			this.priority = priority;
		}
	}

}