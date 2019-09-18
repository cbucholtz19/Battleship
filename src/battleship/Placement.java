package battleship;
import java.util.ArrayList;

/*
 * Methods for placement of player and computer ships
 */

class Placement extends Game {
    
	static void main() {
		System.out.println("\nStart by placing your ships onto the game board.");
        Display.displayBoards(playerBoard, computerBoard);
        System.out.println("\nThere are " + playerBoard.boardShips.size() + " ships for you to place.");
        System.out.println("Place your ships by designating a coordinate and the direction your ship will extend from that coordinate.");
		playerPlacement();
        computerPlacement();
	}
	
	static void playerPlacement() { //Place all the player's ships
        for (int i = 0; i<playerBoard.boardShips.size(); i++) { //Iterate through the array of player ships
            Ship ship = playerBoard.boardShips.get(i); 
            System.out.println("\n---------- Placement of your " + ship.shipName + " (ship length: " + ship.shipLength + ") ----------");
            placePlayerShip(ship);
            Display.displayBoards(playerBoard, computerBoard); //After each ship is placed, display the board
        }
    }

    static void placePlayerShip(Ship ship) { //Places a single player ship
    	
        boolean placementSuccessful = false; 
        while (placementSuccessful == false) { //Loop until placement is successful
        	try { //Whole loop catches format errors in input
        		
	            String startPoint = getStartPoint();
	            int startY = startPoint.charAt(0) - 65;
	            int startX = Integer.parseInt(startPoint.substring(1)) -1;
	            
	            String direction = getDirection();
	
	            boolean coordinatesExist = true;
	            ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	            
	            //Each if statement tests if the ship would be on the board
	            if (direction.charAt(0) == 'u' && startY - (ship.shipLength - 1) > -1) { //up
	                for (int i=0; i<ship.shipLength; i++) {
	                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(startY - i).get(startX);
	                    coordinates.add(coordinate);
	                }
	            } else if (direction.charAt(0) == 'd' && startY + (ship.shipLength - 1) < playerBoard.boardSize) { //down
	                for (int i=0; i<ship.shipLength; i++) {
	                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(startY + i).get(startX);
	                    coordinates.add(coordinate);
	                }
	            }
	            else if (direction.charAt(0) == 'l' && startX - (ship.shipLength - 1) > -1) { //left
	                for (int i=0; i<ship.shipLength; i++) {
	                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(startY).get(startX - i);
	                    coordinates.add(coordinate);
	                }
	            } else if (direction.charAt(0) == 'r' && startX + (ship.shipLength - 1) < playerBoard.boardSize) { //right
	                for (int i=0; i<ship.shipLength; i++) {
	                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(startY).get(startX + i);
	                    coordinates.add(coordinate);
	                }
	            } else { //If the ship would be off the board, then placement was not successful
	                coordinatesExist = false;
	            }
	            
	            
	            if (coordinatesExist && checkCoordinatesUnoccupied(coordinates)) { //Test to see if the ship has not been placed on top of any other ships
	                placementSuccessful = true;
	            	addCoordinatesToShip(ship, coordinates); //Add the Coordinates to the Ship
	                addShipToCoordinates(ship); //Add the Ship to the Coordinates
	                ship.displayOnBoard(); //Update the display of the coordinate objects
	            } else {
	                placementSuccessful = false;
	                System.out.println("Oops! Your response was in the wrong format or the placement could not be processed.");
	                System.out.println("Make sure that your placement fits within the game board and does not collide with any other ships.");
	            }
	            
	        } catch (NumberFormatException e) {
            	System.out.println("Oops, there was a format error while placing your ship. Please try again.");
            }
        }       
    }

    static void computerPlacement() { //Main computer placement method
        for (int i = 0; i<computerBoard.boardShips.size(); i++) {
            Ship ship = computerBoard.boardShips.get(i);
            placeComputerShip(ship);
        }
    }

    static void placeComputerShip(Ship ship) { //Places a single computer ship, the same steps as player placement but with random values
        //Loop until placement is successful
        boolean placementSuccessful = false;
        while (placementSuccessful == false) {
            
            //Generate random starting coordinate and direction
            int coordY = random.nextInt(computerBoard.boardSize);
            int coordX = random.nextInt(computerBoard.boardSize);
            int direction = random.nextInt(4); //0 up, 1 down, 2 left, 3 right
            
            ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
            boolean coordinatesExist = true;
            
            if (direction == 0 && coordY - (ship.shipLength - 1) > -1) { //up
                for (int i=0; i<ship.shipLength; i++) {
                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(coordY - i).get(coordX);
                    coordinates.add(coordinate);
                }
            } else if (direction == 1 && coordY + (ship.shipLength - 1) < computerBoard.boardSize) { //down
                for (int i=0; i<ship.shipLength; i++) {
                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(coordY + i).get(coordX);
                    coordinates.add(coordinate);
                }
            } else if (direction == 2 && coordX - (ship.shipLength - 1) > -1) { //left
                for (int i=0; i<ship.shipLength; i++) {
                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(coordY).get(coordX - i);
                    coordinates.add(coordinate);
                }
            } else if (direction == 3 && coordX + (ship.shipLength - 1) < computerBoard.boardSize) { //right
                for (int i=0; i<ship.shipLength; i++) {
                    Coordinate coordinate = ship.shipBoard.coordinateArray.get(coordY).get(coordX + i);
                    coordinates.add(coordinate);
                }
            } else { //If placement is off board
                coordinatesExist = false;
            }

            
            if (coordinatesExist && checkCoordinatesUnoccupied(coordinates)) {
                placementSuccessful = true;
                addCoordinatesToShip(ship, coordinates);
                addShipToCoordinates(ship);
            } else {
                placementSuccessful = false;
            }
        }
    }

    static String getStartPoint() {
        while (true) {
	        try { //Check for bad input
	        	System.out.print("What coordinate would you like your ship to start at? (\"a5\", \"f7\", \"d10\", etc.) ");
	            String startPoint = kbReader.next().toUpperCase();
	           	int y = startPoint.charAt(0) - 65;
	            int x = Integer.parseInt(startPoint.substring(1)) -1;
	            if (y > -1 && y < computerBoard.boardSize && x > -1 && x < computerBoard.boardSize) {
	                return startPoint;
	            } else {
	                System.out.println("Your input was not on the board. Please try again.");
	            }
	        } catch (NumberFormatException e) {
	        	System.out.println("Your input was not in the correct format. Please try again.");
	        } 
        }
    }
    
    static String getDirection() {
    	System.out.print("In which direction would you like the ship to extend? (\"up\", \"down\", \"left\", \"right\", or just the first letter of each) ");
        return kbReader.next().toLowerCase();
    }
    
    static boolean checkCoordinatesUnoccupied(ArrayList<Coordinate> coordinates) { //Returns whether the coordinates are already occupied
        for (int i=0; i<coordinates.size(); i++) {
            if (coordinates.get(i).shipAtCoord != null) { //If a ship already exists at that coordinate
                return false;
            }
        }
        return true; //If all coordinates pass the test
    }
    
    static void addCoordinatesToShip(Ship ship, ArrayList<Coordinate> coordinates) { //Adds coordinates to the ship object
        for (int i=0; i<coordinates.size(); i++) {
            ship.shipCoordinates.add(coordinates.get(i));
        }
    }
    
    static void addShipToCoordinates(Ship ship) { //Adds ship to the coordinate objects
        for (int i=0; i<ship.shipCoordinates.size(); i++) {
            Coordinate coordinate = ship.shipCoordinates.get(i);
            coordinate.addShip(ship);
        }
    }
}