package tps.tp4;

public class createPlayer extends PlayerData {
	
	public createPlayer(int number) {
		super(number);
	}
		
	// returns the username
	public String getUsername() {
		return this.getNickname();
	}

}
