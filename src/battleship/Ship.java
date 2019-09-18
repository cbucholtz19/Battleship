package battleship;
import java.util.ArrayList;

/*
 * Ship class
 */

class Ship {
    String shipName;
    int shipLength;
    Board shipBoard;
    ArrayList<Coordinate> shipCoordinates = new ArrayList<Coordinate>();
    boolean shipSunk = false;

    Ship(String name, int length) { //Constructor
        shipName = name;
        shipLength = length;
        //shipBoard = board;
    }
    
    void declareBoard(Board board) {
    	shipBoard = board;
    }
    
    void displayOnBoard() { //Edits the display of the ship's coordinates
        for (int i=0; i<shipCoordinates.size(); i++) {
            shipCoordinates.get(i).editDisplay('O');
        }
    }
    
    void updateStatus() { //Checks all coordinates of the ship and updates the sunk status accordingly
        boolean newStatus = true;
        for (int i=0; i<shipCoordinates.size(); i++) {
            if (shipCoordinates.get(i).coordDisplay != 'X') {
                newStatus = false;
            }
        }
        shipSunk = newStatus;
    }
}