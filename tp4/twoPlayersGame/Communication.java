package tps.tp4.twoPlayersGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.SwingUtilities;

/**
 * Implementa a comunicação por Socket.
 */
public class Communication implements ISend {
	protected Socket sock;
	protected ICommunicationsUser window;

	private PrintWriter out;
	private BufferedReader in;

	/**
	 * 
	 */
	public Communication(ICommunicationsUser window) {
		this.window = window;
	}

	/**
	 * 
	 */
	public void setSocket(Socket sock) {
		this.sock = sock;
	}

	/**
	 * 
	 */
	public int getLocalPort() {
		return sock.getLocalPort();
	}

	/**
	 * 
	 */
	public void doit() {
		try {
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			Thread tr = new Thread(new Runnable() {

				public void run() {
					String inputLine;
					try {
						while ((inputLine = in.readLine()) != null) {
							final String iLine = inputLine;
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									window.received(iLine);
								}
							});
						}
					} catch (SocketException e) {
						System.out.println(window.getName() + ": socket reseted by other side");
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(window.getName() + ": End of input from socket .....");
					window.receivedEndOfCommunicationsFromOtherUser();

					// closing socket if this is last part closed
					try {
						sock.shutdownInput();
						if (sock.isOutputShutdown()) {
							sock.close();
							System.out.println(window.getName() + ": Closing socket");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			tr.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void send(String text) {
		out.println(text);
	}

	/**
	 * 
	 */
	public void closeConnection() {
		if (!sock.isOutputShutdown()) {
			try {
				sock.shutdownOutput();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (sock.isInputShutdown()) {
			try {
				sock.close();
				System.out.println(window.getName() + ": Closing socket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
