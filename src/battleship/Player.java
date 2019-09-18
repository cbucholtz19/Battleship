package battleship;

/*
 * Contains the method for the human player's turn
 */

class Player extends Game{
    
	static void turn() {
		
        Coordinate target = new Coordinate(-1, -1); //Placeholder

        boolean targetFound = false;
        while (!targetFound) { //Find the player's guess
            System.out.print("What coordinate would you like to guess? ");
            String input = kbReader.next().toUpperCase();
            
            try { //Check for bad input
	           	int y = input.charAt(0) - 65;
	            int x = Integer.parseInt(input.substring(1)) -1;
	
	            if (y > -1 && y < computerBoard.boardSize && x > -1 && x < computerBoard.boardSize) {
	                target = computerBoard.coordinateArray.get(y).get(x);
	
	                if (target.guessed == false) {
	                    targetFound = true;
	                }
	            }
	            
	            if (!targetFound) {
	                System.out.println("Your guess did not go through. Make sure your target is on the board and has not yet been guessed.");
	            }
            } catch (NumberFormatException e) {
            	System.out.println("Your guess was not in the correct format. Make sure it is in the form (\"a5\", \"f7\", \"d10\", etc.)");
            } 
        }

        if (target.shipAtCoord != null) { //If hit
            Display.playerHit();
            target.editDisplay('X');

            Ship ship = target.shipAtCoord;
            ship.updateStatus();

            if (ship.shipSunk) { //Check if the ship is sunk
                System.out.println("You sunk the computer's " + ship.shipName + "!");
                if (computerBoard.allShipsSunk()) { //Check if all ships are sunk
                    gameInProgress = false;
                    playerWin = true;
                }
            }
        } else { //If miss
            System.out.println("You missed.");
            target.editDisplay('*');
        }
        target.guessed = true; //Update the coordinate to be guessed
    }
}