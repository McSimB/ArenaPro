package javax.microedition.media;

import java.io.IOException;
import java.io.InputStream;

public final class Manager {

	public static Player createPlayer(InputStream stream, String type)
			throws IOException, MediaException {
		return new Player() {
			@Override
			public void prefetch() throws MediaException {

			}

			@Override
			public void start() throws MediaException {

			}

			@Override
			public void stop() throws MediaException {

			}

			@Override
			public void deallocate() {

			}

			@Override
			public void close() {

			}

			@Override
			public int getState() {
				return 0;
			}

			@Override
			public void setLoopCount(int i) {

			}

			@Override
			public void addPlayerListener(PlayerListener playerlistener) {

			}
		};
	}

}