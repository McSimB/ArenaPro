package javax.microedition.media;

import android.media.MediaPlayer;

import com.elkware.midp.games.colorng.Arena3;

public final class Manager {

	public static Player createPlayer(final String name, final Arena3 arena3) {
		return new Player() {

			MediaPlayer mediaPlayer;
			PlayerListener listener;
			int state;

			@Override
			public void prefetch() {
				mediaPlayer = MediaPlayer.create(arena3, arena3.getResources()
						.getIdentifier(name, "raw", arena3.getPackageName()));
				state = PREFETCHED;
			}

			@Override
			public void start() {
				mediaPlayer.start();
			}

			@Override
			public void stop() {
				mediaPlayer.stop();
			}

			@Override
			public void deallocate() {

			}

			@Override
			public void close() {
				mediaPlayer.release();
			}

			@Override
			public int getState() {
				return state;
			}

			@Override
			public void setLoopCount(int i) {

			}

			@Override
			public void addPlayerListener(PlayerListener playerlistener) {
				this.listener = playerlistener;
			}
		};
	}

}