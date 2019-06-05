import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
/*
 * For documentation on what any particular method is doing,
 * refer to Piece.java 
 */
public class Pawn extends Piece {
	private boolean firstMove;
	private ChessBoard b;
	public static final PieceTypes type = PieceTypes.PAWN;
	
	private String graphic;
	private Side team;
	
	private int xPos;
	private int yPos;
	
    private BufferedImage img;
	
	public Pawn (Side affiliation, int xCoord, int yCoord, ChessBoard b) {
		this.xPos = xCoord;
		this.yPos = yCoord;
		this.b = b;
		this.team = affiliation;
		this.firstMove = true;
		
		if (affiliation == Side.BLACK) {
			this.graphic = "files/bPawn.png";
		}
		else {
			this.graphic = "files/wPawn.png";
		}
		
        try {
            if (img == null) {
                img = ImageIO.read(new File(graphic));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}
	
	@Override
	public LinkedList<ChessSquare> validSquares() {
		LinkedList<ChessSquare> squareSet = new LinkedList<ChessSquare>();
		ChessSquare[][] grid = b.accessGrid();
		
		if (team == Side.BLACK && this.xPos == 7) {
			return squareSet;
		}
		
		if (team == Side.WHITE && this.xPos == 0) {
			return squareSet;
		}
		
		if (team == Side.BLACK && this.firstMove == true) {
			Piece p0 = getAt(this.xPos + 1, this.yPos);
			Piece p1 = getAt(this.xPos + 2, this.yPos);
			if (p1 == null && p0 == null) {
				squareSet.add(grid[this.xPos + 1][this.yPos]);
				squareSet.add(grid[this.xPos + 2][this.yPos]);
			}
		}
		if (team == Side.WHITE && this.firstMove == true) {
			Piece p0 = getAt(this.xPos - 1, this.yPos);
			Piece p1 = getAt(this.xPos - 2, this.yPos);
			if (p1 == null && p0 == null) {
				squareSet.add(grid[this.xPos - 1][this.yPos]);
				squareSet.add(grid[this.xPos - 2][this.yPos]);
			}
		}
		
		
		if (team == Side.BLACK) {
		squareSet = ChessHelpers.blackPawn(this.xPos, this.yPos,
				squareSet, grid, this.team);
		}
		else {
			squareSet = ChessHelpers.whitePawn(this.xPos, this.yPos,
					squareSet, grid, this.team);
		}
		return squareSet;
	}
	
	@Override
	public Piece move(int xPos, int yPos) {	
		this.firstMove = false;
		ChessSquare[][] grid = b.accessGrid();
		Piece p = grid[this.xPos][this.yPos].getPiece();
		grid[this.xPos][this.yPos].removePiece();
		
		if (p == null) {
			grid[xPos][yPos].addPiece(p);
			this.xPos = xPos;
			this.yPos = yPos;
			return null;
		}
		else {
			this.xPos = xPos;
			this.yPos = yPos;
			grid[xPos][yPos].addPiece(p);
			return p;
		}
	}
	
	@Override
	public Piece getAt(int xPos, int yPos) {
		ChessSquare[][] grid = b.accessGrid();
		Piece p = grid[xPos][yPos].getPiece();
		return p;
	}
	
	
	@Override
	public PieceTypes getType() {
		return Pawn.type;
	}
	
	@Override
	public Side getSide() {
		return this.team;
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(ChessSquare.SQUARE_WIDTH, ChessSquare.SQUARE_HEIGHT);
    }
    
    @Override
    public void paintComponent(Graphics g, int dx1, int dy1) {
    	g.drawImage(img, 0, 0, ChessSquare.SQUARE_WIDTH, ChessSquare.SQUARE_HEIGHT, null);
    }
}
