package com.elkware.midp.games.colorng;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class Class_18b extends TiledLayer {

	public Class_18b(int var1, int var2, Image var3, int var4, int var5,
			byte[] var6) {
		super(var1, var2, var3, var4, var5);
		int var7 = 0;
		if (var6 != null) {
			for (int var8 = 0; var8 < var2; ++var8) {
				for (int var9 = 0; var9 < var1; ++var9) {
					this.setCell(var9, var8, var6[var7++] & 255);
				}
			}
		}

	}

	public void sub_3d(Graphics var1, int var2, int var3) {
		int var4 = this.getX();
		int var5 = this.getY();
		this.setPosition(var4 + var2, var5 + var3);
		super.paint(var1);
		this.setPosition(var4, var5);
	}
}
