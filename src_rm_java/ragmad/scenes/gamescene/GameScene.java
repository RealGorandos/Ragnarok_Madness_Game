package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.entity.characters.Player;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import java.lang.Math;
import java.util.HashMap;


public class GameScene implements Scene{

	HashMap<Integer, Tile> hashmap;
	public static double xOffset, yOffset;
	public static int SCALING = 1;  // Change it if you want to see different scalings. 
	int xCord;
	int yCord;
	int frameMovement;
	int m_width, m_height;
	private Map map;
	private Player player; ///--------------------------------><><><><<><><>
	//private Keyboard input;
	
	/// _________________________ Constructor Area_________________________________
	
//	public GameScene(int width, int height) {
//		this.m_height = height;
//		this.m_width = width;
//		xCord = 0; 
//		yCord = 0;
//		xOffset  = GameEngine.GetWidth()/2 ; 	//For testing change all offset variables to player.y
//		yOffset = (GameEngine.GetHeight()/2);		//For testing change all offset variables to player.y
//		this.map = new Map();
//		player = new Player(GameEngine.GetWidth()/2, GameEngine.GetHeight()/2);
//	}
	
	
//	public GameScene(int width, int height, String mapPath) {
//		this.m_height = height;
//		this.m_width = width;
//		xCord = 0;
//		yCord = 0;
//		xOffset  = GameEngine.GetWidth()/2 ; 	//For testing change all offset variables to player.y
//		yOffset = GameEngine.GetHeight()/2;		//For testing change all offset variables to player.y
//		hashmap=new HashMap<Integer, Tile>();
//		this.hashmap.put( 0xff0032ff,Tile.PORTAL1);
//		this.hashmap.put( 0xff8e4a4a,Tile.DESERT1);
//		this.map = new Map(mapPath,hashmap); //Our map loaded from a file
//
//		player = new Player(GameEngine.GetWidth()/2, GameEngine.GetHeight()/2);
//	}
	
	
	
	public GameScene(int width, int height, Map map, Player player) {
		this.m_height = height;
		this.m_width = width;
		xCord = 0;
		yCord = 0;
		xOffset = GameEngine.GetWidth()/2; 		//For testing change all offset variables to player.y
		yOffset = GameEngine.GetHeight()/2;		//For testing change all offset variables to player.y
		this.map = map;
		this.player = player;
	}
	
	
	
	///___________________________ GameEngine component methods area _________________________________
	
	
	
	
	/**
	 * Defines Game physics and scene logic
	 */
	@Override
	public void update() {
		frameMovement = 5;// (int)(5.0 *  (GameEngine.GetDelta())); /// <--- BUG: Delta Time is not set properly.
		player.update(frameMovement, this.map);

			/*if(Keyboard.isUp()) yOffset+=frameMovement;
			if(Keyboard.isDown()) yOffset-=frameMovement;
			if(Keyboard.isRight()) xOffset-=frameMovement;
			if(Keyboard.isLeft()) xOffset+=frameMovement;*/

		if (Keyboard.esc()) {
			GameEngine.ChangeScene("Menu");
		}

		int[] testing = this.map.getTileAt(Mouse.x,Mouse.y,(int)xOffset,(int) yOffset);
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
				int id = this.map.getMap()[x+y*map.getWidth()];
				Tile t = this.map.getTile(id);
				if(anchorExists(x, y, t, id)) {
					t.renderToRaster(x, y, (int)xOffset,(int) yOffset, SCALING);
				}

			}
		}
		player.render(1);
		//System.out.println("THe render is render");
		//Tile.PLAYER2.renderPlayer(0,0, xOffset, yOffset, SCALING);
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
	
	
	
	public static void zoomIn() { SCALING = (SCALING < 2 )? SCALING + 1 : 2 ;}
	public static void zoomOut() { SCALING = (SCALING > 1 )? SCALING - 1 : 1 ;}
	
	
	
}



//keyboard shortkey