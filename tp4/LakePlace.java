package tps.tp4;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import org.w3c.dom.events.MouseEvent;

public class LakePlace extends Place {

	private static final long serialVersionUID = 1L;

	private static final String type = "LakePlace";

	private static final Color backgroundColor = new Color(168, 202, 255);

	public LakePlace(int xBoard, int yBoard) {
		super(backgroundColor, xBoard, yBoard);
		setXBoard(xBoard);
		setYBoard(yBoard);
		setBorder(new LineBorder(new Color(117, 111, 95), 1));
	}

	public String toString() {
		return "Type: '" + type + "' " + super.toString();
	}

}
