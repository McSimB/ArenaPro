package javax.microedition.lcdui;

import java.util.Hashtable;

public final class Font {

	private int face;
	private int style;
	private int size;
	private int height;
	private static final Font DEFAULT_FONT = new Font(0, 0, 0);
	private static Hashtable table = new Hashtable(4);

	private Font(int face, int style, int size) {
		this.face = face;
		this.style = style;
		this.size = size;
		init(face, style, size);
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
		Font f;
		Integer key = face | style | size;
		f = (Font) table.get(key);
		if (f == null) {
			f = new Font(face, style, size);
			table.put(key, f);
		}
		return f;
	}

	public int stringWidth(String s){
		return s.length();
	}

	private void init(int i, int j, int k) {
	}

}

