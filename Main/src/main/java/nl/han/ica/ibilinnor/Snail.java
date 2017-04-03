package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Snail extends Enemy {

	static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/Snail/idle.png");

	public Snail(World world) {
		super(world,sprite, 1);
		this.world = world;
		setFriction(0.10f);
		setGravity(0.5f);
		

	}

	@Override
	public void update() {

	}
}
