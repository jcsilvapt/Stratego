package tps.tp4.twoPlayersGame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import tps.tp4.PlayerData;
import tps.tp4.createPlayer;

/** 
 * 
 */
public abstract class GameSupporter extends JFrame implements ICommunicationsUser {
	private static final long serialVersionUID = 1L;

	private boolean isRed;
	private Communication comm;
	private static PlayerData nicknamelocal = new createPlayer(1);
	private static PlayerData nicknameNext = new createPlayer(2);
	
	/**
	 * constructor red
	 */
	public GameSupporter() {
		this.isRed = true;
		System.out.println("Player '" + nicknamelocal.getNickname() + "' has enter the game as Red Player!");
		comm = new CommunicationRed(this);
		setCloseActions();
	}

	/**
	 * constructor blue
	 */
	public GameSupporter(String address, int port) {
		this.isRed = false;
		System.out.println("Player '" + nicknameNext.getNickname() + "' has enter the game as Blue Player!");
		comm = new CommunicationBlue(address, port, this);
		setCloseActions();
	}
	
	/**
	 * 
	 */
	private void setCloseActions() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				comm.closeConnection();
			}
		});
	}


	/**
	 * 
	 */
	public void setCommunications(Communication comm) {
		this.comm = comm;
	}

	/**
	 * 
	 */
	public Communication getCommunications() {
		return comm;
	}

	/**
	 * send
	 */
	public void send(String text) {
		comm.send(text);
	}

	/**
	 * 
	 */
	public boolean getIsRed() {
		return isRed;
	}

	/**
	 * 
	 */
	public String toString() {
		return getName();
	}
	
	public String getPlayer1() {
		return nicknamelocal.getNickname();
	}
	
	public String getPlayer2() {
		return nicknameNext.getNickname();
	}

	/**
	 * 
	 */
	public String getName() {
		return isRed ? nicknamelocal.getNickname() : nicknameNext.getNickname();
	}

}
