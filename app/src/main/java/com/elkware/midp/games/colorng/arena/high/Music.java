package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.Arena3;

import java.io.IOException;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public class Music implements PlayerListener {

	Arena3 arena3;
	Player[] players;
	short[] shorts;
	boolean[] _startedPlayers;
	short aShort;

	public Music(Arena3 var1) {
		this.arena3 = var1;
		int var2 = var1.getParameter(5034); // 15
		if (var2 < 0) {
			try {
				throw new IOException(
						"ERROR: You must set parameter 5034 to the number of sound files you use!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.players = new Player[var2];
			this.shorts = new short[var2];
			this._startedPlayers = new boolean[var2];
		}
	}

	public void beginPlay(int var1, int var2, int var3, boolean var4) {
		startPlayer(var1, var2, var3, var4);
	}

	private void startPlayer(int var1, int name, int loopCount, boolean var4) {
		if ((var1 != 0 || this.arena3._forPlayMus) && (var1 < 1 || this.arena3._forPlayMus1)) {
			try {
				int i = this.createPlayer(name);
				this._startedPlayers[i] = var4;
				this.players[i].setLoopCount(loopCount);
				this.players[i].start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void playerUpdate(Player var1, String var2, Object var3) {
		if (var2.equals(END_OF_MEDIA)) {
			int var4;
			var4 = 0;
			while (var4 < this.players.length && this.players[var4] != var1) {
				++var4;
			}

			if (var4 < this.players.length && this._startedPlayers[var4]) {
				this._startedPlayers[var4] = false;
				this.closePlayer(this.shorts[var4]);
			}
		}
	}

	private int sub_10d(int name) {
		int i = 0;
		while (i < this.shorts.length && this.shorts[i] != name) {
			++i;
		}

		return i < this.shorts.length ? i : -1;
	}

	public int createPlayer(int name) throws IOException, MediaException {
		int i = this.sub_10d(name);
		if (i == -1) {
			if (this.aShort == this.shorts.length) {
				throw new IOException(
						"ERROR: not enough slots for sound. Please increase value of parameter 5034!");
			}

			i = this.aShort++;
			this.shorts[i] = (short) name;
		} else if (this.players[i] != null) {
			this.players[i].prefetch();
			return i;
		}

		this.players[i] = Manager.createPlayer("_" + Integer.toHexString(name)
				.toUpperCase(), arena3);
		this.players[i].addPlayerListener(this);
		this.players[i].prefetch();
		return i;
	}

	public void closePlayer(int var1) {
		int var2 = this.sub_10d(var1);
		if (var2 != -1) {
			try {
				this.players[var2].stop();
				this.players[var2].close();
				this.players[var2] = null;
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}
	}

	public void stopPlayers() {
		try {
			for (Player player : this.players) {
				if (player != null) {
					player.stop();
				}
			}
		} catch (Exception var3) {
			var3.printStackTrace();
		}
	}

}
