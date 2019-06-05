import java.util.LinkedList;

/*
 * To preface, this class contains the helper functions
 * that return a linkedList that checks which squares are valid 
 * for a piece of the grid 
 * 
 * This includes both squares that the piece can move to and 
 * squares that the piece can capture 
 * 
 * The reason why there are multiple diagonal and vertical functions is so that 
 * the same helper can be reused by different pieces, so a rook would 
 * use the vertical/horizontal helpers, a bishop would use the diagonal helpers, 
 * and the queen would be able to use both
 * 
 */
public class ChessHelpers {
	
	// check squares in the horizontal directions
	public static LinkedList<ChessSquare> getHorizontal(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		
		for (int i = xPos + 1; i < 8; i++) {
			if (grid[i][yPos].getPiece() != null) {
				Piece p = grid[i][yPos].getPiece();
				if (p.getSide() == team) {
					break;
				}
				else {
					squareSet.add(grid[i][yPos]);
					break;
				}
			}
			else {
				squareSet.add(grid[i][yPos]);
			}
		}
		
		
		for (int i = xPos - 1; i >= 0; i--) {
			if (grid[i][yPos].getPiece() != null) {
				Piece p = grid[i][yPos].getPiece();
				if (p.getSide() == team) {
					break;
				}
				else {
					squareSet.add(grid[i][yPos]);
					break;
				}
			}
			else {
				squareSet.add(grid[i][yPos]);
			}
		}
		return squareSet;
	}
	
	// check squares in the vertical directions
	public static LinkedList<ChessSquare> getVert(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		for (int i = yPos + 1; i < 8; i++) {
			if (grid[xPos][i].getPiece() != null) {
				Piece p = grid[xPos][i].getPiece();
				if (p.getSide() == team) {
					break;
				}
				else {
					squareSet.add(grid[xPos][i]);
					break;
				}
			}
			else {
				squareSet.add(grid[xPos][i]);
			}
		}
		
		for (int i = yPos - 1; i >= 0; i--) {
			if (grid[xPos][i].getPiece() != null) {
				Piece p = grid[xPos][i].getPiece();
				if (p.getSide() == team) {
					break;
				}
				else {
					squareSet.add(grid[xPos][i]);
					break;
				}
			}
			else {
				squareSet.add(grid[xPos][i]);
			}
		}
		return squareSet;
	}
	
	// check diagonal squares in one direction
	public static LinkedList<ChessSquare> diag1(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos + 1;
		int y = yPos + 1;
		while(x < 8 && y < 8) {
			if (grid[x][y].getPiece() != null) {
				Piece p = grid[x][y].getPiece();
				if (p.getSide() == team) {
					x++;
					y++;
					break;
				}
				else {
					squareSet.add(grid[x][y]);
					x++;
					y++;
					break;
				}
			}
			else {
				squareSet.add(grid[x][y]);
				x++;
				y++;
			}
		}
		
		int x2 = xPos - 1;
		int y2 = yPos - 1;
	    while (x2 > -1 && y2 > -1) {
			if (grid[x2][y2].getPiece() != null) {
				Piece p = grid[x2][y2].getPiece();
				if (p.getSide() == team) {
					x2--;
					y2--;
					break;
				}
				else {
					squareSet.add(grid[x2][y2]);
					x2--;
					y2--;
					break;
				}
			}
			else {
				squareSet.add(grid[x2][y2]);
				x2--;
				y2--;
			}
	    }
		return squareSet;
	}
	
	// diagonal squares in the other direction
	public static LinkedList<ChessSquare> diag2(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos - 1;
		int y = yPos + 1;
		
		while(x >= 0 && y < 8) {
			if (grid[x][y].getPiece() != null) {
				Piece p = grid[x][y].getPiece();
				if (p.getSide() == team) {
					x--;
					y++;
					break;
				}
				else {
					squareSet.add(grid[x][y]);
					x--;
					y++;
					break;
				}
			}
			else {
				squareSet.add(grid[x][y]);
				x--;
				y++;
			}
		}
		
		int x2 = xPos + 1;
		int y2 = yPos - 1;
		
	    while (x2 < 8 && y2 > -1) {
			if (grid[x2][y2].getPiece() != null) {
				Piece p = grid[x2][y2].getPiece();
				if (p.getSide() == team) {
					x2++;
					y2--;
					break;
				}
				else {
					squareSet.add(grid[x2][y2]);
					x2++;
					y2--;
					break;
				}
			}
			else {
				squareSet.add(grid[x2][y2]);
				x2++;
				y2--;
			}
	    }
	    
		return squareSet;
	}
	
	// check valid squares for black pawn 
	public static LinkedList<ChessSquare> blackPawn(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos;
		int y = yPos;
		
		if (inArray(x + 1, y + 1)) {
			if (pawnValid(x + 1, y + 1, grid[x + 1][y + 1], team)) {
				squareSet.add(grid[x + 1][y + 1]);
			}
		}
		
		if (inArray(x + 1, y - 1)) {
			if (pawnValid(x + 1, y - 1, grid[x + 1][y - 1], team)) {
				squareSet.add(grid[x + 1][y - 1]);
			}
		}
		
		if (grid[x + 1][y].getPiece() == null && 
				inArray(x + 1, y)) {
			squareSet.add(grid[x + 1][y]);
		}
		
		return squareSet;
	}
	
	// check valid squares for white pawn 
	public static LinkedList<ChessSquare> whitePawn(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos;
		int y = yPos;
		
		
		if (inArray(x - 1, y + 1)) {
			if (pawnValid(x - 1, y + 1, grid[x - 1][y + 1], team)) {
				squareSet.add(grid[x - 1][y + 1]);
			}
		}
		
		if (inArray(x - 1, y - 1)) {
			if (pawnValid(x - 1, y - 1, grid[x - 1][y - 1], team)) {
				squareSet.add(grid[x - 1][y - 1]);
			}
		}

		if (grid[x - 1][y].getPiece() == null && 
				inArray(x - 1, y)) {
			squareSet.add(grid[x - 1][y]);
		}
		
		return squareSet;
	}
	
	// check if a coordinate is in the grid (helper function)
	private static boolean inArray(int xPos, int yPos) {
		if ((xPos < 8 && xPos >=0) && (yPos < 8 && yPos >=0)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// check if a square is valid for pawn capture 
	private static boolean pawnValid(int xPos, int yPos, ChessSquare s, Side team) {
		if (!inArray(xPos, yPos)) {
			return false;
		}
		
		Piece p = s.getPiece();
		if (p != null && p.getSide() != team) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// check if a square is valid for king movement 
	private static boolean kingValid(int xPos, int yPos, ChessSquare s, Side team) {
		if (!inArray(xPos, yPos)) {
			return false;
		}
		Piece p = s.getPiece();
		if ((p != null && p.getSide() != team) || p == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	// get all squares that a king can move to 
	public static LinkedList<ChessSquare> kingSquares(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos;
		
		for (int i = yPos - 1; i < yPos + 2; i++) {
			if (inArray(x + 1, i) &&
					kingValid(x + 1, i, grid[x + 1][i], team)) {
				squareSet.add(grid[x + 1][i]);
			}
		}
		for (int i = yPos - 1; i < yPos + 2; i++) {
			if (inArray(x - 1, i) &&
					kingValid(x - 1, i, grid[x - 1][i], team)) {
				squareSet.add(grid[x - 1][i]);
			}
		}
		for (int i = yPos - 1; i < yPos + 2; i++) {
			if (i == yPos) {
				continue;
			}
			if (inArray(x, i) &&
					kingValid(x, i, grid[x][i], team)) {
				squareSet.add(grid[x][i]);
			}
		}
		return squareSet;
	}
	
	// get all squares that a knight can move to 
	public static LinkedList<ChessSquare> knightSquares(int xPos, int yPos, 
			LinkedList<ChessSquare> squareSet, ChessSquare[][] grid, Side team) {
		int x = xPos;
		
		for (int i = yPos - 1; i < yPos + 2; i++) {
			if (i == yPos) {
				continue;
			}
			if (inArray(x - 2, i) &&
					kingValid(x - 2, i, grid[x - 2][i], team)) {
				squareSet.add(grid[x - 2][i]);
			}
		}
		for (int i = yPos - 1; i < yPos + 2; i++) {
			if (i == yPos) {
				continue;
			}
			if (inArray(x + 2, i) &&
					kingValid(x + 2, i, grid[x + 2][i], team)) {
				squareSet.add(grid[x + 2][i]);
			}
		}
		for (int i = xPos - 1; i < xPos + 2; i++) {
			if (i == xPos) {
				continue;
			}
			if (inArray(i, yPos - 2) &&
					kingValid(i, yPos - 2, grid[i][yPos - 2], team)) {
				squareSet.add(grid[i][yPos - 2]);
			}
		}
		for (int i = xPos - 1; i < xPos + 2; i++) {
			if (i == xPos) {
				continue;
			}
			if (inArray(i, yPos + 2) &&
					kingValid(i, yPos + 2, grid[i][yPos + 2], team)) {
				squareSet.add(grid[i][yPos + 2]);
			}
		}
		return squareSet;
	}
}
