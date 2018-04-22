package javax.microedition.lcdui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.lcdui.event.SimpleEvent;

public class MicroActivity extends Activity {

    private Displayable current;

    private SimpleEvent msgSetCurrent = new SimpleEvent() {
        public void process() {
            current.setParentActivity(MicroActivity.this);
            setTitle(current.getTitle());
            setContentView(current.getDisplayableView());
        }
    };

    public void setCurrent(Displayable disp) {
        if (current != null) {
            current.setParentActivity(null);
        }

        current = disp;

        if (disp != null) {
            runOnUiThread(msgSetCurrent);
        } else {
            //ContextHolder.notifyPaused();
        }
    }

    public void setFullScreenMode() {
        Window wnd = getWindow();
        wnd.requestFeature(Window.FEATURE_NO_TITLE);
        wnd.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void startActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ContextHolder.addActivityToPool(this);
    }

    public void onResume() {
        super.onResume();
        //ContextHolder.setCurrentActivity(this);
    }

    public void onPause() {
        //ContextHolder.setCurrentActivity(null);
        super.onPause();
    }

    public void onDestroy() {
        if (current != null) {
            current.setParentActivity(null);
        }
        //ContextHolder.compactActivityPool(this);
        super.onDestroy();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (current != null) {
            current.populateMenu(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (current != null) {
            current.menuItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
