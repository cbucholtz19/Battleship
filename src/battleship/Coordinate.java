package battleship;

/*
 * Coordinate class
 */

class Coordinate{
    char coordDisplay = '-';
    Ship shipAtCoord;
    boolean guessed = false;
    int coordY;
    int coordX;
    
    Coordinate(int y, int x) {
        coordY = y;
        coordX = x;
    }
    
    void addShip(Ship ship) { //Add a ship to the coordinate object
        shipAtCoord = ship;
    }
    
    void editDisplay(char newDisplay) { //Edit the display of the coordinate
        coordDisplay = newDisplay;
    }
}