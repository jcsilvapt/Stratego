package tps.tp4;

import java.awt.Color;

public class GenericPiece extends Place {

	private static final long serialVersionUID = 1L;
	
	private static String type = "GenericPiece";
	
	private static final Color backgroundColor = new Color(255, 0, 0);

	public GenericPiece(int xBoard, int yBoard) {
		super(backgroundColor, xBoard, yBoard);
	}

	public String toString() {
		return "Type: '" + type + "' " + super.toString();
	}

	// public enum PieceType {
	// FLAG(0, 1),
	// SPY(1, 1),
	// SCOUT(2, 8),
	// MINER(3, 5),
	// SERGEANT(4, 4),
	// LIEUTENANT(5, 4),
	// CAPTAIN(6, 4),
	// MAJOR(7, 3),
	// CORONEL(8, 2),
	// GENERAL(9, 1),
	// MARSHALL(10, 1),
	// BOMB(11, 6);
	//
	// private int rank;
	// private int numbers;
	//
	// PieceType(int rank, int numbers) {
	// this.rank = rank;
	// this.numbers = numbers;
	// }
	//
	// public int getCount() {
	// return numbers;
	// }
	// public int getRank() {
	// return rank;
	// }
	// }
}
