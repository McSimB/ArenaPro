package javax.microedition.lcdui;

public class List extends Screen {

	public static final Command SELECT_COMMAND = new Command("", 1, 0);
	
	public List(String s, int i, String[] strings, Image[] images) {
	}

	public List(String s, int i) {
	}

	public boolean isSelected(int i) {
		return false;
	}

	public void append(String s, Image image) {
	}

	public int getSelectedIndex() {
		return 0;
	}

	public String getString(int selectedIndex) {
		return "";
	}

	public void set(int var1, String string, Image image) {
	}

	public int size() {
		return 0;
	}

	public void setSelectedFlags(boolean[] booleen) {
	}
}
