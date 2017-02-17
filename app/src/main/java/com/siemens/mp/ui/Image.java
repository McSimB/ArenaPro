package com.siemens.mp.ui;

import java.io.IOException;

public class Image {

	public Image() {
	}
	
	public Image(int imageWidth, int imageHeight) {
		createBlankImage(imageWidth, imageHeight);
		width = imageWidth;
		height = imageHeight;
	}

	public Image(Image image) {
		copyImage(image);
	}

	public Image(byte bytes[], int imageWidth, int imageHeight) {
		createImageFromBitmap0(bytes, imageWidth, imageHeight);
		width = imageWidth;
		height = imageHeight;
	}

	public Image(byte bytes[], int imageWidth, int imageHeight,
			boolean transparent) {
		if (transparent)
			createTransparentImageFromBitmap0(bytes, imageWidth, imageHeight);
		else
			createImageFromBitmap0(bytes, imageWidth, imageHeight);
		width = imageWidth;
		height = imageHeight;
	}

	public Image(String name, boolean doScale) throws IOException {
		createImageFromResource(name, doScale);
	}

	public Image(byte imageData[]) {
		createImageFromBytes(imageData);
	}

	public Image(byte bytes[], int imageWidth, int imageHeight, int BitmapType)
			throws IOException {
		if (BitmapType != 5) {
			throw new IOException("Unsupported bitmap type: " + BitmapType);
		} else {
			createImageFromRGBdata(bytes, imageWidth, imageHeight, BitmapType);
			return;
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public static javax.microedition.lcdui.Image createImageWithScaling(
			String name) throws IOException {
		try {
			Image simg = new Image(name, true);
			javax.microedition.lcdui.Image img = javax.microedition.lcdui.Image
					.createImage(1, 1);
			setNativeImage(img, simg);
			return img;
		} catch (IllegalArgumentException e) {
			throw new IOException("Data could not be decoded.");
		}
	}

	public static javax.microedition.lcdui.Image createImageWithoutScaling(
			String name) throws IOException {
		try {
			Image simg = new Image(name, false);
			javax.microedition.lcdui.Image img = javax.microedition.lcdui.Image
					.createImage(1, 1);
			setNativeImage(img, simg);
			return img;
		} catch (IllegalArgumentException e) {
			throw new IOException("Data could not be decoded.");
		}
	}

	public static javax.microedition.lcdui.Image createImageFromBitmap(
			byte imageData[], int imageWidth, int imageHeight) {
		if (imageWidth <= 0 || imageHeight <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			Image simg = new Image(imageData, imageWidth, imageHeight);
			javax.microedition.lcdui.Image img = javax.microedition.lcdui.Image
					.createImage(1, 1);
			setNativeImage(img, simg);
			return img;
		}
	}

	public static javax.microedition.lcdui.Image createRGBImage(
			byte imageData[], int imageWidth, int imageHeight, int BitmapType)
			throws ArrayIndexOutOfBoundsException, IOException {
		if (imageWidth <= 0 || imageHeight <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			Image simg = new Image(imageData, imageWidth, imageHeight,
					BitmapType);
			javax.microedition.lcdui.Image img = javax.microedition.lcdui.Image
					.createImage(1, 1);
			setNativeImage(img, simg);
			return img;
		}
	}

	public static javax.microedition.lcdui.Image createTransparentImageFromBitmap(
			byte bytes[], int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			Image simg = new Image(bytes, width, height, true);
			javax.microedition.lcdui.Image img = javax.microedition.lcdui.Image
					.createImage(1, 1);
			setNativeImage(img, simg);
			return img;
		}
	}

	public static void mirrorImageHorizontally(
			javax.microedition.lcdui.Image image) {
		mirrorImage(image, true);
	}

	public static void mirrorImageVertically(
			javax.microedition.lcdui.Image image) {
		mirrorImage(image, false);
	}

	private void createBlankImage(int i, int j) {
		
	}

	private void copyImage(Image image) {
		
	}

	private void createImageFromBitmap0(byte abyte0[], int i, int j) {
		
	}

	private void createTransparentImageFromBitmap0(byte abyte0[], int i,
			int j) {
		
	}

	private void createImageFromResource(String s, boolean flag) {
		
	}

	private void createImageFromBytes(byte abyte0[]) {
		
	}

	private void createImageFromRGBdata(byte abyte0[], int i, int j,
			int k) {
		
	}

	private static void setNativeImage(
			javax.microedition.lcdui.Image image, Image image1){
		
	}

	public static Image getNativeImage(javax.microedition.lcdui.Image image) {
		return null;
	}

	private static void mirrorImage(
			javax.microedition.lcdui.Image image, boolean flag) {
		
	}

	public static final int COLOR_BMP_8BIT = 5;
	private int height;
	private int width;
}