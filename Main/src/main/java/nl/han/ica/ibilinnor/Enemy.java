package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles,ICollidableWithGameObjects {
	
	protected int health;
	protected int damage;
	private boolean isAlive;
	protected World world;
	protected Sound hitSound;
	
	public Enemy(World world,Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		this.world=world;
		this.health=1;
		this.damage=1;
		this.isAlive = true;
		this.hitSound=new Sound(world,"src/main/java/nl/han/ica/ibilinnor/media/impactSplat.mp3");
		
	}

	@Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {

    }
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject co : collidedGameObjects) {
			if (co instanceof Attack) {
				hitSound.rewind();
				hitSound.play();
				world.deleteGameObject(this);
				setAlive(false);
				world.objective.addKill();
			}
		}

	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
