package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Flag extends SpriteObject implements ICollidableWithGameObjects {

	private World world;
	private Firework firework;

	public Flag(World world) {
		super(new Sprite("src/main/java/nl/han/ica/ibilinnor/media/flag.png"));
		this.world = world;
		this.firework = new Firework();
		world.addGameObject(firework, 600, 10);
		firework.setVisible(false);
		this.setVisible(false);
	}

	/**
	 * checks if the objective has been completed so that the flag can be shown
	 */
	private void victory() {
		if (world.objective.checkVictory()) {
			firework.setVisible(true);
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject co : collidedGameObjects) {
			if (co instanceof Character) {
				this.setVisible(true);
				victory();
			}
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
