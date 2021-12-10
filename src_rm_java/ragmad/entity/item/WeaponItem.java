package ragmad.entity.item;

import java.util.ArrayList;

import ragmad.GameEngine;
import ragmad.entity.Collider;
import ragmad.entity.Entity;
import ragmad.entity.characters.Characters;
import ragmad.entity.characters.Player;
import ragmad.entity.characters.npc.Foe;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;

public class WeaponItem extends Item{
	private GameScene scene;
	private int damage;	// Strength of the item. Defines the damage dealt to the oponent.
	private Sprite sprite;
	private ArrayList<Bullet> firedBullets;
	private double bulletSpeed;
	private double range; // isometric range of shooting.
	private double explosionRange;
	private Characters holder;
	
	/**
	 * Creates a weapon item. A weapon item is an item that can be equiped by the player as a main weapon. This kind of weapon is capable of shooting bullets.
	 * @param itemName - The item name
	 * @param strength - Strength of the item (Damage dealt by the item)
	 * @param shape - Item shape (Sprite/Image that defines the item bullets)
	 * @param speed - Speed of the item bullets.
	 */
	public WeaponItem(String itemName, int strength, Sprite shape, double speed , double range, double explosionRange) {
		this.itemName = itemName;
		this.damage = strength; 
		this.sprite = shape;
		this.range = range;
		this.bulletSpeed = speed;
		this.firedBullets = new ArrayList();
		this.explosionRange = explosionRange;
	}
	

	 
	/**
	 * Shoots a bullet toward the given angle.
	 * @param angle_r - Angle of the shooting.
	 * @param xCordStart - x isometric starting point.
	 * @param yCordStart - y isometric starting point.
	 */
	public void shoot(double angle_r, double xCordStart, double yCordStart) {
		this.firedBullets.add(new Bullet(xCordStart, yCordStart, angle_r, this.sprite, bulletSpeed, this.range));
	}
	
	
	/**
	 * Defines the usage of an item over an Entity (Scene objects).
	 * @param en - entity.
	 * @return - If the item was successfully used.
	 */
	public boolean usage(Entity en) {
		if(en instanceof Characters) {
			((Characters)en).hit(damage);
			return true;
		}
		return false;
	}
	
	/**
	 * Update all the bullets that were fired by this gun.
	 */
	public void update() {
		
		/*Moving the bullets*/
		for(int i = 0; i < firedBullets.size(); i++) {
			this.firedBullets.get(i).update();			
		}
		
		/*Check for collision and out of range projectiles*/
		int i = 0;
		while(i < firedBullets.size()) {
			Collider c = this.scene.sphereCollider(firedBullets.get(i).getXCord(), firedBullets.get(i).getYCord(), this.explosionRange);
			
			boolean selfCollision = false;
			if(c.collider != null) {
				selfCollision = (c.collider instanceof Player && this.holder instanceof Player) || 
						(c.collider instanceof Foe && this.holder instanceof Foe);
			}
			
			
			if( (!selfCollision && c.collided) || firedBullets.get(i).isOutRange()) {
				if(c.collider != null) {
					this.usage(c.collider);
				}
				this.firedBullets.remove(i);
			}else {
				i++;
			}	
		}
	}
	
	
	
	
	/**
	 * Renders all the bullets that were fired by this gun.
	 */
	public void render() {
		for(int i = 0; i < firedBullets.size(); i++) {
			this.firedBullets.get(i).render();
		}
	}

	
	public void setHolder(Characters ca) {this.holder = ca;}
	public void setScene(GameScene scene) {this.scene = scene;}
	
	
	
}
