package javax.microedition.lcdui;

import android.view.View;

import static javax.microedition.lcdui.Display.HEIGHT;
import static javax.microedition.lcdui.Display.WIDTH;

public abstract class Displayable {

	Display currentDisplay;
	Command[] commands;
	int numCommands;
	CommandListener listener;

	public Displayable() {
	}

	public void addCommand(Command cmd) {
		if (cmd == null)
			throw new NullPointerException();
		addCommandImpl(cmd);
	}

	public void removeCommand(Command cmd) {
		removeCommandImpl(cmd);
	}

	public void setCommandListener(CommandListener l) {
		listener = l;
	}

	public int getwidth() {
		return WIDTH;
	}

	public int getheight() {
		return HEIGHT;
	}

	void addCommandImpl(Command cmd) {
		for (int i = 0; i < numCommands; i++)
			if (commands[i] == cmd)
				return;

		if (commands == null || numCommands == commands.length) {
			Command[] newCommands = new Command[numCommands + 4];
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
		if (currentDisplay != null && currentDisplay.isShown())
			currentDisplay.updateCommandSet();
	}

	Command[] getCommands() {
		return commands;
	}

	int getCommandCount() {
		return numCommands;
	}

	public abstract void callKeyPressed(int key);

	public abstract View getView();

}