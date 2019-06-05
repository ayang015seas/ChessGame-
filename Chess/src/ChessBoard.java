import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class ChessBoard extends JPanel implements MouseListener {
    public static final int BOARD_WIDTH = 480;
    public static final int BOARD_HEIGHT = 480;
	
	private int turnCount;
	private ChessSquare[][] grid;	
	
	private LinkedList<Piece> whiteCaptures;
	private LinkedList<Piece> blackCaptures;
	
	private int whitePoints;
	private int blackPoints;
	
	private int whiteRemaining;
	private int blackRemaining;
	
	private JLabel turnLabel;
	private JLabel status;
	
	private Side currentSide = Side.WHITE;
	
	private ChessSquare designated;
	private boolean ready = false;
	private LinkedList<ChessSquare> validSquares;
	
	
	public ChessBoard(JLabel turnLabel, JLabel status) {
		// instantiate turn and status labels
        this.turnLabel = turnLabel;
        this.status = status;
        
        // set initial point values
		this.whitePoints = 0;
		this.blackPoints = 0;
		this.whiteRemaining = 16;
		this.blackRemaining = 16;
		this.turnCount = 1;
		// instantiate collections and grid 
		this.whiteCaptures = new LinkedList<Piece>();
		this.blackCaptures = new LinkedList<Piece>();
		this.grid = new ChessSquare[8][8];
		this.setLayout(new GridLayout(8, 8, -7, -7));
		Dimension d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
		
		this.setPreferredSize(d);
		
		int colorCount = 1;
		// now we color the grid squares so that it looks like a chess board 
		this.addMouseListener(this);
		Color tan1 = new Color(209, 139, 71);
		Color tan2 = new Color(255, 206, 158);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (colorCount % 2 == 0) {
					ChessSquare add = new ChessSquare(i, j, tan1);
					grid[i][j] = add;
					this.add(add);
					colorCount ++;
				}	
				else {
					ChessSquare add = new ChessSquare(i, j, tan2);
					grid[i][j] = add;
					this.add(add);
					colorCount ++;
				}
			}
			colorCount++;
		}
		// initialize all pieces
		setBoard();
	}
	
	// provides pieces with access to the grid so that 
	// they can calculate valid squares 
	public ChessSquare[][] accessGrid() {
		return this.grid;
	}
	
	// paints the squares in the board 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	// returns dimensions
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	}
	
	// update the side during each move
	private void updateSide() {
		if (this.currentSide == Side.WHITE) {
			this.currentSide = Side.BLACK;
		}
		else if (this.currentSide == Side.BLACK) {
			this.currentSide = Side.WHITE;
		}
	}
	
	// whenever a new piece is captured, this function updates the score
	private void score(Piece p) {
		int points = 0;
    	if (p.getType() == PieceTypes.ROOK) {
    		points = 5;
    	}
    	else if (p.getType() == PieceTypes.KING) {
    		points = 10;
    	}
    	else if (p.getType() == PieceTypes.QUEEN) {
    		points = 9;
    	}
    	else if (p.getType() == PieceTypes.KNIGHT) {
    		points = 3;
    	}
    	else if (p.getType() == PieceTypes.BISHOP) {
    		points = 3;
    	}
    	else if (p.getType() == PieceTypes.PAWN) {
    		points = 1;
    	}
    	
		if (p.getSide() == Side.BLACK) {
			this.whitePoints += points; 
		}
		else {
			this.blackPoints += points; 
		}
	}
	
	/*
	 * this function configures the board initially with a standard setup
	 */
	private void setBoard() {
		for (int i = 0; i < 8; i++) {
			Piece w1 = new Pawn (Side.WHITE, 6, i, this);
			grid[6][i].addPiece(w1);
		}
		
		Piece w9 = new Rook (Side.WHITE, 7, 0, this);
		grid[7][0].addPiece(w9);
		Piece w10 = new Knight (Side.WHITE, 7, 1, this);
		grid[7][1].addPiece(w10);
		Piece w11 = new Bishop (Side.WHITE, 7, 2, this);
		grid[7][2].addPiece(w11);
		Piece w12 = new Queen (Side.WHITE, 7, 3, this);
		grid[7][3].addPiece(w12);
		Piece w13 = new King (Side.WHITE, 7, 4, this);
		grid[7][4].addPiece(w13);
		Piece w14 = new Bishop (Side.WHITE, 7, 5, this);
		grid[7][5].addPiece(w14);
		Piece w15 = new Knight (Side.WHITE, 7, 6, this);
		grid[7][6].addPiece(w15);
		Piece w16 = new Rook (Side.WHITE, 7, 7, this);
		grid[7][7].addPiece(w16);
		
		for (int i = 0; i < 8; i++) {
			Piece w1 = new Pawn (Side.BLACK, 1, i, this);
			grid[1][i].addPiece(w1);
		}
		Piece b9 = new Rook (Side.BLACK, 0, 0, this);
		grid[0][0].addPiece(b9);
		Piece b10 = new Knight (Side.BLACK, 0, 1, this);
		grid[0][1].addPiece(b10);
		Piece b11 = new Bishop (Side.BLACK, 0, 2, this);
		grid[0][2].addPiece(b11);
		Piece b12 = new Queen (Side.BLACK, 0, 3, this);
		grid[0][3].addPiece(b12);
		Piece b13 = new King (Side.BLACK, 0, 4, this);
		grid[0][4].addPiece(b13);
		Piece b14 = new Bishop (Side.BLACK, 0, 5, this);
		grid[0][5].addPiece(b14);
		Piece b15 = new Knight (Side.BLACK, 0, 6, this);
		grid[0][6].addPiece(b15);
		Piece b16 = new Rook (Side.BLACK, 0, 7, this);
		grid[0][7].addPiece(b16);
		
	}
	
	/*
	 * My game does not use drag and drop, instead using a click only UI. 
	 * Basically, if the user selects a piece, all squares that the piece 
	 * can move to light up with blue x. 
	 * 
	 * The user can deselect the piece by clicking away 
	 * 
	 * However, if the user wants to confirm a move, they need to make sure a selection
	 * is active and then click on one of the valid squares(as highlighted with a blue x) 
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ChessSquare oldSquare = this.designated;
		Component init = this.getComponentAt(e.getX(), e.getY());	
		// make sure that we are clicking squares and not a null part of the board  
		if (init == this || init == null) {
			return;
		}
		
		ChessSquare square = (ChessSquare) this.getComponentAt(e.getX(), e.getY());		
		if (this.ready && this.validSquares.contains(square)) {
			Piece current = this.designated.getPiece();
			
			// if the ready state is active, we want to move a piece
			 if (current.getSide() == this.currentSide) {
				int x = square.getXLocation();
				int y = square.getYLocation();
				
				// we get the piece at the location it moves to and move it
				Piece p0 = current.getAt(x, y);
				Piece p = current.move(x, y);
				
				// we move the pieces and update the counters if need be
				// we also add the captured piece to the linkedList collections
			    if (p != null && p0 != null) {
			    	score(p0);
			    	if (currentSide == Side.WHITE) {
			    		whiteCaptures.add(p0);
			    		blackRemaining -= 1;
			    	}
			    	else {
			    		blackCaptures.add(p0);
			    		whiteRemaining -= 1;
			    	}
			    	turnLabel.setText("Green Score: " + this.whitePoints + "          " +
			    		"Moves: " + this.turnCount + "          " + 
			    		"Red Score: " + this.blackPoints);
			    }
			    // if it moves to a null square we have no need to update the counters
			    else if (p != null && p0 == null) {
			    	turnLabel.setText("Green Score: " + this.whitePoints + "          " +
				    		"Moves: " + this.turnCount + "          " + 
				    		"Red Score: " + this.blackPoints);
			    }
			    // check if the endgame has been reached 
				if (whiteRemaining == 0) {
					status.setText("RED WINS!");
					try {
						writeToFile("TextOutput/scores.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (blackRemaining == 0) {
					status.setText("GREEN WINS!");
					try {
						writeToFile("TextOutput/scores.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}				
				}
				// update turncounts, deselect ready squares
				this.turnCount = turnCount + 1;
			    this.ready = false;
			    this.updateSide();
				oldSquare.deActivePath();
				for (ChessSquare s: this.validSquares) {
					s.deActivePath();
				}
				repaint();
			    return;
			  }
		}
		
		// this block handles what happens when the user has selected a piece but
		// chooses to deselect it by clicking away 
		if (this.designated != null || square != oldSquare && oldSquare != null) {
			oldSquare.deActivePath();
			for (ChessSquare s: this.validSquares) {
				s.deActivePath();
				}
			this.ready = false;
			}
		
		// finally, this accounts for the case where the user has not selected a piece 
		// yet, so it selects a piece and highlights all possible squares the piece 
		// could end up going to 
		if (square.getPiece() != null) {
			this.ready = true;
			Piece p = square.getPiece();
			this.designated = square;
			this.validSquares = p.validSquares();
			
			if (p.getSide() == currentSide) {
			square.activePath();
				for (ChessSquare s: this.validSquares) {
					s.activePath();
				}
			}
		}
		repaint();
	}
	
	/*
	 * None of these mouse inputs are used for anything
	 */
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * this function writes the game scores to a file titled scores.txt
	 * since there are no "high scores", there is no sorting. However
	 * it does also write the pieces that each side captured over 
	 * the course of the game since they are in a collection
	 */
	
	private void writeToFile(String filename) throws IOException {
		if (filename == null) {
			return;
		}
		
		String moves = "Total Moves: " + this.turnCount;
		String greenPoints = "Green Score: " + this.whitePoints;
		String redPoints = "Red Score: " + this.blackPoints;
		String greenCaptures = "Green Captures: ";
		String redCaptures = "Red Captures: ";

		for (Piece p: this.whiteCaptures) {
			PieceTypes t = p.getType();
			greenCaptures = greenCaptures + t + ", ";
		}
		for (Piece p: this.blackCaptures) {
			PieceTypes t = p.getType();
			redCaptures = redCaptures + t + ", ";
		}
		FileWriter w = new FileWriter(filename, true);
		BufferedWriter writ = new BufferedWriter(w);
		try {
			writ.write("   ");
			writ.newLine();
			writ.write(moves);
			writ.newLine();
			writ.write(greenPoints);
			writ.newLine();
			writ.write(redPoints);
			writ.newLine();
			writ.write(greenCaptures);
			writ.newLine();
			writ.write(redCaptures);
			writ.write("   ");
			writ.newLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			writ.flush();
			writ.close();
		}
	}
	
}