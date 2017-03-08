import java.util.ArrayList;
import java.util.Random;

public class Labyrinth {
	
//------------------ VARIABLES -------------------------------------------------------
	
	private boolean debug = true;
	private ArrayList<Piece> pieces = new ArrayList();
	private Piece[][] board = new Piece[7][7];
	private Piece spare;
	private Player player1;
	private Player player2;
	private ArrayList<String> deck = new ArrayList(); 
	//tracker for whose turn it is
	boolean tracker;
	//playing a cp?
	boolean playCP;
	boolean gameOver = false;
	
//----------------- SET UP THE GAME --------------------------------------------------	
	
	private void initializePieces(){
		//start by adding all the pieces that are "glued"
		pieces.add( new Piece(false,true,true,false,"blueStart",6,0) );
		pieces.add( new Piece(false,true,true,true,"A",6,2) );
		pieces.add( new Piece(false,true,true,true,"B",6,4) );
		pieces.add( new Piece(false,false,true,true,"null",6,6) );
		pieces.add( new Piece(true,true,true,false,"C",4,0) );
		pieces.add( new Piece(true,true,true,false,"D",4,2) );
		pieces.add( new Piece(false,true,true,true,"E",4,4) );
		pieces.add( new Piece(true,false,true,true,"F",4,6) );
		pieces.add( new Piece(true,true,true,false,"G",2,0) );
		pieces.add( new Piece(true,true,false,true,"H",2,2) );
		pieces.add( new Piece(true,false,true,true,"I",2,4) );
		pieces.add( new Piece(true,false,true,true,"J",2,6) );
		pieces.add( new Piece(true,true,false,false,"null",0,0) );
		pieces.add( new Piece(true,true,false,true,"K",0,2) );
		pieces.add( new Piece(true,true,false,true,"L",0,4) );
		pieces.add( new Piece(true,false,false,true,"greenStart",0,6) );
		//now add the rest of the treasure pieces (6 L's and 6 T's)
		pieces.add( new Piece(true,true,false,false,"M",-1,-1));
		pieces.add( new Piece(true,true,false,false,"N",-1,-1));
		pieces.add( new Piece(true,true,false,false,"O",-1,-1));
		pieces.add( new Piece(true,true,false,false,"P",-1,-1));
		pieces.add( new Piece(true,true,false,false,"Q",-1,-1));
		pieces.add( new Piece(true,true,false,false,"R",-1,-1));
		pieces.add( new Piece(true,true,true,false,"S",-1,-1));
		pieces.add( new Piece(true,true,true,false,"T",-1,-1));
		pieces.add( new Piece(true,true,true,false,"U",-1,-1));
		pieces.add( new Piece(true,true,true,false,"V",-1,-1));
		pieces.add( new Piece(true,true,true,false,"W",-1,-1));
		pieces.add( new Piece(true,true,true,false,"X",-1,-1));
		//finally the last null pieces (10 L's and 12 straight pieces)
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,true,false,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
		pieces.add( new Piece(true,false,true,false,"null",-1,-1));
	}
	private void initializeBoard(){
		//this for loop places all the glued pieces in the correct location
		for(int i =0; i<pieces.size(); i++){
			Piece thisPiece = pieces.get(i);
			if(thisPiece.isitGlued() == true){
				int x = thisPiece.spot[0];
				int y = thisPiece.spot[1];
				board[x][y] = thisPiece;
				pieces.remove(thisPiece);
				i= i-1;
			}
		}
		//this while loop will choose a random remaining piece, spin it to a random orientation, then put it on the board
		while(pieces.size()!=1){
			Random rand = new Random();
			int randomPiece = rand.nextInt(pieces.size());
			Piece aPiece = pieces.get(randomPiece);
			int randomSpin = rand.nextInt(4)+1;
			for(int i=0; i<randomSpin; i++){
				aPiece.rotateLeft();
			}
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
					if(board[i][j]==null){
						board[i][j]= aPiece;
						pieces.remove(aPiece);
						//kill the for loops after the piece is placed
						i=7;
						j=7;
					}
				}
			}	
		}
		//the last remaining piece is the spare
		spare = pieces.get(0);
		pieces.remove(spare);
	}
	private void initializeDeck(){
		//add cards to deck
		ArrayList<String> deckTemp = new ArrayList(); 
		deckTemp.add("A");
		deckTemp.add("B");
		deckTemp.add("C");
		deckTemp.add("D");
		deckTemp.add("E");
		deckTemp.add("F");
		deckTemp.add("G");
		deckTemp.add("H");
		deckTemp.add("I");
		deckTemp.add("J");
		deckTemp.add("K");
		deckTemp.add("L");
		deckTemp.add("M");
		deckTemp.add("N");
		deckTemp.add("O");
		deckTemp.add("P");
		deckTemp.add("Q");
		deckTemp.add("R");
		deckTemp.add("S");
		deckTemp.add("T");
		deckTemp.add("U");
		deckTemp.add("V");
		deckTemp.add("W");
		deckTemp.add("X");
		//now shuffle it
		Random rand = new Random();
		while(deckTemp.size() >= 1){
			int randomCardIndex = rand.nextInt(deckTemp.size());
			String card = deckTemp.get(randomCardIndex);
			deck.add(card);
			deckTemp.remove(card);
		}
		
	}	
	private void initializePlayers(){
		//get user input from a gui that pops up at the start maybe?
		String player1Name = getPlayer1Name();
		ArrayList<String> halfDeck = new ArrayList(); 
		for(int i=0;i<deck.size()/2;i++){
			halfDeck.add(deck.get(2*i));
		}
		for(int i=0;i<halfDeck.size();i++){
			deck.remove(halfDeck.get(i));
		}
		player1 = new Player(player1Name,"blue",6,0,halfDeck);
		//user input to say, "do you want to play a cp or 1V1
		playCP = whoToPlay();
		if(playCP == true){
			player2 = new Player("CP","green",0,6,deck);
		}
		else{
			String player2Name = getPlayer2Name();
			player2 = new Player(player2Name,"green",0,6,deck);
		}
	}
	
//------------------- GAME PLAY METHODS -----------------------------------------------	
	
	//to change the turn
	private void nextTurn(){
		if(tracker==true){
			tracker=false;
		}else{
			tracker=true;
		}
	}
	//to check whose turn it is
	public Player getWhoseTurn(){
		if(tracker==true){
			return player1;
		}
		return player2;
	}
	public Piece[][] getBoard(){
		return board;
	}
	
	private void isTheGameOver(){
		if(player1.cardsLeft == 0 || player2.cardsLeft == 0){
			gameOver = true;
		}
	}
	
	//----Incomplete methods----------------------------------------
	private boolean canMove(int x, int y, String dir){
		
		return false;
	}
	private boolean pathExists(int x1, int y1, int x2, int y2){
		
		return true;
	}
	private boolean checkForTreasure(Player player){
		//checks if the piece the player move to has the treasure it needs
		
		//update players deck and currentTreasure
		return false;
	}
	private void insertPiece(int location){
		//the orange triangles labeled 1-12 are the location
		
		//method should update board and new spare piece
	}
	
	private void playGame(){
		while(gameOver == false){
			nextTurn();
			Player player = getWhoseTurn();
			//get user input on where to put in their spare piece
			int location = getInsertLocation();
			insertPiece(location);
			
			//get where the player wants to move and where they are
			int hereX = player.location[0];
			int hereY = player.location[1];
			int[] moveTo = wantToMoveHere();
			int moveX = moveTo[0];
			int moveY = moveTo[1];
			
			//some sort of recursive canMove in pathExists
			
			while(pathExists(hereX,hereY,moveX,moveY)==false){
				//tell user to try a new spot
				moveTo = wantToMoveHere();
				moveX = moveTo[0];
				moveY = moveTo[1];
			}
			
			//if path exists move the player to the piece
			player.updateLocation(moveX,moveY);
			
			//then check for treasure for that player
			if(checkForTreasure(player)==true){
				player.flipCard();
			}
			//lastly check if the game is over
			isTheGameOver();
			//just to go through one round when debug is set to true so while loop can end
			if(debug==true){
				gameOver=true;
			}
		}
	}
	
//--------------------------- GUI CALLS THESE ------------------------------------------------
	//GUI CLASS CAN ALSO CALL METHODS LISTED HERE:
	
	 //REGULAR USE:
		//getWhoseTurn()
		//getBoard()
	
	 //MUST USE player.method():	(getWhoseTurn() returns player)
		//getLocation()			-returns coordinates of players location
		//getColor()			-returns color of players token
		//getName()				-returns players name
		//getCurrentCard()		-returns the treasure they're looking for
		//getWhatFlipped()		-returns an ArrayList of all the cards the player has flipped over (found treasures for)
		//getCardsLeft()		-returns how many cards in the deck
	
	 //MUST USE piece.method():   (getBoard() returns Piece[][], so let x = getBoard(), then x[i][j] is a piece)
		//getPaths()			-returns boolean[] of {north,east,south,west}, when true then you can move that direction
		//getTreasure()			-returns what treasure is on the piece, if null there is no treasure
		//getSpot()				-returns int[] of {x,y} coordinates of where the piece is
	
	//all the methods bellow must be changed by Sean
	//these methods will be called throughout the code above written by Alex and should provoke methods in Sean's GUI class
	public String getPlayer1Name(){
		//text box input
		return "ram";
	}
	public boolean whoToPlay(){
		//maybe radio buttons here?
		
		//if user chooses to play a cp return true
		return true;
	}
	//only called when whoToPlay() returns false
	public String getPlayer2Name(){
		//text box input
		return "sean";
	}	
	//click on one of the orange triangles around the board to insert piece here
	//label them numerically
	public int getInsertLocation(){
		//user input should be returned
		return 0;
	}
	//this method will get called again if the user chooses a path that doesn't work
	public int[] wantToMoveHere(){
		//user input
		int[] loc = {0,0};
		return loc;
	}
	
	//these methods need a button that calls them
	public void rotateSpareRight(){
		spare.rotateRight();
	}
	public void rotateSpareLeft(){
		spare.rotateLeft();
	}
	
//--------------------------- MAIN METHOD ----------------------------------------------------
	
	public static void main(String[] args) {
		Labyrinth labyrinth = new Labyrinth();
		labyrinth.initializePieces();
		labyrinth.initializeBoard();
		labyrinth.initializeDeck();
		labyrinth.initializePlayers();
		labyrinth.playGame();
		
		if(labyrinth.debug == true){
			//print out the board pieces treasure.
			//you can see any of the variables for the piece by changing labyrinth.board[i][j].???? <-here
			for(int i=6;i>=0;i--){
				for(int j=0;j<7;j++){
					System.out.print(labyrinth.board[i][j].treasure + ", ");
				}
				System.out.print("\n");
			}
			System.out.println("Spare: " + labyrinth.spare.treasure);
			
			System.out.println("Player1 deck: " + labyrinth.player1.deck);
			
			System.out.println("Player2 deck: " + labyrinth.player2.deck);
		}

	}

//--------------------------- PIECE CLASS ----------------------------------------------------	
	
	public class Piece{
		
		//array of which directions on the piece has an opening in the order {N,E,S,W}
		boolean[] paths = new boolean[4];
		String treasure;
		//pass in -1 for x and y if the piece doesn't have a designated spot on the board
		//so it can get randomly placed later
		int[] spot = new int[2];
		
		Piece(boolean N, boolean E, boolean S, boolean W, String treasure, int x, int y){
			paths[0] = N;
			paths[1] = E;
			paths[2] = S;
			paths[3] = W;
 			this.treasure = treasure;
 			spot[0] = x;
 			spot[1] = y;
		}
		public boolean isitGlued(){
			if(spot[0]==-1){
				return false;
			}
			return true;
		}
		public boolean[] getPaths(){
			return paths;
		}
		public String getTreasure(){
			return treasure;
		}
		public int[] getSpot(){
			return spot;
		}
		
		public void rotateLeft(){
			boolean temp = paths[0];
			for(int i=0;i<4;i++){
				if(i<3){
					paths[i] = paths[i+1];
				} else{
					paths[3] = temp;
				}
			}
		}
		public void rotateRight(){
			boolean temp = paths[3];
			for(int i=0;i<4;i++){
				if(i<3){
					paths[i+1] = paths[i];
				} else{
					paths[0] = temp;
				}
			}
		}
		
	}
	
// ---------------------------- PLAYER CLASS -----------------------------------------------------	
	
	public class Player{
		String playerName;
		String playerColor;
		//Coordinates of player
		int[] location = new int[2];
		ArrayList<String> deck = new ArrayList();
		ArrayList<String> treasuresFound = new ArrayList();
		int cardsLeft;
		String currentTreasure;
		
		Player(String player, String color, int startX, int startY, ArrayList cardsDelt ){
			playerName = player;
			playerColor = color;
			location[0] = startX;
			location[1] = startY;
			deck = cardsDelt;
			cardsLeft = deck.size();
			currentTreasure = deck.get(0);
		}
		
		//if the player lands on their treasure
		public void flipCard(){
			cardsLeft--;
			treasuresFound.add(currentTreasure);
			deck.remove(currentTreasure);
			currentTreasure = deck.get(0);
		}
		public void updateLocation(int x, int y){
			location[0]=x;
			location[1]=y;
		}
		
		public int[] getLocation(){
			return location;
		}
		public String getColor(){
			return playerColor;
		}
		public String getName(){
			return playerName;
		}
		public String getCurrentCard(){
			return currentTreasure;
		}
		public ArrayList getWhatFlipped(){
			return treasuresFound;
		}
		public int getCardsLeft(){
			return cardsLeft;
		}
	}
	
}
