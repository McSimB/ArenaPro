package com.elkware.midp.games.colorng;

import com.elkware.midp.games.colorng.arena.high.Arena;

import java.io.IOException;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public abstract class Canvas3 extends Canvas2 implements PlayerListener {

	Player[] players;
	short[] shortsPlayers;
	boolean[] booleanPlayers;

	public Canvas3(Arena var1) {
		super(var1);
		super.arena = var1;
		int var2 = var1.sub_dc(5034);
		if (var2 < 0) {
			try {
				throw new IOException(
						"ERROR: You must set parameter 5034 to the number of sound files you use!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.players = new Player[var2];
			this.shortsPlayers = new short[var2];
			this.booleanPlayers = new boolean[var2];
		}
	}

	@Override
	public void playerUpdate(Player var1, String var2, Object var3) {
		if (var2.equals("endOfMedia")) {
			int var4;
			var4 = 0;
			while (var4 < this.players.length && this.players[var4] != var1) {
				++var4;
			}

			if (var4 < this.players.length && this.booleanPlayers[var4]) {
				this.booleanPlayers[var4] = false;
				this.closePlayer(this.shortsPlayers[var4]);
			}
		}
	}

	private int sub_21(int var1) {
		int var2;
		var2 = 0;
		while (var2 < this.shortsPlayers.length && this.shortsPlayers[var2] != var1) {
			++var2;
		}

		return var2 < this.shortsPlayers.length ? var2 : -1;
	}

	private void closePlayer(int var1) {
		int var2 = this.sub_21(var1);
		if (var2 != -1) {
			try {
				this.players[var2].close();
				this.players[var2] = null;
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}
	}

	@Override
	public void stopPlayers(int var1) {
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
