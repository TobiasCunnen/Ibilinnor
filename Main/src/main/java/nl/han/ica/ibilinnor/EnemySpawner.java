package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

/**
 * EnemySpawner is een klasse die ervoor zorgt dat er altijd X-aantal vijanden
 * leven. Als een van de vijanden sterft dan wordt de waarde isAlive in de Enemy
 * klasse op false gezet. Ook wordt er tegelijkertijd de timer voor de respawn
 * gezet die ervoor zorgt dat de vijand weert tot leven komt na een bepaalde
 * tijd.
 * 
 * @author Joep
 *
 */
public class EnemySpawner implements IAlarmListener {

	World world;
	Enemy enemy;

	private float x, y;
	private boolean checkRespawn;
	private static final float enemysPerSecond = 12;

	public EnemySpawner(World world, Enemy enemy, float x, float y) {
		this.world = world;
		this.enemy = enemy;
		this.x = x;
		this.y = y;
		this.checkRespawn = true;

		addGameObject();

	}

	/**
	 * creates game object and adds it to the game object list
	 */
	private void addGameObject() {
		world.addGameObject(enemy, x, y);
	}

	/**
	 * makes an alarm so that we can set a timer on certain actions
	 * 
	 * @param name
	 * @param time
	 */
	private void startAlarm() {
		Alarm alarm = new Alarm("New enemy", enemysPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}

	/**
	 * if an enemy is dead than the respawn timer will start
	 */
	public void updateTimer() {
		if (!enemy.isAlive()) {
			while (checkRespawn) {
				startAlarm();
				checkRespawn = false;
			}
		}
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (!enemy.isAlive()) {
			addGameObject();
			enemy.setAlive(true);
			checkRespawn = true;
		}
	}

}
