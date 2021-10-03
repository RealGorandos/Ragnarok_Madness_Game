package ragmad.entity.characters;


import ragmad.GameEngine;
import ragmad.entity.Entity;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;

public abstract class Characters extends Entity {
	protected Sprite sprites;
	protected int direction = 0;
	protected boolean isMoving = false;
	
	public void move(int dirX, int dirY, Map map) {
		if(dirX > 0) direction = 1;
		if(dirY > 0) direction = 2;
		if(dirX < 0) direction = 3;
		if(dirY < 0) direction = 0;
		if(dirX > 0 && dirY > 0) direction = 4;
		if(dirX < 0 && dirY > 0) direction = 5;
		if(dirX < 0 && dirY < 0) direction = 6;
		if(dirX > 0 && dirY < 0) direction = 7;
		collision(map);
	
	}
	public void update() {}
	
	private boolean collision(Map map) {
		boolean solid = false;
		int[] n = map.getTileAt(-x,-y, GameScene.xOffset, GameScene.yOffset);
		
		if(n == null) return false;
		
		System.out.println(n[0] + " , " +  n[1]);
		//if(map.getTileAt(0, 0, x, y).isSolid()) {solid = true;}
		return  solid;
		}
}
