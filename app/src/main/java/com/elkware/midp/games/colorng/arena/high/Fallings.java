package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.colorng.MySprite;

public class Fallings {

	//public final int var_65 = 1;
	//public final int var_c0 = 2;
	//public final int var_106 = 3;
	//public final int var_176 = 40;
	public final int[] var_1c4_damage = new int[] { 50, 120, 70 };
	int type;
	int x;
	int y;
	MySprite sprite;
	MyCanvas myCanvas;
	int var_3bc;
	int var_407;
	public boolean var_41b = false;
	int var_429 = 0;
	int var_43a;

	public Fallings(int type, int x, int y, MyCanvas myCanvas, int var5) {
		this.type = type;
		myCanvas.getClass();
		this.x = x * 15;
		myCanvas.getClass();
		this.y = y * 15;
		this.myCanvas = myCanvas;
		this.var_43a = var5;

		while (true) {
			byte var10000 = myCanvas.var_1c98[x + y * myCanvas.var_1d1c];
			myCanvas.getClass();
			if (var10000 <= 32) {
				var10000 = myCanvas.var_1c98[x + y * myCanvas.var_1d1c];
				myCanvas.getClass();
				if (var10000 >= 1) {
					int var10001 = this.var_3bc;
					myCanvas.getClass();
					this.var_3bc = var10001 * 15;
					this.var_407 = 9;
					this.sprite = new MySprite(myCanvas.fallingsAnims[type - 1]);
					myCanvas.sub_16c(myCanvas.var_fb7, this.sprite);
					this.sprite.setPosition(x, y);
					return;
				}
			}

			++this.var_3bc;
			++y;
		}
	}

	public boolean sub_20_update_fallings() {
		try {
			this.var_41b = this.var_3bc <= this.var_407 >> 2;
			if (!this.var_41b) {
				switch (this.type) {
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
				this.y += this.var_407 >> 2;
				this.var_429 = (this.var_429 + 1) % 4;
				this.sprite.setFrame(this.var_429);
				this.sprite.setPosition(this.x, this.y);
			}

			return this.var_41b;
		} catch (Exception var2) {
			return true;
		}
	}

	public void sub_36(Warrior warrior) {
		warrior.var_a35 = this.var_43a;
		warrior.var_a79 = this.var_43a;
		warrior.sub_1da_hit(this.var_1c4_damage[this.type - 1]);
		this.var_3bc = -1;
		this.myCanvas.sub_a24(0, this.x, this.y);
	}
}
