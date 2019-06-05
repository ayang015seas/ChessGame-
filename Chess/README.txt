=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: ayang015 
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. The first core concept was collections
  LinkedList collections are extremely important for the implementation of this game, since 
  when a user selects a piece on screen, a linkedList is generated containing all of the valid 
  squares that the piece can move to. These squares are highlighted by a blue x and the user can
  decide to move to any one of them. In this way, LinkedLists are integral to the actual
  functioning of the game itself. 
  
  Additionally, LinkedLists are also used to store the pieces that each side captured over the
  course of the round to output to a print file. 
  
  2. The second concept is input output
  Full disclosure, no input is implemented. However, there is a text file in the TextOutput folder
  titled scores.txt. This is where all of the round statistics for each round are written.
  There are no high scores in the game, so there is no sorting. Instead, the data 
  shows the scores of each side and all the pieces that are captured, as well as how many 
  moves were made in total. This state is stored between games, so the file will expand 
  with each complete game that the user plays. 
  
  3. The third core concept is inheritance
  I created an abstract class piece.java that is implemented by every single piece type
  All methods in piece.java need to be overridden, but this was a very useful implementation
  not only because of the intuitive hierarchy between a "piece" and a "Rook" or "Bishop" but 
  also because it would be very hard to move pieces around the board without a general
  "piece" type that I could interact with. Instead of getting the valid moves from a piece, 
  I would have to find the exact type of the piece and then cast it in order to access the 
  actual methods needed. Having the general piece type allows me to manipulate pieces in a much
  easier fashion.
  
  4. The final core concept implemented is 2d array
  The board is modeled through a 2D array of chess squares. These chess squares in turn can 
  contain pieces. The way that the pieces are able to determine what squares they can move/capture
  to is through going through the square array user helper functions. Without this 2D array, 
  it would be very very difficult to model movement or the board in general. 
  

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  First, we will start with the enums. 
  The PieceTypes.java class contains enums to make it easy to identify what type a piece 
  is when calculating scores or determining how a pieces should move.
  
  The Side.java class contains enums to make it easy to identify which side a piece is on.
  First, I was implementing the game with standard white and black sides, but later I decided
  to change to red vs green. I understand why this is a bit confusing in some parts of the code,
  so sorry about that. 
  
  Next, the Piece.java class is an abstract class that allows for interactions with pieces 
  at a high level. For example, all of the chesssquares have the option to store a "piece" 
  subtype. Without the piece class, I wouldn't know if I needed to store a "Rook", "Bishop", etc. 
  All of the methods in Piece.java are abstract and must be overridden by subtype classes. 
  
  Next, I will group Pawn.java, Knight.java, King.java, Queen.java, Rook.java, and Bishop.java into
  one explanation. All of these extend the Piece abstract class and override all of the methods in 
  piece so that I can actually work with and model the pieces. The pieces are able to generate a
  linkedList of valid squares that they can move to, which enables their movement. 
  
  Next, I also have a ChessSquare.java class. The point of this class is so that I can have a grid
  of ChessSquares that models the state of the game. Each square has the option of containing a 
  piece, and this makes drawing the ChessBoard a much easier task. Additionally, the squares 
  also have the important function of lighting up with a blue x to represent valid moves when 
  a piece is selected. 
  
  The most important class of all is the ChessBoard class. This class handles the initialization 
  of all the pieces, handling the user click inputs, and determining which side has won in the end. 
  This class also handles the I/O, and keeps track of the 2D array that models locations of pieces
  on the board. Basically, every important piece of state is integrated through the ChessBoard.java 
  class. 
  
  Next, I have a ChessHelper.java class. The point of this class is so that each piece can calculate
  which squares on the board are valid for movement/capture. This contains functions specific to 
  King movement, Pawn movement, and Knight movement. The Rook, Queen, and Bishop share similar 
  movement styles and there are also helper functions that are used among all 3 of those pieces. 
  
  Finally, to bring all of these classes together there is the Game.java class. This class
  is identical to the mushroom of doom Game class in that it creates a JFrame with a board 
  and status panels to directly display everything to the user and allow the user to interact 
  with the game. 

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  By far, the most challenging part of the game was modeling the "ready" state after the user 
  has selected a piece and has the option to move it simply because I need to be able to handle
  all scenarios. This is the reason the logic behind the MouseClick handling function is so 
  long. 
  
- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  The encapsulation aspect of the design could most definitely be much better. Individual pieces
  are able to access the 2D array grid from ChessBoard so that they can calculate which squares
  are valid movements, which breaks encapsulation somewhat. If I were to remake the game, I would 
  restructure so that the board class is reponsible for the valid squares calculations instead 
  of the pieces so that encapsulation in this regard is better. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  All of the images (with the exception of the Rajiv pictures) were downloaded from 
  wikiMedia commons. Additionally, I used the online JavaDocs extensively to implement 
  the I/O function. 
  External libraries include java Collections, java awt, java I/O, and java swing.
  
  Finally, I also looked extensively at the Game class of the Mushroom of Doom example game 
  in order to get my own game frames working. 
  
