package ragmad.entity.item;

import ragmad.GameEngine;
import ragmad.entity.Entity;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Tile;

/**
 * An encapsulation of the Item. This class represents the item that is shown on the map.
 * 		A player can collide with it. Then, press a button, such as 'E',  to equip it.
 * 
 * @author Mohido
 *
 */
public class ItemCapsule extends Entity {
	private Sprite sprite;		// Shape of the Item.
	private int xCord, yCord; 	// Map coordinates where the item resides at
	private Item item;			// Encapsulated item within the item capsule
	
	/**
	 * Create an Item capsule to be displayed on the scene. 
	 * @param x_cord - The X coordinate in the Isometric space related to the map. 
	 * @param y_cord - The y Coordinate in the Isometric space related to the map.
	 * @param sprite - The shape of the Capsule. It can be a treasure for example.
	 * @param item - The item that is within the capsule.
	 * 
	 * Note: Size of hte itemCapsule is Sprite related. It also accounts for the scaling. 
	 */
	public ItemCapsule(int x_cord, int y_cord, Sprite sprite, Item item){
		this.xCord = x_cord;
		this.yCord = y_cord;
		this.item = item;
		this.sprite = sprite;
		setRasterPosFromCord(-this.sprite.getWidth()/2, this.sprite.getHeight()/2);
	}
	
	
	/**
	 * Renders the capsule in the world.
	 */
	public void render() {
		int[] outputPixels = GameEngine.GetPixels();
		int scale = GameScene.SCALING;
		
		int yPixel = (int)(this.y - GameScene.yOffset);
		int xPixel = (int)(this.x - GameScene.xOffset);
		
		for(int y = 0; y < this.sprite.getHeight()*scale ; y++) {
			int yy = y - yPixel;
			for(int x = 0 ; x < this.sprite.getWidth()*scale ;x++) {
				int xx = x - xPixel;
				int col = this.sprite.getPixels()[x/scale + (y/scale) * this.sprite.getWidth()];
				if(0 <= xx && xx < GameEngine.GetWidth() && 0 <= yy && yy < GameEngine.GetHeight() && (col & 0xff000000) != 0 )
					outputPixels[xx + yy*GameEngine.GetWidth()] = col;
			}
		}
	}
	
//	/**
//	 * This is used for updating the raster position of the item capsule. It is better called whenever the Offset is changed in the gamescene. Or before rendering to set the raster coordinates
//	 * 
//	 * @param xOffset - X Camera Offset (It is also GameScene offset).
//	 * @param yOffset - Y Camera Offset (It is also GameScene offset).
//	 * @param tileWidth - normal tile width defined in the map.
//	 * @param tileHeight - normal tile height defined in the map.
//	 */
//	public void updateXY(int xOffset, int yOffset, int tileWidth, int tileHeight) {
//		int scale = GameScene.SCALING;
//		int n_width_half = (tileWidth*scale) >> 1;
//		int n_height_half = (tileHeight*scale) >> 1;
//
//		this.x = yCord * n_width_half + xCord * n_width_half + xOffset ; 
//		this.y = yCord * n_height_half - xCord * n_height_half + yOffset - n_height_half/2;
//	}
	
	
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
	
	
	/*=----------------------------Getters area---------------------------==*/
	/**
	 * Gets the xCordinates of the Item Capsule.
	 * @return - Isometric X coordinates (Corresponding to the map)
	 */
	public int getXCord () {return this.xCord;}
	
	/**
	 * Gets the yCordinates of the Item Capsule.
	 * @return - Isometric Y coordinates (Corresponding to the map)
	 */
	public int getYCord () {return this.yCord;}
	
	/**
	 * Gets the Item stored within the capsule
	 * @return - The item stored within the capsule
	 */
	public Item getItem() {return this.item;}
}








