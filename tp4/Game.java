package tps.tp4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import tps.tp4.twoPlayersGame.CommunicationRed;
import tps.tp4.twoPlayersGame.GameSupporter;
import tps.tp4.twoPlayersGame.ICommunicationsUser;

/**
 * The received(String) method will be called when there is data from the other
 * player. To send data, call comm.send(String).
 * 
 * 
 */
public class Game extends GameSupporter {
	
	private static final long serialVersionUID = 1L;

	private JButton sendBtn;
	private JTextField inputJtf;

	/**
	 * Red player constructor
	 */
	public Game() {
		super();
		init();
	}

	/**
	 * Blue player constructor
	 */
	public Game(String address, int port) {
		super(address, port);
		init();
	}

	/**
	 * common initialization actions
	 */
	private void init() {

		setTitle("Player " + getName());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setBounds(getIsRed() ? 0 : width / 2, 0, width / 2, height / 2 + height / 4);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		gridLayout();
		
		commsGUI(); // Final

		setVisible(true);
	}

	/**
	 * when communication are ready we can enable interface access
	 */
	public void setCommunicationsReady() {
		// unblock GUI
		sendBtn.setEnabled(getIsRed());
		inputJtf.setEnabled(getIsRed());
	}

	/**
	 * 
	 */
	public void received(String text) {
		System.out.println(getName() + ": Received: " + text);
		sendBtn.setEnabled(true);
		inputJtf.setEnabled(true);
	}

	/**
	 * called when other user closes connection
	 */
	public void receivedEndOfCommunicationsFromOtherUser() {
		// disable interface and only enable end actions
		System.out.println(getName() + ": Received end of communications from other user");
	}
	
	/**
	 * Method that designs the communication system
	 */
	public void commsGUI() {
		
		JPanel panelControls = new JPanel();

		sendBtn = new JButton("Send");
		sendBtn.setEnabled(false);
		panelControls.add(sendBtn);

		inputJtf = new JTextField(20);
		inputJtf.setEnabled(false);
		panelControls.add(inputJtf);

		add(panelControls, BorderLayout.SOUTH);

		sendBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				send(inputJtf.getText());
				sendBtn.setEnabled(false);
				inputJtf.setEnabled(false);
			}
		});
	}
	
	/**
	 * Method that creates the Layout of the Frame
	 */
	public void gridLayout() {

		// north (header)
		JPanel topPanel = new JPanel();
		JLabel topIcon = new JLabel(new ImageIcon("src/tps/tp4/images/stratego-logo.png"));
		topPanel.add(topIcon);
		add(topPanel, BorderLayout.NORTH);

		// Creates a center Panel that will have everything
		JPanel ActionPanel = new JPanel(new BorderLayout());
				
		//Adding the Board to the frame
		ActionPanel.add(new Board(), BorderLayout.CENTER); 
		
		// Add Side Panels to show players info to the frame
		if(getIsRed()) {
			ActionPanel.add(playerDataGUI(getIsRed()), BorderLayout.WEST);
			ActionPanel.add(playerDataGUI(!getIsRed()), BorderLayout.EAST);
		}else {
			ActionPanel.add(playerDataGUI(getIsRed()), BorderLayout.WEST);
			ActionPanel.add(playerDataGUI(!getIsRed()), BorderLayout.EAST);
		}

		add(ActionPanel);

	}
	
	public JPanel playerDataGUI(boolean isRed) {
		JPanel playerContent = new JPanel(new BorderLayout());
		
		JPanel playerNameHolder = new JPanel();
		playerNameHolder.setPreferredSize(new Dimension(50, 25));
		playerNameHolder.setBackground(isRed? Color.red: Color.blue);
		setPreferredSize(new Dimension(150, getHeight()));
		if(isRed) {
			JLabel playerName = new JLabel(getPlayer1());
			playerName.setVerticalAlignment(SwingConstants.CENTER);
			playerName.setHorizontalAlignment(SwingConstants.CENTER);
			playerName.setForeground(Color.white);
			playerNameHolder.add(playerName);
		}else {
			JLabel playerName = new JLabel(getPlayer2());
			playerName.setVerticalAlignment(SwingConstants.CENTER);
			playerName.setHorizontalAlignment(SwingConstants.CENTER);
			playerName.setForeground(Color.white);
			playerNameHolder.add(playerName);
		}
				
		playerContent.add(playerNameHolder, BorderLayout.NORTH);
		
		JPanel playerDataHolder = new JPanel(new GridLayout(1, 2,1,1));
		playerDataHolder.setPreferredSize(new Dimension(100, 500));
		playerDataHolder.setBackground(Color.red);
		
		JPanel pieceNames = new JPanel(new GridLayout(11, 1,1,1));
		// Adding type of pieces
		JLabel[] pieces = new JLabel[11];
		for(int i = 0; i < 11; ++i) {
			JLabel piece = pieces[i] = new JLabel("Peça");
			piece.setOpaque(true);
			piece.setBackground(Color.GREEN);
			pieceNames.add(piece);
		}
		
		JPanel pieceNumber = new JPanel(new GridLayout(11, 1,1,1));
		// Adding the number of the pieces
		JLabel[] npieces = new JLabel[11];
		for(int i = 0; i < 11; ++i) {
			JLabel npiece = npieces[i] = new JLabel("Nº Peça");
			npiece.setOpaque(true);
			npiece.setBackground(Color.cyan);
			pieceNumber.add(npiece);
		}
		playerDataHolder.add(pieceNames);
		playerDataHolder.add(pieceNumber);
		
		playerContent.add(playerDataHolder, BorderLayout.CENTER);
		
		return playerContent;
	}

	/**
	 * main method. Create a red instance, a blue instance, both instances, or
	 * nothing if bad arguments
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Error, missing: RED or BLUE!!!");
			System.exit(1);
		}

		// Remote address - default to local computer "127.0.0.1"
		// To connect to other computer either put here or read it from the
		// keyboard
		String remoteAddress = "127.0.0.1";

		if (args[0].equalsIgnoreCase("red")) {
			// call player red code
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// create RED
					new Game();
				}
			});
		}

		else if (args[0].equalsIgnoreCase("blue")) {
			// call player blue code
			Scanner scan = new Scanner(System.in);

			// To enable connection to another computer uncomment the following
			// two lines
			// System.out.print("Set remote IP address: ");
			// remoteAddress = scan.next();

			final String finalRemoteAddress = remoteAddress;

			// read remote port - it always change
			System.out.print("Set remote port: ");
			if (!scan.hasNextInt()) {
				System.out.println("Input is not an int value: " + scan.next());
				System.exit(3);
			}
			int remotePort = scan.nextInt();
			scan.close();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// create BLUE
					new Game(finalRemoteAddress, remotePort);
				}
			});
		} else if (args[0].equalsIgnoreCase("both")) {
			// call player red code
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// create RED
					ICommunicationsUser red = new Game();
					// get red local port
					int redLocalPort = ((CommunicationRed) red.getCommunications()).getServerSocketLocalPort();
					// create BLUE
					new Game(remoteAddress, redLocalPort);
				}
			});
		}

		else {
			System.out.println("Error, bad argument. Expected: RED, BLUE or BOTH!!!");
			System.exit(2);
		}
	}
}
