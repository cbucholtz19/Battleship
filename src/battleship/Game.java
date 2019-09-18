package battleship;

/*
 * Game class
 */

class Game extends Main{
	
	//Create game-wide variables and objects
    static Board playerBoard, computerBoard; 
    static AI computer;
    
    static boolean gameInProgress;
    static boolean playerWin;
    
    Game() {
    	
    }
    
    void setup() {
    	Setup.main();
    }
    
    void play() { //plays the game
        
    	//Placement of ships
    	Placement.main();
        
        //Gameplay loop
        gameInProgress = true;
        while (gameInProgress) {
        	System.out.println("\n---------- Your Turn ----------");
        	Player.turn();
            
            if (gameInProgress) { //Check to see if the game has ended
            	System.out.println("\n---------- Computer's Turn ----------");
            	computer.turn();
            }
            
            Display.displayBoards(playerBoard, computerBoard); //After the turns are evaluated, display the boards
        }
        
        //End of game message
        if (playerWin) {
            Display.win();
        } else {
            Display.lose();
        }
    }
}