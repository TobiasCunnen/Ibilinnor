package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

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
		this.vX=3;
		this.vY=3;
		
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
