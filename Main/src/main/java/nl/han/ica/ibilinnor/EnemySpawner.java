package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class EnemySpawner implements IAlarmListener {

	World world;
	Enemy enemy;
	
	private float x,y;
	private static final float enemysPerSecond = 12;

	public EnemySpawner(World world, Enemy enemy, float x, float y) {
		this.world = world;
		this.enemy = enemy;
		this.x=x;
		this.y=y;
		
		addGameObject();
		startAlarm();
	}

	private void addGameObject(){
		world.addGameObject(enemy,x,y);
	}
	
    private void startAlarm() {
        Alarm alarm=new Alarm("New enemy",enemysPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

	@Override
	public void triggerAlarm(String alarmName) {
		if(!enemy.isAlive()){
			addGameObject();
		}
		startAlarm();
	}
}
