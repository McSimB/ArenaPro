package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

class CanvView extends Canvas {

	private final Image downloadMoreImage;
	private final ArenaMidlet var_b8;

	CanvView(ArenaMidlet var1, Image var2) {
		super(var1);
		this.var_b8 = var1;
		this.downloadMoreImage = var2;
	}

	@Override
	protected void keyPressed(int var1) {
		ArenaMidlet.sub_64f(this.var_b8, true);
	}

	@Override
	protected void paint(Graphics var1) {
		int var2 = this.getwidth();
		short var3 = 176;
		var1.setClip(0, 0, var2, var3);
		var1.setColor(255, 0, 0);
		var1.fillRect(0, 0, var2, var3);
		int var4 = this.downloadMoreImage.getHeight();
		int var5 = this.downloadMoreImage.getWidth();
		var1.drawImage(this.downloadMoreImage, (var2 - var5) / 2, (var3 - var4) / 2, 20);
	}

}
