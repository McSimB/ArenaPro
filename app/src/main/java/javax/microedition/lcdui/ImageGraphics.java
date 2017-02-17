package javax.microedition.lcdui;

class ImageGraphics extends Graphics {

	ImageGraphics(Image img) {
		super(img.getWidth(), img.getHeight());
		destination = img;
	}

	public void translate(int x, int y) {
		synchronized (this) {
			super.translate(x, y);
		}
	}

	public void setColor(int red, int green, int blue) {
		synchronized (this) {
			super.setColor(red, green, blue);
		}
	}

	public void setColor(int RGB) {
		synchronized (this) {
			super.setColor(RGB);
		}
	}

	public void setGrayScale(int value) {
		synchronized (this) {
			super.setGrayScale(value);
		}
	}

	public int getClipX() {
		return super.getClipX();
	}

	public int getClipY() {
		return super.getClipY();
	}

	public void clipRect(int x, int y, int w, int h) {
		synchronized (this) {
			super.clipRect(x, y, w, h);
		}
	}

	public void setClip(int x, int y, int w, int h) {
		synchronized (this) {
			super.setClip(x, y, w, h);
		}
	}
}
