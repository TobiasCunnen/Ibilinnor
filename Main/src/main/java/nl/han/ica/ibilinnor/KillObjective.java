package nl.han.ica.ibilinnor;

public class KillObjective implements IObjective {
	
	private int kills;
	private int numberOfKills;
	
	public KillObjective(int numberOfKills){
		this.numberOfKills=numberOfKills;
		this.kills=0;
	}
	
	public void addKill(){
		this.kills++;
	}

	@Override
	public void drawObjective() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkVictory() {
		// TODO Auto-generated method stub
		
	}

}
