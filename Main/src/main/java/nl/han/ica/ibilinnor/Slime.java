package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import processing.core.PVector;

public class Slime extends Enemy {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");

	public Slime(World world) {
		super(world,sprite, 1);
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
}
