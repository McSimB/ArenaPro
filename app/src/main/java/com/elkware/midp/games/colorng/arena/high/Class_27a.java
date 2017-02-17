package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.Class_3a;

public class Class_27a {

	public final int var_65 = 1;
	public final int var_c0 = 2;
	public final int var_106 = 3;
	public final int var_176 = 40;
	public final int[] var_1c4 = new int[] { 50, 120, 70 };
	int var_219;
	int var_29d;
	int var_2e9;
	Class_3a var_314;
	Class_c6 var_375;
	int var_3bc = 0;
	int var_407 = 0;
	public boolean var_41b = false;
	int var_429 = 0;
	int var_43a;

	public Class_27a(int var1, int var2, int var3, Class_c6 var4, int var5) {
		this.var_219 = var1;
		var4.getClass();
		this.var_29d = var2 * 15;
		var4.getClass();
		this.var_2e9 = var3 * 15;
		this.var_375 = var4;
		this.var_43a = var5;

		while (true) {
			byte var10000 = var4.var_1c98[var2 + var3 * var4.var_1d1c];
			var4.getClass();
			if (var10000 <= 32) {
				var10000 = var4.var_1c98[var2 + var3 * var4.var_1d1c];
				var4.getClass();
				if (var10000 >= 1) {
					int var10001 = this.var_3bc;
					var4.getClass();
					this.var_3bc = var10001 * 15;
					this.var_407 = 9;
					this.var_314 = new Class_3a(var4.var_1dec[var1 - 1]);
					var4.sub_16c(var4.var_fb7, this.var_314);
					this.var_314.setPosition(var2, var3);
					return;
				}
			}

			++this.var_3bc;
			++var3;
		}
	}

	public boolean sub_20() {
		try {
			this.var_41b = this.var_3bc <= this.var_407 >> 2;
			if (!this.var_41b) {
				switch (this.var_219) {
				case 1:
					this.var_407 += Math.min(3, 40 - this.var_407);
					break;
				case 2:
					this.var_407 += Math.min(1, 40 - this.var_407);
					break;
				case 3:
					this.var_407 += Math.min(2, 40 - this.var_407);
				}

				this.var_3bc -= this.var_407 >> 2;
				this.var_2e9 += this.var_407 >> 2;
				this.var_429 = (this.var_429 + 1) % 4;
				this.var_314.setFrame(this.var_429);
				this.var_314.setPosition(this.var_29d, this.var_2e9);
			}

			return this.var_41b;
		} catch (Exception var2) {
			return true;
		}
	}

	public void sub_36(Class_2b8 var1) {
		var1.var_a35 = this.var_43a;
		var1.var_a79 = this.var_43a;
		var1.sub_1da(this.var_1c4[this.var_219 - 1]);
		this.var_3bc = -1;
		this.var_375.sub_a24(0, this.var_29d, this.var_2e9);
	}
}
