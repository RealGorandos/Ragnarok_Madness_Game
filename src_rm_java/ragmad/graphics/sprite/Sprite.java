package ragmad.graphics.sprite;


public enum Sprite {
	DESERT_TILE_1(SpriteSheet.DESERT_SHEET, 0, 0, 64, 32),
	DESERT_TILE_2(SpriteSheet.DESERT_SHEET, 0, 1, 64, 32),
	
	PORTAL_TILE_1(SpriteSheet.PORTAL_SHEET, 0, 0, 128, 64, -1, 1, 0)
	
	;
	
	
	
	int width, height;
	int bgBorderWidth;
	SpriteSheet sheet;
	int[] pixels;
	
	/**
	 * Initializes the current sprite
	 * @param sheet - The sheet in which the values will be cropped
	 * @param xCoordinates - The x-Axis (row) in which the sprite exists in the sprite sheet
	 * @param yCoordinates - The y-Axis (column) in which the sprite exists in the sprite sheet
	 * @param width - The width of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given width
	 * @param height - The height of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given height
	 */
	private Sprite( SpriteSheet sheet , int xCoordinates , int yCoordinates, int width , int height) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.bgBorderWidth = 0;
		this.pixels = new int[this.width * this.height];
		sheet.crop(xCoordinates * width, yCoordinates * height, width, height, pixels);		
	}
	
	
	
	/**
	 * Initializes the current sprite
	 * @param sheet - The sheet in which the values will be cropped
	 * @param xCoordinates - The x-Axis (row) in which the sprite exists in the sprite sheet
	 * @param yCoordinates - The y-Axis (column) in which the sprite exists in the sprite sheet
	 * @param width - The width of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given width
	 * @param height - The height of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given height
	 * @param xOffset - costum offseting for special sprites in the spritesheet. Note: The offset also preserves original
	 * @param xPeak- Decides how much to fill before start cutting. It can be - or + to define right and left cropping, up and down.
	 */
	private Sprite( SpriteSheet sheet , int xCoordinates , int yCoordinates, int width , int height, int xOffset, int yOffset, int xCropper) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.bgBorderWidth = 0;
		this.pixels = new int[this.width * this.height];
		sheet.crop(xCoordinates * width + xOffset, yCoordinates * height + yOffset, width, height, pixels, xCropper);		
	}
	
	
	
	
	public int[] getPixels() {return pixels;}
	public int getWidth () {return this.width;}
	public int getHeight () {return this.height;}
	public int getBorderWidth() { return this.bgBorderWidth;}
}