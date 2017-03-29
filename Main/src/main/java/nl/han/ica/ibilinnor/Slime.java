package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Slime extends Enemy {

	public Slime() {
		super(new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif"),1,800,800);
		
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
