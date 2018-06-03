package tps.tp4;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;

	private int nRows = 10;
	private int nCols = 10;

	private ArrayList<EmptyPlace> emptyPlaces = new ArrayList<>();
	private ArrayList<Place> places = new ArrayList<>();

	public Board() {
		// set layout to Grid
		setLayout(new GridLayout(nRows, nCols, 1, 1));
		// Create the grid
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				if ((r == 4 || r == 5) && (c == 2 || c == 3 || c == 6 || c == 7)) {
					add(new LakePlace(r, c));
					places.add(new LakePlace(r, c));
				} else {
					add(setPlaceAt(new EmptyPlace(r, c), r, c));
					emptyPlaces.add(new EmptyPlace(r, c));
				}
			}
		}
		System.out.println(Arrays.toString(places.toArray()));
	}

	public Place getPlaceAt(int xBoard, int yBoard) {
		return null;
	}

	public Place setPlaceAt(Place p, int xBoard, int yBoard) {
		if (getComponentAt(xBoard, yBoard) == null) {
			p.setXBoard(xBoard);
			p.setYBoard(yBoard);
		} else {
			System.out.println("FAIL");
		}
		return p;
	}

	public String toString() {
		return "Array: EmptyPlaces " + Arrays.toString(emptyPlaces.toArray());
	}

}