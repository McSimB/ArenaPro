package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.MySprite;

import static com.elkware.midp.games.colorng.arena.high.Music.sound48;
import static com.elkware.midp.games.colorng.arena.high.Music.sound49;

public class Bullets {

	static final int[] var_19 = new int[] { 7, 7, 1, 7, 7, 4 };
	static MySprite[][] var_23 = new MySprite[var_19.length][50];
	static int[] var_7b = new int[var_19.length];
	int var_95;
	int var_112;
	int var_172;
	int var_1bf;
	int var_1ee;
	int var_21d;
	MyCanvas myCanvas;
	int var_2ab = 0;
	int var_2e6;
	int var_31c;
	int var_37c;
	MySprite sprite;
	Warrior warrior;

	public Bullets(int var1, int var2, int var3, int var4, int var5,
				   Warrior var6, MyCanvas var7) {
		this.sub_33_init(var1, var2, var3, var4, var5, var6, var7);
	}

	public void sub_33_init(int var1, int var2, int var3, int var4, int var5,
							Warrior var6, MyCanvas var7) {
		int var10001;
		if (this.var_95 == 0) {
			var10001 = var7.width;
			var7.getClass();
			this.var_95 = var10001 / 15 + 3;
		}

		this.var_112 = var1;
		this.var_172 = var2;
		this.var_1bf = var3;
		this.var_1ee = var4;
		this.var_21d = var5;
		this.myCanvas = var7;
		this.warrior = var6;
		if (var_7b[var1] == 0) {
			this.sprite = new MySprite(var7.bulletsAnims[var1]);
		} else {
			this.sprite = var_23[var1][var_7b[var1] - 1];
			var_23[var1][--var_7b[var1]] = null;
		}

		this.sprite.setPosition(var2, var3);
		this.sprite.defineCollisionRectangle(-4, -3,
				this.sprite.sub_6a() + 8, this.sprite.sub_96() + 6);

		try {
			this.sprite.setFrame(this.var_37c = var4 < 0 ? 0
					: var_19[var1] * 2 - 1);
		} catch (Exception var15) {
			var15.printStackTrace();
		}

		var7.var_2445[var7.var_24b6++] = this;
		var7.sub_16c(var7.var_1074, this.sprite);
		this.var_2e6 = this.sprite.sub_6a() / 2;
		this.var_31c = this.sprite.sub_96() / 2;
		int var8 = var4 * 100;
		int var9 = var5 * 100;
		var7.getClass();
		int var10 = 15 * var9 / Math.abs(var8);
		int var11 = 0;
		int var10000 = var2 + this.var_2e6;
		var7.getClass();
		int var12 = var10000 / 15;
		var10000 = var3 + this.var_31c;
		var7.getClass();
		int var13 = var10000 / 15;

		try {
			while (true) {
				byte var17 = var7.var_1c98[var12 + (var13 + var11 / 10)
						* var7.var_1d1c];
				var7.getClass();
				if (var17 >= 1) {
					var17 = var7.var_1c98[var12 + (var13 + var11 / 10)
							* var7.var_1d1c];
					var7.getClass();
					if (var17 <= 32) {
						break;
					}
				}

				if (this.var_2ab > this.var_95) {
					break;
				}

				var10 += Warrior.var_b0[var1];
				var11 += Math.min(10, var10);
				++this.var_2ab;
				var12 += var4 > 0 ? 1 : -1;
			}
		} catch (Exception var16) {
			var16.printStackTrace();
		}

		if (Math.abs(var4) > Math.abs(var5)) {
			var10001 = this.var_2ab;
			var7.getClass();
			this.var_2ab = var10001 * Math.abs(15 / var4);
		} else {
			var10001 = this.var_2ab;
			var7.getClass();
			this.var_2ab = var10001 * Math.abs(15 / var5);
		}

		this.var_2ab = Math.min(this.var_2ab, Warrior.var_294[var1]);
	}

	public boolean sub_7a_update_bullets() {
		this.var_21d += Warrior.var_b0[this.var_112];
		this.var_172 += this.var_1ee;
		this.var_1bf += this.var_21d;
		if (this.var_112 == 3) {
			this.var_1bf += Warrior.random(5) - 2;
		}

		this.sprite.setPosition(this.var_172, this.var_1bf);
		this.sprite
				.setFrame(this.var_37c = this.var_1ee > 0 ? (this.var_37c - 1 <= var_19[this.var_112] ? var_19[this.var_112] * 2 - 1
						: this.var_37c - 1)
						: (this.var_37c + 1 >= var_19[this.var_112] ? 0
								: this.var_37c + 1));
		boolean var1 = --this.var_2ab < 1;
		if (var1) {
			var_23[this.var_112][var_7b[this.var_112]++] = this.sprite;
		}

		return var1;
	}

	public void sub_bb_hit(Warrior warrio) {
		if (warrio != this.warrior) {
			warrio.var_d8b = this.warrior.var_b9b;
			warrio.var_a35 = this.warrior.var_b9b;
			warrio.var_a79 = 10;
			warrio.sub_2e4(Warrior.var_1e4_damage[this.var_112],
					this.var_1ee > 0 ? Warrior.var_142[this.var_112]
							: -Warrior.var_142[this.var_112]);
			this.var_2ab = -1;
			if (warrio.var_957 == 0) {
				warrio.var_957 = 5;
			}

			// TODO : sound
			/*if (this.myCanvas.isSoundPlay && warrio.var_bd6_is_enemy
					&& this.warrior == this.myCanvas.var_1f25_player) {
				myCanvas.playSound(Warrior.random(2) > 0 ? sound48 : sound49);
			}*/
		}
	}
}
