package ragmad.scenes.gamescene;

import ragmad.graphics.sprite.*;



/**
 * Not being used at the moment.. Needs implementation :/
 * @author Mohido
 *
 */
public class Tile {
	private int xCord, yCord, zCord; // xCord,yCord are in coordinates while zCord are pixels bias
	private Sprite sprite;
	
	public Tile(int xCord, int yCord, int zHeight, Sprite sprite){
		this.xCord = xCord;
		this.yCord = yCord;
		this.zCord = zHeight;
		this.sprite = sprite;
	}
	
	
	
}
