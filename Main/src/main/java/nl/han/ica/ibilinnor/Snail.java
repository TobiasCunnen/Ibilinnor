package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import processing.core.PVector;

public class Snail extends Enemy {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/Snail/idle.png");

	private World world;

	public Snail(World world) {
		super(sprite, 1);
		this.world = world;
		setFriction(0.10f);
		setGravity(0.5f);

	}

	@Override
	public void update() {

	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

		for (CollidedTile ct : collidedTiles) {
			if (ct.theTile instanceof GrassTile || ct.theTile instanceof GroundTile){
				
				if (ct.collisionSide == ct.TOP){

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);
					setY(vector.y - height);
				}
			}
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject co : collidedGameObjects) {
			if (co instanceof Attack) {
				world.deleteGameObject(this);
				setAlive(false);
			}
		}

	}
}
