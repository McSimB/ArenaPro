package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

class Class_1bb extends Canvas {

	private final Image var_88;
	private final ArenaMidlet var_b8;

	Class_1bb(ArenaMidlet var1, Image var2) {
		this.var_b8 = var1;
		this.var_88 = var2;
	}

	protected void keyPressed(int var1) {
		ArenaMidlet.sub_64f(this.var_b8, true);
	}

	protected void paint(Graphics var1) {
		int var2 = this.getWidth();
		short var3 = 176;
		var1.setClip(0, 0, var2, var3);
		var1.setColor(255, 0, 0);
		var1.fillRect(0, 0, var2, var3);
		int var4 = this.var_88.getHeight();
		int var5 = this.var_88.getWidth();
		var1.drawImage(this.var_88, (var2 - var5) / 2, (var3 - var4) / 2, 20);
	}
}
