package ragmad.graphics.sprite;


public enum Sprite {
	DESERT_TILE_1(SpriteSheet.DESERT_SHEET, 0, 0, 64, 32),
	DESERT_TILE_2(SpriteSheet.DESERT_SHEET, 0, 1, 64, 32),
	
	PORTAL_TILE_1(SpriteSheet.PORTAL_SHEET, 0, 0, 128, 64, -1, 1, 0),
	
	/*MOVING DOWNWARD*/
	PLAYER_TILE_BACK_1(SpriteSheet.PLAYER_SHEET, 6, 0, 51, 84),
	PLAYER_TILE_BACK_2(SpriteSheet.PLAYER_SHEET, 0, 0, 51, 84),
	PLAYER_TILE_BACK_3(SpriteSheet.PLAYER_SHEET, 1, 0, 51, 84),
	PLAYER_TILE_BACK_4(SpriteSheet.PLAYER_SHEET, 2, 0, 51, 84),
	PLAYER_TILE_BACK_5(SpriteSheet.PLAYER_SHEET, 3, 0, 51, 84),
	PLAYER_TILE_BACK_6(SpriteSheet.PLAYER_SHEET, 4, 0, 51, 84),
	PLAYER_TILE_BACK_7(SpriteSheet.PLAYER_SHEET, 5, 0, 51, 84),
	
	/*MOVING FORWARD*/
	PLAYER_TILE_FORWARD_1(SpriteSheet.PLAYER_SHEET, 0, 2,   51, 84),
	PLAYER_TILE_FORWARD_2(SpriteSheet.PLAYER_SHEET, 4, 1,   51, 84),
	PLAYER_TILE_FORWARD_3(SpriteSheet.PLAYER_SHEET, 5, 1,   51, 84),
	PLAYER_TILE_FORWARD_4(SpriteSheet.PLAYER_SHEET, 6, 1,   51, 84),
	PLAYER_TILE_FORWARD_5(SpriteSheet.PLAYER_SHEET, 7, 1,   51, 84),
	PLAYER_TILE_FORWARD_6(SpriteSheet.PLAYER_SHEET, 8, 1,   51, 84),
	PLAYER_TILE_FORWARD_7(SpriteSheet.PLAYER_SHEET, 9, 1,   51, 84),
	
	/*MOVING LEFT*/
	PLAYER_TILE_LEFT_1(SpriteSheet.PLAYER_SHEET, 3, 1,   51, 84),
	PLAYER_TILE_LEFT_2(SpriteSheet.PLAYER_SHEET, 7, 0,   51, 84),
	PLAYER_TILE_LEFT_3(SpriteSheet.PLAYER_SHEET, 8, 0,   51, 84),
	PLAYER_TILE_LEFT_4(SpriteSheet.PLAYER_SHEET, 9, 0,   51, 84),
	PLAYER_TILE_LEFT_5(SpriteSheet.PLAYER_SHEET, 0, 1,   51, 84),
	PLAYER_TILE_LEFT_6(SpriteSheet.PLAYER_SHEET, 1, 1,   51, 84),
	PLAYER_TILE_LEFT_7(SpriteSheet.PLAYER_SHEET, 2, 1,   51, 84),
	
	
	/*MOVING RIGHT*/
	PLAYER_TILE_RIGHT_1(SpriteSheet.PLAYER_SHEET, 7, 2, 51, 84),
	PLAYER_TILE_RIGHT_2(SpriteSheet.PLAYER_SHEET, 1, 2, 51, 84),
	PLAYER_TILE_RIGHT_3(SpriteSheet.PLAYER_SHEET, 2, 2, 51, 84),
	PLAYER_TILE_RIGHT_4(SpriteSheet.PLAYER_SHEET, 3, 2, 51, 84),
	PLAYER_TILE_RIGHT_5(SpriteSheet.PLAYER_SHEET, 4, 2, 51, 84),
	PLAYER_TILE_RIGHT_6(SpriteSheet.PLAYER_SHEET, 5, 2, 51, 84),
	PLAYER_TILE_RIGHT_7(SpriteSheet.PLAYER_SHEET, 6, 2, 51, 84),

	/*UPPER LEFT*/
	PLAYER_TILE_UPPER_LEFT_1(SpriteSheet.PLAYER_SHEET, 3, 4, 51, 84),
	PLAYER_TILE_UPPER_LEFT_2(SpriteSheet.PLAYER_SHEET, 7, 3, 51, 84),
	PLAYER_TILE_UPPER_LEFT_3(SpriteSheet.PLAYER_SHEET, 8, 3, 51, 84),
	PLAYER_TILE_UPPER_LEFT_4(SpriteSheet.PLAYER_SHEET, 9, 3, 51, 84),
	PLAYER_TILE_UPPER_LEFT_5(SpriteSheet.PLAYER_SHEET, 0, 4, 51, 84),
	PLAYER_TILE_UPPER_LEFT_6(SpriteSheet.PLAYER_SHEET, 1, 4, 51, 84),
	PLAYER_TILE_UPPER_LEFT_7(SpriteSheet.PLAYER_SHEET, 2, 4, 51, 84),
	
	/*UPPER RIGHT*/
	PLAYER_TILE_UPPER_RIGHT_1(SpriteSheet.PLAYER_SHEET, 0, 5, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_2(SpriteSheet.PLAYER_SHEET, 4, 4, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_3(SpriteSheet.PLAYER_SHEET, 5, 4, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_4(SpriteSheet.PLAYER_SHEET, 6, 4, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_5(SpriteSheet.PLAYER_SHEET, 7, 4, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_6(SpriteSheet.PLAYER_SHEET, 8, 4, 51, 84),
	PLAYER_TILE_UPPER_RIGHT_7(SpriteSheet.PLAYER_SHEET, 9, 4, 51, 84),
	
	
	/*DOWN RIGHT*/
	PLAYER_TILE_DOWN_RIGHT_1(SpriteSheet.PLAYER_SHEET, 7, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_2(SpriteSheet.PLAYER_SHEET, 1, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_3(SpriteSheet.PLAYER_SHEET, 2, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_4(SpriteSheet.PLAYER_SHEET, 3, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_5(SpriteSheet.PLAYER_SHEET, 4, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_6(SpriteSheet.PLAYER_SHEET, 5, 5, 51, 84),
	PLAYER_TILE_DOWN_RIGHT_7(SpriteSheet.PLAYER_SHEET, 6, 5, 51, 84),
	
	/*DOWN LEFT*/
	PLAYER_TILE_DOWN_LEFT_1(SpriteSheet.PLAYER_SHEET, 6, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_2(SpriteSheet.PLAYER_SHEET, 0, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_3(SpriteSheet.PLAYER_SHEET, 1, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_4(SpriteSheet.PLAYER_SHEET, 2, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_5(SpriteSheet.PLAYER_SHEET, 3, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_6(SpriteSheet.PLAYER_SHEET, 4, 3, 51, 84),
	PLAYER_TILE_DOWN_LEFT_7(SpriteSheet.PLAYER_SHEET, 5, 3, 51, 84)

	;
	
	
	
	int width, height;
	int bgBorderWidth;
	SpriteSheet sheet;
	int[] pixels;
	
	
	/*Cunstructor for the player png exclusivily this could be deleted later*/
	//private Sprite(SpriteSheet sheet , int )
	
	
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