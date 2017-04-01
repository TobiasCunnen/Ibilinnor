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
		System.out.println(kills);
	}

	@Override
	public void drawObjective() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkVictory() {
		if(this.kills==this.numberOfKills){
			return true;
		}else{
			return false;
		}
		
	}

}
