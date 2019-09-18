package battleship;
import java.util.ArrayList;

/*
 * Computer Opponent AI Class
 */

class AI extends Game{
    
	int smallestShip;
	
	boolean firstGuess = true;
    ArrayList<Coordinate> coordsHitNotSunk = new ArrayList<Coordinate>();

    //For guessing phase
    int guessingFactor;

    //For seeking phase
    int seekingDirection = -1; // -1 = not set, 0 = up, 1 = left, 2 = right, 3 = down
    boolean foundAxis = false;
    boolean directionFlipped = false;

    AI(int smallestShipSize) {
    	smallestShip = smallestShipSize;
    }

    void turn() {
        //Get the target that the computer will guess
        Coordinate target = getTarget();

        if (target.shipAtCoord != null) { //If the target is a hit
            Display.computerHit();
            target.editDisplay('X');

            coordsHitNotSunk.add(target); //Add the target to a list of coordinates that are hit
            if (seekingDirection != -1) {
                foundAxis = true;
            }

            Ship ship = target.shipAtCoord;
            ship.updateStatus(); //Update the sunk status of the ship that was hit

            if (ship.shipSunk) { //If the ship is sunk
                System.out.println("The computer sunk your " + ship.shipName + "!");
                seekingDirection = -1; //Reset the seeking direction
                foundAxis = false; //Reset that the axis has been found
                directionFlipped = false;

                //Remove the ship's coordinates from the list of hit but not sunk coordinates
                for (int i=0; i<ship.shipCoordinates.size(); i++) {
                    Coordinate coordinate = ship.shipCoordinates.get(i);
                    coordsHitNotSunk.remove(coordsHitNotSunk.indexOf(coordinate));
                }
                
                if (playerBoard.allShipsSunk()) { //If all ships are sunk
                    gameInProgress = false; //Game ends
                    playerWin = false; //Computer wins
                }
            }
        } else { //If the computer missed
            System.out.println("The computer missed."); 
            target.editDisplay('*');

            if (foundAxis) { //If the axis of the ship is found, switch the the opposite seeking direction
                if (directionFlipped) {
                    foundAxis = false;
                    seekingDirection = -1;
                }
                else {
                    seekingDirection = 3 - seekingDirection;
                    directionFlipped = true;
                }
            }
            else { //If axis not found, reset the seeking direction
                seekingDirection = -1;
            }
        }

        target.guessed = true; //The target has now been guessed
    }

    Coordinate getTarget() {
        Coordinate target = new Coordinate(-1, -1); //Placeholder because code is annoying
        boolean targetFound = false; //Used to loop until a guess is found

        int y = -1; //Also a placeholder because code is annoying
        int x = -1;

        if (firstGuess) {  //Only triggered once in a game, purely a random guess         
            y = random.nextInt(playerBoard.boardSize);
            x = random.nextInt(playerBoard.boardSize);
            target = playerBoard.coordinateArray.get(y).get(x);
            
            //Set the guessing factor, this allows for an optimized guessing pattern based on the smallest ship
            guessingFactor = (y+x) % smallestShip;
            firstGuess = false;
        }

        else if (coordsHitNotSunk.size() == 0) { //Guessing phase    
            while (!targetFound) {
                //Pick random coordinate on the board
                y = random.nextInt(playerBoard.boardSize);
                x = random.nextInt(playerBoard.boardSize);

                target = playerBoard.coordinateArray.get(y).get(x);

                //Check that the guess fits in the pattern needed to find the smallest ship and that it hasn't been guessed before
                if ( (target.coordY + target.coordX) % smallestShip == guessingFactor && target.guessed == false) {
                    targetFound = true;
                }
            }
        }

        else { //Seeking phase
            //Start with the first coordinate hit on ship
            Coordinate seekingCoord = coordsHitNotSunk.get(0);
            y = seekingCoord.coordY;
            x = seekingCoord.coordX;

            while (!targetFound) {
                
                //If no seeking direction set, pick a random one
                if (seekingDirection == -1) {
                    seekingDirection = random.nextInt(4);
                }

                //Find the next coordinate to guess based on the direction
                if (seekingDirection == 0) { //up
                    y = y - 1;
                } else if (seekingDirection == 1) { //left
                    x = x - 1;
                } else if (seekingDirection == 2) { //right
                    x = x + 1;
                } else if (seekingDirection == 3) { //down
                    y = y + 1;
                }

                if (y > -1 && y < playerBoard.boardSize && x > -1 && x < playerBoard.boardSize) { //If guess on board
                    //Take that coordinate
                    target = playerBoard.coordinateArray.get(y).get(x);

                    if (target.guessed == false) { //If it has never been guessed, exit the loop
                        targetFound = true;
                    } 
                    else { //If it has been guessed already
                        if (target.shipAtCoord == null) { //If there is not a ship there
                            if (foundAxis) { //If the axis of the ship has been found
                                if (directionFlipped) { //If the direction has already been reversed
                                    //Reset
                                    foundAxis = false;
                                    seekingDirection = -1;
                                }
                                else { //If the direction has not been reversed
                                    //Reverse the direction
                                    seekingDirection = 3 - seekingDirection;
                                    
                                    directionFlipped = true;
                                }
                            }
                            else { //Reset
                                seekingDirection = -1;
                            }
                            //Go back to the first coordinate hit
                            y = seekingCoord.coordY;
                            x = seekingCoord.coordX;
                        }

                        //If there is a ship there, let the loop restart and continue in the same direction
                    }
                }
                else { //If guess not on board
                    if (foundAxis) { //If the axis of the ship has been found
                        if (directionFlipped) { //If the direction has already been reversed
                            //Reset
                            foundAxis = false;
                            seekingDirection = -1;
                        }
                        else {
                            seekingDirection = 3 - seekingDirection;
                            directionFlipped = true;
                        }
                    }
                    else { //Otherwise, just reset the guessing direction
                        seekingDirection = -1;
                    }
                    //Go back to the first coordinate hit
                    y = seekingCoord.coordY;
                    x = seekingCoord.coordX;
                }
            }
        }

        return target;
    }
}