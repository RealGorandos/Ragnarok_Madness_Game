package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import java.lang.Math;


public class GameScene implements Scene{
	
	
	int xOffset, yOffset;
	public final static int SCALING = 2;  // Change it if you want to see different scalings. 
	int xCord;
	int yCord;
	int frameMovement;
	int m_width, m_height;
	private Map map;
	
	
	/// _________________________ Constructor Area_________________________________
	
	public GameScene(int width, int height) {
		this.m_height = height;
		this.m_width = width;
		xCord = 0;
		yCord = 0;
		xOffset = 0;	//The camera shifting ratio at x
		yOffset = 0;	//The camera shifting ratio at y
		this.map = new Map();
	}
	
	
	public GameScene(int width, int height, String mapPath) {
		this.m_height = height;
		this.m_width = width;
		xCord = 0;
		yCord = 0;
		xOffset = 0;	//The camera shifting ratio at x
		yOffset = 0;	//The camera shifting ratio at y
		this.map = new Map(mapPath); //Our map loaded from a file
	}
	
	
	
	///___________________________ GameEngine component methods area _________________________________
	
	
	
	
	/**
	 * Defines Game physics and scene logic
	 */
	@Override
	public void update() {
		frameMovement = 5;// (int)(5.0 *  (GameEngine.GetDelta())); /// <--- BUG: Delta Time is not set properly.
		
		if(Keyboard.isUp()) {yOffset += frameMovement;}
		if(Keyboard.isDown()) {yOffset -= frameMovement;}
		if(Keyboard.isRight()) {xOffset-= frameMovement;}
		if(Keyboard.isLeft()) {xOffset += frameMovement;}
		
		int[] testing = this.map.getTileAt(Mouse.x,Mouse.y,xOffset, yOffset);
		if(testing == null) return;
		
	}

	
	
	
	/**
	 * Takes care of rendering ot the GameEngine class
	 * 
	 * Developing Note: Speeding things up can be by getting the first XTile in the current raster space and first YTile.
	 * 			This can be done when working with mouse input. The sources are in the Map() class.
	 */
	@Override
	public void render() {
		for(int x = this.map.getWidth() - 1; x >= 0 ; x--) {
			for(int y = 0 ; y < this.map.getHeight(); y++) {
				
				/*THIS WHOLE MESS SHOULD BE CLEANED!!!*/
				switch(this.map.getMap()[x+y*map.getWidth()] ) {
				case 0xff0032ff:
					if(anchorExists(x,y,Tile.PORTAL1, 0xff0032ff)) Tile.PORTAL1.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				case 0xff5d3030:
					//GameEngine.FULL_WALL.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				case 0xff612929: // First left
					//Tile.WALL1.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				case 0xff7f7f7f:
					//Tile.WALL2.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				case 0xff414040:
					//Tile.WALL3.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				case 0xff000000:
					//Tile.WALL4.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				default:
					Tile.DESERT1.renderToRaster(x, y, xOffset, yOffset, SCALING);
					break;
				}
					
			}
		}
	}
	
	
	
	/**
	 * This is a simple rendering algorithm that I created. We render big sprites from top down, but before we start rendering, we check if the base of the tile (meant to be rendered) exists.
	 * I call the base "Anchor" (Just trying to be cool :) )!
	 * @param x, y - Coordinates of the tile head on the map
	 * @param t - The tile that is meant to be rendered.
	 * @param id - The colour id of the tile (Base colour should be equal to head colour => Tile base and head exists => We can start rendering :))) )
	 * */
	private boolean anchorExists(int x, int y, Tile t, int id) {
		 return this.map.getMap()[ ( x - (t.getIsoWidth() - 1)) + (y + (t.getIsoHeight() - 1)) *map.getWidth()] == id;
	}
}



