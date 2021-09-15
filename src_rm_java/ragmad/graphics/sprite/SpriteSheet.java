package ragmad.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;


/**
 * Hard Corded Enum values for the engine. If a sprite sheet needs to be added it can be added to this 
 * 		Enum directly.
 * @author Mohido
 *
 */
public enum SpriteSheet {

	DESERT_SHEET(Paths.get("").toAbsolutePath().getParent().toString() + "\\res\\desert_res.png");
	
	
	BufferedImage image;
	int width, height; 
	final int[] pixels;
	
	
	/**
	 * Initiate a spritesheet. 
	 * @param path - The path to the sprite-sheet file. The path should contain even the extension.
	 */
	private SpriteSheet(String path){
		int[] tempPixels = null;
		try {
			image = ImageIO.read(new File(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tempPixels = new int[this.width * this.height];
			image.getRGB(0, 0, this.width, this.height, tempPixels, 0, this.width);
			System.out.println("A spritesheet has been loaded!");
		} catch (IOException e) {
			System.out.println("Can't find file: " + path);
			e.printStackTrace();
		}
		this.pixels = tempPixels;
	}
	

	
	
	/**
	 * Crops a specific sprite from the object's Sprite-Sheet.
	 * @param xCoordinates - x-axis: row of the sprite you want to crop from the photo
	 * @param yCoordinates - y-axis: column of the sprite you want to crop from the spritesheet
	 * @param width - The width of the sprite you want to crop
	 * @param height - The height of the sprite you want to crop
	 * @param pixels - a reference array of pixels in which the croppepd values will be fed into. 
	 */
	public void crop(int xCoordinates, int yCoordinates , int width, int height , int[] pixels) {
		System.out.println(xCoordinates + ", " + yCoordinates);
		for(int y = 0; y < height ; y++) {
			int ny = y + yCoordinates;
			for(int x = 0 ; x < width ; x++) {
				int nx = x + xCoordinates;
				pixels[x + y * width] = this.pixels[nx + ny * this.width];
			}
		}
	}
	
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int[] getPixels() {return this.pixels;}
	
	
}