package tps.tp4;

import java.awt.Color;

import javax.swing.border.LineBorder;

public class EmptyPlace extends Place {

	private static final long serialVersionUID = 1L;

	private static final Color backgroundColor = new Color(181, 191, 157);

	private static final String type = "EmptyPlace";

	public EmptyPlace(int xBoard, int yBoard) {
		super(backgroundColor, xBoard, yBoard);
		setBorder(new LineBorder(new Color(139, 149, 114), 1));
	}

	public String toString() {
		return "Type: '" + type + "' " + super.toString();
	}

}
