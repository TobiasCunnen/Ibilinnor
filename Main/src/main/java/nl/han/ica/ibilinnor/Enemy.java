package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {
	
	protected int health;
	protected int damage;
	
	public Enemy(Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		this.health=1;
		this.damage=1;
		
	}

	public void walkLeft(){
		
	}
	
	public void walkRight(){
		
	}
	
	public void die(){
		
	}
	
	public void takeDamage(){
		
	}
	

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {

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
