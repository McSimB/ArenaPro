package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.MySprite;

public class Warrior {

	static int[] var_13 = new int[] { 7, 6, 10, 8, 4, 4 };
	static int[] var_61 = new int[] { 0, 0, 0, 0, 0, 0 };
	static int[] var_b0 = new int[] { 0, 1, 0, 0, 0, 0 };
	static int[] var_111 = new int[] { -1, 0, 3, 0, 0, -2 };
	static int[] var_142 = new int[] { 4, 10, 3, 7, 3, 6 };
	static final int[] var_18b = new int[] { 4, 11, 2, 5, 4, 3 };
	static final int[] var_1e4_damage = new int[] { 5, 30, 10, 20, 20, 15 };
	static final int[] var_248 = new int[] { 5000, 5, 25, 10, 7, 15 };
	static final int[] var_294 = new int[] { 1000, 1000, 1000, 1000, 7, 20 };
	static final int[] var_2ad = new int[] { 1, 9, 0, 8, 5, 13, 6, 14, 7, 15 };
	static final int[] var_2f8 = new int[] { 4, 12, 0, 8, 5, 13, 6, 14, 7, 15 };
	int var_30b = 0;
	int var_36c = 0;
	public static MyCanvas mCanvas;
	public static int var_3f5;
	static int var_452 = 0;
	int var_48e;
	int var_4dc;
	int var_53c;
	int var_579;
	int var_596;
	int var_5ef = 0;
	int var_60f;
	int var_654;
	boolean var_6b1;
	boolean var_6e1;
	int var_6f5 = 0;
	boolean var_716;
	boolean var_74b;
	boolean var_792;
	boolean var_7c1;
	boolean var_822 = false;
	boolean var_82c = false;
	boolean var_86a = false;
	boolean var_899 = false;
	boolean var_8af = false;
	public int var_8ee;
	public int var_94c;
	public int var_957 = 0;
	int var_98b = 0;
	int var_9de;
	int var_a0d;
	int var_a1b;
	int var_a35 = -1;
	int var_a79 = 0;
	public boolean var_aaf = true;
	int var_af7;
	int var_b25;
	int var_b5f;
	int var_b74;
	int var_b81_hp;
	public int var_b9b;
	boolean var_bd6_is_enemy = false;
	boolean var_c05_use = false;
	boolean var_c39 = false;
	boolean var_c7b = false;
	int var_cd8;
	int var_d12;
	int var_d40 = 0;
	int var_d5c = 0;
	int var_d8b = -1;
	int var_dc6 = -1;
	int var_e08 = 0;
	boolean[] var_e26;
	int var_e4b = 1;
	MySprite var_e6d;
	MySprite var_ea2;
	int var_ec1 = 12;
	int var_ee8 = 0;
	int var_f3a = 0;
	int var_f87 = 0;
	public int var_feb;
	public int var_ff9;
	static int var_1043 = (int) System.currentTimeMillis();
	boolean var_104d = false;
	boolean var_1060 = false;
	int var_10b4 = 0;
	static int var_110a = 4;
	static int var_112f = 0;
	static int var_1178 = 1;
	static int var_11c0 = 2;
	static byte var_11d5 = 1;
	static byte var_121d = 2;
	static byte var_123f = 3;
	static byte var_1291 = 4;
	static byte var_12cd = 5;
	static byte var_1315 = 6;
	int var_1326;
	int var_1385;
	int var_13e5;
	int var_13ef;
	int var_1447;
	int var_1495;
	int var_14a7;
	int var_14e5;
	int var_152b;
	boolean var_1577;
	boolean var_15b5;
	boolean var_160b;
	boolean var_161a;
	boolean var_1656;
	boolean var_16a0;
	boolean var_16fc;
	int var_171e;
	int var_176b;
	boolean var_17c0;
	boolean var_17fe;
	boolean var_181a;
	private int var_182b;
	private int var_1887;
	public int var_18c8;

	public Warrior(MySprite var1, MySprite var2, int var3, int var4,
				   int var5, int var6, int var7, int var8) {
		this.var_13e5 = var_112f;
		this.var_1495 = 0;
		this.var_14a7 = 0;
		this.var_14e5 = 0;
		this.var_152b = 0;
		this.var_1577 = false;
		this.var_15b5 = false;
		this.var_1656 = false;
		this.var_16a0 = false;
		this.var_16fc = false;
		this.var_171e = -1;
		this.var_176b = 0;
		this.var_17c0 = false;
		this.var_17fe = false;
		this.var_181a = false;
		this.var_182b = 0;
		this.var_1887 = 0;
		this.var_18c8 = 0;
		this.var_e6d = new MySprite(var1);
		this.var_ea2 = new MySprite(var2);
		this.var_48e = var3;
		this.var_4dc = var4;
		this.var_af7 = this.var_b81_hp = var5;
		this.var_b25 = var6;
		this.var_b5f = var7;
		this.var_b74 = var8;
		this.var_a1b = 0;
		mCanvas.sub_16c(var_3f5, this.var_e6d);
		mCanvas.sub_16c(var_3f5, this.var_ea2);
		this.var_e6d.defineCollisionRectangle(0, 0, this.var_e6d.sub_6a(),
				this.var_e6d.sub_96() + this.var_ea2.sub_96());
		this.var_ff9 = var1.sub_96() + var2.sub_96();
		this.var_feb = var1.sub_6a();
		this.var_b9b = var_452++;
		this.var_e26 = new boolean[mCanvas.var_1efb.length];
	}

	public static void sub_1d(MyCanvas var0, int var1) {
		mCanvas = var0;
		var_3f5 = var1;
	}

	static int random(int var0) {
		var_1043 = (int) ((long) var_1043 + System.currentTimeMillis());
		return Math.abs(var_1043 % var0);
	}

	public void sub_8f() {
		this.var_c39 = this.var_822 = this.var_c7b = false;
		if (this.var_5ef == 0) {
			this.var_957 = 0;
		} else if (this.var_957 != 0) {
			this.var_957 = Math.max(this.var_957 - 1, 1);
		}

		this.var_aaf = this.var_b81_hp > 0;
		int var10001;
		int var10002;
		if (this.var_aaf && this.var_82c) {
			this.var_86a = this.var_899 = false;
			this.var_ee8 = Math.max(0, this.var_ee8 - 1);
			if (!this.var_8af && this.var_6b1 && !this.var_6e1) {
				this.var_53c /= 4;
			}

			if (this.var_a79-- < 1) {
				this.var_a79 = 0;
				this.var_a35 = -1;
			}

			var10001 = this.var_4dc;
			var10002 = this.var_579;
			mCanvas.getClass();
			this.var_4dc = var10001 + Math.min(var10002, 15 - 1);
			if (!this.var_7c1) {
				var10001 = this.var_48e;
				var10002 = this.var_6b1 ? this.var_53c + this.var_5ef
						: this.var_53c / 2 + this.var_5ef;
				mCanvas.getClass();
				this.var_48e = var10001 + Math.min(var10002, 15);
			} else {
				var10001 = this.var_48e;
				var10002 = this.var_5ef / 2;
				mCanvas.getClass();
				this.var_48e = var10001 + Math.min(var10002, 15);
			}

			int var10003;
			boolean var7;
			label196: {
				var10001 = Math.max(7, this.var_48e);
				var10002 = mCanvas.var_1d1c;
				mCanvas.getClass();
				this.var_48e = Math.min(var10001, var10002 * 15 - this.var_feb
						- 7);
				var10001 = Math.max(0, this.var_4dc);
				var10002 = mCanvas.var_1d37;
				mCanvas.getClass();
				this.var_4dc = Math.min(var10001, var10002 * 15 - this.var_ff9);
				var10002 = this.var_8ee;
				mCanvas.getClass();
				var10002 /= 15;
				var10003 = this.var_4dc + this.var_ff9 - 1;
				mCanvas.getClass();
				this.var_6f5 = this.var_98b = var10002 + var10003 / 15
						* mCanvas.var_1d1c;
				byte var6 = mCanvas.var_1c98[this.var_6f5];
				mCanvas.getClass();
				if (var6 >= 75) {
					var6 = mCanvas.var_1c98[this.var_6f5];
					mCanvas.getClass();
					if (var6 <= 78) {
						var7 = true;
						break label196;
					}
				}

				var7 = false;
			}

			this.var_6e1 = var7;
			byte var5 = mCanvas.var_1c98[this.var_98b];
			mCanvas.getClass();
			int var10004;
			if (var5 == 8) {
				this.var_6b1 = this.var_86a = this.var_579 > -1;
				if (this.var_6b1) {
					var10001 = this.var_98b / mCanvas.var_1d1c;
					mCanvas.getClass();
					var10001 *= 15;
					var10003 = this.var_48e;
					var10004 = this.var_98b % mCanvas.var_1d1c;
					mCanvas.getClass();
					this.var_4dc = var10001
							+ Math.max(-1, var10003 - var10004 * 15)
							- this.var_ff9;
				}
			} else {
				var5 = mCanvas.var_1c98[this.var_98b];
				mCanvas.getClass();
				if (var5 == 7) {
					this.var_6b1 = this.var_86a = this.var_579 > -1;
					if (this.var_6b1) {
						var10001 = this.var_98b / mCanvas.var_1d1c + 1;
						mCanvas.getClass();
						var10001 *= 15;
						mCanvas.getClass();
						var10002 = 15 + 1;
						var10003 = this.var_48e + this.var_feb;
						var10004 = this.var_98b % mCanvas.var_1d1c;
						mCanvas.getClass();
						this.var_4dc = var10001
								- Math.min(var10002, var10003 - var10004 * 15)
								- this.var_ff9;
					}
				}
			}

			this.var_6b1 = this.var_6b1 && this.var_579 > -1;
			if (!this.var_6e1 || mCanvas.var_ba9 % 5 == 0) {
				if (this.var_5ef < 0) {
					++this.var_5ef;
				} else if (this.var_5ef > 0) {
					--this.var_5ef;
				}
			}

			this.var_104d = this.var_104d && !this.var_6b1;
			if (this.var_6b1) {
				this.var_579 = 0;
				this.var_53c /= 2;
				if (this.var_6e1 && (this.var_716 || this.var_74b)) {
					int var10000 = this.var_5ef;
					mCanvas.getClass();
					if (var10000 < 15 / 3) {
						this.var_5ef += this.var_716 ? -1 : 1;
					}
				}
			} else if (this.var_792) {
				this.var_579 += 2;
			} else {
				this.var_579 += 3;
			}

			var10001 = this.var_579;
			mCanvas.getClass();
			this.var_579 = Math.min(var10001, 15);
			if (this.var_1060) {
				//noinspection ConstantConditions
				this.var_1060 = this.var_1060 && !this.var_6b1;
				if (!this.var_1060) {
					this.var_14a7 = 50;
					this.var_176b = 20;
				}
			}

			this.var_60f = this.var_4dc;
			this.var_654 = this.var_48e;
			this.var_8ee = this.var_48e + this.var_feb / 2;
			this.var_94c = this.var_4dc + this.var_ff9 / 2;
			this.var_d40 = Math.max(this.var_d40 - 1, 0);
			this.var_13ef = mCanvas.var_1cdd[this.var_6f5];
			if ((this.var_596 != 0 || this.var_5ef <= 0)
					&& (this.var_596 != 1 || this.var_5ef >= 0)) {
				if (!this.var_6b1 && this.var_579 < 0) {
					this.var_36c = 4;
				} else if (!this.var_6b1 && this.var_579 > -1) {
					this.var_36c = 6;
				} else if (this.var_53c != 0 && !this.var_7c1) {
					this.var_36c = 0;
				} else {
					this.var_36c = 2;
					this.var_30b = 0;
				}
			} else {
				this.var_36c = 8;
			}

			this.var_36c += this.var_596;
			++this.var_30b;
			this.var_30b %= 1 + (var_2f8[this.var_36c] - var_2ad[this.var_36c]);
			this.var_ea2.setFrame(var_2ad[this.var_36c] + this.var_30b);
			this.var_e6d.setFrame(this.var_596);
		} else if (this.var_e4b-- < 1) {
			if (this == mCanvas.var_1f25_player) {
				mCanvas.var_3386 = mCanvas.var_a72 = true;
			}

			this.var_82c = this.var_899 = true;
			this.var_1656 = false;
			this.var_5ef = 0;

			try {
				int var4 = random(mCanvas.var_1f77.length);
				this.var_b81_hp = this.var_af7;
				this.var_53c = this.var_579 = this.var_f3a = this.var_e4b = 0;
				this.var_a35 = -1;
				this.var_a79 = 0;
				this.var_dc6 = -1;
				this.var_1060 = this.var_104d = this.var_16a0 = false;
				var10002 = mCanvas.var_1f77[var4] % mCanvas.var_1d1c;
				mCanvas.getClass();
				this.var_48e = this.var_654 = var10002 * 15 - this.var_feb / 2;
				var10002 = mCanvas.var_1f77[var4] / mCanvas.var_1d1c;
				mCanvas.getClass();
				this.var_4dc = this.var_60f = var10002 * 15 - this.var_feb;
				this.var_1326 = this.var_48e + this.var_feb / 2;
				this.var_1385 = this.var_4dc + this.var_ff9 / 2;
				var10001 = this.var_8ee;
				mCanvas.getClass();
				var10001 /= 15;
				var10002 = this.var_4dc + this.var_ff9 - 1;
				mCanvas.getClass();
				this.var_6f5 = var10001 + var10002 / 15 * mCanvas.var_1d1c;
				this.var_1447 = this.var_13ef = mCanvas.var_1cdd[this.var_6f5];
				this.var_e6d.setPosition(this.var_48e, this.var_4dc);
				this.var_ea2.setPosition(this.var_48e, this.var_4dc
						+ this.var_e6d.sub_96());
				mCanvas.sub_a24(2, this.var_48e - 2, this.var_4dc
						+ this.var_ff9 - mCanvas.var_2415[2]);
			} catch (Exception var3) {
				var3.printStackTrace();
			}
		}

		this.var_822 = this.var_7c1;
		this.var_c39 = this.var_c05_use;
		this.var_c7b = this.var_181a;
		this.var_716 = this.var_74b = this.var_792 = this.var_7c1 = this.var_c05_use = this.var_181a = false;
		this.var_e6d.setVisible(this.var_b81_hp > 0);
		this.var_ea2.setVisible(this.var_b81_hp > 0);
		this.var_94c = (this.var_e6d.sub_96() + this.var_ea2.sub_96()) / 2;
		this.var_8af = this.var_6b1;
		this.var_ec1 = this.var_b81_hp * 12 / this.var_af7;
	}

	public void sub_d8_left() {
		this.var_53c = Math.max(this.var_53c - this.var_b74, -this.var_b25);
		this.var_716 = true;
		this.var_596 = 0;
	}

	public void sub_11d_right() {
		this.var_53c = Math.min(this.var_53c + this.var_b74, this.var_b25);
		this.var_74b = true;
		this.var_596 = 1;
	}

	public void sub_143_up() {
		if (this.var_6b1 || this.var_86a) {
			this.var_579 = -this.var_b5f;
			this.var_792 = true;
		}
	}

	public void sub_1a1() {
		this.var_579 = mCanvas.var_458 * -1;
		this.var_792 = this.var_1060 = true;
		this.var_d5c = this.var_1326 < this.var_48e ? 1 : 2;
	}

	public void sub_1c8() {
		this.var_7c1 = true;
	}

	public void sub_1da_hit(int var1) {
		if (this.var_b81_hp > 0) {
			mCanvas.makeVibrate(40);
			this.var_b81_hp -= var1;
			if (this.var_b81_hp <= 0) {
				this.var_e4b = 20;
				if (!this.var_bd6_is_enemy && mCanvas.settings[2]) {
					mCanvas.makeVibrate(100);
				}

				// TODO : play sound
				if (mCanvas.isSoundPlay) {
					switch (random(3)) {
						case 0:
							mCanvas.playSound(Music.sound47_0);
							break;
						case 1:
							mCanvas.playSound(Music.sound47_1);
							break;
						case 2:
							mCanvas.playSound(Music.sound47_2);
							break;
					}
				}

				mCanvas.sub_a24(1, this.var_48e - 2, this.var_4dc
						- mCanvas.var_2415[1] + this.var_ff9);
				if (mCanvas.var_891 < 3) {
					if (this.var_a35 > -1) {
						++mCanvas.var_1efb[this.var_a35].var_a1b;
					} else if (!this.var_bd6_is_enemy) {
						this.var_a1b = Math.max(0, this.var_a1b - 1);
					}
				} else if (mCanvas.var_891 == 4) {
					if (this.var_a35 > -1 && mCanvas.var_157f == this.var_b9b) {
						mCanvas.var_157f = this.var_a35;
					} else if (mCanvas.var_157f == this.var_b9b) {
						this.var_a1b = Math.max(0, this.var_a1b - 2);
						int var2 = 1000;
						int var3 = 1;

						while (true) {
							mCanvas.getClass();
							if (var3 >= 4) {
								break;
							}

							if (var2 > mCanvas.var_1efb[var3].var_a1b) {
								mCanvas.var_157f = var3;
								var2 = mCanvas.var_1efb[var3].var_a1b;
							}

							++var3;
						}
					}
				} else if (mCanvas.var_891 == 5 && this == mCanvas.var_1f25_player) {
					--mCanvas.var_1462;
				} else if (mCanvas.var_891 == 5
						&& this.var_a35 == mCanvas.var_1f25_player.var_b9b) {
					++mCanvas.var_1f25_player.var_a1b;
				}
			}
		}

		if (this == mCanvas.var_1f25_player) {
			mCanvas.var_3386 = true;
		}
	}

	public void sub_236(int var1, int var2, int var3) {
		this.var_bd6_is_enemy = true;
		this.var_cd8 = var1;
		this.var_10b4 = var2;
		this.var_d12 = var3;
	}

	public void sub_272_update() {
		int i;
		int i1;
		if (this.var_aaf && this.var_b9b == mCanvas.var_1fa2) {
			boolean var1 = false;
			this.var_94c = this.var_4dc + this.var_ff9 / 2;

			for (i = 0; i < mCanvas.var_1efb.length; ++i) {
				if (i != this.var_b9b) {
					this.var_e26[i] = Math
							.abs(mCanvas.var_1efb[i].var_48e - this.var_48e) < 81
							&& Math.abs(mCanvas.var_1efb[i].var_4dc
									- this.var_4dc) < 31
							&& mCanvas.var_1efb[i].var_aaf && !this.var_1060;
					var1 = var1 || this.var_e26[i];
				}
			}

			this.var_13ef = mCanvas.var_1cdd[this.var_6f5 - mCanvas.var_1d1c];
			this.var_17c0 = false;

			for (i = 0; i < this.var_e26.length; ++i) {
				if (i != this.var_b9b
						&& this.var_e26[i]
						&& Math.min(this.var_8ee, this.var_1326) < mCanvas.var_1efb[i].var_8ee
						&& Math.max(this.var_8ee, this.var_1326) > mCanvas.var_1efb[i].var_8ee) {
					if (mCanvas.var_891 >= 3 && i != mCanvas.var_157f) {
						if (mCanvas.var_1efb[this.var_dc6].var_6b1) {
							this.sub_143_up();
						}
					} else {
						this.var_dc6 = i;
						this.var_17c0 = true;
					}
				}
			}

			if (this.var_dc6 > -1 && !this.var_e26[this.var_dc6]) {
				this.var_13e5 = var_112f;
			}

			byte var15 = var_121d;
			int var3 = 0;
			int var16 = mCanvas.var_21de.length > 0 ? 100 - this.var_b81_hp
					- this.var_cd8 / 3 : 0;
			if (var3 > var16) {
				var15 = var_11d5;
				var3 = var16;
			}

			if (mCanvas.var_891 == 4 && mCanvas.var_157f > -1 && !this.var_17c0) {
				if (mCanvas.var_157f == this.var_b9b) {
					this.var_dc6 = -1;
					if (var1 && this.var_b81_hp > this.var_af7 / 2) {
						var15 = var_121d;
					} else if (this.var_b81_hp < this.var_af7 / 2
							&& mCanvas.var_21de.length > 0) {
						var15 = var_11d5;
					} else {
						var15 = var_12cd;
					}

					var3 = 1000;
				} else {
					var3 = 1000;
					if (this.var_b81_hp < this.var_af7 - this.var_cd8 / 4
							&& !this.var_16a0 && mCanvas.var_21de.length > 0) {
						var15 = var_11d5;
					} else {
						var15 = var_121d;
						this.var_dc6 = mCanvas.var_157f;
						this.var_1447 = mCanvas.var_1efb[this.var_dc6].var_13ef;
						this.var_13e5 = this.var_e26[this.var_dc6] ? var_1178
								: var_112f;
					}
				}
			}

			int var5;
			int var6;
			try {
				var16 = 50 + this.var_cd8 / 3 + (var1 ? 40 : 0)
						+ (this.var_17c0 ? 100 : 0);
				var5 = 2000;

				for (var6 = 0; var6 < mCanvas.var_1efb.length; ++var6) {
					if (var6 != this.var_b9b) {
						var5 = Math.min(var5, mCanvas.var_1efb[var6].var_b81_hp);
					}
				}

				if (var5 < 50) {
					var16 += this.var_cd8;
				}

				if (mCanvas.var_891 == 4 && mCanvas.var_157f != this.var_b9b) {
					var16 = 40;
				}

				if (var16 > var3) {
					var15 = var_121d;
					var3 = var16;
				}

				if (mCanvas.var_3103) {
					var16 = 30 + this.var_10b4 * 30 - this.sub_329() * 5;
					if (var16 > 40
							&& this.sub_384(mCanvas.var_1e04[this.var_182b])) {
						var16 += 70;
					}

					if (var16 > var3) {
						var15 = var_123f;
						var3 = var16;
					}

					byte var10000 = mCanvas.var_1c98[this.var_6f5];
					mCanvas.getClass();
					if (var10000 == 71
							&& this.sub_384((Tiled) mCanvas.var_1e1d
									.get(this.var_6f5))) {
						var16 = 90 + 90 * this.var_10b4;
						if (var16 > var3) {
							var15 = var_1291;
							var3 = var16;
						}
					}
				}

				var16 = 70 - this.var_f3a * 30 + this.var_10b4 * 10;
				if (var16 > var3) {
					var15 = var_12cd;
					var3 = var16;
				}

				if (mCanvas.var_891 == 3) {
					if (!this.var_17fe) {
						short var20 = 500;
						if (var20 > var3) {
							var15 = var_1315;
						}
					} else {
						this.var_13e5 = this.var_48e >= mCanvas.var_146d
								&& this.var_48e + this.var_feb <= mCanvas.var_14a4
								&& var1 ? var_1178 : var_11c0;
						if (this.var_13e5 == var_11c0) {
							this.var_1326 = (mCanvas.var_146d + mCanvas.var_14a4) / 2;
						}
					}
				}
			} catch (Exception var13) {
				var13.printStackTrace();
			}

			this.var_17fe = false;
			int var7;
			int var8;
			if (var15 == var_11d5 && !this.var_17c0) {
				var5 = 1000;
				var7 = 0;

				for (var8 = 0; var8 < mCanvas.var_21de.length; ++var8) {
					var6 = mCanvas.var_21de[var8] % mCanvas.var_1d1c
							+ mCanvas.var_21de[var8] / mCanvas.var_1d1c;
					if (var6 < var5) {
						var7 = var8;
						var5 = var6;
					}
				}

				for (var8 = 0; var8 < mCanvas.var_210c.length; ++var8) {
					if (mCanvas.var_21de[var7] == mCanvas.var_210c[var8]) {
						this.var_1447 = var8;
						break;
					}
				}

				this.var_13e5 = var_112f;
			} else if (var15 == var_12cd && !this.var_17c0) {
				this.var_1447 = this.sub_365();
				this.var_13e5 = var_112f;
			} else if (var15 == var_1291 && !this.var_17c0) {
				this.var_c05_use = true;
			} else if (var15 == var_1315 && !this.var_17c0) {
				boolean var23;
				label603: {
					byte[] var10001 = mCanvas.var_1cdd;
					int var10002 = (mCanvas.var_146d + mCanvas.var_14a4) / 2;
					mCanvas.getClass();
					var10002 /= 15;
					int var10003 = mCanvas.var_1504 - 20;
					mCanvas.getClass();
					this.var_1447 = var10001[var10002 + var10003 / 15
							* mCanvas.var_1d1c];
					this.var_13e5 = var_112f;
					if (this.var_579 == 0
							&& this.var_4dc + this.var_ff9 <= mCanvas.var_1504 + 10) {
						i1 = this.var_4dc + this.var_ff9;
						var10002 = mCanvas.var_1504;
						mCanvas.getClass();
						if (i1 >= var10002 - 15) {
							var23 = true;
							break label603;
						}
					}

					var23 = false;
				}

				this.var_17fe = var23;
			} else if (var15 == var_123f && !this.var_17c0) {
				this.var_1447 = this.var_182b;
				this.var_13e5 = var_112f;
			} else {
				if (this.var_dc6 > -1) {
					this.var_13e5 = this.var_e26[this.var_dc6] ? var_1178
							: var_112f;
				} else if (!mCanvas.var_92a) {
					var5 = -1;
					var6 = 2000;

					for (var7 = 0; var7 < mCanvas.var_1efb.length; ++var7) {
						if (var7 != this.var_b9b
								&& mCanvas.var_1efb[var7].var_b81_hp < var6) {
							var5 = var7;
							var6 = mCanvas.var_1efb[var7].var_b81_hp;
						}
					}

					this.var_dc6 = var5;
				} else {
					this.var_dc6 = mCanvas.var_1f25_player.var_b9b;
				}

				if (this.var_dc6 == -1
						|| !mCanvas.var_1efb[this.var_dc6].var_aaf) {
					if (this.var_d8b > -1 && mCanvas.var_891 < 3) {
						this.var_dc6 = this.var_d8b;
						this.var_13e5 = var_112f;
					} else {
						this.var_13e5 = var_112f;
						this.var_1447 = this.var_13ef;
						this.var_dc6 = -1;

						for (var5 = 0; var5 < mCanvas.var_1efb.length; ++var5) {
							if (var5 != this.var_b9b && this.var_e26[var5]) {
								this.var_dc6 = var5;
								this.var_13e5 = var_1178;
							}
						}
					}
				}

				if (this.var_dc6 > -1) {
					this.var_1447 = mCanvas.var_1efb[this.var_dc6].var_13ef;
				} else {
					this.var_1447 = this.var_13ef;
				}
			}

			this.var_160b = this.var_48e + this.var_feb / 2 + var_110a > this.var_1326
					&& this.var_48e + this.var_feb / 2 - var_110a < this.var_1326;
			this.var_161a = this.var_4dc + this.var_ff9 / 2 + var_110a > this.var_1385
					&& this.var_4dc + this.var_ff9 / 2 - var_110a < this.var_1385;
			this.var_1577 = this.var_160b && this.var_161a && !this.var_16a0
					|| this.var_1326 == 0;
			this.var_1656 = this.var_48e + var_110a > this.var_1326
					&& this.var_48e - var_110a < this.var_1326 || this.var_1656;

			try {
				if (this.var_6b1
						&& mCanvas.var_212d[this.var_171e][this.var_1495] % 2 == 0
						&& this.var_4dc > this.var_1385) {
					this.var_14a7 = 1;
				}
			} catch (Exception var11) {
				this.var_14a7 = 0;
				this.var_171e = this.var_13ef;
				this.var_1495 = this.var_1447;
			}

			this.var_1577 = this.var_1577 || this.var_14a7 > 0
					|| this.var_176b++ > 10;
			if (this.var_176b > 10) {
				this.var_48e += this.var_596 == 0 ? -6 : 6;
			}

			if (this.var_13e5 == var_112f && this.var_13ef > -1
					&& this.var_1447 != this.var_13ef && this.var_1577
					&& (this.var_6b1 || this.var_86a)) {
				this.var_14a7 = this.var_176b = 0;
				byte[] var19 = new byte[mCanvas.var_2272];
				var19[this.var_1447] = 1;
				var6 = -1;
				if (!this.var_1656) {
					var6 = this.var_13ef;
				}

				try {
					if (this.var_16a0) {
						var6 = this.var_13ef;
					}

					var7 = 0;

					while (var6 < 0) {
						if (var7++ > var19.length) {
							var7 = -1;
							break;
						}

						for (var8 = 0; var8 < mCanvas.var_2272; ++var8) {
							if (var19[var8] == 1) {
								byte[] var9 = mCanvas.var_217e[var8];

								for (byte aVar9 : var9) {
									var19[var8] = 2;
									if (aVar9 == this.var_13ef) {
										var6 = var8;
										var8 = mCanvas.var_2272;
										break;
									}

									if (var19[aVar9] == 0) {
										var19[aVar9] = 1;
									}
								}
							}
						}
					}

					this.var_15b5 = var7 == -1;
					if (this.var_15b5) {
						var6 = this.var_13ef;
					}
				} catch (Exception var12) {
					var12.printStackTrace();
				}

				this.var_171e = this.var_13ef;
				this.var_1447 = this.var_1495 = var6;
				this.var_16fc = mCanvas.var_22a0[this.var_13ef][this.var_1447];
				var7 = this.var_1447;
				if (var7 > -1
						&& (this.var_6f5 != mCanvas.var_1cdd[this.var_6f5] || this
								.sub_3ab())) {
					i1 = mCanvas.var_210c[var7] % mCanvas.var_1d1c;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1326 = i1 + 15 / 2;
					i1 = mCanvas.var_210c[var7] / mCanvas.var_1d1c - 1;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1385 = i1 + 15 / 2;
				}

				if (mCanvas.var_212d[this.var_13ef][this.var_1447] % 2 == 0
						&& this.var_6b1) {
					this.var_d5c = this.var_1326 < this.var_48e ? 1 : 2;
					this.sub_143_up();
					this.var_104d = true;
				} else {
					this.var_104d = false;
				}

				this.var_d8b = -1;
			}
		}

		if (!this.var_16fc) {
			if (this.var_1447 > -1 && this.var_13ef > -1
					&& this.var_13e5 == var_112f) {
				if (this.var_1060 && this.var_16a0) {
					this.var_16a0 = false;
					i1 = mCanvas.var_210c[this.var_14e5] % mCanvas.var_1d1c;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1326 = i1 + 15 / 2;
					i1 = mCanvas.var_210c[this.var_14e5] / mCanvas.var_1d1c
							- 1;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1385 = i1 + 15 / 2;
					this.var_1326 += this.var_48e < this.var_1326 ? 7 : -7;
				}

				if (mCanvas.var_212d[this.var_13ef][this.var_1447] == 3
						&& !this.var_1060
						&& this.var_6f5 != mCanvas.var_210c[this.var_13ef]) {
					this.var_14e5 = this.var_1447;
					this.var_1447 = this.var_13ef;
					i1 = mCanvas.var_210c[this.var_13ef] % mCanvas.var_1d1c;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1326 = i1 + 15 / 2;
					i1 = mCanvas.var_210c[this.var_13ef] / mCanvas.var_1d1c
							- 1;
					mCanvas.getClass();
					i1 *= 15;
					mCanvas.getClass();
					this.var_1385 = i1 + 15 / 2;
					this.var_16a0 = true;
				}
			}

			if (Math.abs(this.var_5ef) > 1 && this.var_957 < this.var_d12
					&& this.var_10b4 > 0) {
				if (this.var_5ef < 0) {
					this.sub_11d_right();
				} else {
					this.sub_d8_left();
				}
			}

			if (this.var_13e5 == var_1178 && this.var_dc6 > -1) {
				Warrior var14 = mCanvas.var_1efb[this.var_dc6];
				if (this.var_1326 == this.var_8ee) {
					this.var_596 = var14.var_48e < this.var_48e ? 0 : 1;
				}

				i = random(100);
				if (this.var_10b4 == 2 && var14.var_579 < 0) {
					this.var_e08 = i % 10;
				}

				if ((var14.var_c7b || var14.var_181a)
						&& i % this.var_d12 != 0) {
					this.sub_2cf_fire();
					if (this.var_d12 == 3 && !var14.var_6b1) {
						this.sub_1c8();
					} else {
						this.sub_143_up();
					}
				}

				if (this.var_6b1 && i % 8 == 0) {
					this.var_e08 = i % 7;
				} else if (i % 14 == 0 && var_18b[this.var_f3a] > 5) {
					this.sub_143_up();
				} else if (!var14.var_6b1 && !var14.var_86a) {
					if (var14.var_6b1 && var14.var_4dc > this.var_4dc) {
						this.var_1326 = (this.var_48e + var14.var_48e) / 2;
					} else if (Math.abs(var14.var_48e - this.var_48e) > 31) {
						this.var_1326 = (this.var_8ee + var14.var_8ee) / 2;
					}
				} else if (var14.var_4dc < this.var_4dc) {
					this.sub_143_up();
					this.var_1326 = (this.var_48e + var14.var_48e) / 2;
				} else {
					this.var_1326 = (this.var_48e + var14.var_48e) / 2;
				}

				if (var_18b[this.var_f3a] > 10
						&& Math.abs(var14.var_48e - this.var_48e) > 20) {
					this.var_1326 = this.var_8ee
							+ (var14.var_8ee - this.var_8ee);
				}

				if (var_18b[this.var_f3a] > 6) {
					if (this.var_4dc + this.var_94c + 5 > var14.var_4dc
							&& this.var_4dc + this.var_94c - 5 < var14.var_4dc
									+ var14.var_ff9) {
						this.sub_2cf_fire();
					}
				} else {
					this.sub_2cf_fire();
				}

				this.var_1326 = this.var_8ee;
				this.var_1385 = this.var_4dc + this.var_ff9 / 2;
			}

			if (!this.var_16a0 && !this.var_1060) {
				if (!this.var_6b1 && !this.var_86a) {
					if (this.var_8ee - var_110a > this.var_1326
							&& this.var_d5c == 1) {
						this.sub_d8_left();
					} else if (this.var_8ee + var_110a < this.var_1326
							&& this.var_d5c == 2) {
						this.sub_11d_right();
					}
				} else if (this.var_8ee - var_110a > this.var_1326) {
					this.sub_d8_left();
				} else if (this.var_8ee + var_110a < this.var_1326) {
					this.sub_11d_right();
				}
			} else if (this.var_8ee > this.var_1326 - var_110a) {
				this.sub_d8_left();
			} else if (this.var_8ee < this.var_1326 + var_110a) {
				this.sub_11d_right();
			}

			if (mCanvas.var_891 == 3
					&& this.var_17fe
					&& (this.var_48e < mCanvas.var_146d || this.var_48e
							+ this.var_feb > mCanvas.var_14a4)) {
				this.var_1326 = (mCanvas.var_146d + mCanvas.var_14a4) / 2;
			}

			if (this.var_e08 > 0) {
				this.sub_1c8();
				--this.var_e08;
			}

			if (this.var_104d) {
				this.var_792 = true;
				if (this.var_d5c == 1) {
					this.sub_d8_left();
				} else if (this.var_d5c == 2) {
					this.sub_11d_right();
				}
			}

			this.var_94c = (this.var_e6d.sub_96() + this.var_ea2.sub_96()) / 2;
		}
	}

	public void sub_2cf_fire() {
		try {
			if (this.var_d40 == 0) {
				this.var_ee8 = 4;
				if (mCanvas.isSoundPlay/* && this != mCanvas.var_1f25_player && this.var_dc6 == 0*/) {
					// TODO : sound
					mCanvas.playSound(this.var_f3a != 0 && this.var_f3a != 2 ?
							Music.sound4B : Music.sound4A);
				}

				if (mCanvas.var_2510 == 0) {
					new Bullets(this.var_f3a,
							this.var_596 == 0 ? this.var_48e
									- mCanvas.bulletsAnims[this.var_f3a].sub_6a()
									: this.var_48e + this.var_feb,
							this.var_4dc + this.var_ff9 / 2
									+ (this.var_7c1 ? 7 : 4),
							this.var_596 == 0 ? -var_13[this.var_f3a]
									: var_13[this.var_f3a],
							var_61[this.var_f3a], this, mCanvas);
				} else {
					mCanvas.var_2445[mCanvas.var_24b6] = mCanvas.var_2455[--mCanvas.var_2510];
					mCanvas.var_2455[mCanvas.var_2510] = null;
					mCanvas.var_2445[mCanvas.var_24b6].sub_33_init(this.var_f3a,
							this.var_596 == 0 ? this.var_48e
									- mCanvas.bulletsAnims[this.var_f3a].sub_6a()
									: this.var_48e + this.var_feb,
							this.var_4dc + this.var_ff9 / 2
									+ (this.var_7c1 ? 7 : 4),
							this.var_596 == 0 ? -var_13[this.var_f3a]
									: var_13[this.var_f3a],
							var_61[this.var_f3a], this, mCanvas);
				}

				this.var_d40 = var_18b[this.var_f3a];
				this.var_181a = true;
				if (this.var_f87-- == 0) {
					this.var_f3a = 0;
					this.var_f87 = var_248[0];
				}
			}
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	public void sub_2e4(int var1, int var2) {
		this.var_5ef = var2;
		if (!this.var_bd6_is_enemy && mCanvas.settings[2] && this.var_b81_hp > var1) {
			mCanvas.makeVibrate(20);
		}
		this.sub_1da_hit(var1);
	}

	public int sub_329() {
		int var1 = 1000;

		for (int var3 = 0; var3 < mCanvas.var_220e.length; ++var3) {
			int var2 = Math.abs(this.var_6f5 % mCanvas.var_1d1c
					- mCanvas.var_220e[var3] % mCanvas.var_1d1c)
					+ Math.abs(this.var_6f5 / mCanvas.var_1d1c
							- mCanvas.var_220e[var3] / mCanvas.var_1d1c);
			if (var2 < var1) {
				this.var_182b = var3;
				var1 = var2;
			}
		}

		return var1;
	}

	public int sub_365() {
		int var1 = 1000;

		for (int var3 = 0; var3 < mCanvas.var_2230.length; ++var3) {
			int var10000 = Math.abs(this.var_6f5 % mCanvas.var_1d1c
					- mCanvas.var_2230[var3] % mCanvas.var_1d1c)
					+ Math.abs(this.var_6f5 / mCanvas.var_1d1c
							- mCanvas.var_2230[var3] / mCanvas.var_1d1c);
			byte var10001 = mCanvas.var_1c98[mCanvas.var_2230[var3]];
			mCanvas.getClass();
			int var2 = var10000 - (var10001 - 67) * 10;
			if (var2 < var1) {
				this.var_1887 = mCanvas.var_1cdd[mCanvas.var_2230[var3]];
				var1 = var2;
			}
		}

		return this.var_1887;
	}

	public boolean sub_384(Tiled var1) {
		int var2;
		if (var1.var_1c1) {
			for (var2 = 0; var2 < mCanvas.var_1efb.length; ++var2) {
				if (var2 != this.var_b9b
						&& mCanvas.var_1efb[var2].var_6f5 % mCanvas.var_1d1c >= var1.var_e - 1
						&& mCanvas.var_1efb[var2].var_6f5 % mCanvas.var_1d1c <= var1.var_72 + 1
						&& mCanvas.var_1efb[var2].var_6f5 / mCanvas.var_1d1c >= var1.var_38 - 2
						&& mCanvas.var_1efb[var2].var_6f5 / mCanvas.var_1d1c <= var1.var_97) {
					this.var_18c8 = 1;
					return true;
				}
			}
		} else {
			for (var2 = 0; var2 < mCanvas.var_1efb.length; ++var2) {
				if (var2 != this.var_b9b
						&& mCanvas.var_1efb[var2].var_6f5 % mCanvas.var_1d1c >= var1.var_72 - 1
						&& mCanvas.var_1efb[var2].var_6f5 % mCanvas.var_1d1c <= var1.var_72 + 1
						&& mCanvas.var_1efb[var2].var_6f5 / mCanvas.var_1d1c >= var1.var_97) {
					this.var_18c8 = 1;
					return true;
				}
			}
		}

		this.var_18c8 = 0;
		return false;
	}

	public boolean sub_3ab() {
		return Math.abs(this.var_6f5 - mCanvas.var_210c[this.var_1447]) < 2
				|| Math.abs(this.var_6f5 - mCanvas.var_1d1c
						- mCanvas.var_210c[this.var_1447]) < 2;
	}

	public static void sub_3eb(Arena var0) {
		var_13 = new int[6];
		var_b0 = new int[6];
		var_61 = new int[6];
		var_111 = new int[6];
		var_142 = new int[6];

		for (int var1 = 0; var1 < var_13.length; ++var1) {
			var_13[var1] = var0.getParameter(420 + var1 * 5);
			var_b0[var1] = var0.getParameter(422 + var1 * 5);
			var_61[var1] = var0.getParameter(421 + var1 * 5) * -1;
			var_111[var1] = var0.getParameter(423 + var1 * 5);
			var_142[var1] = var0.getParameter(424 + var1 * 5);
		}
	}
}
