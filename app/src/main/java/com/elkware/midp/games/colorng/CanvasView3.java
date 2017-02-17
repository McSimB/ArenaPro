package com.elkware.midp.games.colorng;

import com.elkware.midp.games.colorng.arena.high.ArenaMidlet;

import java.io.IOException;

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public abstract class CanvasView3 extends CanvasView2 implements PlayerListener {

	Player[] var_22;
	short[] var_6e;
	boolean[] var_103;

	public CanvasView3(ArenaMidlet var1) {
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
			this.var_22 = new Player[var2];
			this.var_6e = new short[var2];
			this.var_103 = new boolean[var2];
		}
	}

	@Override
	public void playerUpdate(Player var1, String var2, Object var3) {
		if (var2.equals("endOfMedia")) {
			int var4;
			var4 = 0;
			while (var4 < this.var_22.length && this.var_22[var4] != var1) {
				++var4;
			}

			if (var4 < this.var_22.length && this.var_103[var4]) {
				this.var_103[var4] = false;
				this.sub_4b(this.var_6e[var4]);
			}
		}
	}

	private int sub_21(int var1) {
		int var2;
		var2 = 0;
		while (var2 < this.var_6e.length && this.var_6e[var2] != var1) {
			++var2;
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

	@Override
	public void sub_7a1(int var1) {
		try {
			for (Player aVar_22 : this.var_22) {
				if (aVar_22 != null) {
					aVar_22.stop();
				}
			}
		} catch (MediaException var3) {
			var3.printStackTrace();
		}
	}

}
