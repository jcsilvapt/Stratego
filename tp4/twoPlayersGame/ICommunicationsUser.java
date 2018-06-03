package tps.tp4.twoPlayersGame;

/**
 * 
 */
public interface ICommunicationsUser {

	/**
	 * 
	 */
	void setCommunicationsReady();

	/**
	 * 
	 */
	Communication getCommunications();

	/**
	 * 
	 */
	void received(String text);

	/**
	 * 
	 */
	void receivedEndOfCommunicationsFromOtherUser();

	/**
	 * 
	 */
	String getName();
}
