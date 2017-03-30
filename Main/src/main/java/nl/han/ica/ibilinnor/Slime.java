package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import nl.han.ica.waterworld.WaterWorld;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PVector;

public class Slime extends Enemy implements ICollidableWithTiles {
	static Sprite sprite=new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");
    private final World world;

	public Slime(World world) {
		super(sprite,1,20, 550);
        this.world=world;
		setFriction(0.10f);
		
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
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
        	if (ct.theTile instanceof GrassTile || ct.theTile instanceof GroundTile)

            {

                if (ct.collisionSide == ct.TOP)

                {

                    vector = world.getTileMap().getTilePixelLocation(ct.theTile);

                    setY(vector.y - height);

                }

                if (ct.collisionSide == ct.LEFT)

                {

                    vector = world.getTileMap().getTilePixelLocation(ct.theTile);

                    setX(vector.x - width);

                }

                if (ct.collisionSide == ct.RIGHT)

                {

                    vector = world.getTileMap().getTilePixelLocation(ct.theTile);

                    setX(vector.x + world.getTileMap().getTileSize());

                }

            }
        }
    }
}
