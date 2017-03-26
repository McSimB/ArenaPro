package com.siemens.mp.lcdui;

import java.io.IOException;

public class Image extends com.siemens.mp.ui.Image {

	public static final int TYPE_BMP = 1;
	public static final int TYPE_JPG = 2;

	public static void writeBmpToFile(javax.microedition.lcdui.Image image,
									  String s) throws IOException {
	}

	public static void writeImageToFile(javax.microedition.lcdui.Image image,
										String s, int i) throws IOException {
	}

	public static javax.microedition.lcdui.Image createImageFromFile(String s, int i, int j)
			throws IOException {
		return null;
	}

	public static int getPixelColor(javax.microedition.lcdui.Image image, int x, int y)
			throws IllegalArgumentException {
		return image.getBitmap().getPixel(x, y);
	}

	public static void setPixelColor(javax.microedition.lcdui.Image image, int x, int y, int col)
			throws IllegalArgumentException {
		image.getBitmap().setPixel(x, y, col);
	}

}