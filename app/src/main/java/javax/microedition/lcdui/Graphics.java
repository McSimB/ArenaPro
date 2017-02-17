package javax.microedition.lcdui;

public class Graphics
{

	private native void init();

	Graphics(int w, int h)
	{
		clip = new short[4];
		clipped = false;
		destination = null;
		maxWidth = (short)(w & 0x7fff);
		maxHeight = (short)(h & 0x7fff);
		init();
		reset();
	}

	public void translate(int x, int y)
	{
		transX += x;
		transY += y;
	}

	public int getTranslateX()
	{
		return transX;
	}

	public int getTranslateY()
	{
		return transY;
	}

	public int getColor()
	{
		return rgbColor;
	}

	public int getRedComponent()
	{
		return rgbColor >> 16 & 0xff;
	}

	public int getGreenComponent()
	{
		return rgbColor >> 8 & 0xff;
	}

	public int getBlueComponent()
	{
		return rgbColor & 0xff;
	}

	public int getGrayScale()
	{
		return gray;
	}

	public void setColor(int red, int green, int blue)
	{
		if(red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255)
		{
			throw new IllegalArgumentException("Value out of range");
		} else
		{
			rgbColor = red << 16 | green << 8 | blue;
			gray = grayVal(red, green, blue);
			pixel = getPixel(rgbColor, gray, false);
			return;
		}
	}

	public void setColor(int RGB)
	{
		int red = RGB >> 16 & 0xff;
		int green = RGB >> 8 & 0xff;
		int blue = RGB & 0xff;
		rgbColor = RGB & 0xffffff;
		gray = grayVal(red, green, blue);
		pixel = getPixel(rgbColor, gray, false);
	}

	public void setGrayScale(int value)
	{
		if(value < 0 || value > 255)
		{
			throw new IllegalArgumentException("Gray value out of range");
		} else
		{
			rgbColor = value << 16 | value << 8 | value;
			gray = value;
			pixel = getPixel(rgbColor, gray, true);
			return;
		}
	}

	public Font getFont()
	{
		return currentFont;
	}

	public void setStrokeStyle(int style)
	{
		if(style != 0 && style != 1)
		{
			throw new IllegalArgumentException("Invalid line style");
		} else
		{
			this.style = style;
			return;
		}
	}

	public int getStrokeStyle()
	{
		return style;
	}

	public void setFont(Font font)
	{
		currentFont = font != null ? font : Font.getDefaultFont();
	}

	public int getClipX()
	{
		return clip[0] - transX;
	}

	public int getClipY()
	{
		return clip[1] - transY;
	}

	public int getClipWidth()
	{
		return clip[2];
	}

	public int getClipHeight()
	{
		return clip[3];
	}

	public void clipRect(int x, int y, int width, int height)
	{
		if(width <= 0 || height <= 0)
		{
			clip[2] = 0;
			clip[3] = 0;
			clipped = true;
			return;
		}
		translatedX1 = x + transX;
		translatedY1 = y + transY;
		if(translatedX1 < 0)
			translatedX1 = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if(translatedY1 < 0)
			translatedY1 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		clipX2 = clip[0] + clip[2];
		clipY2 = clip[1] + clip[3];
		if(clipX2 < translatedX1 || clipY2 < translatedY1)
		{
			clip[2] = 0;
			clip[3] = 0;
			clipped = true;
			return;
		}
		if(translatedX1 > clip[0])
		{
			clip[0] = (short)(translatedX1 & 0x7fff);
			clipped = true;
		}
		if(translatedY1 > clip[1])
		{
			clip[1] = (short)(translatedY1 & 0x7fff);
			clipped = true;
		}
		translatedX2 = x + transX + width;
		translatedY2 = y + transY + height;
		if(translatedX2 < 0)
			translatedX2 = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if(translatedY2 < 0)
			translatedY2 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		if(translatedX2 < clip[0] || translatedY2 < clip[1])
		{
			clip[2] = 0;
			clip[3] = 0;
			clipped = true;
			return;
		}
		if(translatedX2 <= clipX2)
		{
			clipX2 = translatedX2;
			clipped = true;
		}
		if(translatedY2 <= clipY2)
		{
			clipY2 = translatedY2;
			clipped = true;
		}
		if(clipped)
		{
			clipX2 -= clip[0];
			clipY2 -= clip[1];
			clip[2] = clipX2 <= 0 ? 0 : (short)(clipX2 & 0x7fff);
			clip[3] = clipY2 <= 0 ? 0 : (short)(clipY2 & 0x7fff);
		}
	}

	public void setClip(int x, int y, int width, int height)
	{
		if(width <= 0 || height <= 0)
		{
			clip[0] = clip[1] = clip[2] = clip[3] = 0;
			clipped = true;
			return;
		}
		translatedX1 = x + transX;
		translatedY1 = y + transY;
		if(translatedX1 < 0)
			translatedX1 = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if(translatedY1 < 0)
			translatedY1 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		clipX1 = (short)(translatedX1 & 0x7fff);
		clipY1 = (short)(translatedY1 & 0x7fff);
		if(translatedX1 >= maxWidth || translatedY1 >= maxHeight)
		{
			clip[0] = clip[1] = clip[2] = clip[3] = 0;
			clipped = true;
			return;
		}
		clip[0] = clipX1;
		clip[1] = clipY1;
		translatedX2 = x + transX + width;
		translatedY2 = y + transY + height;
		if(translatedX2 < 0)
			translatedX2 = x >= 0 && transX >= 0 ? ((int) (maxWidth)) : 0;
		if(translatedY2 < 0)
			translatedY2 = y >= 0 && transY >= 0 ? ((int) (maxHeight)) : 0;
		clip[2] = (short)(translatedX2 - clipX1 & 0x7fff);
		clip[3] = (short)(translatedY2 - clipY1 & 0x7fff);
		if(clip[2] > maxWidth)
			clip[2] = maxWidth;
		if(clip[3] > maxHeight)
			clip[3] = maxHeight;
		if(clip[0] > 0 || clip[1] > 0 || clip[2] < maxWidth || clip[3] < maxHeight)
			clipped = true;
	}

	public native void drawLine(int i, int j, int k, int l);

	public native void fillRect(int i, int j, int k, int l);

	public native void drawRect(int i, int j, int k, int l);

	public native void drawRoundRect(int i, int j, int k, int l, int i1, int j1);

	public native void fillRoundRect(int i, int j, int k, int l, int i1, int j1);

	public native void fillArc(int i, int j, int k, int l, int i1, int j1);

	public native void drawArc(int i, int j, int k, int l, int i1, int j1);

	public native void drawString(String s, int i, int j, int k);

	public native void drawSubstring(String s, int i, int j, int k, int l, int i1);

	public native void drawChar(char c, int i, int j, int k);

	public native void drawChars(char ac[], int i, int j, int k, int l, int i1);

	public native void drawImage(Image image, int i, int j, int k);

	public native void drawRegion(Image image, int i, int j, int k, int l, int i1, int j1,
								  int k1, int l1);

	public void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor)
	{
		if(Display.isGraphicsDisplay(this))
		{
			throw new IllegalStateException();
		} else
		{
			doCopyArea(x_src, y_src, width, height, x_dest, y_dest, anchor);
			return;
		}
	}

	public native void fillTriangle(int i, int j, int k, int l, int i1, int j1);

	private native void doCopyArea(int i, int j, int k, int l, int i1, int j1, int k1);

	public native void drawRGB(int ai[], int i, int j, int k, int l, int i1, int j1,
							   boolean flag);

	public native int getDisplayColor(int i);

	static Graphics getGraphics(Image img)
	{
		if(img == null)
			return new Graphics(Display.WIDTH, Display.HEIGHT);
		else
			return new ImageGraphics(img);
	}

	void reset(int x1, int y1, int x2, int y2)
	{
		transX = transY = 0;
		currentFont = Font.getDefaultFont();
		style = 0;
		rgbColor = gray = 0;
		pixel = getPixel(rgbColor, gray, true);
		setClip(x1, y1, x2 - x1, y2 - y1);
	}

	void reset()
	{
		reset(0, 0, maxWidth, maxHeight);
	}

	private static int grayVal(int red, int green, int blue)
	{
		return red * 76 + green * 150 + blue * 29 >> 8;
	}

	private native int getPixel(int i, int j, boolean flag);

	public static final int HCENTER = 1;
	public static final int VCENTER = 2;
	public static final int LEFT = 4;
	public static final int RIGHT = 8;
	public static final int TOP = 16;
	public static final int BOTTOM = 32;
	public static final int BASELINE = 64;
	public static final int SOLID = 0;
	public static final int DOTTED = 1;
	private int transX;
	private int transY;
	private short clipX1;
	private short clipY1;
	private int clipX2;
	private int clipY2;
	private int translatedX1;
	private int translatedY1;
	private int translatedX2;
	private int translatedY2;
	private short clip[];
	private boolean clipped;
	private int rgbColor;
	private int gray;
	private int pixel;
	private int style;
	private Font currentFont;
	private short maxWidth;
	private short maxHeight;
	Image destination;
}
