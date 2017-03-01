package com.elkware.midp.games.colorng.arena.high;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import javax.microedition.lcdui.Graphics;

import static javax.microedition.lcdui.Display.HEIGHT;
import static javax.microedition.lcdui.Display.SCALE;
import static javax.microedition.lcdui.Display.WIDTH;

public class CanvasView extends View {

	private MyCanvas myCanvas;
	private Canvas bufCanvas;
	private Graphics graphics;
	private Bitmap bitmap;

	public CanvasView(Context context) {
		super(context);
	}

	void initView(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		this.graphics = new Graphics(WIDTH, HEIGHT);
		this.bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
		this.bufCanvas = new Canvas(bitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (myCanvas != null) {
			graphics.canvas = bufCanvas;
			graphics.firstSave = true;
			myCanvas.paint(graphics);
			if (graphics.isSave) {
				graphics.isSave = false;
				bufCanvas.restore();
				canvas.scale(SCALE, SCALE);
				canvas.drawBitmap(bitmap, 0, 0, null);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(WIDTH * SCALE, HEIGHT * SCALE);
	}

}
