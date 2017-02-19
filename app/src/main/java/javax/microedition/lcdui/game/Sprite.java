package javax.microedition.lcdui.game;

import android.graphics.Paint;
import android.graphics.Rect;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprite extends Layer {

	Image sourceImage;
	int sc = 1;
	int numFrames;
	int frameCoordsX[];
	int frameCoordsY[];
	int srcFrameWidth;
	int srcFrameHeight;
	int frameSeq[];
	private int seqIndex;
	private boolean customSequenceDefined;
	int dRefX;
	int dRefY;
	int collisionRectX;
	int collisionRectY;
	int collisionRectWidth;
	int collisionRectHeight;
	int t_currentTransformation;
	int t_collisionRectX;
	int t_collisionRectY;
	int t_collisionRectWidth;
	int t_collisionRectHeight;

	public Sprite(Image image) {
		super(image.getWidth(), image.getHeight());
		initializeFrames(image, image.getWidth(), image.getHeight(), false);
		initCollisionRectBounds();
		setTransformImpl(0);
	}

	public Sprite(Image image, int frameWidth, int frameHeight) {
		super(frameWidth, frameHeight);
		if (frameWidth < 1 || frameHeight < 1 || image.getWidth() % frameWidth != 0 || image.getHeight() % frameHeight != 0) {
			throw new IllegalArgumentException();
		} else {
			initializeFrames(image, frameWidth, frameHeight, false);
			initCollisionRectBounds();
			setTransformImpl(0);
		}
	}

	public void setFrame(int sequenceIndex) {
		if (sequenceIndex < 0 || sequenceIndex >= frameSeq.length) {
			throw new IndexOutOfBoundsException();
		} else {
			this.seqIndex = sequenceIndex;
		}
	}

	@Override
	public final void paint(Graphics g) {
		if (g == null)
			throw new NullPointerException();
		if (visible) {
			Rect dst = new Rect(x * sc, y * sc, (x + srcFrameWidth) * sc, (y + srcFrameHeight) * sc);
			Rect src = new Rect(frameCoordsX[frameSeq[seqIndex]] * sc, frameCoordsY[frameSeq[seqIndex]] * sc,
					(frameCoordsX[frameSeq[seqIndex]] + srcFrameWidth) * sc, (frameCoordsY[frameSeq[seqIndex]] + srcFrameHeight) * sc);
			g.canvas.drawBitmap(sourceImage.getBitmap(), src, dst, new Paint());
		}
	}

	public void setImage(Image img, int frameWidth, int frameHeight) {
		if (frameWidth < 1 || frameHeight < 1 || img.getWidth() % frameWidth != 0 || img.getHeight() % frameHeight != 0)
			throw new IllegalArgumentException();
		int noOfFrames = (img.getWidth() / frameWidth) * (img.getHeight() / frameHeight);
		boolean maintainCurFrame = true;
		if (noOfFrames < numFrames) {
			maintainCurFrame = false;
			customSequenceDefined = false;
		}
		if (srcFrameWidth != frameWidth || srcFrameHeight != frameHeight) {
			int oldX = x + getTransformedPtX(dRefX, dRefY, t_currentTransformation);
			int oldY = y + getTransformedPtY(dRefX, dRefY, t_currentTransformation);
			setWidthImpl(frameWidth);
			setHeightImpl(frameHeight);
			initializeFrames(img, frameWidth, frameHeight, maintainCurFrame);
			initCollisionRectBounds();
			x = oldX - getTransformedPtX(dRefX, dRefY, t_currentTransformation);
			y = oldY - getTransformedPtY(dRefX, dRefY, t_currentTransformation);
			computeTransformedBounds(t_currentTransformation);
		} else {
			initializeFrames(img, frameWidth, frameHeight, maintainCurFrame);
		}
	}

	public void defineCollisionRectangle(int x, int y, int width, int height) {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException();
		} else {
			collisionRectX = x;
			collisionRectY = y;
			collisionRectWidth = width;
			collisionRectHeight = height;
			setTransformImpl(t_currentTransformation);
		}
	}

	public final boolean collidesWith(Sprite s, boolean pixelLevel) {
		if (!s.visible || !visible)
			return false;
		int otherLeft = s.x + s.t_collisionRectX;
		int otherTop = s.y + s.t_collisionRectY;
		int otherRight = otherLeft + s.t_collisionRectWidth;
		int otherBottom = otherTop + s.t_collisionRectHeight;
		int left = x + t_collisionRectX;
		int top = y + t_collisionRectY;
		int right = left + t_collisionRectWidth;
		int bottom = top + t_collisionRectHeight;
		if (intersectRect(otherLeft, otherTop, otherRight, otherBottom, left, top, right, bottom)) {
			if (pixelLevel) {
				if (t_collisionRectX < 0)
					left = x;
				if (t_collisionRectY < 0)
					top = y;
				if (t_collisionRectX + t_collisionRectWidth > width)
					right = x + width;
				if (t_collisionRectY + t_collisionRectHeight > height)
					bottom = y + height;
				if (s.t_collisionRectX < 0)
					otherLeft = s.x;
				if (s.t_collisionRectY < 0)
					otherTop = s.y;
				if (s.t_collisionRectX + s.t_collisionRectWidth > s.width)
					otherRight = s.x + s.width;
				if (s.t_collisionRectY + s.t_collisionRectHeight > s.height)
					otherBottom = s.y + s.height;
				if (!intersectRect(otherLeft, otherTop, otherRight, otherBottom, left, top, right, bottom)) {
					return false;
				} else {
					int intersectLeft = left >= otherLeft ? left : otherLeft;
					int intersectTop = top >= otherTop ? top : otherTop;
					int intersectRight = right >= otherRight ? otherRight : right;
					int intersectBottom = bottom >= otherBottom ? otherBottom : bottom;
					int intersectWidth = Math.abs(intersectRight - intersectLeft);
					int intersectHeight = Math.abs(intersectBottom - intersectTop);
					int thisImageXOffset = getImageTopLeftX(intersectLeft, intersectTop, intersectRight, intersectBottom);
					int thisImageYOffset = getImageTopLeftY(intersectLeft, intersectTop, intersectRight, intersectBottom);
					int otherImageXOffset = s.getImageTopLeftX(intersectLeft, intersectTop, intersectRight, intersectBottom);
					int otherImageYOffset = s.getImageTopLeftY(intersectLeft, intersectTop, intersectRight, intersectBottom);
					return doPixelCollision(thisImageXOffset, thisImageYOffset, otherImageXOffset, otherImageYOffset, sourceImage, t_currentTransformation, s.sourceImage, s.t_currentTransformation, intersectWidth, intersectHeight);
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}


	private void initializeFrames(Image image, int fWidth, int fHeight, boolean maintainCurFrame) {
		int imageW = image.getWidth();
		int imageH = image.getHeight();
		int numHorizontalFrames = imageW / fWidth;
		int numVerticalFrames = imageH / fHeight;
		sourceImage = image;
		srcFrameWidth = fWidth;
		srcFrameHeight = fHeight;
		numFrames = numHorizontalFrames * numVerticalFrames;
		frameCoordsX = new int[numFrames];
		frameCoordsY = new int[numFrames];
		if (!maintainCurFrame)
			seqIndex = 0;
		if (!customSequenceDefined)
			frameSeq = new int[numFrames];
		int currentFrame = 0;
		for (int yy = 0; yy < imageH; yy += fHeight) {
			for (int xx = 0; xx < imageW; xx += fWidth) {
				frameCoordsX[currentFrame] = xx;
				frameCoordsY[currentFrame] = yy;
				if (!customSequenceDefined)
					frameSeq[currentFrame] = currentFrame;
				currentFrame++;
			}

		}

	}

	private void initCollisionRectBounds() {
		collisionRectX = 0;
		collisionRectY = 0;
		collisionRectWidth = width;
		collisionRectHeight = height;
	}

	private boolean intersectRect(int r1x1, int r1y1, int r1x2, int r1y2, int r2x1, int r2y1, int r2x2,
								  int r2y2) {
		return r2x1 < r1x2 && r2y1 < r1y2 && r2x2 > r1x1 && r2y2 > r1y1;
	}

	private static boolean doPixelCollision(int image1XOffset, int image1YOffset, int image2XOffset, int image2YOffset, Image image1, int transform1, Image image2, int transform2,
											int width, int height) {
		int numPixels = height * width;
		int argbData1[] = new int[numPixels];
		int argbData2[] = new int[numPixels];
		int startY1;
		int xIncr1;
		int yIncr1;
		if (0 != (transform1 & 4)) {
			if (0 != (transform1 & 1)) {
				xIncr1 = -height;
				startY1 = numPixels - height;
			} else {
				xIncr1 = height;
				startY1 = 0;
			}
			if (0 != (transform1 & 2)) {
				yIncr1 = -1;
				startY1 += height - 1;
			} else {
				yIncr1 = 1;
			}
			image1.getRGB(argbData1, 0, height, image1XOffset, image1YOffset, height, width);
		} else {
			if (0 != (transform1 & 1)) {
				startY1 = numPixels - width;
				yIncr1 = -width;
			} else {
				startY1 = 0;
				yIncr1 = width;
			}
			if (0 != (transform1 & 2)) {
				xIncr1 = -1;
				startY1 += width - 1;
			} else {
				xIncr1 = 1;
			}
			image1.getRGB(argbData1, 0, width, image1XOffset, image1YOffset, width, height);
		}
		int startY2;
		int xIncr2;
		int yIncr2;
		if (0 != (transform2 & 4)) {
			if (0 != (transform2 & 1)) {
				xIncr2 = -height;
				startY2 = numPixels - height;
			} else {
				xIncr2 = height;
				startY2 = 0;
			}
			if (0 != (transform2 & 2)) {
				yIncr2 = -1;
				startY2 += height - 1;
			} else {
				yIncr2 = 1;
			}
			image2.getRGB(argbData2, 0, height, image2XOffset, image2YOffset, height, width);
		} else {
			if (0 != (transform2 & 1)) {
				startY2 = numPixels - width;
				yIncr2 = -width;
			} else {
				startY2 = 0;
				yIncr2 = width;
			}
			if (0 != (transform2 & 2)) {
				xIncr2 = -1;
				startY2 += width - 1;
			} else {
				xIncr2 = 1;
			}
			image2.getRGB(argbData2, 0, width, image2XOffset, image2YOffset, width, height);
		}
		int numIterRows = 0;
		int xLocalBegin1 = startY1;
		int xLocalBegin2 = startY2;
		for (; numIterRows < height; numIterRows++) {
			int numIterColumns = 0;
			int x1 = xLocalBegin1;
			int x2 = xLocalBegin2;
			for (; numIterColumns < width; numIterColumns++) {
				if ((argbData1[x1] & 0xff000000) != 0 && (argbData2[x2] & 0xff000000) != 0)
					return true;
				x1 += xIncr1;
				x2 += xIncr2;
			}

			xLocalBegin1 += yIncr1;
			xLocalBegin2 += yIncr2;
		}

		return false;
	}

	private int getImageTopLeftX(int x1, int y1, int x2, int y2) {
		int retX = 0;
		switch (t_currentTransformation) {
			case 0:
			case 1:
				retX = x1 - x;
				break;

			case 2:
			case 3:
				retX = (x + width) - x2;
				break;

			case 4:
			case 5:
				retX = y1 - y;
				break;

			case 6:
			case 7:
				retX = (y + height) - y2;
				break;
		}
		retX += frameCoordsX[frameSeq[seqIndex]];
		return retX;
	}

	private int getImageTopLeftY(int x1, int y1, int x2, int y2) {
		int retY = 0;
		switch (t_currentTransformation) {
			case 0:
			case 2:
				retY = y1 - y;
				break;

			case 1:
			case 3:
				retY = (y + height) - y2;
				break;

			case 4:
			case 6:
				retY = x1 - x;
				break;

			case 5:
			case 7:
				retY = (x + width) - x2;
				break;
		}
		retY += frameCoordsY[frameSeq[seqIndex]];
		return retY;
	}

	public void setTransform(int transform) {
		setTransformImpl(transform);
	}


	private void setTransformImpl(int transform) {
		x = (x + getTransformedPtX(dRefX, dRefY, t_currentTransformation)) - getTransformedPtX(dRefX, dRefY, transform);
		y = (y + getTransformedPtY(dRefX, dRefY, t_currentTransformation)) - getTransformedPtY(dRefX, dRefY, transform);
		computeTransformedBounds(transform);
		t_currentTransformation = transform;
	}

	private void computeTransformedBounds(int transform) {
		switch (transform) {
			case 0:
				t_collisionRectX = collisionRectX;
				t_collisionRectY = collisionRectY;
				t_collisionRectWidth = collisionRectWidth;
				t_collisionRectHeight = collisionRectHeight;
				width = srcFrameWidth;
				height = srcFrameHeight;
				break;

			case 2:
				t_collisionRectX = srcFrameWidth - (collisionRectX + collisionRectWidth);
				t_collisionRectY = collisionRectY;
				t_collisionRectWidth = collisionRectWidth;
				t_collisionRectHeight = collisionRectHeight;
				width = srcFrameWidth;
				height = srcFrameHeight;
				break;

			case 1:
				t_collisionRectY = srcFrameHeight - (collisionRectY + collisionRectHeight);
				t_collisionRectX = collisionRectX;
				t_collisionRectWidth = collisionRectWidth;
				t_collisionRectHeight = collisionRectHeight;
				width = srcFrameWidth;
				height = srcFrameHeight;
				break;

			case 5:
				t_collisionRectX = srcFrameHeight - (collisionRectHeight + collisionRectY);
				t_collisionRectY = collisionRectX;
				t_collisionRectHeight = collisionRectWidth;
				t_collisionRectWidth = collisionRectHeight;
				width = srcFrameHeight;
				height = srcFrameWidth;
				break;

			case 3:
				t_collisionRectX = srcFrameWidth - (collisionRectWidth + collisionRectX);
				t_collisionRectY = srcFrameHeight - (collisionRectHeight + collisionRectY);
				t_collisionRectWidth = collisionRectWidth;
				t_collisionRectHeight = collisionRectHeight;
				width = srcFrameWidth;
				height = srcFrameHeight;
				break;

			case 6:
				t_collisionRectX = collisionRectY;
				t_collisionRectY = srcFrameWidth - (collisionRectWidth + collisionRectX);
				t_collisionRectHeight = collisionRectWidth;
				t_collisionRectWidth = collisionRectHeight;
				width = srcFrameHeight;
				height = srcFrameWidth;
				break;

			case 7:
				t_collisionRectX = srcFrameHeight - (collisionRectHeight + collisionRectY);
				t_collisionRectY = srcFrameWidth - (collisionRectWidth + collisionRectX);
				t_collisionRectHeight = collisionRectWidth;
				t_collisionRectWidth = collisionRectHeight;
				width = srcFrameHeight;
				height = srcFrameWidth;
				break;

			case 4:
				t_collisionRectY = collisionRectX;
				t_collisionRectX = collisionRectY;
				t_collisionRectHeight = collisionRectWidth;
				t_collisionRectWidth = collisionRectHeight;
				width = srcFrameHeight;
				height = srcFrameWidth;
				break;

			default:
				throw new IllegalArgumentException();
		}
	}

	private int getTransformedPtX(int x, int y, int transform) {
		int t_x;
		switch (transform) {
			case 0:
				t_x = x;
				break;

			case 2:
				t_x = srcFrameWidth - x - 1;
				break;

			case 1:
				t_x = x;
				break;

			case 5:
				t_x = srcFrameHeight - y - 1;
				break;

			case 3:
				t_x = srcFrameWidth - x - 1;
				break;

			case 6:
				t_x = y;
				break;

			case 7:
				t_x = srcFrameHeight - y - 1;
				break;

			case 4:
				t_x = y;
				break;

			default:
				throw new IllegalArgumentException();
		}
		return t_x;
	}

	private int getTransformedPtY(int x, int y, int transform) {
		int t_y;
		switch (transform) {
			case 0:
				t_y = y;
				break;

			case 2:
				t_y = y;
				break;

			case 1:
				t_y = srcFrameHeight - y - 1;
				break;

			case 5:
				t_y = x;
				break;

			case 3:
				t_y = srcFrameHeight - y - 1;
				break;

			case 6:
				t_y = srcFrameWidth - x - 1;
				break;

			case 7:
				t_y = srcFrameWidth - x - 1;
				break;

			case 4:
				t_y = x;
				break;

			default:
				throw new IllegalArgumentException();
		}
		return t_y;
	}

}
