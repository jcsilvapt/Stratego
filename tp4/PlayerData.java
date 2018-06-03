package tps.tp4;

import javax.swing.JOptionPane;

public abstract class PlayerData {

	private String nickname;
	private int playerNumber;
	
	public PlayerData(int number) {
		// FIXME For test i disable the first setNickname enable in final version
		this.playerNumber = number;
		//setNickname(JOptionPane.showInputDialog(null,"Insere o teu nickname!","Jogador "+number, JOptionPane.WARNING_MESSAGE));
		setNickname(null);
	}
	
	public void setNickname(String nickname) {
		if(nickname == null)
			this.nickname = "Player " + playerNumber;
		else
			this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Board setPlaces(Place p, int xBoard, int yBoard) {
		return null;
	}
}
