package javax.microedition.lcdui;

import android.view.View;

import com.elkware.midp.games.Arena1;

import static javax.microedition.lcdui.Canvas.KEY_DISPLAY1;
import static javax.microedition.lcdui.Canvas.KEY_DISPLAY2;
import static javax.microedition.lcdui.Display.HEIGHT;
import static javax.microedition.lcdui.Display.WIDTH;

public abstract class Displayable {

	Display currentDisplay;
	Command[] commands;
	int numCommands;
	CommandListener listener;
	Arena1 arena1;

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

	public void callKeyPressed(int key) {
		if (key == KEY_DISPLAY1) {
			Command[] commands = getCommands();
			if (commands != null) {
				for (int i = 0; i < getCommandCount(); i++) {
					Command command = getCommands()[i];
					if (command != null && (command.getCommandType() == Command.SCREEN
							| command.getCommandType() == Command.OK
							| command.getCommandType() == Command.HELP
							| command.getCommandType() == Command.ITEM)) {
						listener.commandAction(command, this);
						break;
					}
				}
			}
		} else if (key == KEY_DISPLAY2) {
			Command[] commands = getCommands();
			if (commands != null) {
				for (int i = 0; i < getCommandCount(); i++) {
					Command command = getCommands()[i];
					if (command != null && (command.getCommandType() == Command.BACK
							| command.getCommandType() == Command.CANCEL
							| command.getCommandType() == Command.STOP
							| command.getCommandType() == Command.EXIT)) {
						listener.commandAction(command, this);
						break;
					}
				}
			}
		}
	}

	public abstract View getView();

}