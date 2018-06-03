package javax.microedition.lcdui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.elkware.midp.games.colorng.arena.high.R;

import javax.microedition.util.ContextHolder;

import static javax.microedition.lcdui.Canvas.KEY_DISPLAY1;
import static javax.microedition.lcdui.Canvas.KEY_DISPLAY2;

public abstract class Displayable {

    private ViewGroup layout;
    private Command[] commands;
    private int numCommands;
    private CommandListener commandListener;

    @SuppressLint("InflateParams")
    public Displayable() {
        LayoutInflater inflater = ContextHolder.getContext().getLayoutInflater();
        layout = (ViewGroup) inflater.inflate(R.layout.list, null, false);
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public TextView getTitleView() {
        return layout.findViewById(R.id.titleView);
    }

    public ListView getListView() {
        return layout.findViewById(R.id.listView);
    }

    public EditText getEditText() {
        return layout.findViewById(R.id.editText);
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

    public void setCommandListener(CommandListener l) {
        commandListener = l;
    }

    public void addCommand(Command cmd) {
        if (cmd == null)
            throw new NullPointerException();
        addCommandImpl(cmd);
    }

    public void removeCommand(Command cmd) {
        removeCommandImpl(cmd);
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
        Command[] screenCommands = getCommands();
        int screenComCount = getCommandCount();
        for (int i = 0; i < screenComCount; i++)
            screenCommands[i].setInternalID(i);
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
                        commandListener.commandAction(command, this);
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
                        commandListener.commandAction(command, this);
                        break;
                    }
                }
            }
        }
    }

    public abstract View getView();

    protected void removeScrollView() {
        while (layout.getChildCount() > 6) {
            layout.removeViewAt(layout.getChildCount() - 1);
        }
    }
}