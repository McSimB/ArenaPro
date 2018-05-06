package javax.microedition.lcdui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.elkware.midp.games.colorng.arena.high.R;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.util.ContextHolder;

public class List extends Displayable {

    public static final Command SELECT_COMMAND = new Command("", 1, 0);
    private Command selectCommand;
    private boolean[] selectedArray;
    private int selectedIndex;
    private int listType;
    private ArrayList<String> items;
    private String title;
    private ArrayAdapter<String> adapter;

    public List(String title, int listType, String[] items) {
        super();
        this.title = title;
        this.items = new ArrayList<String>();
        Collections.addAll(this.items, items);
        selectCommand = SELECT_COMMAND;
        this.listType = listType;
        if (listType != 3 && listType != 1 && listType != 2)
            throw new IllegalArgumentException();
        if (listType == 2) {
            selectedArray = new boolean[items.length];
        }
        adapter = new ArrayAdapter<String>(ContextHolder.getContext(), R.layout.list_item);
        getListView().setAdapter(adapter);
    }

    public List(String title, int listType) {
        this(title, listType, new String[0]);
    }

    @SuppressWarnings("unused")
    public List(String title, int listType, String[] items, Object image) {
        this(title, listType, items);
    }

    public int size() {
        return items.size();
    }

    public String getString(int elementNum) {
        return items.get(elementNum);
    }

    @SuppressWarnings({"SameParameterValue", "UnusedReturnValue", "unused"})
    public int append(String stringPart, Object image) {
        items.add(stringPart);
        if (listType == 2) {
            boolean[] selArray = new boolean[selectedArray.length + 1];
            System.arraycopy(selectedArray, 0, selArray, 0, selectedArray.length);
            selectedArray = selArray;
        }
        return items.indexOf(stringPart);
    }

    @SuppressWarnings({"SameParameterValue", "unused"})
    public void set(int var1, String string, Object o) {
        set(var1, string);
    }

    public void set(int elementNum, String stringPart) {
        items.set(elementNum, stringPart);
    }

    public boolean isSelected(int elementNum) {
        return selectedArray[elementNum];
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int elementNum) {
        selectedIndex = elementNum;
    }

    public void setSelectedFlags(boolean selectedArray[]) {
        this.selectedArray = selectedArray;
    }

    @Override
    public void callKeyPressed(int key) {
        super.callKeyPressed(key);
        if (key == Canvas.FIRE) {
            if (getCommandListener() != null && listType == 3)
                getCommandListener().commandAction(selectCommand, this);
        }
    }

    @Override
    public void removeCommand(Command cmd) {
        super.removeCommandImpl(cmd);
        if (cmd == selectCommand)
            selectCommand = null;
    }

    @Override
    public View getView() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedIndex(position);
                callKeyPressed(Canvas.FIRE);
            }
        });
        getCheckBox1().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[0] = isChecked;
            }
        });
        getCheckBox2().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[1] = isChecked;
            }
        });
        getCheckBox3().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[2] = isChecked;
            }
        });
        //selectedArray = new boolean[3];

        getEditText().setVisibility(View.INVISIBLE);
        getTitleView().setText(title);
        if (listType == 2) {
            getListView().setVisibility(View.INVISIBLE);
            getCheckBox1().setVisibility(View.VISIBLE);
            getCheckBox2().setVisibility(View.VISIBLE);
            getCheckBox3().setVisibility(View.VISIBLE);
            getCheckBox1().setText(items.get(0));
            getCheckBox2().setText(items.get(1));
            getCheckBox3().setText(items.get(2));
            getCheckBox1().setChecked(selectedArray[0]);
            getCheckBox2().setChecked(selectedArray[1]);
            getCheckBox3().setChecked(selectedArray[2]);
        } else {
            getCheckBox1().setVisibility(View.GONE);
            getCheckBox2().setVisibility(View.GONE);
            getCheckBox3().setVisibility(View.GONE);
            getListView().setVisibility(View.VISIBLE);
            adapter.clear();
            for (String s : items) {
                adapter.add(s);
            }
            removeScrollView();
        }
        return getLayout();
    }
}
