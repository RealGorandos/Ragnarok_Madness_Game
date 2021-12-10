package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.graphics.sprite.*;




public class Tile {
	
	
	private int zCord; // xCord,yCord are in coordinates while zCord are pixels bias
	private int isoWidth, isoHeight; // How many tiles do this tile occupy! (Since we can't have an arc shap '>' for example we can say that xTiles and yTiles are the width,height in the isometric space)
	private boolean solid;
	private Sprite sprite;
	
	/*These are the normalized base tile height and width. Change it if you want to have anotehr system in your rendering process.*/
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 32;
	
	// _________________________________ Constructors area _____________________________________________
	
	/**
	 * Creates a tile. A tile is a piece of earth used to render the environment map
	 * @param zHeight - height of hte tile (z axis)
	 * @param sprite - Tile Sprite
	 * @param solid - whether it is collidable or not.
	 */
	public Tile( int zHeight, Sprite sprite, boolean solid){
		this.zCord = zHeight;
		this.solid = solid;
		this.sprite = sprite;
		this.isoWidth = sprite.getWidth() / TILE_WIDTH; 		// normal sprite width is 32
		this.isoHeight = sprite.getHeight() / TILE_HEIGHT; 		// Normal sprite height	
	}

	
	
	
	
	//_______________________________________ Methods Area ________________________
	/**
	 * A special rendering function in which it takes 2 raster coordinates and renders to these coordinates!
	 * @param xCord - X coordinates of the Isometric coordinates that you want to start rendering at.
	 * @param yCord - Y coordinates of the Isometric coordinates that you want to start rendering at.
	 * @param xOffset - x pixels offset of the screen.
	 * @param yOffset - y pixels offset of the screen.
	 * @param scaling Game scale ratio
	 */
	public void renderToRaster(int xCord, int yCord, int xOffset, int yOffset, int scaling) {
		if(this.zCord > 0)
			renderBlock( sprite, xCord  , yCord, xOffset, yOffset, scaling);
		else 
			renderTile( sprite, xCord, yCord, xOffset, yOffset, scaling);
	}
	
	
	
	
	
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
	 *  FUTURE EDIT: The xCord and yCord should be compatible with the x,y offset. So whenever the yOffset increases. xCord is the screen coordinate on Isometric.
	 */
	private void renderTile(Sprite sprite, int xCord, int yCord, int xOffset, int yOffset, int SCALING) {
		int[] outputPixels = GameEngine.GetPixels();
		int[] tilePixels = sprite.getPixels();
		
		int s_height = sprite.getHeight()*SCALING;
		int s_width = sprite.getWidth()*SCALING;

		int normal_height = TILE_HEIGHT*SCALING;
		int normal_width = TILE_WIDTH*SCALING;
		int n_width_half = normal_width >> 1;
		int n_height_half = normal_height >> 1;
		
		
		int xPixel = yCord * n_width_half + xCord * n_width_half + xOffset - (this.isoWidth-1)*n_width_half;
		int yPixel = yCord * n_height_half - xCord * n_height_half + yOffset;
		
		
		//System.out.println(xPixel + ", " + yPixel);
		for(int y = 0 ; y < s_height; y++) {
			int yy = y + yPixel;   //Mapping coordinates space to the GameEngine pixel Space (Raster space) //yOffset for vertical movement
			if( yy >= GameEngine.GetHeight()) break;
			if(yy < -s_height) break;
			if(yy < 0) continue;
			for(int x = 0 ; x < s_width; x++) {
				int xx = x + xPixel;
				int col = tilePixels[x/SCALING + (y/SCALING) * sprite.getWidth()]; // getting the pixel colour of the tile
				
				if ( xx >= GameEngine.GetWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0  )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				outputPixels[xx + yy * GameEngine.GetWidth()] = col;
			}
		}
	}
	
	
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	 
	
	/**
	 * Interpolates a block shape by taking the edge color of the tile and shade it a bit and render it to the base
	 * of the tile (where the tile z is equal to 0)
	 * @param sprite - The sprite meant to be rendered
	 * @param xCord - xCoordinate on the isometric space. Check the renderTile() description to understand the space.
	 * @param yCord - yCoordinate on the isometric space. Check the renderTile() description to understand the space.
	 * @param zPixels - The offset height of the tile. This function will render the edges of the given tile as well.
	 * 
	 * A fragile piece of code that barely works :) 
	 */
	private void renderBlock (Sprite sprite, int xCord, int yCord, int xOffset, int yOffset, int SCALING) {
		int zPixels = zCord * SCALING; // should also response with the scaling factor
		 
		int[] outputPixels = GameEngine.GetPixels();
		int[] tilePixels = sprite.getPixels();
		
		int s_height = sprite.getHeight()*SCALING;
		int s_width = sprite.getWidth()*SCALING;
		int s_height_half = s_height >> 1; // half sprite scaled height
		int s_width_half = s_width >> 1; // half sprite scaled width
	
		int normal_height = TILE_HEIGHT*SCALING;
		int normal_width = TILE_WIDTH*SCALING;
		int n_width_half = normal_width >> 1;
		int n_height_half = normal_height >> 1;
		
		
		/*For the sake of simplicity. Draw a cartesian graph and map the point (1,1) to its coordinate.*/
		/*- (this.isoWidth-1)*n_width_half ::: This helps in adding offsets for different kind of tile sizes*/
		int xPixel = yCord * n_width_half + xCord * n_width_half + xOffset - (this.isoWidth-1)*n_width_half;
		int yPixel = yCord * n_height_half - xCord * n_height_half + yOffset;
		
			
		for(int y = 0 ;y < s_height; y++) {
			int yy = y + yPixel;
			
			if(yy <= -s_height) break;
			if(yy < 0) continue; // speeding things up a bit.
			
			yy = yy - zPixels;
			
			/*Height boundaries checking*/
			if( yy >= GameEngine.GetHeight()) 
				break;
			 
			for(int x = 0 ; x < s_width; x++) {
				int col = tilePixels[ x/SCALING + (y/SCALING) * sprite.getWidth() ];
				int xx = x + xPixel; 
				
				/*width boundaries checking*/
				if ( xx >= GameEngine.GetWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0 )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				if(yy >= 0) 
					outputPixels[xx + yy * GameEngine.GetWidth()] = col; // rendering to the gamengien
				
				/*Rendering Left edge*/
				if( (zPixels > 0) && (x <= s_width_half) && (y >= s_height_half) &&  (y - s_height_half == x >> 1) ) {
					for(int z = 0 ; z < zPixels && (yy+z) < GameEngine.GetHeight(); z++) {
						if((yy+z) >= 0) 
							outputPixels[xx + (yy+z) * GameEngine.GetWidth()] = shade(col,0.3);
					}
				} 
				
				/*Rendering Right edge*/
				else if( (zPixels > 0) && (x >= s_width_half) && (y >= s_height_half) &&  (y - s_height_half == (s_width - x) >> 1) ) {	
					for(int z = 0 ; z < zPixels && (yy+z) < GameEngine.GetHeight(); z++) {
						if((yy+z) >= 0)  {
							outputPixels[xx + (yy+z) * GameEngine.GetWidth()]  = shade(col, 0.7);
							if((xx - 1) >= 0)
								outputPixels[(xx - 1) + (yy+z) * GameEngine.GetWidth()] = shade(col, 0.7);
						}
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
	
	
	
	
	
	//___________________________ Getters Area ___________________________________
	
	public boolean isSolid() {return this.solid;}
	public int getIsoWidth() { return this.isoWidth;}
	public int getIsoHeight() { return this.isoHeight;}

	
}
