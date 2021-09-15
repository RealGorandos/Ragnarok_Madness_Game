package ragmad.graphics.sprite;


public enum Sprite {
	DESERT_TILE_1(SpriteSheet.DESERT_SHEET, 0, 0, 64, 32);
	
	
	
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
	
	
	public int[] getPixels() {return pixels;}
	public int getWidth () {return this.width;}
	public int getHeight () {return this.height;}
	public int getBorderWidth() { return this.bgBorderWidth;}
}