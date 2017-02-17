package javax.microedition.rms;


public interface RecordComparator {

	public abstract int compare(byte abyte0[], byte abyte1[]);

	public static final int EQUIVALENT = 0;
	public static final int FOLLOWS = 1;
	public static final int PRECEDES = -1;
}
