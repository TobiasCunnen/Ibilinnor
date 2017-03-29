package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Slime extends Enemy {
	static Sprite sprite=new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation2.gif");
	

	public Slime() {
		super(sprite,1,800,800);
		
	}
	
	//ripped van waterworld player
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
            sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/slime/walk_left_animation.gif");
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
        }
        if (key == ' ') {
        	setDirectionSpeed(0, 0);
        	sprite.setSprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation2.gif");
        } 
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
