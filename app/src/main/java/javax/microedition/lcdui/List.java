package javax.microedition.lcdui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.elkware.midp.games.colorng.arena.high.R;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.util.ContextHolder;
import javax.microedition.util.MainActivity;

public class List extends Displayable {

    public static final Command SELECT_COMMAND = new Command("", 1, 0);
    private Command selectCommand;
    private boolean[] selectedArray;
    private int selectedIndex;
    private int listType;
    private ArrayList<String> items;
    private String title;

    public List(String title, int listType, String[] items) {
        this.title = title;
        this.items = new ArrayList<>();
        Collections.addAll(this.items, items);
        selectCommand = SELECT_COMMAND;
        this.listType = listType;
        if (listType != 3 && listType != 1 && listType != 2)
            throw new IllegalArgumentException();
        if (listType == 2) {
            selectedArray = new boolean[items.length];
        }
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
        return elementNum == selectedIndex;
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

    @SuppressLint("InflateParams")
    @Override
    public View getView() {
        MainActivity context = ContextHolder.getContext();
        LayoutInflater inflater = context.getLayoutInflater();
        layout = (ViewGroup) inflater.inflate(R.layout.list, null, false);
        titleView = layout.findViewById(R.id.titleView);
        editText = layout.findViewById(R.id.editText);
        listView = layout.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(context, R.layout.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedIndex(position);
                callKeyPressed(Canvas.FIRE);
            }
        });
        checkBox1 = layout.findViewById(R.id.checkBox1);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[0] = isChecked;
            }
        });
        checkBox2 = layout.findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[1] = isChecked;
            }
        });
        checkBox3 = layout.findViewById(R.id.checkBox3);
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedArray[2] = isChecked;
            }
        });
        selectedArray = new boolean[3];

        editText.setVisibility(View.INVISIBLE);
        titleView.setText(title);
        if (listType == 2) {
            listView.setVisibility(View.INVISIBLE);
            checkBox1.setVisibility(View.VISIBLE);
            checkBox2.setVisibility(View.VISIBLE);
            checkBox3.setVisibility(View.VISIBLE);
            checkBox1.setText(items.get(0));
            checkBox2.setText(items.get(1));
            checkBox3.setText(items.get(2));
            checkBox1.setChecked(selectedArray[0]);
            checkBox2.setChecked(selectedArray[1]);
            checkBox3.setChecked(selectedArray[2]);
        } else {
            checkBox1.setVisibility(View.GONE);
            checkBox2.setVisibility(View.GONE);
            checkBox3.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            //listView.setMinimumHeight(HEIGHT * SCALE);
            adapter.clear();
            for (String s : items) {
                adapter.add(s);
            }
            removeScrollView();
        }
        return layout;
    }
}
