package com.siemens.mp.lcdui;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Graphics;

public class Image extends com.siemens.mp.ui.Image {

	public static void writeBmpToFile(javax.microedition.lcdui.Image image,
			String s) throws IOException {
	}

	public static void writeImageToFile(javax.microedition.lcdui.Image image,
			String s, int i) throws IOException {
	}

	public static javax.microedition.lcdui.Image createScaledImage(
			InputStream inputstream, int i, int j) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createImageFromFile(String s,
			boolean flag) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createImageFromFile(String s,
			int i, int j) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image clipAndScaleImage(
			javax.microedition.lcdui.Image image, int i, int j, int k, int l,
			int i1, int j1) throws IllegalArgumentException {
		return null;
	}

	public static void drawImage(javax.microedition.lcdui.Image image,
			Graphics g, int i, int j, int k, int l, int i1, int j1, int k1,
			int l1, boolean flag) throws IllegalArgumentException {
	}

	public static int getPixelColor(javax.microedition.lcdui.Image image,
			int i, int j) throws IllegalArgumentException {
		return 0;
	}

	public static void setPixelColor(javax.microedition.lcdui.Image image,
			int i, int j, int k) throws IllegalArgumentException {
	}

	public static int[] readImageSize(String s) throws IOException {
		return null;
	}

	public static int[] readImageSize(InputStream inputstream)
			throws IOException {
		return null;
	}

	public static int[] readImageSize(byte abyte0[]) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createClippedAndScaledImage(
			String s, int i, int j, int k, int l, int i1, int j1)
			throws IOException, IllegalArgumentException {
		return null;
	}

	public static javax.microedition.lcdui.Image createClippedAndScaledImage(
			InputStream inputstream, int i, int j, int k, int l, int i1, int j1)
			throws IOException, IllegalArgumentException {
		return null;
	}

	public static final int TYPE_BMP = 1;
	public static final int TYPE_JPG = 2;
}