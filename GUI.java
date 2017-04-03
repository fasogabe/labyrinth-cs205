/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthram;

import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.util.Arrays;

/**
 *
 * @author seanflynn
 */
public class GUI extends GameGrid {
    
    public static GGTileMap tileMap;
    public static GGTileMap spareMap;
    Piece spare = Labyrinth.spare;

    //--------------------------- GUI CALLS THESE ------------------------------------------------
    //GUI CLASS CAN ALSO CALL METHODS LISTED HERE:
    //REGULAR USE:
    //getWhoseTurn()
    //getBoard()
    //MUST USE player.method():	(getWhoseTurn() returns player)
    //getLocation()			-returns coordinates of players location
    //getColor()			-returns color of players token
    //getName()			-returns players name
    //getCurrentCard()		-returns the treasure they're looking for
    //getWhatFlipped()		-returns an ArrayList of all the cards the player has flipped over (found treasures for)
    //getCardsLeft()		-returns how many cards in the deck
    //MUST USE piece.method():   (getBoard() returns Piece[][], so let x = getBoard(), then x[i][j] is a piece)
    //getPaths()			-returns boolean[] of {north,east,south,west}, when true then you can move that direction
    //getTreasure()			-returns what treasure is on the piece, if null there is no treasure
    //getSpot()			-returns int[] of {x,y} coordinates of where the piece is
    //all the methods bellow must be changed by Sean
    //these methods will be called throughout the code above written by Alex and should provoke methods in Sean's GUI class
    
    public  GUI(){
        super(7, 7, 62, java.awt.Color.white, false);
        GUI.tileMap = createTileMap(7,7,62,62);
        GUI.tileMap.setPosition(new Point(0,0));
//        GUI.spareMap.setPosition(new Point(500,500));
        
        super.setTitle("Labyrinth");

        show();
        
    }
   
    void addTile(String type, int orientation, String treasure, int x, int y) {
        String imageName = type+"-"+orientation+".png";
        System.out.println(orientation);
        tileMap.setImage("sprites/L-1.png", 6-y, 6-x);
        
        
    }
    public void showGui(){
        show();
    }

    static void rotateLeft(Piece piece) { 
        int x = piece.spot[0];
        int y = piece.spot[1];
        String imageName = piece.type+"-"+piece.orientation+".png";
        
                tileMap.setImage(imageName, new Location(x,y));
       
    }

    static void rotateRight( Piece piece) {
        int x = piece.spot[0];
        int y = piece.spot[1];
        String imageName = piece.type+"-"+piece.orientation+".png";
        
        tileMap.setImage(imageName, new Location(x,y));
        
        
    }

    public static String getPlayer1Name() {
        //text box input
        return "ram";
    }

    public static boolean whoToPlay() {
        //maybe radio buttons here?

        //if user chooses to play a cp return true
        return true;
    }
    //only called when whoToPlay() returns false

    public static String getPlayer2Name() {
        //text box input
        return "sean";
    }
    //click on one of the orange triangles around the board to insert piece here
    //label them based on the closest board spot to the triangle

    public static int[] getInsertLocation() {
        //user input should be returned
        //{x,y}
        int[] topLeftTriangle = {1, 6};
        return topLeftTriangle;
    }
    //this method will get called again if the user chooses a path that doesn't work

    public static int[] wantToMoveHere() {
        //user input
        int[] loc = {0, 6};
        return loc;
    }

    //these methods need a button that calls them
    public void rotateSpareRight() {
        spare.rotateRight();
    }

    public void rotateSpareLeft() {
        spare.rotateLeft();
    }


    void displayBoard(Piece[][] board) {
        for (int i=0;i<7;i++){
            for (int j=0;j<7;j++){
                Piece piece = board[6-j][6-i];
                String type = piece.type;
                int orientation = piece.orientation;            
                String imageName = "sprites/"+type+"-"+orientation+".png";
                tileMap.setImage(imageName, 6-j, i);//because Ram made origin top left instead of bottom left
                
            }
        }
        refresh();
    }
    
    
}
