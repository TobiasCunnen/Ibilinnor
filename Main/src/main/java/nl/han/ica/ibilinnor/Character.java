package nl.han.ica.ibilinnor;

import java.util.ArrayList;
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

public class Character extends AnimatedSpriteObject
		implements ICollidableWithTiles, ICollidableWithGameObjects, IAlarmListener {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");

	private World world;
	private Attack attack;

	private ArrayList<Key> playerControl;

	float jumpHeight;

	public Character(World world) {

		super(sprite, 1);

		this.world = world;
		attack = new Attack(world);

		playerControl = new ArrayList<>();

		playerControl.add(new Key('a'));
		playerControl.add(new Key('d'));
		playerControl.add(new Key(' '));
		playerControl.add(new Key('j'));

		jumpHeight = 30;

		setFriction(0.10f);
		setGravity(0.5f);

	}

	@Override
	public void update() {
		updateOutOfBounds();
		keyAction();
	}

	private void updateOutOfBounds() {
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

	private void keyAction() {
		for (Key key : playerControl) {
			if (key.isPressedKey()) {

				int speed = 4;
				if (key.getKey() == 'a') {
					setDirectionSpeed(270, speed);
					setCurrentFrameIndex(0);

				}
				if (key.getKey() == 'd') {
					setDirectionSpeed(90, speed);
					setCurrentFrameIndex(0);
				}
				if (key.getKey() == ' ') {
					setDirectionSpeed(0, jumpHeight);
				}
				if (key.getKey() == 'j') {
					sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_animation.gif");
					addGameObject();

					Alarm alarm = new Alarm("hoi", 0.5);
					alarm.addTarget(this);

					alarm.start();
				}
			}
		}

	}

	@Override
	public void keyPressed(int keyCode, char key) {

		for (Key tempKey : playerControl) {
			if (key == tempKey.getKey()) {
				tempKey.setPressedKey(true);
			}
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		for (Key tempKey : playerControl) {
			if (key == tempKey.getKey()) {
				tempKey.setPressedKey(false);
			}
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
