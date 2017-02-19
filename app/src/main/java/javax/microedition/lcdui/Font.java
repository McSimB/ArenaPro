package javax.microedition.lcdui;

import android.graphics.Paint;
import android.graphics.Typeface;

public final class Font {

	private static final Font DEFAULT_FONT = new Font(0, 0, 0);

	public Paint paint;

	private Font(int face, int style, int size) {
		Typeface f;
		int s;
		paint = new Paint();
		if (face == 32) {
			f = Typeface.MONOSPACE;
		} else {
			f = Typeface.DEFAULT;
		}
		if (style == 1) {
			s = Typeface.BOLD;
		} else if (style == 5) {
			s = Typeface.BOLD;
			paint.setUnderlineText(true);
		} else {
			s = Typeface.NORMAL;
		}
		paint.setTypeface(Typeface.create(f, s));
		if (size == 0) {
			paint.setTextSize(13);
		} else if (size == 8) {
			paint.setTextSize(10);
		}
	}

	public static Font getDefaultFont() {
		return DEFAULT_FONT;
	}

	public static Font getFont(int face, int style, int size) {
		if (face != 0 && face != 32 && face != 64)
			throw new IllegalArgumentException("Unsupported face");
		if ((style & 7) != style)
			throw new IllegalArgumentException("Illegal style");
		if (size != 8 && size != 0 && size != 16)
			throw new IllegalArgumentException("Unsupported size");
		return new Font(face, style, size);
	}

	public int stringWidth(String s){
		return  Math.round(paint.measureText(s));
	}

}

