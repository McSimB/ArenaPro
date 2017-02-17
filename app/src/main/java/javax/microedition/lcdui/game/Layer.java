package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;

public abstract class Layer {

	int x;
	int y;
	int width;
	int height;
	boolean visible;

	Layer() {}

	Layer(int width, int height) {
		visible = true;
		setWidthImpl(width);
		setHeightImpl(height);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public final boolean isVisible() {
		return visible;
	}

	public abstract void paint(Graphics g);

	public void setWidthImpl(int width) {
		if (width < 0) {
			throw new IllegalArgumentException();
		} else {
			this.width = width;
		}
	}

	public void setHeightImpl(int height) {
		if (height < 0) {
			throw new IllegalArgumentException();
		} else {
			this.height = height;
		}
	}
}