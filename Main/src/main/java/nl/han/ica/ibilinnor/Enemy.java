package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {
	
	public Enemy(Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		// TODO Auto-generated constructor stub
	}

	protected int health;
	protected int damage;
	protected Sprite sprite;
	protected int x;
	protected int y;
	protected int vX;
	protected int vY;
	
	public void walkLeft(){
		
	}
	
	public void walkRight(){
		
	}
	
	public void die(){
		
	}
	
	public void takeDamage(){
		
	}

}
