package javax.microedition.lcdui;

import android.view.View;

import com.elkware.midp.games.Arena1;

public class TextBox extends Displayable {

	private String title;
	private String hint;

	public TextBox(String s1, String s2, int i, int i1, Arena1 arena1) {
		this.arena1 = arena1;
		title = s1;
		hint = s2;
	}

	public void setString(String s) {
		hint = s;
	}

	public String getString() {
		return arena1.getFromTextBox();
	}

	@Override
	public void callKeyPressed(int key) {
		super.callKeyPressed(key);
	}

	@Override
	public View getView() {
		arena1.initTextBox(title, hint);
		return arena1.menuView;
	}

}
