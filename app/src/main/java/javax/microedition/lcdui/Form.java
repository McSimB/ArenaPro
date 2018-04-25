package javax.microedition.lcdui;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.microedition.util.ContextHolder;

public class Form extends Displayable {

	private String title;
	private ArrayList<String> strings;

	public Form(String title) {
		super();
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

	@SuppressLint("InflateParams")
	@Override
	public View getView() {
		getEditText().setVisibility(View.GONE);
		getListView().setVisibility(View.GONE);
		getTitleView().setText(title);
		removeScrollView();
		ScrollView scrollView = new ScrollView(ContextHolder.getContext());
		getLayout().addView(scrollView);
		for (String string : strings) {
			TextView textView = new TextView(ContextHolder.getContext());
			textView.setText(string);
			scrollView.addView(textView);
		}
		return getLayout();
	}
}
