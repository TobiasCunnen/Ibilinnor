package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import nl.han.ica.ibilinnor.tiles.SecretTile;
import processing.core.PApplet;

public class World extends GameEngine {
	
	private Slime slime;
	private Character player;

	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.ibilinnor.World"});

	}

	@Override
	public void setupGame(){
		setupWorld();
	}
	
	public void setupWorld() {

		int worldWidth = 1600;
		int worldHeight = 800;
		

		initializeTileMap();
		
		createObjects();
		
		createView(worldWidth, worldHeight);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
    private void createObjects() {
        slime=new Slime(this,600,600);
       // addGameObject(slime, slime.getX(), slime.getY()); 
        player = new Character(this,100,300);
        addGameObject(player,100,300);
    }


	private void initializeTileMap() {
		Sprite grassSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/grassTile.png");
		TileType<GrassTile> grassTileType = new TileType<>(GrassTile.class, grassSprite);
		Sprite groundSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/groundTile.png");
		TileType<GroundTile> groundTileType = new TileType<>(GroundTile.class, groundSprite);
		Sprite secretSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/groundTile.png");
		TileType<SecretTile> secretTileType = new TileType<>(SecretTile.class, secretSprite);

		TileType[] tileTypes = { grassTileType, groundTileType, secretTileType };
		int tileSize = 80;
		int tilesMap[][] = { 
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },
				{-1,-1,-1,-1,-1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0},
				{-1,-1,-1,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,1,1},
				{0,0,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,-1,-1,0,0,1,1,1,1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,1,1,1,1,1,1},
				{-1,-1,0,-1,-1,-1,-1,-1,-1,1,0,-1,1,-1,2,2,2,2,2,2},
				{-1,-1,-1,-1,0,-1,-1,-1,-1,2,2,-1,1,-1,2,2,2,2,2,2},
				{0,-1,-1,-1,1,-1,-1,-1,-1,2,2,-1,1,-1,2,2,2,2,2,2},
				{1,0,0,0,1,0,0,0,0,1,1,0,1,0,1,1,1,1,1,1}

				};
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

	private void createView(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/ibilinnor/media/background_168.png"));

		setView(view);
		size(screenWidth, screenHeight);
	}

}
