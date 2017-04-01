package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

	private World world;
	private String text;

	public TextObject(World world, String text,int width, int height) {
		this.world=world;
		this.text = text;
		createDashboard(width,height);
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(PGraphics g) {
		
		g.textAlign(g.LEFT, g.TOP);
		g.textSize(50);
		g.text(text, getX(), getY());
	}
	
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboard.addGameObject(this);
        world.addDashboard(dashboard);
    }


}
