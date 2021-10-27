package ragmad.entity.characters;


import java.util.HashMap;

import ragmad.GameEngine;
import ragmad.entity.Entity;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;

public abstract class Characters extends Entity {
	protected Sprite sprites;
	protected int direction = 0;
	protected boolean isMoving = false;	
	/**
	 * Takes care if the movement of the character.
	 * @dirX the offset which will update the x motion
	 * @dirY the offset which will update the x motion
	 * @colorsMap A hash map of colors which returns tile
	 * @sprites sprites object which update the sprite of the player
	 * */
	public void move(int dirX, int dirY, Map map, HashMap<Integer, Tile> colorsMap, Sprite sprites) {
		this.sprites = sprites;
		
		if(dirX > 0) direction = 1;
		if(dirY > 0) direction = 2;
		if(dirX < 0) direction = 3;
		if(dirY < 0) direction = 0;
		if(dirX > 0 && dirY > 0) direction = 4;
		if(dirX < 0 && dirY > 0) direction = 5;
		if(dirX < 0 && dirY < 0) direction = 6;
		if(dirX > 0 && dirY < 0) direction = 7;
		

		double temp = Math.sqrt(dirX*dirX + dirY*dirY);
		
		double modifiedDirX = (3 * dirX/temp);
		
		double modifiedDirY = (3* dirY/temp);
		
		if(!collision( 0,  modifiedDirY, map, colorsMap)) {
			
			GameScene.yOffset += modifiedDirY ;
		}
		
		if(!collision( 2 * modifiedDirX,  0, map, colorsMap)) {
			GameScene.xOffset += modifiedDirX;
		}

	
	}
public void update() {}
	/**
	 * Method which returns if a tile is collidable or not
	 * @dirX the pixels which need to be checked before moving on x-axis
	 * @dirY the pixels which need to be checked before moving on y-axis
	 * @map it stores the worlds map
	 * @colorsMap A hash map of colors which returns tile
	 * */
private boolean collision(double dirX, double dirY, Map map, HashMap<Integer, Tile> colorsMap) {
		boolean solid = false;		
		int playerWidth = sprites.getWidth() ;
		int playerHeight = sprites.getHeight();

		int[] n = map.getTileAt(-x ,-y , (int)(GameScene.xOffset - (playerWidth/2) + dirX),(int) (GameScene.yOffset -  (playerHeight - 14)  + dirY));
		if(n != null) {
			if( map.tileExists(n[0], n[1])) {
				solid = solid || colorsMap.get(map.getMap()[(n[0])+ (n[1])*map.getWidth()]).isSolid();
				}
			}


		return solid;
		
		}
}
