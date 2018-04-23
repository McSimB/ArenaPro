package javax.microedition.lcdui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkware.midp.games.colorng.arena.high.R;

import javax.microedition.util.ContextHolder;
import javax.microedition.util.MainActivity;

public class TextBox extends Displayable {

	private String title;
	private String hint;

	@SuppressWarnings("unused")
	public TextBox(String title, String hint, int maxSize, int constraints) {
		this.title = title;
		this.hint = hint;
	}

	public void setString(String s) {
		hint = s;
	}

	public String getString() {
		return editText.getText().toString();
	}

	@Override
	public void callKeyPressed(int key) {
		super.callKeyPressed(key);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView() {
		MainActivity context = ContextHolder.getContext();
		LayoutInflater inflater = context.getLayoutInflater();
		layout = (ViewGroup) inflater.inflate(R.layout.list, null, false);
		editText.setVisibility(View.VISIBLE);
		listView.setVisibility(View.GONE);
		titleView.setText(title);
		editText.setText(hint);
		removeScrollView();
		return layout;
	}
}
