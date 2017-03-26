package javax.microedition.rms;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static com.elkware.midp.games.Arena1.APP_DATA_DIR;

public class RecordStoreFile {

	private static RecordStoreFile rootRSF;
	private String myStoragePath;
	private String path = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/" + APP_DATA_DIR;;
	private RandomAccessFile recordStream;

	public RecordStoreFile() {
		if (rootRSF != null) {
			throw new SecurityException();
		} else {
			rootRSF = this;
		}
	}

	private RecordStoreFile(String uidPath) throws IOException {
		myStoragePath = uidPath;
		File file = new File(uidPath);
		recordStream = new RandomAccessFile(file, "rw");
	}

	public RecordStoreFile newRecordStoreFile(String uidPath) throws IOException {
		if (this != rootRSF)
			throw new SecurityException();
		else
			return new RecordStoreFile(uidPath);
	}

	public String getUniqueIdPath(String fileName) {
		return getStoragePath(fileName);
	}

	public String getUniqueIdPath() {
		return myStoragePath;
	}

	public boolean exists(String uidPath) {
		File f = null;
		try {
			f = new File(uidPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f != null && f.exists();
	}

	public void seek(int pos)
			throws IOException {
		recordStream.seek(pos);
	}

	public void write(byte buf[]) throws IOException {
		write(buf, 0, buf.length);
	}

	public void write(byte buf[], int offset, int numBytes) throws IOException {
		recordStream.write(buf, offset, numBytes);
	}

	public int read(byte buf[]) throws IOException {
		return read(buf, 0, buf.length);
	}

	public int read(byte buf[], int offset, int numBytes) throws IOException {
		return recordStream.read(buf, offset, numBytes);
	}

	public void close() throws IOException {
		if (recordStream != null) {
			recordStream.close();
			recordStream = null;
		}
	}

	public void truncate(int size) throws IOException {
		if (recordStream != null)
			recordStream.setLength(size);
	}

	public int spaceAvailable() {
		return Integer.MAX_VALUE;
	}

	private String getStoragePath(String name) {
		return path + "/" + name;
	}

}