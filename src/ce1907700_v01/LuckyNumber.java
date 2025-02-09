/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce1907700_v01;

import java.util.Random;

/**
 * V01 - Lucky Number Game This class handle the core logic of the Lucky Number
 * game
 *
 * @author Dinh Cong Phuc - CE190770 - 13/Jan/2025
 */
public class LuckyNumber {

    // Flag to track if the player has guessed the correct number
    private boolean isLucky = false;

    /**
     * Generates a random number between 0 and 100
     *
     * @return random integer between 0-100
     */
    public int randNum() {
        // Generate a random number between 0-100 (exclusive)
        int randNum = new Random().ints(0, 101).findFirst().getAsInt();
        // Inform about random number generated
        System.out.println("A random number has been generated between 0 and 100");
        // Return the generated random number
        return randNum;
    }

    /**
     * Checks if the player's guess matches the random number
     *
     * @param inputN The player's guessed number
     * @param randNum The generated number need to be guessed right to win
     */
    public void guesses(int inputN, int randNum) {
        // Check if guess matches the random number
        if (inputN == randNum) {
            // Set win condition to true
            isLucky = true;
            // Display winning message
            System.out.println("You are lucky!");
        } else {
            // Provide feedback based on guess
            if (inputN > randNum) {
                // Inform if guess is too high
                System.out.println("Your number is higher than the lucky number, try again");
            } else {
                // Inform if guess is too low
                System.out.println("Your number is lower than the lucky number, try again");
            }
        }
    }

    /**
     * Getter for isLucky flag
     *
     * @return boolean indicating if player has won
     */
    public boolean isLucky() {
        // Return the value of isLucky flag
        return isLucky;
    }
}
