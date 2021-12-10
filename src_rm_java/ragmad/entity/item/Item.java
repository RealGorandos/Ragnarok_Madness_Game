package ragmad.entity.item;

import ragmad.entity.Entity;

/**
 * Item abstract class is an abstract class where all items should inherit from. Note that it does not have a shape (Sprite because some items do not have a shape when they are used.)
 * @author Mohido
 *
 */
public abstract class Item extends Entity{ 
	protected String itemName;
	
	/**
	 * Defines the usage of an item over an Entity (Scene objects).
	 * @param en - entity.
	 * @return - If the item was successfully used.
	 */
	protected boolean usage(Entity en) {return false;}
	 
	public void update() {}
	
	public void render() {}
	
	
	public String toString() {
		return itemName;
	}
	
}
