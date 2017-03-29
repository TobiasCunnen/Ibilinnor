package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.ibilinnor.tiles.GrassTile;
import processing.core.PApplet;

public class Ibilinnor extends GameEngine{

	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.ibilinnor.Ibilinnor"});

	}

	@Override
	public void setupGame() {
		World aestamar = new World();
		aestamar.setupWorld();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
