/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce1907700_v01;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * V01 - Lucky Number Game Main UI class to handle user interaction and game
 * flow
 *
 * @author Dinh Cong Phuc - CE190770 - 13/Jan/2025
 */
public class LuckyNumberUI {

    // Scanner for reading player input
    private Scanner sc = new Scanner(System.in);

    // Controls whether to continue playing
    private char continueGame;

    // Tracks total number of guesses across all games
    private int totalGuess = 0;

    // Stores and automatically sorts guess counts for each game
    private TreeSet<Integer> gamesGuessRecord = new TreeSet<>();

    // Tracks total number of games played
    private int totalGames = gamesGuessRecord.size();

    // Stores the average number of guesses per game
    private int avgGuess;

    /**
     * Main game loop method
     */
    public void play() {
        // Counter for guesses in current game
        int currentTotalGuess = 0;
        // Initialize continue flag
        continueGame = 'y';
        // Display welcome message
        System.out.println("Welcome to the Lucky Number Game!");

        // Main game loop
        do {
            // Create new game instance
            LuckyNumber ln = new LuckyNumber();
            // Generate random number for this round
            int randNum = ln.randNum();

            // Continue until correct number is guessed
            while (!ln.isLucky()) {
                // Get player's guess with input validation
                int inputN = inputGuess();
                // Process the guess and provide feedback
                ln.guesses(inputN, randNum);
                // Increment total guesses counter
                totalGuess++;
                // Increment current game guesses
                currentTotalGuess++;
            }

            // Increment total games counter
            totalGames++;
            // Display number of guesses for this game
            System.out.println("You guessed " + currentTotalGuess + " times");
            // Record number of guesses for this game
            gamesGuessRecord.add(currentTotalGuess);

            // Ask if player wants to play again
            System.out.println("Enter 'Y' to play again, or anything else to exit:");
            // Get and process player's response
            String again = sc.nextLine().toLowerCase().trim();
            // Check if player only press enter without input anything
            if (again.isEmpty()) {
                // Set continueGame to n to exit
                continueGame = 'n';
            } else {
                // Set continueGame based on whether input starts with 'y'
                continueGame = again.startsWith("y") ? 'y' : 'n';
            }
            // Continue if player enters anything starting with 'y' or 'Y'
        } while (continueGame == 'y');

        // Add spacing before final report
        System.out.println("\n\n\n");
        // Display goodbye message
        System.out.println("Thank you for playing!");
        // Show game statistics
        report();
    }

    /**
     * Method to find the game with least number of guesses
     *
     * @return Lowest guess count from past game
     */
    public int findBestGame() {
        // Check if any games have been played
        if (!gamesGuessRecord.isEmpty()) {
            // Return the lowest guess count
            return gamesGuessRecord.first();
        }
        // Return -1 if no games played
        return -1;
    }

    /**
     * Method to find average guesses from all games This method must be run
     * before displaying avgGuess
     */
    public void findAvgGuess() {
        // Calculate average with exception handling
        try {
            // Calculate average guess
            avgGuess = totalGuess / totalGames;
            // Catch divide by zero or any exception
        } catch (Exception e) {
            // Set avgGuess to 0 in case of exception
            avgGuess = 0;
        }
    }

    /**
     * Handles player input with validation
     * 
     * @return validated input to caller function
     */
    public int inputGuess() {
        // Variable to store valid input
        int inputN;

        // Continue until valid input received
        while (true) {
            try {
                // Prompt for guess
                System.out.println("What is your guess? ");
                // Read and validate input
                inputN = sc.nextInt();
                // Clear input buffer
                sc.nextLine();
                // Return valid input
                return inputN;
                // Catch exception if non-integer has been entered
            } catch (Exception e) {
                // Clear the invalid input from scanner
                sc.nextLine();
                // Inform user of invalid input
                System.out.println("Please enter a valid number!");
            }
        }
    }

    /**
     * Method to display game statistics
     */
    public void report() {
        // Display total number of games played
        System.out.println("Total games: " + totalGames);
        // Display total number of guesses made
        System.out.println("Total guesses: " + totalGuess);
        // Calculate average guess
        findAvgGuess();
        // Display average guesses per game
        System.out.println("Average guesses: " + avgGuess);
        // Display best game performance
        System.out.println("Your best game took " + findBestGame() + " guesses");
    }
}
