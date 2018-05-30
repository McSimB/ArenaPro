package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.MySprite;

public class Effects {

	static int[] var_55;
	public static int[] var_9d;
	static MySprite[][] var_a7;
	static int[] var_dc;
	int var_116;
	int var_134;
	int var_169;
	int var_182;
	MySprite sprite = null;
	MyCanvas var_1e1;

	static void sub_2c(MyCanvas var0) {
		try {
			var_55 = new int[5];

			int var1;
			for (var1 = 0; var1 < 5; ++var1) {
				var_55[var1] = var0.arena3.getParameter(350 + var1);
			}

			var_a7 = new MySprite[var_55.length][50];
			var_dc = new int[5];
			var_9d = new int[2];

			for (var1 = 0; var1 < 2; ++var1) {
				var_9d[var1] = var0.arena3.getParameter(300 + var1);
			}
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}

	public Effects(int var1, int var2, int var3, MyCanvas var4) {
		this.sub_6f_update_effects(var1, var2, var3, var4);
	}

	public void sub_6f_update_effects(int var1, int var2, int var3, MyCanvas var4) {
		try {
			this.var_116 = var1;
			this.var_134 = var2;
			this.var_169 = var3;
			this.var_1e1 = var4;
			this.var_182 = 0;
			if (var_dc[var1] == 0) {
				this.sprite = new MySprite(var4.effectsAnims[var1]);
			} else {
				this.sprite = var_a7[var1][var_dc[var1] - 1];
				var_a7[var1][--var_dc[var1]] = null;
			}

			this.sprite.setFrame(this.var_182);
			this.sprite.setPosition(var2, var3);
			var4.sub_16c(var4.var_1011, this.sprite);
			this.var_182 = 0;
		} catch (Exception var6) {
			var6.printStackTrace();
		}
	}

	public boolean sub_c5() {
		try {
			this.sprite.setFrame(this.var_182);
			this.sprite.setPosition(this.var_134, this.var_169);
			boolean var1 = ++this.var_182 >= var_55[this.var_116];
			if (var1) {
				var_a7[this.var_116][var_dc[this.var_116]++] = this.sprite;
			}

			return var1;
		} catch (Exception var2) {
			return true;
		}
	}
}
