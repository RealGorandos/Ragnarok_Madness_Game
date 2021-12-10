package ragmad.entity.item;

import ragmad.GameEngine;
import ragmad.entity.Entity;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Tile;

public class Bullet extends Entity{ 
	private double angle_r;
	private Sprite sprite;
	private double speed;
	private double xCord , yCord;
	private double xStart , yStart;
	private boolean outOfRange;
	private double range;
	
	/*These defines how much x and y the bullet is moving (it can be deduced from the angle)*/
	private double yUnit, xUnit;
	
	
	/**
	 * Creates a bullet object (A projectile).
	 * @param xCord - Raster coordinates of where the bullet should start.
	 * @param yCord - Raster coordinates of where the bullet should start.
	 * @param angle_r - Angle of the projection.
	 * @param sprite - Bullet shape
	 * @param speed - Bullet speed
	 */
	public Bullet(double xCord, double yCord, double angle_r, Sprite sprite, double speed, double range) {
		this.xCord = xCord; 
		this.yCord = yCord;
		this.angle_r = angle_r;
		this.sprite = sprite;
		this.speed = speed;
		this.range = range;
		this.xStart = xCord;
		this.yStart = yCord;
		this.outOfRange = false;
		this.yUnit = Math.sin(angle_r);
		this.xUnit = Math.cos(angle_r);
		
		setRasterPosFromCord(-this.sprite.getWidth()/2, -this.sprite.getHeight()/2);
		
	}
	
	
	/**
	 * Updates the projectile movement
	 */
	public void update() {
		this.xCord += speed*xUnit;
		this.yCord += speed*yUnit;
		//System.out.println("Update projectile at: " + xCord + " " + yCord);
		this.outOfRange = ((xCord-xStart)*(xCord-xStart) + 
							(yCord-yStart)*(yCord-yStart) >= range*range)? true : false ;
		
		setRasterPosFromCord(this.sprite.getWidth()/2, this.sprite.getHeight()/2);
	}
	
	/**
	 * Renders the Projectile
	 */
	public void render() {
		int[] outputPixels = GameEngine.GetPixels();
		int scale = GameScene.SCALING;
		
		int yPixel = (int)(this.y - GameScene.yOffset);
		int xPixel = (int)(this.x - GameScene.xOffset);
		
		for(int y = 0; y < this.sprite.getHeight()*scale; y++) {
			int yy = (int)( y - yPixel);
			for(int x = 0; x < this.sprite.getWidth()*scale; x++) {
				int xx = (int)( x - xPixel);
				int col = this.sprite.getPixels()[ (x/scale) + (y/scale) * this.sprite.getWidth()];
				
				if(0 <= xx && xx < GameEngine.GetWidth() && 0 <= yy && yy < GameEngine.GetHeight() && (col & 0xff000000) != 0 ) {
					outputPixels[xx + yy*GameEngine.GetWidth()] = col;
				}
			}
		}
	}
	
	
	 
	

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
		
		this.x = -(xCord + yCord) * n_width_half + xOffset; //this.curSprite.getWidth()/2;
		this.y  = (xCord - yCord) * n_height_half + yOffset; //this.curSprite.getHeight()/3;
	}
	
	public boolean isOutRange() {return this.outOfRange;}
	
	public double getXCord() {return this.xCord;}
	public double getYCord() {return this.yCord;}
	
	
}
