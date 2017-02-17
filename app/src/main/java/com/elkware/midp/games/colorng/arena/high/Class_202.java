package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.Class_3a;

public class Class_202 {

	static int[] var_55;
	public static int[] var_9d;
	static Class_3a[][] var_a7;
	static int[] var_dc;
	int var_116;
	int var_134;
	int var_169;
	int var_182;
	Class_3a var_18c = null;
	Class_c6 var_1e1;

	static void sub_2c(Class_c6 var0) {
		try {
			var_55 = new int[5];

			int var1;
			for (var1 = 0; var1 < 5; ++var1) {
				var_55[var1] = var0.var_11b.sub_dc(350 + var1);
			}

			var_a7 = new Class_3a[var_55.length][50];
			var_dc = new int[5];
			var_9d = new int[2];

			for (var1 = 0; var1 < 2; ++var1) {
				var_9d[var1] = var0.var_11b.sub_dc(300 + var1);
			}
		} catch (Exception var2) {
			;
		}

	}

	public Class_202(int var1, int var2, int var3, Class_c6 var4) {
		this.sub_6f(var1, var2, var3, var4);
	}

	public void sub_6f(int var1, int var2, int var3, Class_c6 var4) {
		try {
			this.var_116 = var1;
			this.var_134 = var2;
			this.var_169 = var3;
			this.var_1e1 = var4;
			this.var_182 = 0;
			if (var_dc[var1] == 0) {
				this.var_18c = new Class_3a(var4.var_22ea[var1]);
			} else {
				this.var_18c = var_a7[var1][var_dc[var1] - 1];
				var_a7[var1][--var_dc[var1]] = null;
			}

			this.var_18c.setFrame(this.var_182);
			this.var_18c.setPosition(var2, var3);
			var4.sub_16c(var4.var_1011, this.var_18c);
			this.var_182 = 0;
		} catch (Exception var6) {
			;
		}

	}

	public boolean sub_c5() {
		try {
			this.var_18c.setFrame(this.var_182);
			this.var_18c.setPosition(this.var_134, this.var_169);
			boolean var1 = ++this.var_182 >= var_55[this.var_116];
			if (var1) {
				var_a7[this.var_116][var_dc[this.var_116]++] = this.var_18c;
			}

			return var1;
		} catch (Exception var2) {
			return true;
		}
	}
}
