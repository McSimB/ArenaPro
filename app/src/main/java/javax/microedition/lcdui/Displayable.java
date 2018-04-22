/*
 * Copyright 2012 Kulikov Dmitriy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.microedition.lcdui;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import javax.microedition.lcdui.event.CommandActionEvent;

public abstract class Displayable {

    private MicroActivity parent;
    private String title;
    private ArrayList<Command> commands;
    private CommandListener listener;
    private EventQueue queue;

    public Displayable() {
        commands = new ArrayList<>();
        listener = null;

        queue = new EventQueue();
    }

    public void setParentActivity(MicroActivity activity) {
        parent = activity;

        if (activity != null) {
            queue.startProcessing();
        } else {
            queue.stopProcessing();
        }

        clearDisplayableView();
    }

    public MicroActivity getParentActivity() {
        if (parent == null) {
            throw new IllegalStateException("call setParentActivity() before calling getParentActivity()");
        }

        return parent;
    }

    public boolean hasParentActivity() {
        return parent != null;
    }

    public void setTitle(String title) {
        this.title = title;

        if (parent != null) {
            parent.setTitle(title);
        }
    }

    public String getTitle() {
        return title;
    }

    public abstract View getDisplayableView();

    public abstract void clearDisplayableView();

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public void removeCommand(Command cmd) {
        commands.remove(cmd);
    }

    public int countCommands() {
        return commands.size();
    }

    public Command[] getCommands() {
        return commands.toArray(new Command[0]);
    }

    public void setCommandListener(CommandListener listener) {
        this.listener = listener;
    }

    public void fireCommandAction(Command c, Displayable d) {
        if (listener != null) {
            queue.postEvent(CommandActionEvent.getInstance(listener, c, d));
        }
    }

    public void populateMenu(Menu menu) {
        menu.clear();

        for (Command cmd : commands) {
            menu.add(Menu.NONE, cmd.hashCode(), cmd.getPriority(), cmd.getLabel());
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean menuItemSelected(MenuItem item) {
        if (listener == null) {
            return false;
        }

        int id = item.getItemId();

        for (Command cmd : commands) {
            if (cmd.hashCode() == id) {
                queue.postEvent(CommandActionEvent.getInstance(listener, cmd, this));
                return true;
            }
        }

        return false;
    }

    public EventQueue getEventQueue() {
        return queue;
    }

    public void postEvent(Event event) {
        queue.postEvent(event);
    }
}