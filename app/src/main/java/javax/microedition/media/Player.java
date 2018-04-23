package javax.microedition.media;

public interface Player {

    int CLOSED = 0;
    int UNREALIZED = 100;
    int REALIZED = 200;
    int PREFETCHED = 300;
    int STARTED = 400;

    long TIME_UNKNOWN = -1;

    void realize() throws MediaException;

    void prefetch() throws MediaException;

    void start() throws MediaException;

    void stop() throws MediaException;

    void deallocate();

    void close();

    long setMediaTime(long now) throws MediaException;

    long getMediaTime();

    long getDuration();

    void setLoopCount(int count);

    int getState();

    void addPlayerListener(PlayerListener playerListener);

    void removePlayerListener(PlayerListener playerListener);
}