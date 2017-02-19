package javax.microedition.lcdui;

import android.view.View;

import com.elkware.midp.games.colorng.Arena3;

import java.util.ArrayList;
import java.util.Collections;

public class List extends Displayable {

	public static final Command SELECT_COMMAND = new Command("", 1, 0);
	private Command selectCommand;
	private boolean[] selectedArray;
	private int selectedIndex;
	private int listType;
	private ArrayList<String> items;
	private String title;
	private Arena3 arena;

	public List(String title, int listType, String[] items, Arena3 arena) {
		this.arena = arena;
		this.title = title;
		this.items = new ArrayList<String>();
		Collections.addAll(this.items, items);

		selectCommand = SELECT_COMMAND;
		this.listType = listType;
		if (listType != 3 && listType != 1 && listType != 2)
			throw new IllegalArgumentException();
	}

	public List(String title, int listType, Arena3 arena) {
		this(title, listType, new String[0], arena);
	}

	public int size() {
		return items.size();
	}

	public String getString(int elementNum) {
		return items.get(elementNum);
	}

	public int append(String stringPart) {
		items.add(stringPart);
		return 0;
	}

	public void set(int elementNum, String stringPart) {
		items.set(elementNum, stringPart);
	}

	public boolean isSelected(int elementNum) {
		return elementNum == selectedIndex;
	}

	public void setSelectedIndex(int elementNum) {
		selectedIndex = elementNum;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedFlags(boolean selectedArray[]) {
		this.selectedArray = selectedArray;
	}

	 public  void callKeyPressed() {
		if (listener != null && listType == 3)
			listener.commandAction(selectCommand, this);
	}

	@Override
	public void removeCommand(Command cmd) {
		super.removeCommandImpl(cmd);
		if (cmd == selectCommand)
			selectCommand = null;
	}

	@Override
	void callShowNotify(Display d) {
		super.callShowNotify(d);
	}

	@Override
	public View getView() {
		arena.initMenuView(title, items);
		return arena.menuView;
	}

}
