package battleship;

/*
 * Methods for game setup
 */

class Setup extends Game{
    
	static void main() {
		int gamemode = getGamemode();
		if (gamemode == 1) {
			standard();
		}
		else if (gamemode == 2) {
			custom();
		}
    }
    
	static int getGamemode() {
		while (true) {
			System.out.print("Would you like to play a 'standard' game or a 'custom' game? ");
			String choice = kbReader.nextLine();
			
			if (choice.equals("standard")) {
				return 1;
			} else if (choice.equals("custom")) {
				return 2;
			} else {
				System.out.println("Oops, your choice was not understood. Please try again.");
				continue;
			}
		}
	}
	
    static void standard() {
    	
    	System.out.println("---------- Standard Game ----------");
    	
        playerBoard = new Board(10, "Your Board");
        computerBoard = new Board(10, "Computer's Board");
        
        shipSetup("Carrier", 5);
        shipSetup("Battleship", 4);
        shipSetup("Cruiser", 3);
        shipSetup("Submarine", 3);
        shipSetup("Destroyer", 2);
        
        int smallestShip = 2;
    	computer = new AI(smallestShip);
    }
    
    static void custom() {
    	
    	System.out.println("\n---------- Custom Game Setup ----------");

    	
    	int boardsize = getBoardsize();
    	playerBoard = new Board(boardsize, "Your Board");
        computerBoard = new Board(boardsize, "Computer's Board");
        
    	createShips(boardsize);
    	int smallestShip = boardsize; //Largest possible ship
    	
    	for (int i=0; i<playerBoard.boardShips.size(); i++) {
            Ship ship = playerBoard.boardShips.get(i);
    		if (ship.shipLength < smallestShip) {
            	smallestShip = ship.shipLength;
            }
        }
    	computer = new AI(smallestShip);
    }
    
    static void shipSetup(String shipName, int shipLength) {
    	Ship playerShip = new Ship(shipName, shipLength);
    	playerBoard.addShip(playerShip);
    	playerShip.declareBoard(playerBoard);
    	
    	Ship computerShip = new Ship(shipName, shipLength);
    	computerBoard.addShip(computerShip);
    	computerShip.declareBoard(computerBoard);
    }
    
    static int getBoardsize() {
		System.out.println("What size would you like your board to be?");
    	while (true) {
	    	try {
		    	System.out.print("Enter a positive integer (maximum 32): ");
		    	int boardsize = Integer.parseInt(kbReader.nextLine());
		    	if (boardsize > 0 && boardsize < 33) {
		    		return boardsize;
		    	} else {
		    		System.out.println("Oops, your input was not a number from 1 to 32. Please try again.");
		    	}
	    	} catch (NumberFormatException e) {
	    		System.out.println("Your input could not be understood. Please try again.");
	    	}
    	}
    }
    
    static void createShips(int boardsize) {
    	int numberOfShips = getNumberOfShips();    	
    	for (int i=0; i<numberOfShips; i++) {
    		System.out.println("\n---------- Creation of ship " + (i+1) + " ----------");
    		createCustomShip(boardsize);
    	}	
    }
    
    static int getNumberOfShips() {
		System.out.println("How many ships would you like your game to have?");
    	while (true) {
	    	try {
		    	System.out.print("Enter a positive integer: ");
		    	int numberOfShips = Integer.parseInt(kbReader.nextLine());
		    	if (numberOfShips > 0) {
		    		return numberOfShips;
		    	} else {
		    		System.out.println("You must have at least 1 ship. Please try again.");
		    	}
		    	
	    	} catch (NumberFormatException e) {
	    		System.out.println("Your input could not be understood. Please try again.");
	    	}
    	}
    }
    
    static void createCustomShip(int boardsize) {
    	
    	System.out.print("Give your custom ship a name: ");
    	String shipName = kbReader.nextLine();
    	
    	int shipLength;
		System.out.println("What length would you like the " + shipName + " to be?");
    	while (true) {
	    	try {
		    	System.out.print("Enter a positive integer (maximum length " + boardsize + "): ");
		    	shipLength = Integer.parseInt(kbReader.nextLine());
		    	if (shipLength > 0 && shipLength <= boardsize) {
		    		break;
		    	} else {
		    		System.out.println("Your input was not a number from 1 to " + boardsize + ". Please try again.");
		    	}
	    	} catch (NumberFormatException e) {
	    		System.out.println("Your input could not be understood. Please try again.");
	    	}
    	}
    	
    	shipSetup(shipName, shipLength);
    }
}