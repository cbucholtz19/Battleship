package battleship;
import java.util.ArrayList;

/*
 * Board class
 */

class Board{
    String boardName;
    int boardSize;
    ArrayList<ArrayList<Coordinate>> coordinateArray = new ArrayList<ArrayList<Coordinate>>(); //2D array of coordinates
    ArrayList<Ship> boardShips = new ArrayList<Ship>(); //ships on the board
    
    Board(int size, String name) {
        boardName = name;
        boardSize = size;
        
        //Create the coordinate array
        for (int y = 0; y<size; y++) { //Iterate through each row
            ArrayList<Coordinate> row = new ArrayList<Coordinate>(); //Create the row
            for (int x =0; x<size; x++) { //Add each item into the row
                Coordinate coord = new Coordinate(y, x);
                row.add(coord);
            }
            coordinateArray.add(row); //Add the row to the board
        }
    }
    
    void addShip(Ship ship) { //Add a ship to the list of ships that the board contains
        boardShips.add(ship);
    }
    
    boolean allShipsSunk() { //Returns whether all the ships on the board have been sunk
        for (int i = 0; i<boardShips.size(); i++) {
            if (boardShips.get(i).shipSunk == false) {
                return false;
            }
        }
        return true;
    }
}