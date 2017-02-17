package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.a;
import com.elkware.midp.games.colorng.j;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public class Class_114 extends Thread implements PlayerListener {

	boolean var_d = false;
	j var_63;
	private int var_c1 = -1;
	private int var_f6 = -1;
	private int var_152 = 1;
	Object var_1bd = new Object();
	Player[] var_1e8;
	short[] var_1f3;
	boolean[] var_23a;
	short var_297;
	Object var_2b6 = new Object();

	public Class_114(j var1) {
		this.var_63 = var1;
		int var2 = var1.sub_dc(5034);
		if (var2 < 0) {
			try {
				throw new IOException(
						"ERROR: You must set parameter 5034 to the number of sound files you use!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.var_1e8 = new Player[var2];
			this.var_1f3 = new short[var2];
			this.var_23a = new boolean[var2];
		}
	}

	public void run() {
		while (!this.var_d) {
			if (this.var_c1 != -1) {
				synchronized (this.var_1bd) {
					this.sub_9e(this.var_f6, this.var_c1, this.var_152, true);
					this.var_f6 = -1;
					this.var_c1 = -1;
				}
			}

			try {
				Thread.sleep(20L);
			} catch (Exception var3) {
				;
			}
		}

	}

	public void sub_53(int var1, int var2, int var3, boolean var4) {
		synchronized (this.var_1bd) {
			this.var_f6 = var1;
			this.var_c1 = var2;
			this.var_152 = var3;
		}
	}

	private void sub_9e(int var1, int var2, int var3, boolean var4) {
		if ((var1 != 0 || this.var_63.var_166)
				&& (var1 < 1 || this.var_63.var_b4)) {
			try {
				int var5 = this.sub_142(var2);
				if (this.sub_ab()) {
					return;
				}

				this.var_23a[var5] = var4;
				this.var_1e8[var5].setLoopCount(var3);
				this.var_1e8[var5].start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean sub_ab() {
		synchronized (this.var_2b6) {
			for (int var2 = 0; var2 < this.var_1e8.length; ++var2) {
				if (this.var_1e8[var2] != null
						&& this.var_1e8[var2].getState() != 300) {
					return true;
				}
			}

			return false;
		}
	}

	public void playerUpdate(Player var1, String var2, Object var3) {
		if (var2.equals("endOfMedia")) {
			int var4;
			for (var4 = 0; var4 < this.var_1e8.length
					&& this.var_1e8[var4] != var1; ++var4) {
				;
			}

			if (var4 < this.var_1e8.length && this.var_23a[var4]) {
				this.var_23a[var4] = false;
				this.sub_16e(this.var_1f3[var4]);
			}
		}

	}

	private int sub_10d(int var1) {
		int var2;
		for (var2 = 0; var2 < this.var_1f3.length && this.var_1f3[var2] != var1; ++var2) {
			;
		}

		return var2 < this.var_1f3.length ? var2 : -1;
	}

	public int sub_142(int var1) throws IOException, MediaException {
		int var2 = this.sub_10d(var1);
		if (var2 == -1) {
			if (this.var_297 == this.var_1f3.length) {
				throw new IOException(
						"ERROR: not enough slots for sound. Please increase value of parameter 5034!");
			}

			var2 = this.var_297++;
			this.var_1f3[var2] = (short) var1;
		} else if (this.var_1e8[var2] != null) {
			this.var_1e8[var2].prefetch();
			return var2;
		}

		InputStream var3 = a.sub_439(var1);
		int var4;
		String[] var5;
		if (var3 != null) {
			var4 = this.var_63.sub_37b() - 3;
		} else {
			var5 = new String[] { "mid", "wav", "mmf", "spf", "mp3" };

			for (var4 = 0; var4 < var5.length; ++var4) {
				var3 = this.getClass().getResourceAsStream(
						"/_" + Integer.toHexString(var1).toUpperCase() + "."
								+ var5[var4]);
				if (var3 != null) {
					break;
				}
			}

			if (var3 == null) {
				throw new IOException("sound not found!");
			}
		}

		var5 = new String[] { "audio/midi", "audio/x-wav", "audio/mmf",
				"audio/x-smaf", "audio/mp3" };
		this.var_1e8[var2] = Manager.createPlayer(var3, var5[var4]);
		this.var_1e8[var2].addPlayerListener(this);
		this.var_1e8[var2].prefetch();
		return var2;
	}

	public void sub_16e(int var1) {
		int var2 = this.sub_10d(var1);
		if (var2 != -1) {
			try {
				this.var_1e8[var2].stop();
				this.var_1e8[var2].deallocate();
				this.var_1e8[var2].close();
				this.var_1e8[var2] = null;
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}

	}

	public void sub_1a8(int var1) {
		try {
			for (int var2 = 0; var2 < this.var_1e8.length; ++var2) {
				if (this.var_1e8[var2] != null) {
					this.var_1e8[var2].stop();
				}
			}
		} catch (MediaException var3) {
			;
		}

	}

	public void sub_1ba() {
		this.sub_1a8(0);
	}
}
