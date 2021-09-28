package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import java.lang.Math;


public class GameScene implements Scene{
	
	
	int xOffset, yOffset;
	public final static int SCALING = 1;  // Change it if you want to see different scalings. 
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
	
	
	///___________________________ GameEngine component methods area ______________
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
				if(this.map.getMap()[x+y*map.getWidth()] == 0xff5d3030)
					Tile.DESERT2.renderToRaster(x, y, xOffset, yOffset, SCALING);
				else 
					Tile.DESERT1.renderToRaster(x, y, xOffset, yOffset, SCALING);
			}
		}
	}
	
}



