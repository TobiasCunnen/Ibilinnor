package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import processing.core.PGraphics;
import processing.core.PVector;

public class Character extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	World world;
	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");
	float jumpHeight;

	public Character(World world, int x, int y) {
		super(sprite, 1);
		this.world = world;
		jumpHeight = 30;
		setX(x);
		setY(y);
		setFriction(0.10f);
		setGravity(0.5f);
	}

	@Override
	public void update() {

	}

	@Override
	public void keyPressed(int keyCode, char key) {
		int speed = 4;
		if (keyCode == LEFT) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(0);

		}
		if (keyCode == UP) {
			setDirectionSpeed(0, speed);
		}
		if (keyCode == RIGHT) {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(0);

		}
		if (keyCode == DOWN) {
			setDirectionSpeed(180, speed);
			sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_effect_animation.gif");
		}
		if (key == ' ') {


			setDirectionSpeed(0, jumpHeight);
			

		}
		if (key == ENTER) {
			sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_animation.gif");
			
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

		for (CollidedTile ct : collidedTiles) {
			if (ct.theTile instanceof GrassTile || ct.theTile instanceof GroundTile)

			{

				if (ct.collisionSide == ct.TOP)

				{

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);

					setY(vector.y - height);

				}

				if (ct.collisionSide == ct.LEFT)

				{

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);

					setX(vector.x - width);

				}

				if (ct.collisionSide == ct.RIGHT)

				{

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);

					setX(vector.x + world.getTileMap().getTileSize());

				}

			}
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject co : collidedGameObjects) {
			if (co instanceof Enemy) {
				world.deleteGameObject(this);
			}
		}
	}

}
