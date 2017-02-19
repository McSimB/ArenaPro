package com.elkware.midp.games.colorng.arena.high;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import javax.microedition.lcdui.Graphics;

public class CanvasView extends View {

	int width = 132;
	int height = 176;
	Paint mPaint;
	public MyCanvas myCanvas;
	public Graphics graphics;
	private Bitmap bitmap;
	public Canvas mCanvas;

	public CanvasView(Context context) {
		super(context);
		initView();
	}

	void initView() {
		mPaint = new Paint(Paint.DITHER_FLAG);
		graphics = new Graphics(width, height);
		bitmap = Bitmap.createBitmap(528, 704, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(bitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (myCanvas != null) {
			graphics.canvas = mCanvas;
			graphics.firstSave = true;
			myCanvas.paint(graphics);
			if (graphics.isSave) {
				graphics.isSave = false;
				mCanvas.restore();
				//canvas.scale(2, 2);
				canvas.drawBitmap(bitmap, 0, 0, mPaint);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(width, height);
	}

}
