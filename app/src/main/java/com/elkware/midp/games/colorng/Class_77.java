package com.elkware.midp.games.colorng;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Class_77 extends Sprite {

	int var_1d;
	int var_33;
	int[] var_62;
	int[] var_ac;
	int[] var_de;
	int var_1e9;
	int var_233;
	Image[] var_24b;
	public int[] var_27d;
	public int[] var_2d4;
	public int[][] var_323;
	public byte[][] var_337;
	int var_351 = 256;
	boolean var_387;
	boolean var_3b8;
	boolean var_410;
	public boolean var_441;
	public boolean[] var_475;
	int var_4c0;
	int var_4f2;
	int var_4ff;
	int var_528;

	Class_77() {
		super(Image.createImage(1, 1));
	}

	public Class_77(Image var1, int var2, int var3) {
		super(Image.createImage(1, 1));
		this.sub_37(1);
		this.sub_56(0, var1, var2, var1.getWidth() / var2, var3);
	}

	public Class_77(Class_3a var1) {
		super(var1.var_24b[0], var1.var_62[0], var1.var_ac[0]);
		this.var_24b = var1.var_24b;
		this.var_62 = var1.var_62;
		this.var_ac = var1.var_ac;
		this.var_de = var1.var_de;
		if (var1.var_4ff != 0 && var1.var_528 != 0) {
			this.defineCollisionRectangle(var1.var_4c0, var1.var_4f2,
					var1.var_4ff, var1.var_528);
		}

		this.var_27d = var1.var_27d;
		this.var_2d4 = var1.var_2d4;
		this.var_323 = var1.var_323;
		this.var_337 = var1.var_337;
		this.var_351 = var1.var_351;
		this.var_410 = var1.var_410;
		this.var_441 = var1.var_441;
		this.var_475 = var1.var_475;
	}

	protected void sub_37(int var1) {
		this.var_24b = new Image[var1];
		this.var_62 = new int[var1];
		this.var_ac = new int[var1];
		this.var_de = new int[var1];
	}

	protected void sub_56(int var1, Image var2, int var3, int var4, int var5) {
		super.setImage(var2, var3, var5);
		this.var_24b[var1] = var2;
		this.var_62[var1] = var3;
		this.var_ac[var1] = var5;
		this.var_de[var1] = var4;
	}

	public void setFrame(int var1) {
		this.var_33 = var1;
		if (!this.var_410) {
			super.setFrame(var1);
		}

	}

	public int sub_6a() {
		return this.var_62[this.var_1d];
	}

	public int sub_96() {
		return this.var_ac[this.var_1d];
	}

	public synchronized void sub_eb(CanvasView1 var1, Graphics var2, int var3,
									int var4) {
		if (this.isVisible()) {
			int var5 = var2.getClipX();
			int var6 = var2.getClipY();
			int var7 = var2.getClipWidth();
			int var8 = var2.getClipHeight();
			var3 += this.getX() + this.var_1e9;
			var4 += this.getY() + this.var_233;
			int var9;
			int var10;
			if (this.var_410) {
				var9 = this.var_62[this.var_33];
				var10 = this.var_ac[this.var_33];
				int var11 = var9 * this.var_351 >> 8;
				int var12 = var10 * this.var_351 >> 8;
				int var13 = var3;
				int var14 = var4;
				int var15 = var3 + var11;
				int var16 = var4 + var12;
				int var17 = var5 + var7;
				int var18 = var6 + var8;
				if (var3 < var5) {
					var13 = var5;
				}

				if (var4 < var6) {
					var14 = var6;
				}

				if (var15 > var17) {
					var15 = var17;
				}

				if (var16 > var18) {
					var16 = var18;
				}

				if (var13 >= var15 || var14 >= var16) {
					return;
				}

				if (this.var_351 == 256) {
					var1.sub_135(var2, this.var_337[this.var_33],
							this.var_475[this.var_441 ? 0 : this.var_33], var9,
							var10, this.var_441 ? this.var_27d
									: this.var_323[this.var_33], var3, var4,
							this.var_387, this.var_3b8, var13, var14, var15,
							var16);
				} else {
					var1.sub_14b(var2, this.var_337[this.var_33],
							this.var_475[this.var_441 ? 0 : this.var_33], var9,
							var10, this.var_441 ? this.var_27d
									: this.var_323[this.var_33], var3, var4,
							var11, var12, this.var_387, this.var_3b8, var13,
							var14, var15, var16);
				}
			} else {
				var9 = this.getX();
				var10 = this.getY();
				this.setPosition(var3, var4);
				super.paint(var2);
				this.setPosition(var9, var10);
			}

		}
	}

	void sub_108(int var1) {
		if (!this.var_410) {
			super.setImage(this.var_24b[var1], this.var_62[var1],
					this.var_ac[var1]);
		}

	}

	void sub_166(int var1, int var2) {
		this.var_1e9 = var1;
		this.var_233 = var2;
	}

	void sub_18c(boolean var1, boolean var2) {
		this.var_387 = var1;
		this.var_3b8 = var2;
		if (!this.var_410) {
			int var3 = this.getX();
			int var4 = this.getY();
			if (var1) {
				this.setTransform(2);
			} else if (var2) {
				this.setTransform(1);
			} else {
				this.setTransform(0);
			}

			this.setPosition(var3, var4);
		}

	}

	public void defineCollisionRectangle(int var1, int var2, int var3, int var4) {
		super.defineCollisionRectangle(var1, var2, var3, var4);
		this.var_4c0 = var1;
		this.var_4f2 = var2;
		this.var_4ff = var3;
		this.var_528 = var4;
	}

	public boolean sub_1cc(Class_3a var1, boolean var2) {
		int var3;
		int var4;
		int var5;
		int var6;
		if (!this.var_410) {
			var3 = this.getX();
			var4 = this.getY();
			var5 = var1.getX();
			var6 = var1.getY();
			this.setPosition(var3 + this.var_1e9, var4 + this.var_233);
			var1.setPosition(var5 + var1.var_1e9, var6 + var1.var_233);
			boolean var7 = super.collidesWith(var1, var2);
			this.setPosition(var3, var4);
			var1.setPosition(var5, var6);
			return var7;
		} else {
			var3 = this.getX() + this.var_4c0;
			var4 = this.getY() + this.var_4f2;
			var5 = var1.getX() + var1.var_4c0;
			var6 = var1.getY() + var1.var_4f2;
			return var5 + var1.var_4ff > var3 && var5 < var3 + this.var_4ff
					&& var6 + var1.var_528 > var4 && var6 < var4 + this.var_528;
		}
	}
}
