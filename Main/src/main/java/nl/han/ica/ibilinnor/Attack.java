package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Attack is een klasse die gebruikt wordt om vijanden te elimineren.
 * 
 * @author Tobias
 *
 */
public class Attack extends AnimatedSpriteObject implements ICollidableWithGameObjects {

	public Attack() {
		super(new Sprite("src/main/java/nl/han/ica/ibilinnor/media/character/attack_effect_animation.gif"), 1);
	}

	@Override
	public void update() {

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
	}

}
