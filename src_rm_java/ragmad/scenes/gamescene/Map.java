package ragmad.scenes.gamescene;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;


/**
 * Stores a grid of pixels which identifies our Map. Our world basically.
 * @author Mohido
 *
 */
public class Map {
	private int[] map;
	private int width,height;
	private String path;
	HashMap<Integer, Tile> hashmap;

	//map is given in the constructor
	public Map(String path,HashMap hm) {
		this.path = path;
		try {
			BufferedImage mapImage = ImageIO.read(new File(path)); 	// Loading an image to the memory
			this.width = mapImage.getWidth();
			this.height = mapImage.getHeight();
			this.hashmap=hm;
			map = new int [this.width*this.height];
			mapImage.getRGB(0, 0, this.width, this.height, map, 0, this.width); 	//Copies the loaded image pixels to our pixels-grid
			
		} catch (Exception e) {
			System.out.println("Error with loading the image from the path: " + path );
			e.printStackTrace();
		}
	}

	//method which receives an int i.e color, return tile type
	public Tile getTile(Integer color){
		return this.hashmap.get(color);
	}
	public Map() {
		this.width = 0;
		this.height = 0;
		map = new int[0];
	}
	
	
	
	/// ________________________________ METHDOS AREA ___________________________
	
	
	
	/**
	 * Get the exact tile from raster coordinate system.
	 * Pre-requisites: Tiles map.
	 * Ported From:
	 * 		Source: https://github.com/jorgt/perlin-landscape/pull/1/commits/9a8504043d2c02df507a6b1b3be794aee801b5ad
	 * 		Source: https://stackoverflow.com/questions/21842814/mouse-position-to-isometric-tile-including-height
	*/
	public int[] getTileAt(int mouseX,int mouseY, int xOffset, int yOffset) {
        //	half width, height... MUST BE SUBSTITUTED PROPERLY
        var tileWidth = (Sprite.DESERT_TILE_1.getWidth() * GameScene.SCALING) >> 1; 
        var tileHeight = (Sprite.DESERT_TILE_1.getHeight() * GameScene.SCALING) >> 1;
        
        
        // TOP LEFT CORNER
        var firstTileXShiftAtScreen = xOffset;
        var firstTileYShiftAtScreenAt0Height = tileHeight + yOffset;

        var columnNo = getColumn(mouseX, firstTileXShiftAtScreen, tileWidth);

        var tilesInColumn = getTilesInColumn(columnNo);
        
        //System.out.println("__________________________________");
        int[] exactTile = findExactTile(mouseX, mouseY, tilesInColumn, firstTileXShiftAtScreen, firstTileYShiftAtScreenAt0Height, tileWidth,tileHeight);
        //System.out.println("__________________________________");
        
        
        if(exactTile == null) return null;
        return exactTile;
    }
	
	
	
	
	/**
	 * Iterate through the object map matrix and get all the tiles in that column. Specifies as well if the tile side that is in the column is the left or right part
	 * @param columnNo - the column that you want to get the tiles of. Column is half width of a tile, so it can split the tile into left and right.
	 * @return - returns an array with the tiles in this pattern [tile1.x, tile1.y, tile1.isLeft ...]
	 */
	public int[] getTilesInColumn(int columnNo) {
	  int startTileX = 0, startTileY = 0;
	  boolean xShift = true;
	  
	  for (int i = 0; i < columnNo; i++) {
	    if (tileExists(startTileX + 1, startTileY)) {
	      startTileX++;
	    } else {
	      if (xShift) {
	        xShift = false;
	      } else {
	        startTileY++;
	      }
	    }
	  }
	  
	  ArrayList<Integer> tilesInColumn = new ArrayList<Integer>();
	  while(tileExists(startTileX, startTileY)) {
	    tilesInColumn.add(startTileX);
	    tilesInColumn.add(startTileY);
	    int s = (xShift)? 1 : 0;
	    tilesInColumn.add(s);
	    
	    
	    if (xShift) {
	      startTileX--;
	    } else {
	      startTileY++;
	    }
	    xShift = !xShift;
	  }
	  int[] arr = tilesInColumn.stream().mapToInt(i -> i).toArray();
	  
	  return arr;
	}

	
	

	/**
	 * Find the exact tile in which the mouse is hovering on.
	 * @param mouseX - The mouseX_to_raster in pixels
	 * @param mouseY - The mouseY_to_raster in pixels
	 * @param tilesInColumn - The column with its tiles. It should be an array looks like this [tile1.x, tile1.y, tile1.left, .......] for other tiles
	 * @param firstTileXShiftAtScreen - The first tile pixel position with respect to the raster. (distance from first tile to the top-left edge of the screen)
	 * @param firstTileYShiftAtScreenAt0Height - The first tile pixel position with respect to the raster. (distance from first tile to the top-left edge of the screen)
	 * @param tileWidth - half the width of the tile
	 * @param tileHeight - half the height of the tile
	 * @return [tile.x, tile.y] on the map. Tile can be accessed by these coordinates.
	 */
	public int[] findExactTile(int mouseX,int mouseY,int[] tilesInColumn, 
			int firstTileXShiftAtScreen,int firstTileYShiftAtScreenAt0Height,
	                       int tileWidth,int tileHeight) {	
		
	    for(var i = tilesInColumn.length-3; i >= 0; i -= 3) {
	        int tileInfoX = tilesInColumn[i];
	        int tileInfoY = tilesInColumn[i + 1];
	        int f = tilesInColumn[i+2];
	        boolean isLeft = (tilesInColumn[i + 2] == 1)? true: false;
	        
	        /*Line equation y=x*a + b */
	        double a =   (double)tileHeight / (double)tileWidth;	 // top left or right line of the tile
			if(isLeft) a = -a;
			int b = (isLeft)? tileInfoY * tileHeight * 2 : -(tileInfoX + 1) * tileHeight * 2; // The bias (just tileheight of that specific tile * its position)
	        
			/* mouse y with the center of the first tile (0,0) and mouseX centered on tile (0,0)*changeRatio + tileHeight*/
	        if ((mouseY - firstTileYShiftAtScreenAt0Height) > ((mouseX - firstTileXShiftAtScreen)*a + b)) {
	        	int[] ret = new int[2];
	        	ret[0] = tileInfoX;
	        	ret[1] = tileInfoY;	        	
	            return ret;
	        }
	    }
	    return null;
	}
	
	
	

	/// ________________________________ GETTERS AREA ___________________________
	public boolean tileExists(int x, int y) {return x >= 0 & y >= 0 & x < width & y < height;}
	public int getTileYIncrementByTileZ(int tileZ) {return 0;} // Might be needed in the getTileAt() function
	public int[] getMap() {return this.map;}
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	
	/**
	 * Get the column in which the mouse is currently in. each half a tile is a column
	 * @param mouseX - the Mouse x position
	 * @param firstTileXShiftAtScreen - First tile pixel with the respect to the shift. E.g: it can be -130 pixels from the screen 
	 * @param columnWidth - the column width which is half of the tile
	 * @return - The column number
	 */
	public int getColumn(int mouseX, int firstTileXShiftAtScreen, int columnWidth) {
		return (mouseX - firstTileXShiftAtScreen) / columnWidth;
	}
}












