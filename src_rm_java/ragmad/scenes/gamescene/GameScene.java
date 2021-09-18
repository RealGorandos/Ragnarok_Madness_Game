package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import java.lang.Math;


public class GameScene implements Scene{
	
	
	int xOffset, yOffset;
	private Keyboard key;
	public final static int SCALING = 1;  // Change it if you want to see different scalings. 
	int xCord;
	int yCord;
	int frameMovement;
	/// _________________________ Constructor Area_________________________________
	
	///...
	public GameScene() {
		key = new Keyboard();
		
		xCord = 0;
		yCord = 0;
		xOffset = 0;	//The camera shifting ratio at x
		yOffset = 0;	//The camera shifting ratio at y

	}
	
	///...
	
	///___________________________ GameEngine component methods area ______________
	/**
	 * Defines Game physics and scene logic
	 */
	@Override
	public void update() {
		
	}

	
	/**
	 * Takes care of rendering ot the GameEngine class
	 */
	@Override
	public void render() {
		frameMovement = (int)(10.0 *  (GameEngine.delta));
		if(key.isUp()) {yOffset += frameMovement;}
		if(key.isDown()) {yOffset -= frameMovement;}
		if(key.isRight()) {xOffset-= frameMovement;}
		if(key.isLeft()) {xOffset += frameMovement;}
		
		renderTile(Sprite.DESERT_TILE_1, xCord - 1, yCord);
		renderTile(Sprite.DESERT_TILE_1, xCord + 1, yCord);
		
		renderTile(Sprite.DESERT_TILE_1, xCord , yCord -1);
		renderTile(Sprite.DESERT_TILE_1, xCord, yCord + 1);
		renderBlock(Sprite.DESERT_TILE_2, xCord, yCord, 20); // Half a tile up
	}
	
	
	
	
	///___________________________ Game scene methods area ______________
	
	
	
	
	
	
	
	
	
	
	
	
	///________________________________________________________________________________
	/**
	 * The Isometric space looks as follows:
	 *   x++
	 *  /
	 * /
	 * \
	 *  \
	 *  y++
	 *  
	 *  As the above coordinates system shows. The x value is more to the Up-Right, while the y is the Bottom-Right
	 *  @param sprite - The sprite that is meant to be rendered
	 *  @param xCord - xCoordinates of the Isometric space. See above how Isometric space looks like
	 *  @param yCord - yCoordinates of the Isometric space. See above how Isometric space looks like
	 */
	private void renderTile(Sprite sprite, int xCord, int yCord) {
		int[] outputPixels = GameEngine.getPixels();
		int[] tilePixels = sprite.getPixels();
		
		int s_height = sprite.getHeight()*SCALING;
		int s_width = sprite.getWidth()*SCALING;
		int s_height_half = s_height >> 1;
		int s_width_half = s_width >> 1;

		for(int y = 0 ;y <s_height; y++) {
			int yy = y - xCord * s_height_half + yCord * s_height_half + yOffset;   //Mapping coordinates space to the GameEngine pixel Space (Raster space) //yOffset for vertical movement
			if( yy >= GameEngine.getHeight()) break;
			if(yy < 0) continue;
			for(int x = 0 ; x < s_width; x++) {
				int xx = x + xCord*s_width_half + yCord*s_width_half + xOffset; // mapping // xOffset for horizontal movement 
				int col = tilePixels[x/SCALING + (y/SCALING) * sprite.getWidth()]; // getting the pixel colour of the tile
				
				if ( xx >= GameEngine.getWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0 )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				outputPixels[xx + yy * GameEngine.getWidth()] = col;
			}
		}
	}
	
	
	
	
	/**
	 * Interpolates a block shape by taking the edge color of the tile and shade it a bit and render it to the base
	 * of the tile (where the tile z is equal to 0)
	 * @param sprite - The sprite meant to be rendered
	 * @param xCord - xCoordinate on the isometric space. Check the renderTile() description to understand the space.
	 * @param yCord - yCoordinate on the isometric space. Check the renderTile() description to understand the space.
	 * @param zPixels - The offset height of the tile. This function will render the edges of the given tile as well.
	 */
	private void renderBlock(Sprite sprite, int xCord, int yCord, int zPixels) {
		zPixels = zPixels * SCALING; // should also response with the scaling factor
		
		int[] outputPixels = GameEngine.getPixels();
		int[] tilePixels = sprite.getPixels();
		
		int s_height = sprite.getHeight()*SCALING;
		int s_width = sprite.getWidth()*SCALING;
		int s_height_half = s_height >> 1; // half sprite scaled height
		int s_width_half = s_width >> 1; // half sprite scaled width
		
		for(int y = 0 ;y < s_height; y++) {
			int yy = y - xCord * s_height_half + yCord * s_height_half - zPixels + yOffset;
			
			/*Height boundaries checking*/
			if( yy >= GameEngine.getHeight()) 
				break;
			if(yy < 0) 
				continue;
			
			
			for(int x = 0 ; x < s_width; x++) {
				int col = tilePixels[ x/SCALING + (y/SCALING) * sprite.getWidth() ];
				int xx = x + xCord * s_width_half + yCord * s_width_half + xOffset;
				
				/*width boundaries checking*/
				if ( xx >= GameEngine.getWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0 )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				outputPixels[xx + yy * GameEngine.getWidth()] = col; // rendering to the gamengien
				
				/*Rendering Left edge*/
				if( (zPixels > 0) && (x <= s_width_half) && (y >= s_height_half) &&  (y - s_height_half == x >> 1) ) {
					for(int z = 0 ; z < zPixels && (yy+z) < GameEngine.getHeight(); z++) {
						outputPixels[xx + (yy+z) * GameEngine.getWidth()] = shade(col,0.3);
					}
				}
				/*Rendering Right edge*/
				else if( (zPixels > 0) && (x >= s_width_half) && (y >= s_height_half) &&  (y - s_height_half == (s_width - x) >> 1) ) {	
					for(int z = 0 ; z < zPixels && (yy+z) < GameEngine.getHeight(); z++) {
						outputPixels[xx + (yy+z) * GameEngine.getWidth()] 		= shade(col, 0.7);
						outputPixels[(xx - 1) + (yy+z) * GameEngine.getWidth()] = shade(col, 0.7);
					}
				}
			}
		}
	}

	
	
	/**
	 * Darkens the given colour by the intensity.
	 * @param col - input colour
	 * @param intensity - 0 is black, 1 is do nothing. 100% or 1/1 of the original colour
	 * @return - the darkened version of the given input
	 */
	private int shade(int col,double intensity) {
		int r = (int) (intensity * ((col & 0x00ff0000) >> 16));
		int g = (int) (intensity * ((col & 0x0000ff00) >> 8));
		int b = (int) (intensity * ((col & 0x000000ff)));
		int ret = (r << 16) | (g << 8) | b | 0xff000000;
		return ret ;
	}
	
	
	
	
	
	
	
	
	
	
}



