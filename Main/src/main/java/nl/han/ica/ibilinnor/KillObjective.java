package nl.han.ica.ibilinnor;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class KillObjective implements IObjective, IAlarmListener {

	private int kills;
	private int numberOfKills;
	private boolean allowKill;

	public KillObjective(int numberOfKills) {
		this.numberOfKills = numberOfKills;
		this.kills = 0;
		this.allowKill = true;
	}

	/**
	 * increases the number of kills by one
	 */
	public void addKill() {
		if (allowKill) {
			this.kills++;
			allowKill = false;
			startAlarm("addKill", 0.1f);
		}
	}

	/**
	 * makes an alarm so that we can set a timer on certain actions
	 * 
	 * @param name
	 * @param time
	 */
	private void startAlarm(String name, float time) {
		Alarm alarm = new Alarm(name, time);
		alarm.addTarget(this);
		alarm.start();
	}

	/**
	 * checks if the number of kills is equal or greater then the required
	 * numberOfKills.
	 * 
	 * @return  If the condition has been met then return true. Else return false.
	 */
	@Override
	public boolean checkVictory() {
		if (this.kills >= this.numberOfKills) {
			return true;
		} else {
			return false;
		}
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "addKill") {
			allowKill = true;
		}

	}

}
