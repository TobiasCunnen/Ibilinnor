package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Attack extends AnimatedSpriteObject implements ICollidableWithGameObjects {

	private World world;
	
	public Attack(World world) {
		super(new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_effect_animation.gif"), 1);
		
		this.world=world;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject co : collidedGameObjects) {
			if (co instanceof Enemy) {
				world.deleteGameObject(co);
			}
		}
		
	}

}
