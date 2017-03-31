package nl.han.ica.ibilinnor;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import processing.core.PVector;

public class Slime extends Enemy implements ICollidableWithTiles {
	static Sprite sprite=new Sprite("src/main/java/nl/han/ica/ibilinnor/media/slime/idle_animation.gif");
    private final World world;

	public Slime(World world,int x,int y) {
		super(sprite,1,x, y);
        this.world=world;
		setFriction(0.10f);
		setGravity(0.5f);
		
	}

	@Override
	public void update() {
	
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
