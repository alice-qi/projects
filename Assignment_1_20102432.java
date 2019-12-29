/*
 * Name: Alice Qi
 * Student ID: 20102432
 * Date: September 27, 2019
 * 
 * This program implements the Game of Pig. 
 * It allows the user to play a console version of the Game of Pig against the computer.
 * The dice rolls are randomly generated integers between 1 and 6.
 * The player and computer take turn rolling both dice.
 * The first player to reach a score of 100 wins.
 */

import java.util.Random;
import java.io.IOException;

public class Assignment_1_20102432 {
	//a Random generator with a different seed value every time the program runs
	static Random generator = new Random(System.currentTimeMillis());	

	// Displays Player's sum and Computer's sum after each turn
	// and press <enter> to start next round
	public static void displayTurnResults(int playerScore, int computerScore, boolean humansTurn, int round) {
		int numRead = -1;
		int dump = -1;
		if (humansTurn) {
			System.out.println("\nPlayer's sum is " + playerScore + ", Computer's sum is: " +  computerScore + ".\n");				
		}	
		else {
			System.out.println("\nPlayer's sum is " + playerScore + ", Computer's sum is: " +  computerScore + ". Press <enter> to start round " + round + ".\n");
			try {
				numRead  = System.in.read();
				dump  = System.in.read();
			}
			catch (IOException e) {
				System.out.println("Error reading from user!");
			}
		}					
	} // end displayTurnResults

	// Display player's turn sum and game sum after each roll and asks the player if roll again, returns "y" or "n"
	public static char displayRollResults(int turnScore, int gameScore) throws java.io.IOException {			
		byte[] buffer = new byte[80];
		String message = "Player's turn sum is: " + turnScore + " and game sum would be " + gameScore + ".";
		System.out.println(message);
		System.out.println("Roll again? (Enter 'y' to continue or others to hold): ");
		char userInput = (char) System.in.read();
		int numRead = System.in.read(buffer);		        				
		return userInput;				
	} // end displayRollResults

	// Convert the numbers 1 to 6 to the English words 
	public static String strVersion(int num) {
		String n = "";
		switch (num) { // num is an integer
		case 1:
			n = n + "one";
			break;
		case 2:
			n = n + "two";
			break;
		case 3:
			n = n + "three";
			break;
		case 4:
			n = n + "four";
			break;
		case 5:
			n = n + "five";
			break;
		case 6:
			n = n + "six";
			break;
		default:
			n = n + "";
			break;
		} // end switch
		return n;
	}

	// Display two dice numbers in English words after each roll
	public static void displayRollNum(String owner, int num1, int num2) {
		String n1 = strVersion(num1) ;
		String n2 = strVersion(num2) ;			
		System.out.println(owner + " rolled " + n1 + " + " + n2);			
	}

	// Returns the player's accumulating score
	public static int getPlayerRoll(int humanScore) throws java.io.IOException {
		boolean turn = true;
		int turnScore = 0;
		int pHumanScore = 0;
		System.out.println("Player's turn:");			
		while(turn && pHumanScore < 100) {				
			int num1 = (int)(generator.nextDouble() * 6 + 1);
			int num2 = (int)(generator.nextDouble() * 6 + 1);
			displayRollNum("Player", num1, num2);

			if(num1 == 6 && num2 == 6) {
				System.out.println("DOUBLE SIXES!");		
				pHumanScore = 0;
				turn = false;
			}			
			else if(num1 == 6 || num2 == 6){
				System.out.println("TURN OVER! Turn sum is zero!");
				//if (turnScore == 0)
					pHumanScore = humanScore;
				turn = false;
			}			
			else if(num1 == num2) {
				System.out.println("DOUBLES!");
				turnScore += (num1 + num2)*2;
				pHumanScore = humanScore + turnScore;
				String message = "Player's turn sum is: " + turnScore + " and game sum would be " + pHumanScore + ".";
				System.out.println(message);
				if (pHumanScore < 100)
					System.out.println("Player must roll again!");
			}			
			else {
				turnScore += num1 + num2;
				pHumanScore = humanScore + turnScore;
				char response = displayRollResults(turnScore, pHumanScore);
				if (Character.toLowerCase(response) != 'y')
					break;
			}		
		} // end while loop
		return pHumanScore;	
	} // end getPlayerRoll

	// Returns the computer's accumulating score, the computer will roll the dice until its turn score is over 30 or it has to stop its turn. 
	public static int getComputerRoll(int computerScore) {
		boolean turn = true;
		int turnScore = 0;
		int pComputerScore = 0;
		System.out.println("Computer's turn:");			
		while(turn && turnScore < 30 && pComputerScore < 100) {				
			int num1 = (int)(generator.nextDouble() * 6 + 1);
			int num2 = (int)(generator.nextDouble() * 6 + 1);
			//System.out.println("Computer rolled " + num1 + " + " + num2);
			displayRollNum("Computer", num1, num2);

			if(num1 == 6 && num2 == 6) {
				System.out.println("DOUBLE SIXES!");		
				pComputerScore = 0;
				turn = false;
			}			
			else if(num1 == 6 || num2 == 6){
				System.out.println("TURN OVER! Turn sum is zero!");
				if (turnScore == 0)
					pComputerScore = computerScore;
				turn = false;
			}			
			else if(num1 == num2) {
				System.out.println("DOUBLES!");
				turnScore += (num1 + num2)*2;
				pComputerScore = computerScore + turnScore;
				String message = "Computer's turn sum is: " + turnScore + " and game sum would be " + pComputerScore + ".";
				System.out.println(message);
				if (pComputerScore < 100)
					System.out.println("Computer must roll again!");
			}			
			else {
				turnScore += num1 + num2;
				pComputerScore = computerScore + turnScore;
				String message = "Computer's turn sum is: " + turnScore + " and game sum would be " + pComputerScore + ".";
				System.out.println(message);					
			}		
		}	// end while loop		
		return pComputerScore;
	} // end getComputerRoll

	// main starts the game.
	public static void main(String[] args) throws java.io.IOException {
		int playerScore = 0;
		int computerScore = 0;
		int round = 1;
		boolean humansTurn = true;		

		while (playerScore < 100 & computerScore < 100 ) {
			if (humansTurn) {
				playerScore = getPlayerRoll(playerScore);
				displayTurnResults(playerScore, computerScore, humansTurn, round);				
			}	
			else { 
				computerScore = getComputerRoll(computerScore);
				round += 1;
				if (computerScore < 100) {
					displayTurnResults(playerScore, computerScore, humansTurn, round);					
				}
			}					
			if (playerScore >= 100)
				System.out.println("\nYou win!");
			else if (computerScore >= 100)
				System.out.println("\nComputer wins!");
			humansTurn = !humansTurn;			
		} // end while loop
	} // end main

}



