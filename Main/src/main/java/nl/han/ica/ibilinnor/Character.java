package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import nl.han.ica.ibilinnor.tiles.SecretTile;
import processing.core.PVector;

public class Character extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects, IAlarmListener{

	World world;
	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");
	float jumpHeight;
	Attack attack;

	public Character(World world, int x, int y) {
		super(sprite, 1);
		this.world = world;
		jumpHeight = 30;
		setX(x);
		setY(y);
		setFriction(0.10f);
		setGravity(0.5f);
		attack = new Attack(world);
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}

		if (getY() <= 0) {
			setySpeed(0);
			setY(0);
		}
		if (getX() >= world.getWidth() - getWidth()) {
			setxSpeed(0);
			setX(world.getWidth() - getWidth());
		}
		if (getY() >= world.getHeight() - getHeight()) {
			setySpeed(0);
			setY(world.getHeight() - getHeight());
		}

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
			addGameObject();
			
			Alarm alarm = new Alarm("hoi", 0.5);
			alarm.addTarget(this);

			alarm.start();
	
		}

	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

		for (CollidedTile ct : collidedTiles) {
			if (ct.theTile instanceof GrassTile || ct.theTile instanceof GroundTile) {

				if (ct.collisionSide == ct.TOP) {

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);
					setY(vector.y - height);
				}

				if (ct.collisionSide == ct.LEFT) {

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);
					setX(vector.x - width);
				}

				if (ct.collisionSide == ct.RIGHT) {

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);
					setX(vector.x + world.getTileMap().getTileSize());

				}
			}

			if (ct.theTile instanceof GroundTile) {
				if (ct.collisionSide == ct.BOTTOM) {

					vector = world.getTileMap().getTilePixelLocation(ct.theTile);
					setY(vector.y + height + 10);
				}
			}
			if (ct.theTile instanceof SecretTile) {
				if (ct.collisionSide == ct.INSIDE) {
					Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/secretTile.png");
					ct.theTile.setSprite(sprite);
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

	public void addGameObject() {

		world.addGameObject(attack, this.getX() + attack.getWidth(), this.getY());

	}

	public void removeGameObject() {
		world.deleteGameObject(attack);
	}

	@Override
	public void triggerAlarm(String alarmName) {
		removeGameObject();
		sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");
		
	}
}
