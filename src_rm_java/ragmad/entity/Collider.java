package ragmad.entity;

public class Collider {
	
	public Entity collider; // if it collided with Entity or not
	public boolean collided; // it might collide with solid Tiles
	
	public Collider(Entity collider, boolean collided) {
		this.collided = collided;
		this.collider = collider;
	}
	
	
}
