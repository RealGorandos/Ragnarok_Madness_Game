package ragmad.graphics.sprite;


public class Sprite {
	
	int width, height;
	int bgBorderWidth;
	SpriteSheet sheet;
	int[] pixels;
	int xCord, yCord;
	
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
	public Sprite( SpriteSheet sheet , int xCoordinates , int yCoordinates, int width , int height) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.xCord = xCoordinates;
		this.yCord = yCoordinates;
		this.bgBorderWidth = 0;
		this.pixels = new int[this.width * this.height];
		sheet.crop(xCord * width, yCord * height, width, height, pixels);		
	}
	
	
	
	/**
	 * Initializes the current sprite
	 * @param sheet - The sheet in which the values will be cropped
	 * @param xCoordinates - The x-Axis (row) in which the sprite exists in the sprite sheet
	 * @param yCoordinates - The y-Axis (column) in which the sprite exists in the sprite sheet
	 * @param width - The width of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given width
	 * @param height - The height of the current sprite. It is considered that the sprite-sheet tiles are all based on the same as the given height
	 * @param xOffset - custom x offseting for special sprites in the spritesheet. Note: The offset also preserves original
	 * @param yOffset - custom y offseting for special sprites in the spritesheet
	 * @param xCropper- Decides how much to fill before start cutting. It can be - or + to define right and left cropping, up and down.
	 */
	public Sprite( SpriteSheet sheet , int xCoordinates , int yCoordinates, int width , int height, int xOffset, int yOffset, int xCropper) {
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
	
	public void refresh() {
		this.pixels = new int[this.width * this.height];
		sheet.crop(xCord * width, yCord * height, width, height, pixels);
	}
}






