package javax.microedition.lcdui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.elkware.midp.games.colorng.arena.high.R;

import javax.microedition.util.ContextHolder;
import javax.microedition.util.MainActivity;

public abstract class Canvas extends Displayable implements View.OnTouchListener {

	//public static final int UP = 1;
	//public static final int DOWN = 6;
	//public static final int LEFT = 2;
	//public static final int RIGHT = 5;
	public static final int FIRE = 8;
	public static final int KEY_NUM0 = 48;
	//public static final int KEY_NUM1 = 49;
	public static final int KEY_NUM2 = 50;
	//public static final int KEY_NUM3 = 51;
	public static final int KEY_NUM4 = 52;
	public static final int KEY_NUM5 = 53;
	public static final int KEY_NUM6 = 54;
	//public static final int KEY_NUM7 = 55;
	public static final int KEY_NUM8 = 56;
	//public static final int KEY_NUM9 = 57;
	//public static final int KEY_STAR = 42;
	//public static final int KEY_POUND = 35;
	public static final int KEY_DISPLAY1 = -12;
	public static final int KEY_DISPLAY2 = -4;

	private CanvasView canvasView;

	public Canvas() {
		MainActivity context = ContextHolder.getContext();
		canvasView = new CanvasView(context);
		Button btnLeft = context.findViewById(R.id.btn_left);
		btnLeft.setOnTouchListener(this);
		Button btnRight = context.findViewById(R.id.btn_right);
		btnRight.setOnTouchListener(this);
		Button btnDown = context.findViewById(R.id.btn_down);
		btnDown.setOnTouchListener(this);
		Button btnFire = context.findViewById(R.id.btn_fire);
		btnFire.setOnTouchListener(this);
		Button btnJump = context.findViewById(R.id.btn_jump);
		btnJump.setOnTouchListener(this);
		Button btnUse = context.findViewById(R.id.btn_use);
		btnUse.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (view.getId()) {
			case R.id.btn_left:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM4);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM4);
				}
				break;
			case R.id.btn_right:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM6);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM6);
				}
				break;
			case R.id.btn_down:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM8);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM8);
				}
				break;
			case R.id.btn_fire:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM5);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM5);
				}
				break;
			case R.id.btn_jump:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM2);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM2);
				}
				break;
			case R.id.btn_use:
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					keyPressed(Canvas.KEY_NUM0);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					keyReleased(Canvas.KEY_NUM0);
				}
				break;
		}
		return false;
	}

	public void keyPressed(int i) {
	}

	public void keyReleased(int i) {
	}

	public void paint(Graphics g) {
	}

	public final void repaint() {
	}

	public final void serviceRepaints() {
		canvasView.postInvalidate();
	}

	protected void showNotify() {
	}

	protected void hideNotify() {
	}

	public void setFullScreenMode() {
	}

	public int getHeight() {
		return 176;
	}

	public int getWidth() {
		return 132;
	}

	@Override
	public void callKeyPressed(int key) {
		keyPressed(key);
	}

	@Override
	public View getView() {
		return canvasView;
	}

	public class CanvasView extends View {

		private android.graphics.Canvas bufCanvas;
		private Graphics graphics;
		private Bitmap bitmap;

		public CanvasView(Context context) {
			super(context);
			this.graphics = new Graphics(Display.WIDTH, Display.HEIGHT);
			this.bitmap = Bitmap.createBitmap(Display.WIDTH, Display.HEIGHT, Bitmap.Config.ARGB_8888);
			this.bufCanvas = new android.graphics.Canvas(bitmap);
		}

		@Override
		protected void onDraw(android.graphics.Canvas canvas) {
			//if (myCanvas != null) {
				graphics.canvas = bufCanvas;
				graphics.firstSave = true;
				paint(graphics);
				if (graphics.isSave) {
					graphics.isSave = false;
					bufCanvas.restore();
					canvas.scale(Display.SCALE, Display.SCALE);
					canvas.drawBitmap(bitmap, 0, 0, null);
				}
			//}
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(Display.WIDTH * Display.SCALE, Display.HEIGHT * Display.SCALE);
		}

		public Graphics getGraphics() {
			return graphics;
		}
	}
}