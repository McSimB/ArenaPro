package com.elkware.midp.games.colorng;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MySprite extends MySprite1 {

	boolean[] var_36 = new boolean[0];
	boolean[][] var_57;
	boolean[][] var_8d;
	byte[][] var_cc;
	short[][] var_e0;
	short[][] var_12e;
	short[][] var_165;
	short[][] var_1ab;
	int var_208;
	int var_22a;
	int var_276;

	public MySprite(Image var1, int var2, int var3) {
		super(var1, var2, var3);
	}

	public MySprite(MySprite var1) {
		super(var1);
		this.var_36 = var1.var_36;
		this.var_cc = var1.var_cc;
		this.var_1ab = var1.var_1ab;
		this.var_12e = var1.var_12e;
		this.var_165 = var1.var_165;
		this.var_e0 = var1.var_e0;
		this.var_57 = var1.var_57;
		this.var_8d = var1.var_8d;
		if (this.var_36.length > 0) {
			this.sub_4f(0L);
		}

	}

	public synchronized void sub_4f(long var1) {
		if (this.var_36 != null) {
			this.var_276 = (int) ((long) this.var_276 + var1);

			while (this.var_276 >= this.var_e0[this.var_208][this.var_22a]) {
				this.var_276 -= this.var_e0[this.var_208][this.var_22a];
				if (++this.var_22a == this.var_1ab[this.var_208].length) {
					if (this.var_36[this.var_208]) {
						this.var_22a %= this.var_1ab[this.var_208].length;
					} else {
						--this.var_22a;
					}
				}
			}

			this.sub_108(this.var_cc[this.var_208][this.var_22a] & 255);
			this.setFrame(this.var_1ab[this.var_208][this.var_22a]);
			this.sub_166(this.var_12e[this.var_208][this.var_22a],
					this.var_165[this.var_208][this.var_22a]);
			this.sub_18c(this.var_57[this.var_208][this.var_22a],
					this.var_8d[this.var_208][this.var_22a]);
		}
	}

	public void sub_90(Graphics var1, int var2, int var3) {
		this.sub_eb(null, var1, var2, var3);
	}

}
