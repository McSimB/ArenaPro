package javax.microedition.lcdui;

import android.graphics.Paint;

public class Graphics {

	private int transX = 0;
	private int transY = 0;
	private short clip[] = new short[4];
	private short maxWidth;
	private short maxHeight;
	public boolean firstSave;
	public boolean isSave;

	private Paint paint;
	public android.graphics.Canvas canvas;

	public Graphics(int w, int h) {
		maxWidth = (short) (w & 0x7fff);
		maxHeight = (short) (h & 0x7fff);
		paint = new Paint();
	}

	public Font getFont() {
		return Font.getDefaultFont();
	}

	public void setFont(Font font) {
	}

	public int getClipX() {
		return (clip[0] - transX);
	}

	public int getClipY() {
		return (clip[1] - transY);
	}

	public int getClipWidth() {
		return clip[2];
	}

	public int getClipHeight() {
		return clip[3];
	}

	public void setClip(int x, int y, int width, int height) {
		if (width <= 0 || height <= 0) {
			clip[0] = clip[1] = clip[2] = clip[3] = 0;
			return;
		}
		int translatedX1 = x + transX;
		int translatedY1 = y + transY;
		if (x < 0)
			x = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if (translatedY1 < 0)
			translatedY1 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		short clipX1 = (short) (translatedX1 & 0x7fff);
		short clipY1 = (short) (translatedY1 & 0x7fff);
		if (translatedX1 >= maxWidth || translatedY1 >= maxHeight) {
			clip[0] = clip[1] = clip[2] = clip[3] = 0;
			return;
		}
		clip[0] = clipX1;
		clip[1] = clipY1;
		int translatedX2 = x + transX + width;
		int translatedY2 = y + transY + height;
		if (translatedX2 < 0)
			translatedX2 = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if (translatedY2 < 0)
			translatedY2 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		clip[2] = (short) (translatedX2 - clipX1 & 0x7fff);
		clip[3] = (short) (translatedY2 - clipY1 & 0x7fff);
		if (clip[2] > maxWidth)
			clip[2] = maxWidth;
		if (clip[3] > maxHeight)
			clip[3] = maxHeight;

		if (firstSave) {
			firstSave = false;
			isSave = true;
			canvas.save();
		} else {
			canvas.restore();
			canvas.save();
			isSave = true;
		}
		canvas.clipRect(clip[0], clip[1], clip[0] + clip[2], clip[1] + clip[3]);
	}

	public void setColor(int red, int green, int blue) {
		if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
			throw new IllegalArgumentException("Value out of range");
		} else {
			paint.setARGB(255, red, green, blue);
		}
	}

	public void setColor(int var_56f) {
	}

	public void drawRect(int var_4d3, int var_50d, int i, int i1) {
	}

	public void fillRect(int i, int j, int k, int l) {
		canvas.drawRect(i, j, (i + k), (j + l), paint);
	}

	public void drawString(String s, int i, int j, int k) {
		canvas.drawText(s, i, (j + 10), paint);
	}

	public void drawImage(Image image, int x, int y, int k) {
		//Rect dst = new Rect(clip[0], clip[1], clip[0] + clip[2], clip[1] + clip[3]);
		//Rect src = new Rect(clip[0] - x, clip[1] - y, clip[0] - x + clip[2], clip[1] - y + clip[3]);
		//canvas.drawBitmap(image.getBitmap(), src, dst, new Paint());

		canvas.drawBitmap(image.getBitmap(), x, y, new Paint());
	}

	public void drawLine(int i, int i1, int i2, int i3) {
	}

	public int getTranslateX() {
		return 0;
	}

	public int getTranslateY() {
		return 0;
	}

	public void drawRGB(int[] var_ab, int var4, int var_16c,
						int var2, int var3, int var5, int var6, boolean b) {
	}

	public int getDisplayColor(int var2) {
		return 0;
	}
}
