package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.MyTiledLayer;

import static com.elkware.midp.games.colorng.arena.high.Music.sound46;

public class Tiled {

	int var_e;
	int var_38;
	int var_72;
	int var_97;
	int var_bb;
	int var_f7;
	int var_12b;
	boolean var_15e = false;
	boolean var_1c1;
	int var_1cd = 0;
	int var_1f0;
	int var_242 = 0;
	MyCanvas myCanvas;
	byte[] var_298;
	byte[] var_2ed;

	public Tiled(int var1, int var2, int var3, int var4, int var5,
				 int var6, int var7, MyCanvas var8, int var9) {
		this.var_e = var1;
		this.var_38 = var2;
		this.var_72 = var3;
		this.var_97 = var4;
		this.var_bb = var5;
		this.var_f7 = var6;
		this.var_12b = var7;
		this.myCanvas = var8;
		this.var_1f0 = var9;
		this.var_1c1 = var1 != -1;
		if (this.var_1c1) {
			this.var_298 = new byte[5];
			this.var_2ed = new byte[5];
		}

	}

	public void sub_3f() {
		if (this.var_1cd-- <= 0) {
			if (this.var_1c1) {
				int var1;
				for (var1 = this.var_e; var1 <= this.var_72; ++var1) {
					for (int var2 = this.var_38; var2 <= this.var_97; ++var2) {
						this.myCanvas.var_1c98[var1 + this.myCanvas.var_1d1c
								* var2] = (byte) this.var_12b;
						this.myCanvas.var_1c70.setCell(var1, var2,
								(byte) this.var_12b);
					}
				}

				for (var1 = 0; var1 < this.var_242; ++var1) {
					this.myCanvas.var_22a0[this.var_298[var1]][this.var_2ed[var1]] = false;
					this.myCanvas.var_22a0[this.var_2ed[var1]][this.var_298[var1]] = false;
				}
			}

			this.var_15e = false;
			byte[] var3 = this.myCanvas.var_1c98;
			int var10001 = this.var_1f0;
			this.myCanvas.getClass();
			var3[var10001] = 71;
			MyTiledLayer var4 = this.myCanvas.var_1c70;
			var10001 = this.var_1f0 % this.myCanvas.var_1d1c;
			int var10002 = this.var_1f0 / this.myCanvas.var_1d1c;
			this.myCanvas.getClass();
			var4.setCell(var10001, var10002, 71);
		}

	}

	public void sub_6c(int var1) {
		if (!this.var_15e) {
			if (this.var_1c1) {
				int var2;
				for (var2 = this.var_e; var2 <= this.var_72; ++var2) {
					for (int var3 = this.var_38; var3 <= this.var_97; ++var3) {
						this.myCanvas.var_1c98[var2 + this.myCanvas.var_1d1c
								* var3] = (byte) this.var_bb;
						this.myCanvas.var_1c70.setCell(var2, var3,
								(byte) this.var_bb);
					}
				}

				for (var2 = 0; var2 < this.var_242; ++var2) {
					this.myCanvas.var_22a0[this.var_298[var2]][this.var_2ed[var2]] = true;
					this.myCanvas.var_22a0[this.var_2ed[var2]][this.var_298[var2]] = true;
				}

				this.sub_11b(var1);
			} else {
				//noinspection unchecked
				this.myCanvas.var_1db7.addElement(new Fallings(this.var_38,
						this.var_72, this.var_97, this.myCanvas, var1));
			}

			this.var_15e = true;
			this.var_1cd = this.var_f7;

			// TODO : play sound
			if (myCanvas.isSoundPlay && var1 == 0) {
				myCanvas.playSound(sound46);
			}
		}
	}

	public void sub_bb(byte var1, byte var2) {
		this.var_298[this.var_242] = var1;
		this.var_2ed[this.var_242++] = var2;
	}

	public void sub_11b(int var1) {
		for (int var3 = 0; var3 < this.myCanvas.var_1efb.length; ++var3) {
			if (var3 != var1) {
				Warrior var2 = this.myCanvas.var_1efb[var3];
				int var10000 = var2.var_48e + var2.var_feb;
				int var10001 = this.var_e;
				this.myCanvas.getClass();
				if (var10000 > var10001 * 15) {
					var10000 = var2.var_48e;
					var10001 = this.var_72;
					this.myCanvas.getClass();
					if (var10000 < var10001 * 15) {
						var10000 = var2.var_4dc + var2.var_ff9;
						var10001 = this.var_38 - 1;
						this.myCanvas.getClass();
						if (var10000 >= var10001 * 15) {
							var10000 = var2.var_4dc + var2.var_ff9;
							var10001 = this.var_97 + 1;
							this.myCanvas.getClass();
							if (var10000 <= var10001 * 15) {
								var2.var_a35 = var1;
								var2.var_a79 = 20;
							}
						}
					}
				}
			}
		}
	}
}
