package javax.microedition.lcdui;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Image {

	private Bitmap bitmap;

	private Image(byte[] imageData, int imageOffset, int imageLength) {
		bitmap = BitmapFactory.decodeByteArray(imageData, imageOffset, imageLength);
	}

	private Image(int width, int height) {
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	}

	private Image(String name, Activity activity) {
		AssetManager assetManager = activity.getAssets();
		InputStream inputStream;
		try {
			inputStream = assetManager.open(name);
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Image createImage(String s) {
		return null;
	}

	public static Image createImage(int width, int height) {
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException();
		else
			return new Image(width, height);
	}

	public static Image createImage(String name, Activity activity) {
		return new Image(name, activity);
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
		throw new IllegalStateException();
	}

	public void getRGB(int[] var5, int i, int var3, int i1, int i2, int var31, int var4) {
	}

	public static Image createRGBImage(int[] var8, int var_de1, int var_de11, boolean b) {
		return null;
	}
}