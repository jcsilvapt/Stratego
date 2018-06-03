package tps.tp4;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
/**
 * 
 * @author Jorge Silva, A44615
 * 
 * This Code represents a single place that should be put into the board.
 *
 */
public abstract class Place extends JLabel {

	private static final long serialVersionUID = 1L;

	private int xBoard;
	private int yBoard;

	public Place(Color pieceColor, int xBoard, int yBoard) {
		setOpaque(true);
		setBackground(pieceColor);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Isto é " + e.getComponent().toString());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				
			}
		});
	}

	public int getXBoard() {
		return xBoard;
	}

	public int getYBoard() {
		return yBoard;
	}

	public void setXBoard(int xBoard) {
		this.xBoard = xBoard;
	}

	public void setYBoard(int yBoard) {
		this.yBoard = yBoard;
	}
	
	public String toString() {
		return "|x=" + xBoard + ", y=" + yBoard+"| ";
	}

	public boolean canMoveTo(int x, int y) {
		return false;
	}

	public boolean moveTo(int x, int y) {
		return false;
	}
}
