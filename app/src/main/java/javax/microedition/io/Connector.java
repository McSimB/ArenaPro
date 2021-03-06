package javax.microedition.io;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import javax.microedition.io.file.FileConnection;

public class Connector {

	public static final int READ = 1;
	public static final int WRITE = 2;
	public static final int READ_WRITE = 3;

	private Connector() {
	}

	public static Connection open(String s) throws IOException {
		return null;
	}

	public static Connection open(String s, int i) throws IOException {
		File file = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		// TODO : open Pictures
		return new FileConnection(file);
	}
}
