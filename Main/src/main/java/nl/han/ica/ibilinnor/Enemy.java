package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {
	
	protected int health;
	protected int damage;
	protected Sprite sprite;
	protected int x;
	protected int y;
	protected int vX;
	protected int vY;
	
	public Enemy(Sprite sprite, int totalFrames, int x , int y) {
		super(sprite, totalFrames);
		this.x=x;
		this.y=y;
		this.health=1;
		this.damage=1;
		this.vX=30;
		this.vY=10;
	}

	public void walkLeft(){
		
	}
	
	public void walkRight(){
		
	}
	
	public void die(){
		
	}
	
	public void takeDamage(){
		
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
