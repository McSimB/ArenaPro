package javax.microedition.lcdui;

import java.util.Hashtable;

public final class Font
{

	public static Font getFont(int fontSpecifier)
	{
		Font font;
		switch(fontSpecifier)
		{
			case 0: // '\0'
			case 1: // '\001'
				font = DEFAULT_FONT;
				break;

			default:
				throw new IllegalArgumentException();
		}
		return font;
	}

	private Font(int face, int style, int size)
	{
		this.face = face;
		this.style = style;
		this.size = size;
		init(face, style, size);
	}

	public static Font getDefaultFont()
	{
		return DEFAULT_FONT;
	}

	public static Font getFont(int face, int style, int size)
	{
		if(face != 0 && face != 32 && face != 64)
			throw new IllegalArgumentException("Unsupported face");
		if((style & 7) != style)
			throw new IllegalArgumentException("Illegal style");
		if(size != 8 && size != 0 && size != 16)
			throw new IllegalArgumentException("Unsupported size");
		Font f;
		Integer key = new Integer(face | style | size);
		f = (Font)table.get(key);
		if(f == null)
		{
			f = new Font(face, style, size);
			table.put(key, f);
		}
		return f;
	}

	public int getStyle()
	{
		return style;
	}

	public int getSize()
	{
		return size;
	}

	public int getFace()
	{
		return face;
	}

	public boolean isPlain()
	{
		return style == 0;
	}

	public boolean isBold()
	{
		return (style & 1) == 1;
	}

	public boolean isItalic()
	{
		return (style & 2) == 2;
	}

	public boolean isUnderlined()
	{
		return (style & 4) == 4;
	}

	public int getHeight()
	{
		return height;
	}

	public int getBaselinePosition()
	{
		return baseline;
	}

	public native int charWidth(char c);

	public native int charsWidth(char ac[], int i, int j);

	public native int stringWidth(String s);

	public native int substringWidth(String s, int i, int j);

	private native void init(int i, int j, int k);

	public static final int STYLE_PLAIN = 0;
	public static final int STYLE_BOLD = 1;
	public static final int STYLE_ITALIC = 2;
	public static final int STYLE_UNDERLINED = 4;
	public static final int SIZE_SMALL = 8;
	public static final int SIZE_MEDIUM = 0;
	public static final int SIZE_LARGE = 16;
	public static final int FACE_SYSTEM = 0;
	public static final int FACE_MONOSPACE = 32;
	public static final int FACE_PROPORTIONAL = 64;
	public static final int FONT_STATIC_TEXT = 0;
	public static final int FONT_INPUT_TEXT = 1;
	private int face;
	private int style;
	private int size;
	private int baseline;
	private int height;
	private static final Font DEFAULT_FONT = new Font(0, 0, 0);
	private static Hashtable table = new Hashtable(4);

}

