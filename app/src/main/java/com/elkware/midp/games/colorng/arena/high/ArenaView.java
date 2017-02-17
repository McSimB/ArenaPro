package com.elkware.midp.games.colorng.arena.high;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import javax.microedition.lcdui.Graphics;

public class ArenaView extends View {

	int width = 132;
	int height = 176;
	Paint mPaint;
	public CanvasView canvasView;
	public Graphics graphics;
	private Bitmap bitmap;
	public Canvas mCanvas;

	public ArenaView(Context context) {
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
		if (canvasView != null) {
			graphics.canvas = mCanvas;
			graphics.firstSave = true;
			canvasView.paint(graphics);
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
