/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import ch.aplu.jgamegrid.Actor;

/**
 *
 * @author seanflynn
 */
public class Piece extends Actor{
    
    //array of which directions on the piece has an opening in the order {N,E,S,W}
    boolean[] paths = new boolean[4];
    String treasure;
    int orientation;
    String type;
    //pass in -1 for x and y if the piece doesn't have a designated spot on the board
    //so it can get randomly placed later
    int[] spot = new int[2];
    

    Piece(String type, int orientation, String treasure, int x, int y) {
        super(true,"sprites/"+type+"-"+orientation+".png");
        this.treasure = treasure;
        this.orientation = orientation;
        this.type = type;
        spot[0] = x;
        spot[1] = y;
        setPaths(this);
    }

    public boolean isitGlued() {
        if (spot[0] == -1) {
            return false;
        }
        return true;
    }
    
    public boolean[] getPaths() {
        return paths;
    }

    public String getTreasure() {
        return treasure;
    }

    public int[] getSpot() {
        return spot;
    }

    public void rotateLeft() { //Counter Clockwise
        boolean temp = paths[0];
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                paths[i] = paths[i + 1];
            } else {
                paths[3] = temp;
            }
           }
        

        if (orientation==3){
            orientation=0;
        }
        else{
            orientation++;
        }
        
       //GUI.rotateLeft(spot,orientation); 
    }

    public void rotateRight() { // Clockwise
        boolean temp = paths[3];
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                paths[i + 1] = paths[i];
            } else {
                paths[0] = temp;
            }
        }
       
       if (!isitGlued()) {
           orientation++;
           orientation%=4;
       }
        }
    
    public final void setPaths(Piece piece){
        if ("I".equals(piece.type)){
            paths[0]=true;
            paths[1]=false;
            paths[2]=true;
            paths[3]=false;
        }
        else if ("L".equals(piece.type)){
            paths[0]=true;
            paths[1]=true;
            paths[2]=false;
            paths[3]=false;
            
        }
        else if ("T".equals(piece.type)){
            paths[0]=true;
            paths[1]=true;
            paths[2]=true;
            paths[3]=false;
            
        }
        int rotations = piece.orientation;
        for (int i=0;i<rotations;i++){ // doesn't rotate if orientation is 0 otherwise makes paths match based on 
            piece.rotateRight(); 
            
        }
                
    }

}
