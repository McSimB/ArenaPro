package javax.microedition.lcdui;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.util.ContextHolder;

public class Image {

	private Bitmap bitmap;

	private Image(byte[] imageData, int imageOffset, int imageLength) {
		bitmap = BitmapFactory.decodeByteArray(imageData, imageOffset, imageLength);
	}

	private Image(int width, int height) {
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	}

	private Image(String name) {
		AssetManager assetManager = ContextHolder.getContext().getAssets();
		InputStream inputStream;
		try {
			inputStream = assetManager.open(name);
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Image(int[] rgb, int width, int height, boolean processAlpha) {
		bitmap = Bitmap.createBitmap(rgb, width, height, Bitmap.Config.ARGB_8888);
	}

	private Image(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public static Image createImage(int width, int height) {
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException();
		else
			return new Image(width, height);
	}

	public static Image createImage(String name) {
		if (name.startsWith("/")) {
			name = name.substring(1);
		}
		return new Image(name);
	}

	public static Image createRGBImage(int[] rgb, int width, int height, boolean processAlpha) {
		return new Image(rgb, width, height, processAlpha);
	}

	public static Image createImage(byte imageData[], int imageOffset, int imageLength) {
		if (imageOffset < 0 || imageOffset >= imageData.length || imageLength < 0 ||
				imageOffset + imageLength > imageData.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		else {
			return new Image(imageData, imageOffset, imageLength);
		}
	}

	public static Image createImage(Bitmap bitmap) {
		return new Image(bitmap);
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public int getWidth() {
		return bitmap.getWidth();
	}

	public int getHeight() {
		return bitmap.getHeight();
	}

	public Graphics getGraphics() {
		android.graphics.Canvas bufCanvas = new android.graphics.Canvas(bitmap);
		Graphics g = new Graphics(bitmap.getWidth(), bitmap.getHeight());
		g.canvas = bufCanvas;
		g.firstSave = true;
		return g;
	}

	public void getRGB(int[] var5, int i, int var3, int i1, int i2, int var31, int var4) {
	}

}