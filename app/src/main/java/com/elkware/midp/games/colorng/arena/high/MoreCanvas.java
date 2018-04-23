package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

class MoreCanvas extends Canvas {

	private final Image downloadMoreImage;
	private final Arena arena;

	MoreCanvas(Arena arena, Image var2) {
		this.arena = arena;
		this.downloadMoreImage = var2;
	}

	@Override
	public void keyPressed(int var1) {
		Arena.sub_64f(this.arena, true);
	}

	@Override
	public void paint(Graphics var1) {
		int var2 = this.getWidth();
		short var3 = 176;
		var1.setClip(0, 0, var2, var3);
		var1.setColor(255, 0, 0);
		var1.fillRect(0, 0, var2, var3);
		int var4 = this.downloadMoreImage.getHeight();
		int var5 = this.downloadMoreImage.getWidth();
		var1.drawImage(this.downloadMoreImage, (var2 - var5) / 2, (var3 - var4) / 2, 20);
	}

}
