package javax.microedition.lcdui;

import android.view.View;

public class TextBox extends Displayable {

	private String title;
	private String hint;

	@SuppressWarnings("unused")
	public TextBox(String title, String hint, int maxSize, int constraints) {
		super();
		this.title = title;
		this.hint = hint;
	}

	public void setString(String s) {
		hint = s;
	}

	public String getString() {
		return getEditText().getText().toString();
	}

	@Override
	public void callKeyPressed(int key) {
		super.callKeyPressed(key);
	}

	@Override
	public View getView() {
		getEditText().setVisibility(View.VISIBLE);
		getListView().setVisibility(View.GONE);
		getTitleView().setText(title);
		getEditText().setText(hint);
		removeScrollView();
		return getLayout();
	}
}
