package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * De Firework klasse zorgt voor een spetterend einde zodra je gewonnen hebt.
 * 
 * @author Tobias
 *
 */
public class Firework extends AnimatedSpriteObject implements IAlarmListener {

	public Firework() {
		super(new Sprite("src/main/java/nl/han/ica/ibilinnor/media/firework.png"), 8);
		setCurrentFrameIndex(0);
		startAlarm();
	}

	@Override
	public void update() {
	}

	/**
	 * makes an alarm so that we can set a timer on certain actions
	 * 
	 * @param name
	 * @param time
	 */
	private void startAlarm() {
		Alarm alarm = new Alarm("next frame", 0.2);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		nextFrame();
		startAlarm();

	}

}
