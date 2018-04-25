package javax.microedition.midlet;

import android.app.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

import javax.microedition.util.ContextHolder;

public abstract class MIDlet extends Application {

    private TreeMap<String, String> properties = new TreeMap<String, String>();

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets()
                    .open("META-INF/MANIFEST.MF")));

            String line;
            int index;

            while ((line = br.readLine()) != null) {
                index = line.indexOf(':');

                if (index > 0) {
                    properties.put(line.substring(0, index).trim(), line.substring(index + 1).trim());
                }
            }

            br.close();
        } catch (Throwable t) {
            System.out.println("getAppProperty() will not be available due to " + t.toString());
        }

        initApp();
    }

    public String getAppProperty(String key) {
        return properties.get(key);
    }

    public abstract void pauseApp();

    public abstract void destroyApp(boolean b);

    public void notifyPaused() {
    }

    public void notifyDestroyed() {
        ContextHolder.getContext().finish();
    }

    public abstract void initApp();

    public abstract void startApp();

}
