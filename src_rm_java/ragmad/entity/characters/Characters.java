package ragmad.entity.characters;


import java.util.HashMap;

import ragmad.GameEngine;
import ragmad.entity.Entity;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;

public abstract class Characters extends Entity {
	public boolean blocked;
	protected Direction direction;
	protected boolean isMoving = false;	
	protected double xCord, yCord;
	protected double speed;
	protected Map map;
	protected int health;
	protected GameScene scene;
	
	
	
	/**
	 * synchronizes the raster coordinates attached to the current entity with the its coordinate space
	 * @param xOffset - If an offset is applicable (offset to the result)
	 * @param yOffset - If an offset is applicable (offset to the result)
	 */
	protected void setRasterPosFromCord(int xOffset, int yOffset) {
		double normal_height =  Tile.TILE_HEIGHT*GameScene.SCALING;
		double normal_width = Tile.TILE_WIDTH*GameScene.SCALING;
		double n_width_half = normal_width / 2;
		double n_height_half = normal_height / 2;
		
		/*Foe pixel coordinates*/
		this.x = -(xCord + yCord) * n_width_half + xOffset; //this.curSprite.getWidth()/2;
		this.y  = (xCord - yCord) * n_height_half + yOffset; //this.curSprite.getHeight()/3;
	}
	
	
	/**
	 * Move the character (Entity) with the given directions. You need to normalized dirX and dirY before calling this funciton. 
	 * @param dirX - normalized x direction.
	 * @param dirY - normalized y direction.
	 */
	public void move(double dirX, double dirY){
		if(dirX < 0) direction = Direction.UP_RIGHT;
		if(dirY < 0) direction = Direction.DOWN_RIGHT;
		if(dirX > 0) direction = Direction.DOWN_LEFT;
		if(dirY > 0) direction = Direction.UP_LEFT;
		if(dirX > 0 && dirY < 0) direction = Direction.DOWN;
		if(dirX < 0 && dirY < 0) direction = Direction.RIGHT;
		if(dirX > 0 && dirY > 0) direction = Direction.LEFT;
		if(dirX < 0 && dirY > 0) direction = Direction.UP;
		
		this.xCord += this.speed*dirX;
		this.yCord += this.speed*dirY;
		
		/*Checking for collision*/
		boolean solid = false;
		boolean outbounds = (int)this.xCord < 0 || (int)this.xCord >= this.map.getWidth() || (int)this.yCord < 0 || (int)this.yCord >= this.map.getHeight();
		if( !outbounds) {
			int id = this.map.getMap()[(int)this.xCord + (int)this.yCord*this.map.getWidth()];
			solid = this.map.getTile(id).isSolid();
		} 
		
		/*Moving the Foe.*/
		if(!solid) {
			this.isMoving = true;
		}else {
			this.xCord -= this.speed*dirX;
			this.yCord -= this.speed*dirY;
		}
	}
	
	
	public double getX() {return this.x; }
	public double getY() {return this.y; }
	public double getXCord() {return this.xCord;}
	public double getYCord() {return this.yCord;}
	public Direction getDirection() {return direction;}
	public void setMap(Map map) { this.map = map;}
	public void setHealth(int health) { this.health = health;}
	
	public int getHealth() {return this.health;}
 
	public void hit(int damage) {
		System.out.println("Character hit with daamge: " + damage);
		this.health -= damage;
		this.health = Math.max(this.health, 0); // clamping
	}
	
}
