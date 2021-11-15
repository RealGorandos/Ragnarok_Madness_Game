package ragmad.entity.characters;

import ragmad.scenes.gamescene.Map;

import java.util.HashMap;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Tile;

public class Player extends Characters {
	
	
	private Sprite curSprite;
	private int anim = 0;
	private boolean isWalking = false;
	
	int animationRows, animationCols;
	private Sprite[] animationSprites;
	private HashMap<Direction, Integer> spriteMap;
	private int currentAnimationCol;
	
	
//	public Player() {
//		this.x = -GameEngine.GetWidth()/2;
//		this.y = -GameEngine.GetHeight()/2;	
//	}
	
	
	
	public Player(int x, int y) {
//		this.x = -GameEngine.GetWidth()/2 - x; // x is input as Positive for moving to the right and negative to the left
//		this.y = -GameEngine.GetHeight()/2 - y;	 
		
		this.x = -x;
		this.y = -y;
		
//		this.x = x;
//		this.y = y;
		curSprite = null;
		//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_BACK_1, false);
	}
	
	
	
	/**
	 * A more profissional and dynamic Player class. It can create a player and defines its animation sprites, and directions/animation mapping.
	 * @param x - This is the X position of the player.	Positive X means move the player Right from the top-left of the screen.
	 * @param y - This is the Y position of the player. Positive Y means move the player Downwards from the top-left of the screen
	 * @param animationSprites - This is an array that contains all the Sprites that the player going to use. Define the Sprites in a contigoues order. E.g: Up sprites, then Down direction sprites and so on. Note: Sprites number per direction must be same. 
	 * @param animationTypes - This is the row counts of the animation sprites. In other words, how many animations types do we have. E.g: Up,Right,Left,Down are 4 animation types.
	 * @param animationsPerType - This is the count of animations we do have per each direction. E.G: 'animationsPerType = 4' means that we have 4 different animations for walking on a specific direction. Note that all directions will have the same count of animations.
	 * @param spriteMap - This is a map that maps a Direction to a sprite Row (Animation Type). Note that it relies that the animationSprites contiguous sprites. E.G: UP -> animationType3 (in other words, Row index 3 from the animation Sprites). 
	 */
	public Player(int x, int y, Sprite[] animationSprites, int animationTypes, int animationsPerType,  HashMap<Direction, Integer> spriteMap) {
		this.x = -x; 
		this.y = -y;
		this.currentAnimationCol = 0;
		this.spriteMap = spriteMap;
		this.animationSprites = animationSprites;
		this.animationCols = animationsPerType;
		this.animationRows = animationTypes;
		this.curSprite = animationSprites[0];
	}
	
	
	
	
	/**
	 * A methode which updates the players object (It is thread)
	 * @frameMovement the movement of the character by pixels
	 * @Map the world map
	 * @colorMap hashmap which returns tile by color
	 * */
	public void update(int frameMovement, Map map) {

		anim = (anim+1) & 7; 	// same as anim % 32. But much faster. This here is just an update counter really!
		if(anim == 0) {
			this.currentAnimationCol = (currentAnimationCol + 1) % this.animationCols ; //& (this.animationCols - 1);
		
		}
			
		
		 
		
		int xOffset = 0 ,  yOffset = 0;
		if(Keyboard.isUp()) yOffset+=frameMovement;
		if(Keyboard.isDown()) yOffset-=frameMovement;
		if(Keyboard.isRight()) xOffset-=frameMovement;
		if(Keyboard.isLeft()) xOffset+=frameMovement;
		
		
		if(xOffset != 0 || yOffset != 0) { 
			move(xOffset, yOffset, map, map.getColorMap(), curSprite);
			isWalking = true;
			int a_r = this.spriteMap.get(direction); // Get the row of the animation sprite we will be animating. 
			int a_c = this.currentAnimationCol;
			this.curSprite = this.animationSprites[a_c + a_r * this.animationCols];
		}else {
			isWalking = false;
		}
	}
	
	
	
	
	
	
	/**
	 * Method that renders the player on the screen
	 * 
	 * @SCALING the scaling rate of the player
	 * */
	public void render(int SCALING) {
		int[] outputPixels = GameEngine.GetPixels();
		int[] tilePixels = curSprite.getPixels();
		
		int s_height =( curSprite.getHeight()*SCALING);
		int s_width = (curSprite.getWidth()*SCALING);

		
		
		int xPixel = x;
		int yPixel = y;
		
		
		//System.out.println(xPixel + ", " + yPixel);
		for(int y = 0 ; y < s_height; y++) {
			int yy = y - yPixel;   //Mapping coordinates space to the GameEngine pixel Space (Raster space) //yOffset for vertical movement
			if( yy >= GameEngine.GetHeight()) break;
			if(yy < -s_height) break;
			if(yy < 0) continue;
			for(int x = 0 ; x < s_width; x++) {
				int xx = x - xPixel;
				int col = tilePixels[x/SCALING + (y/SCALING) * curSprite.getWidth()]; // getting the pixel colour of the tile
				
				if ( xx >= GameEngine.GetWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0 )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				if(col != 0xffd6e7ea)outputPixels[xx + yy * GameEngine.GetWidth()] = col;
			}
		}


	}
}
