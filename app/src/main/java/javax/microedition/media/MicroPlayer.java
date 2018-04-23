package javax.microedition.media;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;

public class MicroPlayer implements Player, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    protected DataSource source;
    protected MediaPlayer player;
    protected int state;
    protected int loopCount;

    protected ArrayList<PlayerListener> listeners;

    protected boolean mute;
    protected int level, pan;

    public MicroPlayer() {
        this(null);
    }

    public MicroPlayer(DataSource datasource) {
        player = new MediaPlayer();

        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);

        source = datasource;
        state = UNREALIZED;

        mute = false;
        level = 100;
        pan = 0;

        listeners = new ArrayList<>();
    }

    public void setDataSource(DataSource datasource) throws IOException {
        deallocate();

        if (source != null) {
            source.close();
        }

        source = datasource;
    }

    public void addPlayerListener(PlayerListener playerListener) {
        if (!listeners.contains(playerListener)) {
            listeners.add(playerListener);
        }
    }

    @Override
    public void removePlayerListener(PlayerListener playerListener) {
        listeners.remove(playerListener);
    }

    public void postEvent(String event) {
        for (PlayerListener listener : listeners) {
            listener.playerUpdate(this, event, source.getURL());
        }
    }

    public void onPrepared(MediaPlayer mp) {
        // state = PREFETCHED;
    }

    public synchronized void onCompletion(MediaPlayer mp) {
        postEvent(PlayerListener.END_OF_MEDIA);

        //player.seekTo(0);

        if (loopCount == 1) {
            state = PREFETCHED;
        } else if (loopCount > 1) {
            loopCount--;
        }

        if (state == STARTED) {
            player.start();
            postEvent(PlayerListener.STARTED);
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        System.out.println("Error in MP " + source.getURL() + ": " + what + ", " + extra);
        return true;
    }

    public void realize() throws MediaException {
        checkClosed();

        if (source == null) {
            throw new IllegalStateException("call setDataSource() before calling realize()");
        }

        if (state == UNREALIZED) {
            try {
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                source.setFor(retriever);

                retriever.release();
            } catch (Throwable e) {
                source.close();
            }

            try {
                source.setFor(player);
            } catch (IOException e) {
                throw new MediaException(e);
            }

            state = REALIZED;
        }
    }

    public void prefetch() throws MediaException {
        checkClosed();

        if (state == UNREALIZED) {
            realize();
        }

        if (state == REALIZED) {
            try {
                player.prepare();
                state = PREFETCHED;
            } catch (IOException e) {
                throw new MediaException(e);
            }
        }
    }

    public synchronized void start() throws MediaException {
        prefetch();

        if (state == PREFETCHED) {
            player.start();

            state = STARTED;
            postEvent(PlayerListener.STARTED);
        }
    }

    public synchronized void stop() {
        if (state == STARTED) {
            player.pause();

            state = PREFETCHED;
            postEvent(PlayerListener.STOPPED);
        }
    }

    public void deallocate() {
        checkClosed();

        stop();

        if (state != UNREALIZED) {
            player.reset();
            state = UNREALIZED;
        }
    }

    public void close() {
        stop();

        if (state != CLOSED) {
            player.release();
        }

        source.close();

        state = CLOSED;
        postEvent(PlayerListener.CLOSED);
    }

    protected void checkClosed() {
        if (state == CLOSED) {
            throw new IllegalStateException("player is closed");
        }
    }

    protected void checkRealized() {
        checkClosed();

        if (state == UNREALIZED) {
            throw new IllegalStateException("call realize() before using the player");
        }
    }

    public long setMediaTime(long now) throws MediaException {
        checkRealized();

        player.seekTo((int) (now / 1000L));
        return getMediaTime();
    }

    public long getMediaTime() {
        checkRealized();
        return player.getCurrentPosition() * 1000;
    }

    public long getDuration() {
        checkRealized();
        return player.getDuration() * 1000;
    }

    public void setLoopCount(int count) {
        checkRealized();

        if (count == 0) {
            throw new IllegalArgumentException("loop count must not be 0");
        }

        loopCount = count;
    }

    public int getState() {
        return state;
    }
}