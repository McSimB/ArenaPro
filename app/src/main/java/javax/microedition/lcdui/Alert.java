package javax.microedition.lcdui;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.microedition.util.ContextHolder;

public class Alert extends Displayable {

	private String string;
	private String string2;

	public Alert(String string, String var12, Image image, AlertType alertType) {
		this.string = string;
		string2 = var12;
	}

	public void setTimeout(char c) {
		// TODO Auto-generated method stub
	}

	@Override
	public void callKeyPressed(int key) {

	}

	@Override
	public View getView() {
		Toast.makeText(ContextHolder.getContext(), string + ":" + string2, Toast.LENGTH_SHORT).show();
		Log.d("alert", string + ":" + string2);
		return null;
	}
}
