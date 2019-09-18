package battleship;

import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner kbReader = new Scanner(System.in);
	static Random random = new Random();
	
	public static void main(String[] args) {
		Display.welcome();
		
		while (true) {
			Game game = new Game();
			game.setup();
			game.play();
			if(playAgain()) {
				continue;
			}
			break;
		}
		
		System.out.println("\nThanks for playing!");
		kbReader.close();
	}
	
    static boolean playAgain() {
    	System.out.println("\nWould you like to play another game? Enter 'y' for yes, or 'n' for no.");
    	String playAgain = kbReader.next().toLowerCase();
    	
    	if (playAgain.charAt(0) == 'y') {
    		return true;
    	}
    	else if (playAgain.charAt(0) == 'n') {
    		return false;
    	}
    	else {
    		System.out.print("\nYour response could not be understood. Please try again.");
    		return playAgain();
    	}
    }
}