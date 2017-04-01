package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import processing.core.PVector;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles,ICollidableWithGameObjects {
	
	protected int health;
	protected int damage;
	private boolean isAlive;
	
	public Enemy(Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		
		this.health=1;
		this.damage=1;
		this.isAlive = true;
		
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
