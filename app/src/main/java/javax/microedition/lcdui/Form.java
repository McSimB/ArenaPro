package javax.microedition.lcdui;

import android.view.View;

import com.elkware.midp.games.Arena1;

import java.util.ArrayList;

public class Form extends Displayable {

	private String title;
	private ArrayList<String> strings;

	public Form(String title, Arena1 arena1) {
		this.arena1 = arena1;
		this.title = title;
		strings = new ArrayList<String>();
	}

	public void append(String s) {
		strings.add(s);
	}

	@Override
	public void callKeyPressed(int key) {
		super.callKeyPressed(key);
	}

	@Override
	public View getView() {
		arena1.initForm(title, strings);
		return arena1.menuView;
	}

}
