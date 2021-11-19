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
public class SpriteSheet {

	
	String path;
	BufferedImage image;
	int width, height; 
	int[] pixels; // Should be final in the future
	
	
	/**
	 * Initiate a spritesheet. 
	 * @param path - The path to the sprite-sheet file. The path should contain even the extension.
	 */
	public SpriteSheet(String path){
		
		int[] tempPixels = null;
		try {
			image = ImageIO.read(new File(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tempPixels = new int[this.width * this.height];
			image.getRGB(0, 0, this.width, this.height, tempPixels, 0, this.width);
			System.out.println("A spritesheet has been loaded!");
			this.path= path;
		} catch (IOException e) {
			System.out.println("Can't find file: " + path);
		}
		this.pixels = tempPixels;
	}
	

	
	
	/**
	 * Crops a specific sprite from the object's Sprite-Sheet.
	 * @param xOffset - x-axis: row of the sprite you want to crop from the photo
	 * @param yOffset - y-axis: column of the sprite you want to crop from the spritesheet
	 * @param width - The width of the sprite you want to crop
	 * @param height - The height of the sprite you want to crop
	 * @param pixels - a reference array of pixels in which the croppepd values will be fed into. 
	 */
	public void crop(int xOffset, int yOffset , int width, int height , int[] pixels) {
		for(int y = 0; y < height ; y++) {
			int ny = y + yOffset;
			for(int x = 0 ; x < width ; x++) {
				int nx = x + xOffset;
				
				if(ny < 0 || nx < 0 ) pixels[x + y * width] = 0x00000000; // Transparent background
				
				pixels[x + y * width] = this.pixels[nx + ny * this.width];
			}
		}
	}
	
	

	/**
	 * Crops a specific sprite from the object's Sprite-Sheet.
	 * @param xOffset - x-axis: row of the sprite you want to crop from the photo
	 * @param yOffset - y-axis: column of the sprite you want to crop from the spritesheet
	 * @param width - The width of the sprite you want to crop
	 * @param height - The height of the sprite you want to crop
	 * @param pixels - a reference array of pixels in which the croppepd values will be fed into.
	 * @param xPeak - the amount of x to be cropped
	 */
	public void crop(int xOffset, int yOffset , int width, int height , int[] pixels, int xPeak) {
		for(int y = 0; y < height ; y++) {
			int ny = y + yOffset;
			
			for(int x = 0 ; x < width ; x++) {
				int nx = x + xOffset;
				
				if(ny < 0 || ny >= height) {
					pixels[x + y * width] = 0x00000000; // Transparent background
					continue;
				}
				 
				/*Xpeak is positive, we draw margin on right side*/
				if(xPeak > 0 && x > width - xPeak )
					pixels[x + y * width] = 0x00000000; // Transparent background
				/*Xpeak is negative, we draw margin on left side*/
				else if ((xPeak < 0 && x < -xPeak) || nx < 0 || nx >= this.width)
					pixels[x + y * width] = 0x00000000; // Transparent background
				else
					pixels[x + y * width] = this.pixels[nx + ny * this.width];
			}
		}
	}
	
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int[] getPixels() {return this.pixels;}
	
	public void reload(String newPath) { 
		int[] tempPixels = null;
		try {
			image = ImageIO.read(new File(newPath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tempPixels = new int[this.width * this.height];
			image.getRGB(0, 0, this.width, this.height, tempPixels, 0, this.width);
			System.out.println("A spritesheet has been re-loaded!");
			this.path = newPath;
		} catch (IOException e) {
			System.out.println("Can't find file: " + newPath);
		}
		this.pixels = tempPixels;
	}
	
	public String toString() {
		return this.path;
	}
	
	
}