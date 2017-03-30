package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

public class Slime extends Enemy {
	static Sprite sprite=new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");
	

	public Slime(World world) {
		super(sprite,1,800,800);
		
	}
	
	//ripped van waterworld player
    @Override
    public void keyPressed(int keyCode, char key) {
        
        if (keyCode == LEFT) {
            setDirectionSpeed(270, super.vX);
            setCurrentFrameIndex(0);
            sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/slime/walk_left_animation.gif");
        }
        if (keyCode == UP) {
            setDirectionSpeed(0, super.vY);
        }
        if (keyCode == RIGHT) {
            setDirectionSpeed(90, super.vX);
            setCurrentFrameIndex(0);
        }
        if (keyCode == DOWN) {
            setDirectionSpeed(180, super.vY);
        }
        if (key == ' ') {
        	setDirectionSpeed(0, 0);
        	sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");
        } 
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
