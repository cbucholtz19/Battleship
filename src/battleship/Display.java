package battleship;

/**
 * Contains the method that displays both boards side by side
 */

class Display{
    
	static void displayBoards(Board playerBoard, Board computerBoard) {
        int boardSize = playerBoard.boardSize;
		
		//First line
		System.out.print("\n" + playerBoard.boardName + ":"); //Your Board: 
		
		int fill; //Fill whitespace
		if (boardSize <= 5) {
			fill = 5;
		} else if (boardSize <= 9) {
			fill = 2*boardSize - 5;
		} else {
			fill = 11;
		}
		for (int i=0; i<fill; i++) {
			System.out.print(" ");
		}
		
        if (boardSize >= 10) {
        	for (int i=11; i <= boardSize; i++) { //Iterate through each boardsize above 10
        		if (i%2 == 1) { //if odd print number
        			System.out.print(i);
        		} else { //if even print two spaces
        			System.out.print("  ");
        		}
        	}
        	System.out.print("     ");
        }
        
        System.out.print(computerBoard.boardName + ":"); //Computer's Board:
        
        if (boardSize >= 10) {
        	System.out.print("    ");
        	for (int i=11; i <= boardSize; i++) { //Iterate through each boardsize above 10
        		if (i%2 == 1) { //if odd print number
        			System.out.print(i);
        		} else { //if even print two spaces
        			System.out.print("  ");
        		}
        	}
        	System.out.print("     ");
        }
        System.out.println();
        
        
        //Second Line
        System.out.print("  ");
        for (int i=0; i<2; i++) {
            for (int numHead = 1; numHead <= boardSize; numHead++) {
                if (numHead < 10) {
                	System.out.print(numHead + " ");
                } else if (numHead%2 == 0) {
                	System.out.print(numHead);
                } else {
                	System.out.print("  ");
                }
            }
            System.out.print("      ");
        }
        System.out.println();
        
        
        
        //Board Rows
        for (int y = 0; y < playerBoard.boardSize; y++) {
            //Display alphabetical header of the row
            System.out.print((char)(y + 65) + " ");

            //Iterate through the indexes of the row
            for (int x = 0; x < playerBoard.boardSize; x++) {
                System.out.print(playerBoard.coordinateArray.get(y).get(x).coordDisplay);
                if (x != playerBoard.boardSize-1) {
                    System.out.print(" ");
                }
            }
            System.out.print("     ");
            
            System.out.print((char)(y + 65) + " ");

            //Iterate through the indexes of the row
            for (int x = 0; x < computerBoard.boardSize; x++) {
                System.out.print(computerBoard.coordinateArray.get(y).get(x).coordDisplay);
                if (x != computerBoard.boardSize-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
	
	static void welcome() {
		System.out.println("__        __   _                            _          ____        _   _   _           _     _       _ ");
		System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___  | |_ ___   | __ )  __ _| |_| |_| | ___  ___| |__ (_)_ __ | |");
		System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  |  _ \\ / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\| |");
		System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) | | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |_|");
		System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/(_)");
		System.out.println("                                                                                               |_|     ");
	}
	
	static void win() {
		System.out.println("__   __           __        ___       _ ");
		System.out.println("\\ \\ / /__  _   _  \\ \\      / (_)_ __ | |");
		System.out.println(" \\ V / _ \\| | | |  \\ \\ /\\ / /| | '_ \\| |");
		System.out.println("  | | (_) | |_| |   \\ V  V / | | | | |_|");
		System.out.println("  |_|\\___/ \\__,_|    \\_/\\_/  |_|_| |_(_)");
	}
	
	static void lose() {
		System.out.println("__   __            _                            ");
		System.out.println("\\ \\ / /__  _   _  | | ___  ___  ___             ");
		System.out.println(" \\ V / _ \\| | | | | |/ _ \\/ __|/ _ \\            ");
		System.out.println("  | | (_) | |_| | | | (_) \\__ \\  __/  _   _   _ ");
		System.out.println("  |_|\\___/ \\__,_| |_|\\___/|___/\\___| (_) (_) (_)");
	}
	
	static void playerHit() {
		System.out.println("     ___|___                                       ");
		System.out.println("    /  _|_  \\     __   __            _   _ _ _   _ ");
		System.out.println("   /  / | \\  \\    \\ \\ / /__  _   _  | | | (_) |_| |");
		System.out.println("——|——|——•——|——|——  \\ V / _ \\| | | | | |_| | | __| |");
		System.out.println("   \\  \\_|_/  /      | | (_) | |_| | |  _  | | |_|_|");
		System.out.println("    \\___|___/       |_|\\___/ \\__,_| |_| |_|_|\\__(_)");
		System.out.println("        |                                          ");
	}
	
	static void computerHit() {
		System.out.println("     ___|___                                                                                        ");
		System.out.println("    /  _|_  \\      _____ _             ____                            _              _   _ _ _   _ ");
		System.out.println("   /  / | \\  \\    |_   _| |__   ___   / ___|___  _ __ ___  _ __  _   _| |_ ___ _ __  | | | (_) |_| |");
		System.out.println("——|——|——•——|——|——   | | | '_ \\ / _ \\ | |   / _ \\| '_ ` _ \\| '_ \\| | | | __/ _ \\ '__| | |_| | | __| |");
		System.out.println("   \\  \\_|_/  /      | | | | | |  __/ | |__| (_) | | | | | | |_) | |_| | ||  __/ |    |  _  | | |_|_|");
		System.out.println("    \\___|___/       |_| |_| |_|\\___|  \\____\\___/|_| |_| |_| .__/ \\__,_|\\__\\___|_|    |_| |_|_|\\__(_)");
		System.out.println("        |                                                 |_|                                       ");
	}
}
