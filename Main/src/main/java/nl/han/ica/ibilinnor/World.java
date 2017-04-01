package nl.han.ica.ibilinnor;

import java.util.ArrayList;

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
	
	private ArrayList<EnemySpawner> enemySpawn;
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
		
		player = new Character(this);
		enemySpawn = new ArrayList<>();
		enemySpawn.add(new EnemySpawner(this,new Slime(this),420,100));
		enemySpawn.add(new EnemySpawner(this,new Snail(this),1400,200));

		initializeTileMap();
		
		createObjects();
		
		createView(worldWidth, worldHeight);

	}

	@Override
	public void update() {
		//System.out.println(player.getX());
		//System.out.println(player.getY());

	}
	
    private void createObjects() {
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
		view.setBackground(loadImage("src/main/java/nl/han/ica/ibilinnor/media/background.png"));

		setView(view);
		size(screenWidth, screenHeight);
	}

}
