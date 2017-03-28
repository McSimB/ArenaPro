package javax.microedition.io.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import javax.microedition.io.Connection;

public class FileConnection extends Connection {

	private File file;

	public FileConnection(File file) {
		this.file = file;
	}

	public DataInputStream openDataInputStream() throws IOException {
		return null;
	}

	public Enumeration list(final String s, boolean flag) throws IOException {
		String[] files = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".jpg");
			}
		});
		return Collections.enumeration(Arrays.asList(files));
	}

	@Override
	public void close() {
	}

}
