package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TiledLayer extends Layer {

	private int cellHeight;
	private int cellWidth;
	private int rows;
	private int columns;
	private int cellMatrix[][];
	private Image sourceImage;
	private int numberOfTiles;
	private int tileSetX[];
	private int tileSetY[];
	private int anim_to_static[];
	private int numOfAnimTiles = 0;
	private boolean updated;
	private int tileArrayLength;
	private int x_src[];
	private int y_src[];
	private int x_dest[];
	private int y_dest[];

	public TiledLayer(int columns, int rows, Image image, int tileWidth, int tileHeight) {
		super(columns >= 1 && tileWidth >= 1 ? columns * tileWidth :
				-1, rows >= 1 && tileHeight >= 1 ? rows * tileHeight : -1,
				tileWidth, tileWidth, true);
		updated = true;
		x_src = null;
		y_src = null;
		x_dest = null;
		y_dest = null;
		if (image.getWidth() % tileWidth != 0 || image.getHeight() % tileHeight != 0) {
			throw new IllegalArgumentException();
		} else {
			this.columns = columns;
			this.rows = rows;
			cellMatrix = new int[rows][columns];
			int noOfFrames = (image.getWidth() / tileWidth) * (image.getHeight() / tileHeight);
			createStaticSet(image, noOfFrames + 1, tileWidth, tileHeight, true);
		}
	}

	private int getAnimatedTile(int animatedTileIndex) {
		animatedTileIndex = -animatedTileIndex;
		if (anim_to_static == null || animatedTileIndex <= 0 || animatedTileIndex >= numOfAnimTiles)
			throw new IndexOutOfBoundsException();
		else
			return anim_to_static[animatedTileIndex];
	}

	public void setCell(int col, int row, int tileIndex) {
		if (col < 0 || col >= columns || row < 0 || row >= rows)
			throw new IndexOutOfBoundsException();
		if (tileIndex > 0) {
			if (tileIndex >= numberOfTiles)
				throw new IndexOutOfBoundsException();
		} else if (tileIndex < 0 && (anim_to_static == null || -tileIndex >= numOfAnimTiles))
			throw new IndexOutOfBoundsException();
		cellMatrix[row][col] = tileIndex;
		updated = true;
	}

	@Override
	public synchronized void paint(Graphics g) {
		if (g == null)
			throw new NullPointerException();
		if (isVisible()) {
			int tileIndex;
			if (updated) {
				int arrayLength = 0;
				for (int[] aCellMatrix : cellMatrix) {
					for (int anACellMatrix : aCellMatrix) {
						tileIndex = anACellMatrix;
						if (tileIndex != 0)
							arrayLength++;
					}

				}

				if (x_src == null || arrayLength > x_src.length) {
					x_src = new int[arrayLength];
					y_src = new int[arrayLength];
					x_dest = new int[arrayLength];
					y_dest = new int[arrayLength];
				}
				tileArrayLength = arrayLength;
				updated = false;
			}
			int ty = getY();
			int arrayIndex = 0;
			for (int row = 0; row < cellMatrix.length; ) {
				int tx = getX();
				int totalCols = cellMatrix[row].length;
				for (int column = 0; column < totalCols; ) {
					tileIndex = cellMatrix[row][column];
					if (tileIndex != 0) {
						if (tileIndex < 0)
							tileIndex = getAnimatedTile(tileIndex);
						x_src[arrayIndex] = tileSetX[tileIndex];
						y_src[arrayIndex] = tileSetY[tileIndex];
						x_dest[arrayIndex] = tx;
						y_dest[arrayIndex] = ty;
						arrayIndex++;
					}
					column++;
					tx += cellWidth;
				}

				row++;
				ty += cellHeight;
			}
			//drawTiledRegion(g, sourceImage, tileArrayLength, x_src, y_src, cellWidth, cellHeight,
			//0, x_dest, y_dest, 20);
			for (int i = 0; i < tileArrayLength; i++) {
				/*Rect dst = new Rect(x_dest[i] * sc, y_dest[i] * sc, (x_dest[i] + cellWidth) * sc,
						(y_dest[i] + cellHeight) * sc);
				Rect src = new Rect(x_src[i] * sc, y_src[i] * sc, (x_src[i] + cellWidth) * sc,
						(y_src[i] + cellHeight) * sc);*/
				g.drawRegion(sourceImage, x_src[i], y_src[i], cellWidth, cellHeight, 0,
						x_dest[i], y_dest[i], 20);
			}
		}
	}

	private void createStaticSet(Image image, int noOfFrames, int tileWidth, int tileHeight,
								 boolean maintainIndices) {
		cellWidth = tileWidth;
		cellHeight = tileHeight;
		int imageW = image.getWidth();
		int imageH = image.getHeight();
		sourceImage = image;
		numberOfTiles = noOfFrames;
		tileSetX = new int[numberOfTiles];
		tileSetY = new int[numberOfTiles];
		if (!maintainIndices) {
			for (rows = 0; rows < cellMatrix.length; rows++) {
				int totalCols = cellMatrix[rows].length;
				for (columns = 0; columns < totalCols; columns++)
					cellMatrix[rows][columns] = 0;

			}
			anim_to_static = null;
		}
		int currentTile = 1;
		for (int y = 0; y < imageH; y += tileHeight) {
			for (int x = 0; x < imageW; x += tileWidth) {
				tileSetX[currentTile] = x;
				tileSetY[currentTile] = y;
				currentTile++;
			}
		}
	}
}