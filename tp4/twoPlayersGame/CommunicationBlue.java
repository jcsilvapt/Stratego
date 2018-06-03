package tps.tp4.twoPlayersGame;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 */
public class CommunicationBlue extends Communication {

	/**
	 * 
	 */
	public CommunicationBlue(String address, int port, ICommunicationsUser window) {
		super(window);

		try {

			Socket sock = new Socket(address, port);
			setSocket(sock);

			doit();

		} catch (UnknownHostException e) {
			System.out.println("Endere�o desconhecido ou inv�lido: " + address + ":" + port);
			System.exit(4);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(5);
		}
	}

}
