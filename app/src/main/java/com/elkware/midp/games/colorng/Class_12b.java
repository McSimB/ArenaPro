package com.elkware.midp.games.colorng;

import java.io.IOException;

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public abstract class Class_12b extends Class_116 implements PlayerListener {

	Player[] var_22;
	short[] var_6e;
	boolean[] var_103;

	public Class_12b(j var1) {
		super(var1);
		super.var_96 = var1;
		int var2 = var1.sub_dc(5034);
		if (var2 < 0) {
			try {
				throw new IOException(
						"ERROR: You must set parameter 5034 to the number of sound files you use!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.var_22 = new Player[var2];
			this.var_6e = new short[var2];
			this.var_103 = new boolean[var2];
		}
	}

	public void playerUpdate(Player var1, String var2, Object var3) {
		if (var2.equals("endOfMedia")) {
			int var4;
			for (var4 = 0; var4 < this.var_22.length
					&& this.var_22[var4] != var1; ++var4) {
				;
			}

			if (var4 < this.var_22.length && this.var_103[var4]) {
				this.var_103[var4] = false;
				this.sub_4b(this.var_6e[var4]);
			}
		}

	}

	private int sub_21(int var1) {
		int var2;
		for (var2 = 0; var2 < this.var_6e.length && this.var_6e[var2] != var1; ++var2) {
			;
		}

		return var2 < this.var_6e.length ? var2 : -1;
	}

	public void sub_4b(int var1) {
		int var2 = this.sub_21(var1);
		if (var2 != -1) {
			try {
				this.var_22[var2].close();
				this.var_22[var2] = null;
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}

	}

	public void sub_7a1(int var1) {
		try {
			for (int var2 = 0; var2 < this.var_22.length; ++var2) {
				if (this.var_22[var2] != null) {
					this.var_22[var2].stop();
				}
			}
		} catch (MediaException var3) {
			;
		}

	}
}
