package nl.han.ica.ibilinnor;

import java.util.ArrayList;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import nl.han.ica.ibilinnor.tiles.GroundTile;
import nl.han.ica.ibilinnor.tiles.SecretTile;
import processing.core.PApplet;

public class World extends GameEngine {

	private Character player;
	private ArrayList<EnemySpawner> enemySpawn;
	private Flag flag;
	protected KillObjective objective;
	private TextObject text;
	private Sound backgroundSound;

	public static void main(String[] args) {
		PApplet.main(new String[] { "nl.han.ica.ibilinnor.World" });

	}

	@Override
	public void setupGame() {

		int worldWidth = 1600;
		int worldHeight = 800;

		player = new Character(this);

		enemySpawn = new ArrayList<>();
		enemySpawn.add(new EnemySpawner(this, new Slime(this), 1510, 81));
		enemySpawn.add(new EnemySpawner(this, new Slime(this), 530, 641));
		enemySpawn.add(new EnemySpawner(this, new Slime(this), 1180, 241));
		enemySpawn.add(new EnemySpawner(this, new Snail(this), 885, 641));
		enemySpawn.add(new EnemySpawner(this, new Snail(this), 330, 161));

		flag = new Flag(this);
		objective = new KillObjective(enemySpawn.size());
		text = new TextObject(this, objective.getKills() + " out of " + objective.getNumberOfKills() + " kills",
				worldWidth, 100);

		initializeSound();
		initializeTileMap();
		createObjects();
		createView(worldWidth, worldHeight);

	}

	@Override
	public void update() {

		updateSpawnerTimer();
		updateObjectiveText();
	}

	/**
	 * calls on the methode updateTimer for all the EnemySpawners in the
	 * arraylist
	 */
	private void updateSpawnerTimer() {
		for (EnemySpawner es : enemySpawn) {
			es.updateTimer();
		}
	}

	/**
	 * updates the text to the objective that is at hand
	 */
	private void updateObjectiveText() {
		if (objective.checkVictory()) {
			if (!flag.isVisible()) {
				text.setText("Find the flag");
			} else {
				text.setText("Victory!");
			}
		} else {
			text.setText(objective.getKills() + " out of " + objective.getNumberOfKills() + " kills");
		}
	}

	/**
	 * adds a game object to the list
	 */
	private void createObjects() {
		addGameObject(flag, 1550, 660);
	}

	/**
	 * sets the background music and loops it
	 */
	private void initializeSound() {
		backgroundSound = new Sound(this, "src/main/java/nl/han/ica/ibilinnor/media/EcstasyOfGold.mp3");
		backgroundSound.loop(-1);
	}

	/**
	 * creates the map with all the diffrent tiles
	 */
	private void initializeTileMap() {
		Sprite grassSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/grassTile.png");
		TileType<GrassTile> grassTileType = new TileType<>(GrassTile.class, grassSprite);
		Sprite groundSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/groundTile.png");
		TileType<GroundTile> groundTileType = new TileType<>(GroundTile.class, groundSprite);
		Sprite secretSprite = new Sprite("src/main/java/nl/han/ica/ibilinnor/media/groundTile.png");
		TileType<SecretTile> secretTileType = new TileType<>(SecretTile.class, secretSprite);

		TileType[] tileTypes = { grassTileType, groundTileType, secretTileType };
		int tileSize = 80;
		int tilesMap[][] = { { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
				{ -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 1, 1 },
				{ 0, 0, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, 0, 0, 1, 1, 1, 1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 0, -1, 1, 1, 1, 1, 1, 1 },
				{ -1, -1, 0, -1, -1, -1, -1, -1, -1, 1, 0, -1, 1, -1, 2, 2, 2, 2, 2, 2 },
				{ -1, -1, -1, -1, 0, -1, -1, -1, -1, 2, 2, -1, 2, -1, 2, 2, 2, 2, 2, 2 },
				{ 0, -1, -1, -1, 1, -1, -1, -1, -1, 2, 2, -1, 2, -1, 2, 2, 2, 2, 2, 2 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 } };
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

	/**
	 * sets the worldheigt and width and draws it with the background
	 * 
	 * @param screenWidth
	 * @param screenHeight
	 */
	private void createView(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/ibilinnor/media/background.png"));

		setView(view);
		size(screenWidth, screenHeight);
	}
}
