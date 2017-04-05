package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Een van de soorten vijanden die de klasse Enemy gebruikt als basis. In deze
 * klasse wordt de sprite van de vijand Slime bewaard.
 * 
 * @author Joep
 *
 */
public class Slime extends Enemy {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");

	public Slime(World world) {
		super(world, sprite, 1);
		this.world = world;
		setFriction(0.10f);
		setGravity(0.5f);

	}

	@Override
	public void update() {

	}
}
