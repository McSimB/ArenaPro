package javax.microedition.media;

public interface Player {

	public static final int UNREALIZED = 100;
	public static final int REALIZED = 200;
	public static final int PREFETCHED = 300;
	public static final int STARTED = 400;
	public static final int CLOSED = 0;
	public static final long TIME_UNKNOWN = -1L;

	void prefetch()
			throws MediaException;

	void start()
			throws MediaException;

	void stop()
			throws MediaException;

	void deallocate();

	void close();

	int getState();

	void setLoopCount(int i);

	void addPlayerListener(PlayerListener playerlistener);

}
