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

/**
 * In de Character klasse worden alle acties afgehandeld die met de speelbare
 * personage te maken hebben. Bijvoorbeeld maakt deze een object van de klasse
 * Attack aan als de speler aanvalt.
 * 
 * @author Tobias
 *
 */
public class Character extends AnimatedSpriteObject
		implements ICollidableWithTiles, ICollidableWithGameObjects, IAlarmListener {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");

	private World world;
	private Attack attack;

	private ArrayList<Key> playerControl;

	private float jumpHeight;
	private char previousKey;
	private boolean allowJump;
	private char attackKey;

	public Character(World world) {

		super(sprite, 1);

		this.world = world;
		addGameObjectCharacter();
		attack = new Attack();

		playerControl = new ArrayList<>();
		playerControl.add(new Key('a'));
		playerControl.add(new Key('d'));
		playerControl.add(new Key(' '));
		playerControl.add(new Key('j'));
		playerControl.add(new Key('k'));

		this.jumpHeight = 110;
		this.allowJump = true;

		setFriction(0.3f);
		setGravity(2f);

	}

	@Override
	public void update() {
		updateOutOfBounds();
		keyAction();
		setAttackPosition();
	}

	/**
	 * relocates the Character when he goes out of bounds to make sure he stays
	 * in the picture
	 */
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

	/**
	 * decides which action to take after pressing a key
	 */
	private void keyAction() {
		for (Key key : playerControl) {
			if (key.isPressedKey()) {

				int speed = 6;
				if (key.getKey() == 'a') {
					setDirectionSpeed(270, speed);
				}
				if (key.getKey() == 'd') {
					setDirectionSpeed(90, speed);
				}
				if (key.getKey() == ' ' && allowJump) {
					setDirectionSpeed(0, jumpHeight);
					startAlarm("jump", 1);
					allowJump = false;
				}
				if (key.getKey() == 'j') {
					sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_animation.gif");
					addGameObjectAttack('j');

					startAlarm("attackAnimation", 0.5f);
				}
				if (key.getKey() == 'k') {
					sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_animation.gif");
					addGameObjectAttack('k');

					startAlarm("attackAnimation", 0.5f);
				}
			}
		}

	}
	//test

	/**
	 * Different spites are loaded when a key is pressed
	 * 
	 * @param key
	 */
	private void setSpriteAnimation(char key) {
		if (key != previousKey) {
			previousKey = key;
			if (key == 'a') {
				sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/walk_left_animation.gif");
			}
			if (key == 'd') {
				sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/walk_right_animation.gif");
			}
			if (key == ' ' && allowJump) {
				sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/jump.png");
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		setSpriteAnimation(key);
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
		if (key == previousKey) {
			previousKey = '1';
			sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");
		}
	}

	/**
	 * sets all pressedKey values on false
	 */
	private void realeaseAllKeys() {
		for (Key tempKey : playerControl) {
			tempKey.setPressedKey(false);
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
				realeaseAllKeys();

				Alarm alarm = new Alarm("respawn", 3);
				alarm.addTarget(this);
				alarm.start();
			}
		}
	}

	/**
	 * adds a game object which draws the attack animation in front of the
	 * player
	 * 
	 * @param key
	 */
	private void addGameObjectAttack(char key) {

		if (key == 'k') {
			world.addGameObject(attack, this.getX() + attack.getWidth(), this.getY());
			attackKey = key;
		}
		if (key == 'j') {
			world.addGameObject(attack, this.getX() - attack.getWidth(), this.getY());
			attackKey = key;
		}

	}

	/**
	 * removes the attack game object
	 */
	private void removeGameObject() {
		world.deleteGameObject(attack);
	}

	/**
	 * adds the player to the game object list
	 */
	private void addGameObjectCharacter() {
		world.addGameObject(this, 25, 560);
	}

	/**
	 * sets the position of the attack object relative to the characters
	 * position
	 */
	private void setAttackPosition() {
		if (attackKey == 'k') {
			attack.setX(this.getX() + attack.getWidth());
			attack.setY(this.getY());
		} else if (attackKey == 'j') {
			attack.setX(this.getX() - attack.getWidth());
			attack.setY(this.getY());
		}
	}

	/**
	 * makes an alarm so that we can set a timer on certain actions
	 * 
	 * @param name
	 * @param time
	 */
	private void startAlarm(String name, float time) {
		Alarm alarm = new Alarm(name, time);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "attackAnimation") {
			removeGameObject();
			sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/character/idle_animation.gif");
		}
		if (alarmName == "respawn") {
			addGameObjectCharacter();
		}
		if (alarmName == "jump") {
			allowJump = true;
		}
	}
}
