package ragmad.scenes.gamescene;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.Scene;


// Should it be a singelton class?

public class GameScene implements Scene{
	
	// Delete this line if a higher resolution sprites are added. It should be Tile-Dependent.
	// If it is not deleted in the future, it will be moved to the sprite class for a better independency
	public final static int SCALING = 2; 
	
	/**
	 * Defines Game physics and scene logic
	 */
	@Override
	public void update() {}

	
	/**
	 * Takes care of rendering ot the GameEngine class
	 */
	@Override
	public void render() {
		int[] outputPixels = GameEngine.getPixels();
		/// Expermenting:
		int[] tilePixels = Sprite.DESERT_TILE_1.getPixels();
		for(int y = 0 ;y < Sprite.DESERT_TILE_1.getHeight()*SCALING; y++) {
			for(int x = 0 ; x < Sprite.DESERT_TILE_1.getWidth()*SCALING; x++) {
				outputPixels[x + y * GameEngine.getWidth()] = tilePixels[x/SCALING + y/SCALING * Sprite.DESERT_TILE_1.getWidth()];
			}
		}
	}
	

}
