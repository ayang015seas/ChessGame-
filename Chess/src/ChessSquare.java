import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ChessSquare extends JComponent {
	// these are the same for any chess squares, so they are static
	public static final int SQUARE_WIDTH = 65;
	public static final int SQUARE_HEIGHT = 65;
	// grid coordinates on the 2d array
	private int xPos;
	private int yPos;
	private Color color;
	private boolean path;
	private Piece piece;
	
	// constructor 
    public ChessSquare(int xPos, int yPos, Color color) {
    	this.xPos = xPos;
    	this.yPos = yPos;
    	this.path = false;
        this.color = color;
        this.piece = null;
    }
    
    public int getXLocation() {
    	return this.xPos;
    }
    
    public int getYLocation() {
    	return this.yPos;
    }
    
    // the active path function highlights the gridsquares 
    // with a blue "x" when a valid piece is selected
    public void activePath() {
    	this.path = true;
    }
    
    // the deactive path gets rid of the blue "x" when a square is deselected
    public void deActivePath() {
    	this.path = false;
    }
    
    // this adds a piece to the square
    public void addPiece(Piece newPiece) {
    	this.piece = newPiece;
    }
    
    // this removes a piece from the square 
    public void removePiece() {
    	this.piece = null;
    }
    
    // this returns the piece 
    public Piece getPiece() {
    	return this.piece;
    }
	
    // this paints the square and the piece (if it exists)
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.xPos, this.yPos, SQUARE_WIDTH, SQUARE_HEIGHT);
    	
    	if (this.path) {
    		g.setColor(Color.BLUE);
    		g.drawLine(0, 0, 100, 100);
    		g.drawLine(70, 0, 0, 70);
    	}

        if (this.piece != null) {
        	Piece p = this.piece;
        	p.paintComponent(g, this.xPos, this.yPos);
        }
    }
	
    // returns the dimension
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT);
    }
}
