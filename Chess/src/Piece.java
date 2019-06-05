import java.awt.*;
import java.util.LinkedList;

// for reference, all of the methods here are menat to be overridden 
abstract class Piece {	
	// this method moves pieces to different grid coordinates on the board 
	// it returns the piece that was moved
	public Piece move(int xPos, int yPos) {
		return null;
	}
	
	// this method gets a linkedlist of squares that the current piece can 
	// move to 
	public LinkedList<ChessSquare> validSquares() {
		return null;
	}
	
	// this returns the type of the piece
	public PieceTypes getType() {
		return null;
	}
	
	// this gets the piece at a specific location of the board 
	public Piece getAt(int xPos, int yPos) {
		return null;
	}
	
	// this returns the side of the piece
	public Side getSide() {
		return null;
	}
	
	// this gets the size/dimensions of the piece
	public Dimension getPreferredSize() {
		return null;
	}
	
	// this method paints the piece
	public void paintComponent(Graphics g, int dx1, int dx2) {
		// meant to be overridden
	}
}
