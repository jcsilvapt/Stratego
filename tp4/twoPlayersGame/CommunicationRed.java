package tps.tp4.twoPlayersGame;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 */
public class CommunicationRed extends Communication {
	ServerSocket serverSock;
	Thread t;
	int localPort = -1;

	/**
	 * 
	 */
	public CommunicationRed(ICommunicationsUser window) {
		super(window);

		try {
			serverSock = new ServerSocket(0);

			System.out.println(
					"TCP socket created: address = " + getHostAddress() + ", port = " + serverSock.getLocalPort());

			localPort = serverSock.getLocalPort();

			t = new Thread(new Runnable() {
				public void run() {
					try {
						sock = serverSock.accept();
						System.out.println("TCP connection from: " + sock.getInetAddress().getHostAddress() + ", port "
								+ sock.getPort());

						setSocket(sock);

						doit();

						window.setCommunicationsReady();

						serverSock.close();

					} catch (SocketException e) {
						if (e.getMessage().equals("socket closed"))
							System.out.println("Closing server socket");
						else
							e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the local address of a Datagram socket connected to the Google DNS
	 * server.
	 */
	public static String getHostAddress() throws SocketException, UnknownHostException {
		try (DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			return socket.getLocalAddress().getHostAddress();
		}
	}

	/**
	 * 
	 */
	public int getServerSocketLocalPort() {
		return localPort;
	}

	/**
	 * 
	 */
	public void closeConnection() {
		// check server socket
		if (!serverSock.isClosed()) {
			try {
				serverSock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// wait until server socket thread dies, for concurrent cases and to
		// ensure thread is dead, this should be fast
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// if socket not created, nothing to do
		if (sock == null)
			return;

		// if socket create, close it
		super.closeConnection();
	}
}
