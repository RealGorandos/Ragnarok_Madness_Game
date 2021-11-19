package ragmad.entity;

import java.util.Random;

import ragmad.scenes.gamescene.GameScene;

/**
 * Entity in the game
 */
public abstract class Entity {
	public int x, y;
	public boolean remove = false;
	
	protected final Random random = new Random();
	
	public void update() {	}
	public void render(GameScene scene) {}
	public boolean isRemoved() {return remove;}
	public void remove() {remove = true;}
}
