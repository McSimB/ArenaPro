package javax.microedition.lcdui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.elkware.midp.games.colorng.arena.high.R;

import java.util.ArrayList;

import javax.microedition.util.ContextHolder;
import javax.microedition.util.MainActivity;

public class Form extends Displayable {

	private String title;
	private ArrayList<String> strings;

	public Form(String title) {
		this.title = title;
		strings = new ArrayList<>();
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
		MainActivity context = ContextHolder.getContext();
		LayoutInflater inflater = context.getLayoutInflater();
		layout = (ViewGroup) inflater.inflate(R.layout.list, null, false);
		editText.setVisibility(View.GONE);
		listView.setVisibility(View.GONE);
		titleView.setText(title);
		removeScrollView();
		ScrollView scrollView = new ScrollView(context);
		layout.addView(scrollView);
		for (String string : strings) {
			TextView textView = new TextView(context);
			textView.setText(string);
			scrollView.addView(textView);
		}
		return layout;
	}
}
